package com.next.messenger.server.entity;

import java.util.Set;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class MessageType {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    @Persistent
    private String name;
    @Persistent
    private String description;
    @Persistent
    private String type;
    @Persistent
    private long ownerApplicationId;
    @Persistent
    private long version;
    @Persistent
    private long status;
    @Persistent
    private Set<Long> subscribedApplications;
	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getOwnerApplicationId() {
		return ownerApplicationId;
	}
	public void setOwnerApplicationId(long ownerApplicationId) {
		this.ownerApplicationId = ownerApplicationId;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public long getStatus() {
		return status;
	}
	public void setStatus(long status) {
		this.status = status;
	}
	public Set<Long> getSubscribedApplications() {
		return subscribedApplications;
	}
	public void setSubscribedApplications(Set<Long> subscribedApplications) {
		this.subscribedApplications = subscribedApplications;
	}

}
