package com.next.gwtext.client.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.Reader;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.TabPanel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.event.GridCellListener;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;
import com.next.gwtext.client.generic.beans.FieldsBean;
import com.next.gwtext.client.session.UserInfo;

class CommonResultPanel extends SearchResultPanel {

	private GridCellListener overideGridClickListener = null;
	public CommonResultPanel(GWTProxy gwtProxy, CommonSearchPanel panel)
	{
		super(gwtProxy, panel);
		this.setVisible(false);
		this.overideGridClickListener = panel.getCommonPanel().getOverideGridClickListener();
		//super.getGrid().setWidth(400);
		try{
		super.getGrid().addGridCellListener(getGridClickListener());
		}catch(Exception ex)
		{
			MessageBox.alert("Error = " + ex);
		}
	}
	public Object[][] convertToObjectArray(Object object) {
		CommonSearchPanel commonSearchPanel = 	(CommonSearchPanel)super.searchPanel;
		return commonSearchPanel.convertToObjectArray(object) ;
	}

	protected ColumnModel createColModel() {
		ColumnConfig oneColumn = null ;
		CommonSearchPanel commonSearchPanel = 	(CommonSearchPanel)super.searchPanel;
		FieldsBean[] fields = commonSearchPanel.getFields();
		if(fields == null || fields.length <= 0)
			return null;
		List<ColumnConfig> allColumns = new ArrayList<ColumnConfig>();
		for(int i=0;i<fields.length;i++)
		{
			oneColumn = new ColumnConfig(fields[i].getLabelText(), fields[i].getFieldName());
			
			// Do we hide the column from the results.
			if (fields[i].isHideInSearchResults()) {
			  oneColumn.setHidden(true);
			}
			
			allColumns.add(oneColumn);
			
		}
        ColumnModel colModel = new ColumnModel(allColumns.toArray(new ColumnConfig[0]));
        for (int i = 0; i < colModel.getColumnConfigs().length; i++)
        {
            ((ColumnConfig) colModel.getColumnConfigs()[i]).setSortable(true);
            ((ColumnConfig) colModel.getColumnConfigs()[i]).setWidth(70);
        }
        return colModel;
	}

