package com.next.messenger.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

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
		/*
		Desktop desktop = new Desktop();
		Shortcut sc = new Shortcut();
		sc.setText("First");
		sc.setTitle("FirstTitle");
		desktop.addShortcut(sc);
		*/
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
