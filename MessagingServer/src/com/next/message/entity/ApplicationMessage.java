package com.next.message.entity;

import java.io.Serializable;

public abstract class ApplicationMessage implements Serializable{

	private long senderApplicationId;
	private String messageType;
	private String uniqueId;
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
	public abstract String getVersion();
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getUniqueId() {
		return uniqueId;
	}
}
