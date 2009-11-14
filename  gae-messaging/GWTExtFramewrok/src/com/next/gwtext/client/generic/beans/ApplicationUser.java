package com.next.gwtext.client.generic.beans;

import com.next.gwtext.client.generic.inter.SearchResultInt;


public class ApplicationUser extends BaseObject implements SearchResultInt{
	private static final long serialVersionUID = -7653003342455035754L;
	private String emailId;
    private String soeId;
	private ApplicationFunction[] functions;
	
	public ApplicationFunction[] getFunctions() {
		return functions;
	}
	public void setFunctions(ApplicationFunction[] permissions) {
		this.functions = permissions;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getSoeId() {
		return soeId;
	}
	public void setSoeId(String soeId) {
		this.soeId = soeId;
	}

}
