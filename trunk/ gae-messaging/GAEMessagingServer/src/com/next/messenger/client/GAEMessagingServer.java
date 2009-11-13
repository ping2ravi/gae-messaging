package com.next.messenger.client;

import com.extjs.gxt.desktop.client.Desktop;
import com.extjs.gxt.desktop.client.Shortcut;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GAEMessagingServer implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		Desktop desktop = new Desktop();
		Shortcut sc = new Shortcut();
		sc.setText("First");
		sc.setTitle("FirstTitle");
		desktop.addShortcut(sc);
		
	/*	
		ContentPanel cp = new ContentPanel();
		 cp.setHeading("Folder Contents");
		 cp.setSize(250,140);
		 cp.setPosition(10, 10);
		 cp.setCollapsible(true);
		 cp.setFrame(true);
		 cp.setBodyStyle("backgroundColor: white;");
		 cp.getHeader().addTool(new ToolButton("x-tool-gear"));
		 cp.getHeader().addTool(new ToolButton("x-tool-close"));
		 cp.addText("Some bogus text");
		 cp.addButton(new com.extjs.gxt.ui.client.widget.button.Button("Ok"));
		 cp.setIconStyle("tree-folder-open");
		 RootPanel.get().add(cp);
		 cp.layout();
*/
		//RootPanel.get().add(desktop);
	}
}
