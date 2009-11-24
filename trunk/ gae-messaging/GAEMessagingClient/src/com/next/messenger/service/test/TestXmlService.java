package com.next.messenger.service.test;

import com.next.message.client.entity.ApplicationMessage;
import com.next.message.client.entity.MessageConfiguration;
import com.next.message.client.entity.MessageConfigurations;
import com.next.message.client.exception.MessageException;
import com.next.message.client.service.XmlService;
import com.next.messenger.service.GaeXmlService;


public class TestXmlService {

	public static void main(String[] args) throws MessageException
	{
		test();
		test2();
	}
	public static void test() throws MessageException
	{
		TestClass tc = new TestClass();
		
		tc.setMessageType("MYRYPE");
		
		XmlService xmlService = new GaeXmlService();
		String fullMessage = xmlService.convertObjectToXml(tc,"MessageConfigurations");
		
		ApplicationMessage tc1 = (ApplicationMessage)xmlService.convertXmlToObject(fullMessage, ApplicationMessage.class,"MessageConfigurations");
		System.out.println(tc1.getMessageType());
		System.out.println(tc1.getVersion());
	}
	public static void test2() throws MessageException
	{
		MessageConfigurations tc = new MessageConfigurations();
		
		MessageConfiguration[] confs = new MessageConfiguration[2];
		confs[0] = new MessageConfiguration();
		confs[0].setClassName("My.class.name");
		confs[0].setMessageType("messageType");
		confs[0].setVersion("1.0");

		confs[1] = new MessageConfiguration();
		confs[1].setClassName("2My.class.name");
		confs[1].setMessageType("1messageType");
		confs[1].setVersion("2.0");

		tc.setMessageConfiguration(confs);
		XmlService xmlService = new GaeXmlService();
		String fullMessage = xmlService.convertObjectToXml(tc,"MessageConfigurations");
		
		ApplicationMessage tc1 = (ApplicationMessage)xmlService.convertXmlToObject(fullMessage, ApplicationMessage.class,"MessageConfigurations");
		System.out.println(tc1.getMessageType());
		System.out.println(tc1.getVersion());
	}
}
