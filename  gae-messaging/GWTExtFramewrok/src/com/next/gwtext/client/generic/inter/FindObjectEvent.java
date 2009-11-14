package com.next.gwtext.client.generic.inter;

import com.google.gwt.core.client.JavaScriptObject;
import com.next.gwtext.client.generic.CommonGwtProxySearchImpl;
import com.next.gwtext.client.generic.beans.PageInfoBean;

public interface FindObjectEvent {
	public void findObject(Object obj, PageInfoBean pageInfo,
			final CommonGwtProxySearchImpl gwtProxyImpl,final JavaScriptObject o);

}
