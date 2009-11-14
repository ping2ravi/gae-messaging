package com.next.gwtext.client.generic.beans;

import com.next.gwtext.client.generic.inter.SearchResultInt;


public class ApplicationRole extends BaseObject implements SearchResultInt{
	private static final long serialVersionUID = 5512348006547721619L;
	private String name;
	private String description;
	private ApplicationFunction[] permissions;
	
	public ApplicationFunction[] getPermissions() {
		return permissions;
	}
	public void setPermissions(ApplicationFunction[] permissions) {
		this.permissions = permissions;
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

}
