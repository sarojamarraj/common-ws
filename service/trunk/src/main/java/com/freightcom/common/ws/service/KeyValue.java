package com.freightcom.common.ws.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class KeyValue {
	private String key;
	private Object value;
	
	public KeyValue() {
	}
	
	public KeyValue(String key, Object value) {
		super();
		this.key = key;
		this.value = value;
	}


	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}