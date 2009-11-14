package com.next.gwtext.client.generic.window;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;

//public class PopupWindowDefault extends DecoratedPopupPanel implements ClickListener {
public class PopupWindowDefault extends Window implements ClickHandler {

	private Button okButton;
	private VerticalPanel vp;
	public PopupWindowDefault()
	{
		vp = new VerticalPanel();
		okButton = new Button("Ok");
		okButton.addClickHandler(this);
		VerticalPanel vpMain = new VerticalPanel();
		vpMain.add(vp);
		vpMain.add(okButton);

		//For window
		super.setTitle("Message Window");  
		super.setClosable(true);  
		super.setWidth(400);
		super.setHeight(400);
		super.setAutoScroll(true);
		//super.setWidth("100%");
		//super.setHeight("100%");
		//super.setAutoHeight(true);
		//super.setAutoWidth(true);
		
		super.setPlain(true);  
		super.setLayout(new BorderLayout());  
		super.setCloseAction(Window.HIDE); 
        BorderLayoutData centerData = new BorderLayoutData(RegionPosition.CENTER);  
        centerData.setMargins(3, 0, 3, 3);  
		super.add(vpMain,centerData);
	}
	public void setModal(boolean model)
	{
		super.setModal(model);
	}
	public void setContent(Widget w)
	{
		vp.add(w);
	}
	public void add(Widget w)
	{
		vp.add(w);
	}
	public void close()
	{
		super.hide();
		super.close();
	}
	public void setTitle(String title)
	{
		super.setTitle(title);  
		//super.setText(title);
	}
	public void setPopupPosition(int x, int y)
	{
		super.setPagePosition(x, y);
		//super.setPopupPosition(x, y);
	}
	public void setAutoHideEnabled(boolean autoHide)
	{
		//super.setAutoHideEnabled(true);
	}
	public void show() {
		super.show();
	}
	@Override
	public void onClick(ClickEvent event) {
		this.close();
	}	
}
