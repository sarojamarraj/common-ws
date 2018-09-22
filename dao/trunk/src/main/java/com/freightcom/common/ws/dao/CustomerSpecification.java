package com.freightcom.common.ws.dao;

import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.jpa.domain.Specification;

import com.freightcom.common.model.customer.Customer;




public class CustomerSpecification extends BaseSpecification implements Specification<Customer>
{
	private final transient Log log = LogFactory.getLog(getClass());

    private final Map<String, Object> criteria;

    public CustomerSpecification(final Map<String, Object> criteria)
    {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder builder)
    {
        Predicate predicate = builder.conjunction();
        for (String key : criteria.keySet()) {
            log.debug("KEY IS " + key);

            switch (key.toLowerCase()) {
            case "businessname":
            
                predicate.getExpressions()
                        .add(builder.like(root.get("businessName"), "-?\\\\d+" + criteria.get(key) + "-?\\\\d+"));
                break;

            case "id":
            case "customerid":
                predicate.getExpressions()
                        .add(builder.equal(root.get("id"), criteria.get(key)));
                break;

            case "agentid":
            case "salesAgentId":
                predicate.getExpressions()
                        .add(builder.equal(root.get("salesAgent")
                                .get("id"), criteria.get(key)));
                break;

            case "active":
                if ("all".equalsIgnoreCase(criteria.get(key)
                        .toString())) {
                    // no restriction
                } else if (yesValue(criteria.get(key))) {
                    predicate.getExpressions()
                            .add(builder.isNotNull(root.get("activatedAt")));
                    predicate.getExpressions()
                            .add(builder.isNull(root.get("suspendedAt")));
                } else {
                    Predicate disjunctionActive = builder.disjunction();

                    disjunctionActive.getExpressions()
                        .add(builder.isNull(root.get("activatedAt")));
                    disjunctionActive.getExpressions()
                        .add(builder.isNotNull(root.get("suspendedAt")));

                    predicate.getExpressions()
                        .add(disjunctionActive);
                }
                break;

            case "contact":
                predicate.getExpressions()
                        .add(builder.like(root.get("contact"), "%" + criteria.get(key) + "%"));
                break;

            case "phone":
                predicate.getExpressions()
                        .add(builder.like(root.get("phone"), "%" + criteria.get(key) + "%"));
                break;

            case "email":
                predicate.getExpressions()
                        .add(builder.like(root.get("email"), "%" + criteria.get(key) + "%"));
                break;

            case "address":
                Predicate disjunction = builder.disjunction();
                disjunction.getExpressions()
                        .add(builder.like(root.get("address"), "%" + criteria.get(key) + "%"));
                disjunction.getExpressions()
                        .add(builder.like(root.get("city"), "%" + criteria.get(key) + "%"));

                predicate.getExpressions()
                        .add(disjunction);
                break;



            }
        }

        return predicate;
    }

}
