package com.next.message.entity;

import java.io.Serializable;

public class ApplicationMessage implements Serializable{

	private long senderApplicationId;
	private String messageType;
	private String uniqueId;
	private String version="UNDEFINED";
	public long getSenderApplicationId() {
		return senderApplicationId;
	}
	public void setSenderApplicationId(long senderApplicationId) {
		this.senderApplicationId = senderApplicationId;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getVersion()
	{
		return version;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}
