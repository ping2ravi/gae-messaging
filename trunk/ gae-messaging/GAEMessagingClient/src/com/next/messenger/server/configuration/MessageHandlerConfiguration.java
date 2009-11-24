package com.next.messenger.server.configuration;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.next.message.client.configuration.MessageConfigurator;
import com.next.message.client.entity.MessageConfigurations;
import com.next.message.client.exception.MessageException;
import com.next.message.client.service.XmlService;
import com.next.messenger.service.GaeXmlService;

public class MessageHandlerConfiguration implements MessageConfigurator{

	@Override
	public MessageConfigurations readMessageConfiguration(String xmlFilePath) throws MessageException{
		String messageXml = readXmlFile(xmlFilePath);
		//Parse the XML and create MessageConfigurations Object
		XmlService xmlService = new GaeXmlService();
		return (MessageConfigurations)xmlService.convertXmlToObject(messageXml, MessageConfigurations.class,"MessageConfigurations");
	}
	
	
	protected String readXmlFile(String xmlFilePath)
	{
		StringBuffer sb = new StringBuffer("");
	      try{
	    // Open the file that is the first 
	    // command line parameter
	    FileInputStream fstream = new FileInputStream(xmlFilePath);
	    // Get the object of DataInputStream
	    DataInputStream in = new DataInputStream(fstream);
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));
	    String strLine;
	    //Read File Line By Line
	    
	    while ((strLine = br.readLine()) != null)   {
	      // Print the content on the console
	      System.out.println (strLine);
	      sb.append(strLine);
	    }
	    //Close the input stream
	    in.close();
	    }catch (Exception e){//Catch exception if any
	      System.err.println("Error: " + e.getMessage());
	    }
	    return sb.toString();
	}
	
}
