package com.next.gwtext.client.generic;

import com.google.gwt.user.client.ui.DockPanel;


public abstract class SearchPanel extends DockPanel {
	protected SearchCriteriaPanel criteriaPanel;
	protected SearchResultPanel resultPanel;
	public SearchPanel()
	{
		//criteriaPanel = createSearchCriteriaPanel();
		//resultPanel = createSearchResultPanel();
		//this.add(new Label("SearchPanel"),DockPanel.NORTH);
		//this.add(criteriaPanel, DockPanel.NORTH);
		//this.add(resultPanel, DockPanel.NORTH);
	}
	public abstract SearchCriteriaPanel createSearchCriteriaPanel();
	public abstract SearchResultPanel createSearchResultPanel();
	public SearchCriteriaPanel getCriteriaPanel() {
		return criteriaPanel;
	}
	public void setCriteriaPanel(SearchCriteriaPanel criteriaPanel) {
		this.criteriaPanel = criteriaPanel;
	}
	public SearchResultPanel getResultPanel() {
		return resultPanel;
	}
	public void setResultPanel(SearchResultPanel resultPanel) {
		this.resultPanel = resultPanel;
	}

}
