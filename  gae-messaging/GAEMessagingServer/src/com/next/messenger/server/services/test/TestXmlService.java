package com.next.messenger.server.services.test;

import com.next.message.entity.ApplicationMessage;
import com.next.message.entity.ApplicationMessageAbstract;
import com.next.message.exception.MessageException;
import com.next.message.xmlservice.XmlService;
import com.next.messenger.server.entity.TestClass;
import com.next.messenger.server.services.GaeXmlService;

public class TestXmlService {

	public static void main(String[] args) throws MessageException
	{
		test();
	}
	public static void test() throws MessageException
	{
		TestClass tc = new TestClass();
		tc.setAge("23");
		
		tc.setMessageType("MYRYPE");
		tc.setName("Ravi Sharma");
		tc.setOccuparion("hellp");
		
		XmlService xmlService = new GaeXmlService();
		String fullMessage = xmlService.convertObjectToXml(tc);
		
		ApplicationMessage tc1 = (ApplicationMessage)xmlService.convertXmlToObject(fullMessage, ApplicationMessageAbstract.class);
		System.out.println(tc1.getMessageType());
		System.out.println(tc1.getVersion());
		System.out.println(((TestClass)tc1).getAge());
	}
}
