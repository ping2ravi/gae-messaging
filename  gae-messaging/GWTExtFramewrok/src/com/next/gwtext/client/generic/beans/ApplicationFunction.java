package com.next.gwtext.client.generic.beans;

import com.next.gwtext.client.generic.inter.SearchResultInt;




public class ApplicationFunction extends BaseObject implements SearchResultInt{
	private static final long serialVersionUID = 9198243822058813106L;
	private String name;
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String functionName) {
		this.name = functionName;
	}

}
