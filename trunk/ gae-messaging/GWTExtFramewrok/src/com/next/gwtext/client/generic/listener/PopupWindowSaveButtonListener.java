package com.next.gwtext.client.generic.listener;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.next.gwtext.client.generic.window.TableResultPopupWindow;

public class PopupWindowSaveButtonListener implements ClickHandler {

	private TableResultPopupWindow savePanel;
	private boolean closeAfterSave;
	public PopupWindowSaveButtonListener(TableResultPopupWindow panel){
		savePanel = panel;
	}
	public PopupWindowSaveButtonListener(TableResultPopupWindow panel,boolean closeAfterSave){
		savePanel = panel;
		this.closeAfterSave = closeAfterSave;
	}
	@Override
	public void onClick(ClickEvent event) {
		savePanel.onSave(closeAfterSave);
	}

}
