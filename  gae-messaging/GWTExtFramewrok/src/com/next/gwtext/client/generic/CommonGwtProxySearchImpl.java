package com.next.gwtext.client.generic;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.gwtext.client.core.UrlParam;
import com.gwtext.client.widgets.MessageBox;
import com.next.gwtext.client.generic.beans.BaseObject;
import com.next.gwtext.client.generic.beans.FieldsBean;
import com.next.gwtext.client.generic.beans.PageInfoBean;
import com.next.gwtext.client.generic.inter.FindObjectEvent;

public class CommonGwtProxySearchImpl extends GWTProxy {

	CommonSearchPanel parent;
	FindObjectEvent findEvent;
	public CommonGwtProxySearchImpl(CommonSearchPanel panel,FindObjectEvent findEvent)
	{
		this.parent = panel;
		this.findEvent = findEvent;
	}
	public void load(int start, int limit, String sort, String dir,
			final JavaScriptObject o, UrlParam[] baseParams) {
        start = start + 1;
        //Window.alert("Start = " + start+",Limit="+limit);
        //Window.alert("sort = " + sort + ", dir = " + dir);
        Map<String, String> data = new HashMap<String, String>();
        if(baseParams != null)
        {
	        for(int i=0;i<baseParams.length;i++)
	        {
	        	data.put(baseParams[i].getName(), baseParams[i].getValue());
	        }
        }
        Object bean = this.parent.getObjectFromParams(data);
        PageInfoBean pageInfoBean = new PageInfoBean();
        //Window.alert("Start = " + start/limit+1);
        pageInfoBean.setPageNum(start/limit+1);
        pageInfoBean.setPageSize(limit);
        pageInfoBean.setCalculateTotalPages(true);
        FieldsBean[] allFields = this.parent.getCommonPanel().getFields();
        if(allFields != null)
        {
        	int i=1;
        	for(FieldsBean oneBean:allFields)
        	{
        		if(oneBean.getFieldName().equals(sort))
        		{
        			((BaseObject)bean).setOrderByField(i);
        			break;
        		}
        		i++;
        			
        	}
        }
		if("ASC".equals(dir))
			((BaseObject)bean).setAscending(true);
		else
			((BaseObject)bean).setAscending(false);
        findEvent.findObject(bean,pageInfoBean,this,o);
/*        String displayCordinatorKey =  "CustomerSearch" + shortCode + bicCode;//Other  fields can be added too
        
        //following code will be implemented by Child class of CommonPanel
		ServiceFactory.getAppService().searchCustomer(bean,pageInfoBean,  
    		new DefaultAsyncCallback<SearchResultWrapper>(displayCordinatorKey) 
    		{
            	public void onServiceFailure(Throwable caught) 
            	{
            		Object[][] data = new Object[0][0];
        			loadResponse(o, false, 0, data);
            	}
            	public void onServiceSuccess(SearchResultWrapper result) 
            	{
            		SearchResultWrapper response = (SearchResultWrapper) result;
            		refreshResult(o, result.getData(), result.getTotalSize());
            	}
    		}
        );
*/        

	}
    public void loadResponse(JavaScriptObject o, boolean success, int totalRecords, Object[][] data) {
    	super.loadResponse(o, true, totalRecords, data);        
    }
    public void refreshResult(JavaScriptObject o, Object object, long size,boolean load)
    {
    	try{
		Object[][] data = parent.getResultPanel().convertToObjectArray(object);
		GWT.log("Total Size returned as " + size, null);
		loadResponse(o, load, (int)size, data);
    	}catch(Exception ex)
    	{
    		MessageBox.alert("Error : "+ ex.getMessage() +" : "+ ex  );
    	}
    	
    }

}
