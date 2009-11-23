package com.next.messenger.server.entity;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import com.next.message.entity.MasterMessageInt;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class MasterMessage implements MasterMessageInt{
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    @Persistent
    private long publisherApplicationId;
    @Persistent
    private String messageType;
    @Persistent
    private String version;
    @Persistent
    private String uniqueId;
    @Persistent
    private Text message;
    @Persistent
    private Date messageServerReciveTime;
	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	public long getPublisherApplicationId() {
		return publisherApplicationId;
	}
	public void setPublisherApplicationId(long publisherApplicationId) {
		this.publisherApplicationId = publisherApplicationId;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public Text getMessage() {
		return message;
	}
	public void setMessage(Text message) {
		this.message = message;
	}
	public Date getMessageServerReciveTime() {
		return messageServerReciveTime;
	}
	public void setMessageServerReciveTime(Date messageServerReciveTime) {
		this.messageServerReciveTime = messageServerReciveTime;
	}

}
