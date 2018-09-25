package com.freightcom.common.ws.service.mail.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freightcom.common.dto.KeyValue;
import com.freightcom.common.dto.mail.AddToList;
import com.freightcom.common.dto.mail.MailRecipient;
import com.freightcom.common.dto.mail.SendMail;
import com.freightcom.common.ws.service.mail.MailService;
import com.github.alexanderwe.bananaj.connection.MailChimpConnection;
import com.github.alexanderwe.bananaj.model.list.MailChimpList;
import com.github.alexanderwe.bananaj.model.list.member.MemberStatus;
import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.MergeVar;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.Recipient;

@Transactional(readOnly=true)
@Service("mailService")
public class MailServiceImpl implements MailService {
	@Autowired
	private MandrillApi mandrillApi;
	
	@Autowired
	private MailChimpConnection mailChimpConnection;
	
	@Override
	public void sendSingleEmail(SendMail sendMail) throws MandrillApiError, IOException {
		MandrillMessage message = new MandrillMessage();
		
		message.setSubject(sendMail.getSubject());
		
		message.setFromEmail(sendMail.getFrom().getEmail());
		if(StringUtils.isNotEmpty(sendMail.getFrom().getName())) {
			message.setFromName(sendMail.getFrom().getName());
		}
		
		//Add template values if needed
		if(CollectionUtils.isNotEmpty(sendMail.getTemplateValues())) {
			List<MergeVar> globalMergeVars = new ArrayList<>();
			for (KeyValue keyValue : sendMail.getTemplateValues()) {
				MergeVar mergeVar = new MergeVar();
				mergeVar.setName(keyValue.getKey());
				mergeVar.setContent(keyValue.getValue());
				globalMergeVars.add(mergeVar);
			}
			message.setGlobalMergeVars(globalMergeVars);
		}
		
		//Add to recipients
		ArrayList<Recipient> recipients = new ArrayList<Recipient>();
		for (MailRecipient mailRecipient : sendMail.getTo()) {
			Recipient recipient = new Recipient();
			recipient.setEmail(mailRecipient.getEmail());
			if(StringUtils.isNotEmpty(mailRecipient.getName())) {
				message.setFromName(mailRecipient.getName());
			}
			recipients.add(recipient);
		}
		message.setTo(recipients);
		message.setPreserveRecipients(true);
		
		mandrillApi.messages().sendTemplate(sendMail.getTemplate(), null, message, false);
	}
	
	@Override
	public void addToList(AddToList addToList) throws Exception {
		MailChimpList mailChimpList = mailChimpConnection.getList(addToList.getListId());
		HashMap<String,Object> merge_fields_values = new HashMap<>();
		merge_fields_values.put("FNAME",addToList.getFirstname());
		merge_fields_values.put("LNAME", addToList.getLastname());
		mailChimpList.addMember(MemberStatus.SUBSCRIBED, addToList.getEmail(), merge_fields_values);
	}
}