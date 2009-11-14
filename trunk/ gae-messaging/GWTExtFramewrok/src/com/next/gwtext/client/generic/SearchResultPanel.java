package com.next.gwtext.client.generic;

import com.google.gwt.user.client.ui.DockPanel;
import com.gwtext.client.core.UrlParam;
import com.gwtext.client.data.Reader;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.PagingToolbar;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;

public abstract class SearchResultPanel extends DockPanel {

	private Store store;
	private GridPanel grid;
	private PagingToolbar pagingToolbar;
	protected SearchPanel searchPanel;
	private static final int ROW_SIZE = 21;
	public static int DEFAULT_PAGE_SIZE = 20;
	
	public SearchResultPanel() {
	}
	
	public SearchResultPanel(GWTProxy gwtProxy,SearchPanel searchCriteriaPanel)
	{
		init (gwtProxy, searchCriteriaPanel);
	}
	
	public void init(GWTProxy gwtProxy,SearchPanel searchCriteriaPanel) {
		this.searchPanel = searchCriteriaPanel;
		Reader reader = createReader();
        store = new Store(gwtProxy, reader, true);
		
        pagingToolbar = new PagingToolbar(store);
        pagingToolbar.setPageSize(DEFAULT_PAGE_SIZE);
        pagingToolbar.setDisplayInfo(true);
        pagingToolbar.setDisplayMsg("Displaying records {0} - {1} of {2}");
        pagingToolbar.setEmptyMsg("No records to display");
        
        ColumnModel columnModel = createColModel();
        grid = new GridPanel(store, columnModel);
        this.setGrid(grid);
	}
	/**
	 * @param store the store to set
	 */
	public void setStore(Store store) {
		this.store = store;
	}
	/**
	 * @return the store
	 */
	public Store getStore() {
		return store;
	}
	public void setPageSize(int size)
	{
		pagingToolbar.setPageSize(size);
	}
	
	public void setSize(int width,int height)
	{
        this.grid.setWidth(width);
        
        if (height > 0) {
        	this.grid.setHeight(height);
        }
	}
	
	
	public void setVisible(boolean visible) {
		
		// Ensure the grid width is correct...
		if (visible && this.getParent() != null) { 
			this.grid.setWidth(this.getParent().getOffsetWidth());
		}
		
		super.setVisible(visible);
	}

	public void setDisplayMessage(String message)
	{
        pagingToolbar.setDisplayMsg(message);
	}
	
	public void setDisplayInfo(boolean display)
	{
        pagingToolbar.setDisplayInfo(display);
	}
	
	public void setEmptyMessage(String message)
	{
        pagingToolbar.setEmptyMsg(message);
	}
	
	protected abstract ColumnModel createColModel();
	protected abstract Reader createReader();
	public abstract Object[][] convertToObjectArray(Object object);
	/*
	public void setSearchCriteriaPanel(SearchCriteriaPanel panel)
	{
		this.searchCriteriaPanel = panel;
	}
	*/
	public void refreshSearchResult()
	{
		UrlParam[] allParam = this.searchPanel.getCriteriaPanel().getSearchParams();		
		store.setBaseParams(allParam);
		store.load(0, pagingToolbar.getPageSize());
	}
	public void setSeachParams(UrlParam[] params)
	{
		store.setBaseParams(params);
	}
	/**
	 * @param grid the grid to set
	 */
	public void setGrid(GridPanel grid) {
		
		if (this.grid != null) {
			this.remove(this.grid);
			this.grid.destroy();
		}
		
		this.grid = grid;

		if (this.getParent() != null) {
			this.grid.setWidth(this.getParent().getOffsetWidth());
		}
		
        this.grid.setHeight((2 * (ROW_SIZE + 5)) + (pagingToolbar.getPageSize() * ROW_SIZE));
        
        this.grid.setBottomToolbar(pagingToolbar);
        this.grid.getView().setAutoFill(true);
        this.grid.getView().setForceFit(true);
                
        this.add(this.grid, DockPanel.NORTH);
	}
	/**
	 * @return the grid
	 */
	public GridPanel getGrid() {
		return this.grid;
	}	
}
