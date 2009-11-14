package com.next.gwtext.client.generic.inter;

import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.next.gwtext.client.generic.CommonSavePanel;

public abstract class GenericChangeHandler implements ChangeHandler, ClickHandler {

	protected CommonSavePanel parentPanel;
	public void setParentPanel(CommonSavePanel parentPanel)
	{
		this.parentPanel = parentPanel;
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
	}
}
