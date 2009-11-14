package com.next.gwtext.client.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.gwtext.client.core.UrlParam;
import com.gwtext.client.widgets.MessageBox;
import com.next.gwtext.client.generic.beans.FieldsBean;
import com.next.gwtext.client.session.UserInfo;

class CommonSearchCriteriaPanel  extends SearchCriteriaPanel  implements ClickHandler{

	private Button search = new Button("Search");
	private Button clear = new Button("Clear");
	private Button newButton = new Button("New");
	private HTML errorText = new HTML();
	private Map<String, Widget> searchElements;
	
	/**
   * Flag to determine if the new button should be displayed in the search panel.
   */
  private boolean displayNewButton = true;
  
  /**
   * Number of widgets per line.
   */
  private int widgetsPerLine = 4;

	public CommonSearchCriteriaPanel(CommonSearchPanel panel)
	{
		super(panel);
		searchElements = new HashMap<String, Widget>();
	}
	public UrlParam[] getSearchParams() {
		List<UrlParam> allParamList = new ArrayList<UrlParam>();
		UrlParam[] allParam = null;
		CommonSearchPanel panel = (CommonSearchPanel)super.searchPanel;
		FieldsBean[] fields = panel.getFields();
		if(fields == null || fields.length <= 0)
			return allParam;
		UrlParam oneParam;
		String paramName;
		String paramValue;
		for(int i=0;i<fields.length;i++)
		{
		  if (!fields[i].isHideInSearchCriteria()) {
  			paramName = fields[i].getFieldName();
  			paramValue = FieldTypes.getWidgetValue(fields[i].getType(), searchElements.get(paramName));
  			oneParam = new UrlParam(paramName,paramValue);
  			allParamList.add(oneParam);
		  }
		}
		allParam = allParamList.toArray(new UrlParam[0]);
		return allParam;
	}

	public SearchCriteriaPanel createSearchCriteriaPanel()
	{
		CommonSearchPanel panel = (CommonSearchPanel)super.searchPanel;
		FieldsBean[] fields = panel.getFields();
		if(fields == null || fields.length <= 0)
		{
			MessageBox.alert("No Fields found");
			return null;
		}
		int rowCount = fields.length / (widgetsPerLine / 2) + 1;

		DockPanel allButtonPanel = new DockPanel();
		Grid buttonPanel = new Grid(1,5);
		search.addClickHandler(this);
		buttonPanel.setWidget(0, 0, search);
		
		clear.addClickHandler(this);
		
		buttonPanel.setWidget(0, 1, clear);
		
		// Only display if requested
		if (displayNewButton) {
		  newButton.addClickHandler(this);
		  buttonPanel.setWidget(0, 4, newButton);
		}

		allButtonPanel.add(buttonPanel,DockPanel.WEST);
		Grid allControls = new Grid(rowCount, widgetsPerLine);
		int row = -1;
		int col = 0;
		Widget widget;
		for(int i=0;i<fields.length;i++)
		{
		  // Do we disply the field in the search criteria.
		  if (fields[i].isHideInSearchCriteria()) {
		    continue;
		  }
			if (col % this.widgetsPerLine == 0)
			{
				col = 0;
				row++;
			}
			//Window.alert("Row " + row+",Col="+col);
			widget = new Label(fields[i].getLabelText());
			allControls.setWidget(row, col, widget);
			col++;
			widget = FieldTypes.getWidget(fields[i].getType(),fields[i].isEnabled());
			
			searchElements.put(fields[i].getFieldName(), widget);
			if(FieldTypes.LIST_BOX.equals(fields[i].getType()))
			{
				if(fields[i].getListBoxPopulator() != null)
					fields[i].getListBoxPopulator().populateListBox((ListBox)widget);
				else
					MessageBox.alert("No List box populator is defined");
			}
			allControls.setWidget(row, col, widget);
			
			//TO make All Fields Enabled in search criteria Panel
			if(FieldTypes.TEXT_BOX.equals(fields[i].getType()))
				((TextBox)widget).setEnabled(true);
			if(FieldTypes.LIST_BOX.equals(fields[i].getType()))
				((ListBox)widget).setEnabled(true);
      if(FieldTypes.CHECK_BOX.equals(fields[i].getType()))
        ((CheckBox)widget).setEnabled(true);
				
			
			col++;
		}
		
		Grid searchPannel = new Grid(3, 1);
		searchPannel.setWidget(0,0, errorText);
		searchPannel.setWidget(1, 0, allControls);
		searchPannel.setWidget(2, 0, allButtonPanel);
		this.add(searchPannel,DockPanel.NORTH);
		return this;
	}
	protected void clearSearchCriteriaPanel()
	{
		CommonSearchPanel panel = (CommonSearchPanel)super.searchPanel;
		FieldsBean[] fields = panel.getFields();
		if(fields == null || fields.length <= 0)
			return;
		Widget widget;
		for(int i=0;i<fields.length;i++)
		{
		  if (!fields[i].isHideInSearchCriteria()) {
		    FieldTypes.clearWidget(fields[i].getType(), searchElements.get(fields[i].getFieldName()));
		  }
		}

	}
	protected void createNewEntity(Widget sender)
	{
		CommonSearchPanel panel = (CommonSearchPanel)super.searchPanel;
		CommonSavePanel savePanel = new CommonSavePanel(panel, panel.getFields());
		savePanel.createSavePanel();
		savePanel.setModal(true);
		//savePanel.center(); This is a bug, if its uncommented it will not show the panel.
		savePanel.setTitle("Create " + ((CommonSearchPanel)this.searchPanel).getCommonPanel().labelName);
		savePanel.show();
		savePanel.setPopupPosition(sender.getAbsoluteLeft()+sender.getOffsetWidth(),sender.getAbsoluteTop()+sender.getOffsetHeight());
		
	}

