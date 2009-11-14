package com.next.gwtext.client.generic;

import com.google.gwt.user.client.ui.DockPanel;
import com.gwtext.client.core.UrlParam;

public abstract class SearchCriteriaPanel extends DockPanel {

	protected SearchPanel searchPanel;
	public SearchCriteriaPanel(SearchPanel panel)
	{
		searchPanel = panel;
	}
	public abstract UrlParam[] getSearchParams();
}
