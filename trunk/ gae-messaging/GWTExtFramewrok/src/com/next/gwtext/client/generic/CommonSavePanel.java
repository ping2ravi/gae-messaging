package com.next.gwtext.client.generic;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DisclosureEvent;
import com.google.gwt.user.client.ui.DisclosureHandler;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.gwtext.client.widgets.MessageBox;
import com.next.gwtext.client.generic.beans.FieldsBean;
import com.next.gwtext.client.generic.beans.SearchResultWrapper;
import com.next.gwtext.client.generic.window.TableResultPopupWindow;

public class CommonSavePanel extends TableResultPopupWindow implements DisclosureHandler{

	private boolean historyLoaded = false;
	private HTML errorText = new HTML();
	private FieldsBean[] fields;
	private Map<String, Widget> elements;
	private DisclosurePanel historyPanel;
	private boolean closeAfterSave;
	public CommonSavePanel(SearchPanel panel,FieldsBean[] fields)
	{	super(panel);
		//super.setWidth(400);
		//super.setHeight(400);
		this.fields = fields;
		elements = new HashMap<String, Widget>();
		historyPanel = new DisclosurePanel("History");
		historyPanel.setVisible(false);
		historyPanel.addEventHandler(this);
		
		this.addHistoryPanel(historyPanel);
	}
	public void createSavePanel()
	{
		if(fields == null || fields.length <= 0)
			return ;

		int rowCount = fields.length;
		Grid allControls = new Grid(rowCount,4);
		int row = 0;
		int col = 0;
		Widget widget;
		for(int i=0;i<fields.length;i++)
		{
			widget = new Label(fields[i].getLabelText());
			allControls.setWidget(row, col, widget);
			col++;
			widget = FieldTypes.getWidget(fields[i].getType(),fields[i].isEnabled());
			if(FieldTypes.LIST_BOX.equals(fields[i].getType()))
			{
				if(fields[i].getListBoxPopulator() != null)
					fields[i].getListBoxPopulator().populateListBox((ListBox)widget);
				else
					MessageBox.alert("No List box populator is defined");
			}
			if(fields[i].getChangeHandler() != null)
			{
				fields[i].getChangeHandler().setParentPanel(this);
				if(FieldTypes.TEXT_BOX.equals(fields[i].getType()))
					((TextBox)widget).addChangeHandler(fields[i].getChangeHandler());
				
				if(FieldTypes.LIST_BOX.equals(fields[i].getType()))
					((ListBox)widget).addChangeHandler(fields[i].getChangeHandler());
				
				//if(FieldTypes.CHECK_BOX.equals(fields[i].getType()))
					//((CheckBox)widget).add((ClickHandler)fields[i].getChangeHandler());
			}
			elements.put(fields[i].getFieldName(), widget);
			allControls.setWidget(row, col, widget);
			col = 0;
			row++;
		}
		Grid searchPannel = new Grid(3, 1);
		searchPannel.setWidget(0,0, errorText);
		searchPannel.setWidget(1, 0, allControls);
		this.add(searchPannel);
	}
	public void populateData(Map<String,String> data)
	{
		historyLoaded = false;
		historyPanel.setOpen(false);
		historyPanel.setVisible(true);
		if(data == null)
			return;
		Widget widget;
		String value;
  		GWT.log("populateData Total Field are= " + fields.length +"and todat field in data = " + data.size(), null);
		for(int i=0;i<fields.length;i++)
		{
			widget = elements.get(fields[i].getFieldName());
			value = data.get(fields[i].getFieldName());
	  		GWT.log("populateData Name = " + fields[i].getLabelText() + ", Value=" + value, null);
			FieldTypes.setWidgetValue(fields[i].getType(),widget,value);
		}
	}
	public void onSave(boolean closeAfterSave) {
		
		this.closeAfterSave = closeAfterSave;
		CommonSearchPanel panel = (CommonSearchPanel)getSearchPanel();
		Map<String,String> data = new HashMap<String, String>();
		Widget widget;
		String value;
		for(int i=0;i<fields.length;i++)
		{
			
			widget = elements.get(fields[i].getFieldName());
			value = FieldTypes.getWidgetValue(fields[i].getType(),widget);
			data.put(fields[i].getFieldName(),value);
		}		
		Object obj = panel.getObjectFromParams(data);
		if(panel.validateObject(obj,this))
		{
			panel.onSave(obj,this,closeAfterSave);
		}
	}
	public void afterDataSaved(Object result) {
		CommonSearchPanel searchPanel = (CommonSearchPanel)this.getSearchPanel();
		CommonPanel commonPanel = searchPanel.getCommonPanel();
		Map<String,String> params = commonPanel.getParamMapFromObject(result);
		this.populateData(params);
		if(closeAfterSave)
			this.close();
	}
	public Widget getWidget(String fieldName)
	{
		return elements.get(fieldName);
	}
	
	/*
	 * GWT 1.6 support OpenHadnler, use following function and change implemts to OpenHandler
	 public void onOpen(OpenEvent<DisclosurePanel> arg0) {
		if(!historyLoaded)
		{
			CommonSearchPanel comSearchPanel = (CommonSearchPanel)super.searchPanel;
			Map<String,String> data = new HashMap<String, String>();
			Widget widget;
			String value;
			for(int i=0;i<fields.length;i++)
			{
				
				widget = elements.get(fields[i].getFieldName());
				value = FieldTypes.getWidgetValue(fields[i].getType(),widget);
				data.put(fields[i].getFieldName(),value);
			}		
			Object obj = comSearchPanel.getObjectFromParams(data);
			comSearchPanel.getHistory(obj,this);
		}
	}
	*/
	public void populateHistory(SearchResultWrapper object)
	{
		CommonSearchPanel comSearchPanel = (CommonSearchPanel)super.getSearchPanel();
		Panel panel =comSearchPanel.getHistoryPanel(object);
		historyPanel.clear();
		if(panel != null)
			historyPanel.add(panel);
		historyLoaded = true;
	}
	
	public void onClose(DisclosureEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void onOpen(DisclosureEvent arg0) {
		if(!historyLoaded)
		{
			CommonSearchPanel comSearchPanel = (CommonSearchPanel)super.getSearchPanel();
			Map<String,String> data = new HashMap<String, String>();
			Widget widget;
			String value;
			for(int i=0;i<fields.length;i++)
			{
				
				widget = elements.get(fields[i].getFieldName());
				value = FieldTypes.getWidgetValue(fields[i].getType(),widget);
				data.put(fields[i].getFieldName(),value);
			}		
			Object obj = comSearchPanel.getObjectFromParams(data);
			comSearchPanel.getHistory(obj,this);
		}
	}

	
}