	protected Reader createReader() {
		StringFieldDef oneFieldDefinition = null ;
		CommonSearchPanel commonSearchPanel = 	(CommonSearchPanel)super.searchPanel;
		FieldsBean[] fields = commonSearchPanel.getFields();
		if(fields == null || fields.length <= 0)
			return null;
		List<StringFieldDef> allFieldDef = new ArrayList<StringFieldDef>();
		for(int i=0;i<fields.length;i++)
		{
			oneFieldDefinition = new StringFieldDef(fields[i].getFieldName());
			allFieldDef.add(oneFieldDefinition);
		}

		Reader reader = new ArrayReader(new RecordDef(allFieldDef.toArray(new FieldDef[0])));
		return reader;
	}
	private GridCellListener getGridClickListener()
	{
		String permissionObjectName = ((CommonSearchPanel)super.searchPanel).getCommonPanel().objectName;
		boolean allowed = UserInfo.getInstance().isUserAllowedForOperation("update"+permissionObjectName);
		GridCellListener listener;
		if(allowed)
		{
			if(overideGridClickListener == null)
			{
				listener = new GridCellListener()
		        {
	
					public void onCellClick(GridPanel grid, int rowIndex, int colIndex,
							EventObject e) {
					}
	
					public void onCellContextMenu(GridPanel grid, int rowIndex,
							int cellIndex, EventObject e) {
					}
	
					public void onCellDblClick(GridPanel grid, int rowIndex,
							int colIndex, EventObject e) {
						cellDblClickWhenAllowed(grid, rowIndex, colIndex, e);
					}
		        };
			}
			else
			{
				listener = overideGridClickListener;
			}
		}
		else
		{
			listener = new GridCellListener()
	        {

				public void onCellClick(GridPanel grid, int rowIndex, int colIndex,
						EventObject e) {
				}

				public void onCellContextMenu(GridPanel grid, int rowIndex,
						int cellIndex, EventObject e) {
				}

				public void onCellDblClick(GridPanel grid, int rowIndex,
						int colIndex, EventObject e) {
					cellDblClickWhenNotAllowed(grid, rowIndex, colIndex, e);
				}
	        };	

		}
		return listener;
				
	}
	protected void cellDblClickWhenAllowed(GridPanel grid, int rowIndex,
			int colIndex, EventObject e)
	{
		CommonSearchPanel panel = (CommonSearchPanel)searchPanel;
		FieldsBean[] fields = panel.getFields();
		if(fields == null || fields.length <= 0)
			return;
		String paramName;
		String paramValue;
		Map<String, String> data  = new HashMap<String, String>();
		for(int i=0;i<fields.length;i++)
		{
  		paramName = fields[i].getFieldName();
  		paramValue = CommonResultPanel.this.getStore().getAt(rowIndex).getAsString(fields[i].getFieldName());
  		data.put(paramName, paramValue);
		}
		CommonSavePanel savePanel = new CommonSavePanel(CommonResultPanel.this.searchPanel, fields);
		savePanel.createSavePanel();
		savePanel.populateData(data);
		savePanel.setModal(true);
		savePanel.setPopupPosition(e.getPageX(),e.getPageY());
		savePanel.setTitle("Update " + ((CommonSearchPanel)this.searchPanel).getCommonPanel().labelName);
		//savePanel.center();
		savePanel.show();
		//onModuleLoad();
		//savePanel.setVisible(true);
		
	}
	private void cellDblClickWhenNotAllowed(GridPanel grid, int rowIndex,
			int colIndex, EventObject e)
	{
		MessageBox.alert("You are not allowed to update the data");
	}
	public void setOverideGridClickListener(GridCellListener overideGridClickListener) {
		this.overideGridClickListener = overideGridClickListener;
	}
	public GridCellListener getOverideGridClickListener() {
		return overideGridClickListener;
	}
	 public void onModuleLoad() {  
		         Panel panel = new Panel();  
		         panel.setBorder(false);  
		         panel.setPaddings(15);  
		   
		         //center panel  
		         TabPanel tabPanel = new TabPanel();  
		         tabPanel.setActiveTab(0);  
		   
		         Panel tab1 = new Panel();  
		         tab1.setTitle("Bogus Tab");  
		         tab1.setHtml(getBogusMarkup());  
		         tab1.setAutoScroll(true);  
		   
		         Panel tab2 = new Panel();  
		         tab2.setTitle("Another Tab");  
		         tab2.setHtml(getBogusMarkup());  
		         tab2.setAutoScroll(true);  
		   
		         Panel tab3 = new Panel();  
		         tab3.setTitle("Closable Tab");  
		         tab3.setHtml(getBogusMarkup());  
		         tab3.setAutoScroll(true);  
		         tab3.setClosable(true);  
		   
		         tabPanel.add(tab1);  
		         tabPanel.add(tab2);  
		         tabPanel.add(tab3);  
		   
		         //west panel  
		         Panel navPanel = new Panel();  
		         navPanel.setTitle("Navigation");  
		         navPanel.setWidth(200);  
		         navPanel.setCollapsible(true);  
		   
		         BorderLayoutData centerData = new BorderLayoutData(RegionPosition.CENTER);  
		         centerData.setMargins(3, 0, 3, 3);  
		   
		         BorderLayoutData westData = new BorderLayoutData(RegionPosition.WEST);  
		         westData.setSplit(true);  
		         westData.setMargins(3, 3, 0, 3);  
		         westData.setCMargins(3, 3, 3, 3);  
		   
		         final com.gwtext.client.widgets.Window window = new com.gwtext.client.widgets.Window();  
		         window.setTitle("Layout Window");  
		         window.setClosable(true);  
		         window.setWidth(600);  
		         window.setHeight(350);  
		         window.setPlain(true);  
		         window.setLayout(new BorderLayout());  
		         window.add(tabPanel, centerData);  
		         window.add(navPanel, westData);  
		         window.setCloseAction(com.gwtext.client.widgets.Window.HIDE);  
		   
                 window.show();  
		   
		         //RootPanel.get().add(panel);  
		     }  
		   
		     private static String getBogusMarkup() {  
		         return "<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. " +  
		                 "Sed metus nibh, sodales a, porta at, vulputate eget, dui.  " +  
		                 "In pellentesque nisl non sem. Suspendisse nunc sem, pretium eget, " +  
		                 "cursus a, fringilla vel, urna.";  
		     }  
}
