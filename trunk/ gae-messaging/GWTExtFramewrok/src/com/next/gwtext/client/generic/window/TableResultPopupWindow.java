package com.next.gwtext.client.generic.window;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.next.gwtext.client.generic.SearchPanel;
import com.next.gwtext.client.generic.listener.PopupWindowSaveButtonListener;

public abstract class TableResultPopupWindow extends PopupWindow {

	private boolean somethingSaved = false;
	private SearchPanel searchPanel;
	protected Button save = new Button("Save");
	protected Button cancel = new Button("Cancel");
	protected Button saveAndClose = new Button("Save & Close");
	private HTML errorText = new HTML();
	private Grid mainPanel;
	private DockPanel centerPanel;
	private Grid buttonPanel = new Grid(1,3);
	public TableResultPopupWindow(SearchPanel panel)
	{
		setSearchPanel(panel);
		//this.setClosable(true);
		this.ensureDebugId("cwDialogBox");
		//this.setPlain(false);
		
		buttonPanel.setWidget(0, 0, save);
		buttonPanel.setWidget(0, 1, saveAndClose);
		buttonPanel.setWidget(0, 2, cancel);

		errorText.setStyleName("error-text");
		mainPanel = new Grid(4, 1);
		mainPanel.setWidget(0,0, errorText);
		
		centerPanel = new DockPanel();
		mainPanel.setWidget(1,0, centerPanel);
		
		mainPanel.setWidget(2,0, buttonPanel);

		//Do not use this.add as we are overiding it in this class but we need the super one here
		super.add(mainPanel);
		cancel.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				onCancel();
				TableResultPopupWindow.this.close();
			}
		});
		save.addClickHandler(new PopupWindowSaveButtonListener(this));
		saveAndClose.addClickHandler(new PopupWindowSaveButtonListener(this,true));
	}

	public void add(Widget wid)
	{
		centerPanel.add(wid, DockPanel.NORTH);
	}
	public void addHistoryPanel(Widget wid)
	{
		mainPanel.clearCell(3, 0);
		mainPanel.setWidget(3, 0,wid);
	}
	public void clear()
	{
		centerPanel.clear();
	}
	public void onDestroy()
	{
		if(isSomethingSaved())
			getSearchPanel().getResultPanel().refreshSearchResult();
	}

	public boolean isSomethingSaved() {
		return somethingSaved;
	}

	public void setSomethingSaved(boolean somethingSaved) {
		this.somethingSaved = somethingSaved;
	}
	public abstract void onSave(boolean closeAfterSave);
	public void onCancel()
	{
		
	}
	public void setErrorMessage(String msg)
	{
		errorText.setHTML(msg);
	}

	public void setSearchPanel(SearchPanel searchPanel) {
		this.searchPanel = searchPanel;
	}

	public SearchPanel getSearchPanel() {
		return searchPanel;
	}
	
}
