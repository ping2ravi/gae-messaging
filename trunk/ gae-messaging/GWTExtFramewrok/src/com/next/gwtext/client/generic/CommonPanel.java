package com.next.gwtext.client.generic;

import java.util.Map;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtext.client.widgets.grid.event.GridCellListener;
import com.next.gwtext.client.generic.beans.FieldsBean;
import com.next.gwtext.client.generic.beans.SearchResultWrapper;
import com.next.gwtext.client.generic.inter.FindObjectEvent;
import com.next.gwtext.client.generic.inter.SaveObjectEvent;
import com.next.gwtext.client.generic.window.TableResultPopupWindow;

public abstract class CommonPanel extends DockPanel implements FindObjectEvent,SaveObjectEvent{

	private FieldsBean[] fields;
	private GridCellListener overideGridClickListener = null;
	private CommonSearchPanel commonSearchPanel = null;
	String objectName;
	String labelName;
	
	/**
   * Flag to determine if the new button should be displayed in the search panel.
   */
  private boolean displayNewButton = true;
  
  /**
   * NUmber of search criteria per line in the search component.
   */
  private int searchCriteriaPerLine = 2;
	
  public CommonPanel(String objectName)
	{
		this.objectName = objectName;
		this.labelName = objectName;
	}
  
  public CommonPanel(String objectName, String labelName)
	{
		this.objectName = objectName;
		this.labelName = labelName;
	}
  
	public Widget createPanel()
	{
		commonSearchPanel = new CommonSearchPanel(this);
		this.add(new HTML("<h2>"+labelName+"</h2>"), DockPanel.NORTH);
		this.add(getCommonSearchPanel(),DockPanel.SOUTH);
		return this;
	}
	public abstract Object[][] convertToObjectArray(Object object);
	public abstract Object getObjectFromParams(Map<String, String> params);
	public abstract boolean validateObject(Object obj,TableResultPopupWindow parentPanel);
	public abstract void getHistory(Object object,CommonSavePanel panel);
	public abstract Panel getHistoryPanel(SearchResultWrapper object);

	public Map<String, String> getParamMapFromObject(Object object)
	{
		return null;
	}
	public FieldsBean[] getFields() {
		return fields;
	}
	public void setFields(FieldsBean[] fields) {
		this.fields = fields;
	}
	public CommonSearchPanel getCommonSearchPanel() {
		return commonSearchPanel;
	}
	public void setOverideGridClickListener(GridCellListener overideGridClickListener) {
		this.overideGridClickListener = overideGridClickListener;
	}
	public GridCellListener getOverideGridClickListener() {
		return overideGridClickListener;
	}

	/**
	 * Set the display new button flag on the search panel.
   * @param displayNewButton the displayNewButton to set
   */
  public void setDisplayNewButton(boolean displayNewButton) {
    this.displayNewButton = displayNewButton;
  }
  
  /**
   * @return the displayNewButton
   */
  public boolean isDisplayNewButton() {
    return displayNewButton;
  }
  /**
   * @return the searchCriteriaPerLine
   */
  public int getSearchCriteriaPerLine() {
    return searchCriteriaPerLine;
  }
  /**
   * @param searchCriteriaPerLine the searchCriteriaPerLine to set
   */
  public void setSearchCriteriaPerLine(int searchCriteriaPerLine) {
    this.searchCriteriaPerLine = searchCriteriaPerLine;
  }
}
