package com.freightcom.common.ws.service.mail;

import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.github.alexanderwe.bananaj.connection.MailChimpConnection;
import com.github.alexanderwe.bananaj.model.list.MailChimpList;
import com.github.alexanderwe.bananaj.model.list.member.MemberStatus;

public class MailChimpTest {
	private MailChimpConnection mailChimpConnection;
	
	@Before
	public void init() {
		mailChimpConnection = new MailChimpConnection("3126310f78a94348e0f5d83357fb296b-us14");
	}
	
	@Test
	@Ignore
	public void getLists() {
		try {
			List<MailChimpList> allLists = mailChimpConnection.getLists();
			for (MailChimpList mailChimpList : allLists) {
				System.out.println(mailChimpList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void addMemberToList() {
		try {
			MailChimpList mailChimpList = mailChimpConnection.getList("e0820f1c2d");
			HashMap<String,Object> merge_fields_values = new HashMap<>();
			merge_fields_values.put("FNAME", "Ravi");
			merge_fields_values.put("LNAME", "Narine");
			mailChimpList.addMember(MemberStatus.SUBSCRIBED, "rnarine@freightcom.com", merge_fields_values);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}