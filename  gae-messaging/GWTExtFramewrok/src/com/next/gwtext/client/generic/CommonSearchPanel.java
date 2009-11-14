package com.next.gwtext.client.generic;

import java.util.Map;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Panel;
import com.next.gwtext.client.generic.beans.FieldsBean;
import com.next.gwtext.client.generic.beans.SearchResultWrapper;
import com.next.gwtext.client.generic.window.TableResultPopupWindow;

public class CommonSearchPanel extends SearchPanel{

	private CommonPanel commonPanel;
	
	public CommonPanel getCommonPanel() {
		return commonPanel;
	}
	public void setCommonPanel(CommonPanel commonPanel) {
		this.commonPanel = commonPanel;
	}
	private FieldsBean[] fields;
	public CommonSearchPanel(CommonPanel commonPanel)
	{
		this.fields = commonPanel.getFields();
		this.commonPanel = commonPanel;
		criteriaPanel = createSearchCriteriaPanel();
		resultPanel = createSearchResultPanel();
		this.add(criteriaPanel,DockPanel.NORTH);
		this.add(resultPanel,DockPanel.SOUTH);
		this.setWidth("100%");
	}
	public SearchCriteriaPanel createSearchCriteriaPanel() {
		CommonSearchCriteriaPanel panel = new CommonSearchCriteriaPanel(this);
		panel.setDisplayNewButton(commonPanel.isDisplayNewButton());
		panel.setSearchCriteriaPerLine(commonPanel.getSearchCriteriaPerLine());
		return panel.createSearchCriteriaPanel();
	}
	public Object[][] convertToObjectArray(Object object) {
		
		return commonPanel.convertToObjectArray(object);
	}
	public Object getObjectFromParams(Map<String, String> params)
	{
		return commonPanel.getObjectFromParams(params);
	}

	public boolean validateObject(Object obj,TableResultPopupWindow parentPanel)
	{
		return commonPanel.validateObject(obj,parentPanel);
	}
	public SearchResultPanel createSearchResultPanel() {
		// TODO Auto-generated method stub
		return new CommonResultPanel(new CommonGwtProxySearchImpl(this,commonPanel),this);
	}
	public FieldsBean[] getFields() {
		return fields;
	}
	/*
	public void onSave(Object obj,CommonSavePanel panel)
	{
		commonPanel.saveObject(obj,panel);
	}
	*/
	public void onSave(Object obj,CommonSavePanel panel,boolean closeAfterSave)
	{
		commonPanel.saveObject(obj,panel);
	}
	
	public void getHistory(Object obj,CommonSavePanel panel)
	{
		commonPanel.getHistory(obj,panel);
	}
	public Panel getHistoryPanel(SearchResultWrapper object)
	{
		return commonPanel.getHistoryPanel(object);
	}
}
