package com.next.gwtext.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.widgets.MessageBox;
import com.next.gwtext.client.generic.CommonGwtProxySearchImpl;
import com.next.gwtext.client.generic.CommonPanel;
import com.next.gwtext.client.generic.CommonSavePanel;
import com.next.gwtext.client.generic.FieldTypes;
import com.next.gwtext.client.generic.beans.FieldsBean;
import com.next.gwtext.client.generic.beans.PageInfoBean;
import com.next.gwtext.client.generic.beans.SearchResultWrapper;
import com.next.gwtext.client.generic.window.TableResultPopupWindow;

public class BrokerPanel extends CommonPanel{
	public static final String ID = "Id";
	public static final String SWAPSWIRE_VALUE = "SWAPSWIRE_VALUE";
	public static final String SWAPSWIRE_VALUE_DSCRIPTION = "SWAPSWIRE_VALUE_DSCRIPTION";
	public static final String CDS_VALUE = "CDS_VALUE";
	public static final String CDS_VALUE_DESCRIPTION = "CDS_VALUE_DESCRIPTION";
	
	public BrokerPanel()
	{
		  super("");
		  FieldsBean[] fields = new FieldsBean[5];
		  fields[0] = new FieldsBean(FieldTypes.TEXT_BOX,"Broker Swapswire Value",SWAPSWIRE_VALUE,true,null);
		  fields[1] = new FieldsBean(FieldTypes.TEXT_BOX,"Swaspwire Screen value",SWAPSWIRE_VALUE_DSCRIPTION,true,null);
		  fields[2] = new FieldsBean(FieldTypes.TEXT_BOX,"Broker CDS Value",CDS_VALUE,true,null);
		  fields[3] = new FieldsBean(FieldTypes.TEXT_BOX,"CDS Screen Value",CDS_VALUE_DESCRIPTION,true,null);
		  fields[4] = new FieldsBean(FieldTypes.TEXT_BOX,"Id",ID,false,null);
		  super.setFields(fields);
	}
	public Object[][] convertToObjectArray(Object object) {
		Object[] brokerBeans = (Object[])object;
		
		String[][] returnValue = new String[0][0];
		return (Object[][])returnValue;	
	}
	public Object getObjectFromParams(Map<String,String> params) {
		return new Object();
	}
	public void findObject(Object obj, PageInfoBean pageInfo,
			final CommonGwtProxySearchImpl gwtProxyImpl,final JavaScriptObject o) {
	}
	public void saveObject(Object obj,CommonSavePanel panel) {
		
		
	}
	public boolean validateObject(Object obj,TableResultPopupWindow panel)
	{
		
		return true;
	}	
	
	public Map<String, String> getParamMapFromObject(Object object)
	{
		Map<String, String> returnMap = new HashMap<String, String>();
		return returnMap;
	}
	public void getHistory(Object object, CommonSavePanel panel) {
	}
	
	public Panel getHistoryPanel(SearchResultWrapper object) {
		VerticalPanel vp = new VerticalPanel();
		FlexTable ft = new FlexTable();
		ft.setBorderWidth(1);
		ft.setHTML(0, 0, "<b>Swaspwire Value</b>");
		ft.setHTML(0, 1, "<b>Cds Value</b>");
		ft.setHTML(0, 2, "<b>User</b>");
		ft.setHTML(0, 3, "<b>Update Time</b>");
		Object[] brokerBeans = (Object[])object.getData();
		vp.add(ft);
		return vp;
	}

}