	/**
   * @param displayNewButton the displayNewButton to set
   */
  public void setDisplayNewButton(boolean displayNewButton) {
    this.displayNewButton = displayNewButton;
  }
  /**
   * @param searchCriteriaPerLine the widgetsPerLine to set
   */
  public void setSearchCriteriaPerLine(int searchCriteriaPerLine) {
    this.widgetsPerLine = searchCriteriaPerLine * 2;
  }
@Override
public void onClick(ClickEvent event) {
	// TODO Auto-generated method stub
	Object sender =  event.getSource();
	if(sender.equals(this.clear))
	{
		this.clearSearchCriteriaPanel();
	}
	if(sender.equals(this.search))
	{
		String permissionObjectName = ((CommonSearchPanel)super.searchPanel).getCommonPanel().objectName;
		if(UserInfo.getInstance().isUserAllowedForOperation("find" + permissionObjectName +"s"))
		{
			CommonSearchPanel panel = (CommonSearchPanel)super.searchPanel;
			panel.getResultPanel().setSeachParams(getSearchParams());
			panel.getResultPanel().refreshSearchResult();
			panel.getResultPanel().setVisible(true);
			/*UrlParam[] urlParams = getSearchParams();
			if(urlParams == null || urlParams.length <= 0)
				Window.alert("No Param Entered");
			String str = "Params are";
			for(int i =0;i<urlParams.length;i++)
			{
				str = str + "  [" + urlParams[i].getName()+":"+urlParams[i].getValue()+"]";
			}
			Window.alert(str);*/
		}
		else
		{
			MessageBox.alert("You are not authorized to do this operation");
		}
	}
	if(sender.equals(newButton))
	{
		String permissionObjectName = ((CommonSearchPanel)super.searchPanel).getCommonPanel().objectName;
		if(UserInfo.getInstance().isUserAllowedForOperation("create" + permissionObjectName))
		{
			createNewEntity((Widget)sender);
		}
		else
		{
			MessageBox.alert("You are not authorized to do create new entries");
		}

	}
	
}
}
