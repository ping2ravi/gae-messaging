package com.next.messenger.server.entity;

import com.next.message.entity.ApplicationMessage;

public class TestClass extends ApplicationMessage{

	private String name;
	private String age;
	private String occuparion;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getOccuparion() {
		return occuparion;
	}
	public void setOccuparion(String occuparion) {
		this.occuparion = occuparion;
	}
	@Override
	public String getVersion() {
		return "1.0";
	}
}
