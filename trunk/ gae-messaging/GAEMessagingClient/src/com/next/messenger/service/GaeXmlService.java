package com.next.messenger.service;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.apache.commons.betwixt.io.BeanReader;
import org.apache.commons.betwixt.io.BeanWriter;
import org.xml.sax.SAXException;

import com.next.message.client.entity.ApplicationMessage;
import com.next.message.client.exception.MessageException;
import com.next.message.client.service.XmlService;


public class GaeXmlService implements XmlService {

	
	@Override
	public String convertObjectToXml(Object messageObject,String topLevelTag) throws MessageException{
		String outputXml = null;
        try {
		// Start by preparing the writer
        // We'll write to a string 
        StringWriter outputWriter = new StringWriter(); 
        
        // Betwixt just writes out the bean as a fragment
        // So if we want well-formed xml, we need to add the prolog
        outputWriter.write("<?xml version='1.0' ?>");
        
        // Create a BeanWriter which writes to our prepared stream
        BeanWriter beanWriter = new BeanWriter(outputWriter);
        
        // Configure betwixt
        // For more details see java docs or later in the main documentation
        beanWriter.getXMLIntrospector().getConfiguration().setAttributesForPrimitives(false);
        beanWriter.getBindingConfiguration().setMapIDs(false);
        beanWriter.enablePrettyPrint();

        // If the base element is not passed in, Betwixt will guess 
        // But let's write example bean as base element 'person'
			beanWriter.write(topLevelTag, messageObject);
        
        // Write to System.out
        // (We could have used the empty constructor for BeanWriter 
        // but this way is more instructive)
			outputXml = outputWriter.toString();
        System.out.println(outputXml);
        
        // Betwixt writes fragments not documents so does not automatically close 
        // writers or streams.
        // This example will do no more writing so close the writer now.
        outputWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new MessageException(e);
		} catch (SAXException e) {
			e.printStackTrace();
			throw new MessageException(e);
		} catch (IntrospectionException e) {
			e.printStackTrace();
			throw new MessageException(e);
		}
		
		return outputXml;
	}

	@Override
	public Object convertXmlToObject(String applicationMessage,Class objectClass,String topLevelTag) throws MessageException{
		ApplicationMessage returnObject = null;
		// First construct the xml which will be read in
        // For this example, read in from a hard coded string
		try{
        StringReader xmlReader = new StringReader(applicationMessage);
        
        // Now convert this to a bean using betwixt
        // Create BeanReader
        BeanReader beanReader  = new BeanReader();
        
        // Configure the reader
        // If you're round-tripping, make sure that the configurations are compatible!
        beanReader.getXMLIntrospector().getConfiguration().setAttributesForPrimitives(false);
        beanReader.getBindingConfiguration().setMapIDs(false);
        
        // Register beans so that betwixt knows what the xml is to be converted to
        // Since the element mapped to a PersonBean isn't called the same 
        // as Betwixt would have guessed, need to register the path as well
        beanReader.registerBeanClass(topLevelTag, objectClass);
        
        // Now we parse the xml
        returnObject = (ApplicationMessage) beanReader.parse(xmlReader);
        
		} catch (IOException e) {
			e.printStackTrace();
			throw new MessageException(e);
		} catch (SAXException e) {
			e.printStackTrace();
			throw new MessageException(e);
		} catch (IntrospectionException e) {
			e.printStackTrace();
			throw new MessageException(e);
		}
		return returnObject;
	}

}
