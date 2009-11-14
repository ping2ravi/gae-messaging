package com.next.gwtext.client.generic.beans;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class BaseObject implements Serializable,IsSerializable {

	private long id;
	private int ver;
	private int orderByField;
	private boolean ascending;

	public int getOrderByField() {
		return orderByField;
	}

	public void setOrderByField(int orderByField) {
		this.orderByField = orderByField;
	}

	public boolean isAscending() {
		return ascending;
	}

	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getVer() {
		return ver;
	}

	public void setVer(int ver) {
		this.ver = ver;
	}

	
}
