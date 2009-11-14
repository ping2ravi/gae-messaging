package com.next.gwtext.client.generic;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.gwtext.client.widgets.MessageBox;

public class FieldTypes {

	public static final String TEXT_BOX = "TEXT_BOX";
	public static final String LIST_BOX = "LIST_BOX";
	public static final String CHECK_BOX = "CHECK_BOX";
	
	
	public static Widget getWidget(String type,boolean enabled)
	{
		if(type == null )
			MessageBox.alert("Type can not be null");
		if(TEXT_BOX.equals(type))
		{
			TextBox returnWidget = new TextBox();
			returnWidget.setEnabled(enabled);
			return returnWidget;
		}
			
		if(LIST_BOX.equals(type))
		{
			ListBox returnWidget = new ListBox();
			returnWidget.setEnabled(enabled);
			return returnWidget;
		}
		if(CHECK_BOX.equals(type))
		{
			CheckBox returnWidget = new CheckBox();
			returnWidget.setEnabled(enabled);
			return returnWidget;
		}
		MessageBox.alert("Error : Type " + type+" is not supported");
		return null;
		
	}
	public static String getWidgetValue(String type,Widget widget)
	{
		if(type == null )
			MessageBox.alert("Type can not be null");
		if(TEXT_BOX.equals(type))
		{
			TextBox textBox = (TextBox)widget;
			return textBox.getText();
		}
		if(LIST_BOX.equals(type))
		{
			ListBox listBox = (ListBox)widget;
			return listBox.getValue(listBox.getSelectedIndex());
		}
		if(CHECK_BOX.equals(type))
		{
			CheckBox listBox = (CheckBox)widget;
			if(listBox.getValue())
				return "true";
			else
				return "false";
		}
		MessageBox.alert("Error : Type " + type+" is not supported");
		return null;
		
	}	
	public static void clearWidget(String type,Widget widget)
	{
		if(type == null )
			MessageBox.alert("Type can not be null");
		if(TEXT_BOX.equals(type))
		{
			TextBox textBox = (TextBox)widget;
			textBox.setText("");
			return;
		}
		if(LIST_BOX.equals(type))
		{
			ListBox listBox = (ListBox)widget;
			listBox.setSelectedIndex(0);
			return;
		}
		if(CHECK_BOX.equals(type))
		{
			CheckBox listBox = (CheckBox)widget;
			listBox.setValue(false);
			return;
		}
		MessageBox.alert("Error : Type " + type+" is not supported");
		
	}	
	public static void setWidgetValue(String type,Widget widget,String value)
	{
		if(type == null )
			MessageBox.alert("Type can not be null");
		if(TEXT_BOX.equals(type))
		{
			TextBox textBox = (TextBox)widget;
			textBox.setText(value);
			return;
		}
		if(LIST_BOX.equals(type))
		{
			ListBox listBox = (ListBox)widget;
			int total = listBox.getItemCount();
			int selected = 0;
			if(value != null)
				value = value.trim();
			for(int i=0;i<total;i++)
			{
				if(listBox.getValue(i).trim().equals(value))
				{
					selected = i;
				}
			}
			listBox.setSelectedIndex(selected);
			return;
		}
		if(CHECK_BOX.equals(type))
		{
			CheckBox textBox = (CheckBox)widget;
			if("true".equalsIgnoreCase(value) || "Y".equalsIgnoreCase(value) )
				textBox.setValue(true);
			else
				textBox.setValue(false);
			return;
		}
		MessageBox.alert("Error : Type " + type+" is not supported");
		
	}	
}
