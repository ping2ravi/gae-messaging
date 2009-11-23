package com.next.messenger.server.entity;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.next.message.entity.PublishMessageInt;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class PublishMessage implements PublishMessageInt{
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    @Persistent
    private long masterMessageId;
    @Persistent
    private String jid;
    @Persistent
    private String status;

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public void setJid(String jid) {
		this.jid = jid;
	}

	@Override
	public String getJid() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setMasterMessageId(long masterMessageId) {
		this.masterMessageId = masterMessageId;
	}

	public long getMasterMessageId() {
		return masterMessageId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
