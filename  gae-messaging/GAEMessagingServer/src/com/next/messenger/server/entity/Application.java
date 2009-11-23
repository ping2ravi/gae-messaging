package com.next.messenger.server.entity;

import java.util.Date;
import java.util.Set;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.next.message.entity.ApplicationInt;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Application implements ApplicationInt{
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    @Persistent
    private String applicatioName;
    @Persistent
    private String jid;
    @Persistent
    private String description;
    @Persistent
    private String adminEmailId;
    @Persistent
    private Set<Long> subscribedMessages;
    @Persistent
    private Set<Long> publishedMessages;
    @Persistent
    private long totalMessagesSent;
    @Persistent
    private Date lastMessageSent;
    @Persistent
    private long totalMessagesRecieved;
    @Persistent
    private Date lastMessageRecieved;
    @Persistent
    private Date creationDate;
    
	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	public String getApplicatioName() {
		return applicatioName;
	}
	public void setApplicatioName(String applicatioName) {
		this.applicatioName = applicatioName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAdminEmailId() {
		return adminEmailId;
	}
	public void setAdminEmailId(String adminEmailId) {
		this.adminEmailId = adminEmailId;
	}
	public Set<Long> getSubscribedMessages() {
		return subscribedMessages;
	}
	public void setSubscribedMessages(Set<Long> subscribedMessages) {
		this.subscribedMessages = subscribedMessages;
	}
	public Set<Long> getPublishedMessages() {
		return publishedMessages;
	}
	public void setPublishedMessages(Set<Long> publishedMessages) {
		this.publishedMessages = publishedMessages;
	}
	public long getTotalMessagesSent() {
		return totalMessagesSent;
	}
	public void setTotalMessagesSent(long totalMessagesSent) {
		this.totalMessagesSent = totalMessagesSent;
	}
	public Date getLastMessageSent() {
		return lastMessageSent;
	}
	public void setLastMessageSent(Date lastMessageSent) {
		this.lastMessageSent = lastMessageSent;
	}
	public long getTotalMessagesRecieved() {
		return totalMessagesRecieved;
	}
	public void setTotalMessagesRecieved(long totalMessagesRecieved) {
		this.totalMessagesRecieved = totalMessagesRecieved;
	}
	public Date getLastMessageRecieved() {
		return lastMessageRecieved;
	}
	public void setLastMessageRecieved(Date lastMessageRecieved) {
		this.lastMessageRecieved = lastMessageRecieved;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setJid(String jid) {
		this.jid = jid;
	}
	public String getJid() {
		return jid;
	}
    
}
