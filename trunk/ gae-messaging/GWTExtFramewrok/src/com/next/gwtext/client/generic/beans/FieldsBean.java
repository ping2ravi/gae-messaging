package com.next.gwtext.client.generic.beans;

import java.io.Serializable;

import com.next.gwtext.client.generic.inter.GenericChangeHandler;
import com.next.gwtext.client.generic.inter.ListBoxPopulator;

public class FieldsBean implements Serializable {

	private String type;
	private String labelText;
	private String fieldName;
	private boolean enabled = true;
	private ListBoxPopulator listBoxPopulator;
	private GenericChangeHandler changeHandler;
	
	/**
	 * Flag to determine if this field is displayed in the search criteria
	 */
	private boolean hideInSearchCriteria = false;
	
  /**
   * Flag to determine if this field is displayed in the search results
   */
  private boolean hideInSearchResults = false;
	
	public GenericChangeHandler getChangeHandler() {
		return changeHandler;
	}
	public void setChangeHandler(GenericChangeHandler changeHandler) {
		this.changeHandler = changeHandler;
	}
	/**
   * @param hideInSearchCriteria the hideInSearchCriteria to set
   */
  public void setHideInSearchCriteria(boolean hideInSearchCriteria) {
    this.hideInSearchCriteria = hideInSearchCriteria;
  }
  /**
   * @param hideInSearchResults the hideInSearchResults to set
   */
  public void setHideInSearchResults(boolean hideInSearchResults) {
    this.hideInSearchResults = hideInSearchResults;
  }
  /**
   * @return the hideInSearchCriteria
   */
  public boolean isHideInSearchCriteria() {
    return hideInSearchCriteria;
  }
  /**
   * @return the hideInSearchResults
   */
  public boolean isHideInSearchResults() {
    return hideInSearchResults;
  }
  public FieldsBean(String type,String labelText,String fieldName,boolean enabled,ListBoxPopulator listBoxPopulator)
	{
		this(type, labelText, fieldName, enabled, listBoxPopulator, null);
	}
	public FieldsBean(String type,String labelText,String fieldName,boolean enabled,ListBoxPopulator listBoxPopulator,GenericChangeHandler changeHandler)
	{
		this.type = type;
		this.labelText = labelText;
		this.fieldName = fieldName;
		this.enabled = enabled;
		this.listBoxPopulator = listBoxPopulator;
		this.changeHandler = changeHandler;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLabelText() {
		return labelText;
	}

	public void setLabelText(String labelText) {
		this.labelText = labelText;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public ListBoxPopulator getListBoxPopulator() {
		return listBoxPopulator;
	}

	public void setListBoxPopulator(ListBoxPopulator listBoxPopulator) {
		this.listBoxPopulator = listBoxPopulator;
	}
	
	
}
