package com.next.message.xmlservice;

import com.next.message.entity.ApplicationMessage;
import com.next.message.exception.MessageException;


public interface XmlService {

	public String convertObjectToXml(ApplicationMessage messageObject) throws MessageException;
	public ApplicationMessage convertXmlToObject(String messageXml,Class objectClass) throws MessageException;
}
