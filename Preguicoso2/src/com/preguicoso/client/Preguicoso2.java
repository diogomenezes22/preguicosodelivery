package com.preguicoso.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.preguicoso.client.backend.Backend;
import com.preguicoso.client.backend.Login;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Preguicoso2 implements EntryPoint {

	public void onModuleLoad() {
		if(true){
			Backend backend = new Backend();
			RootPanel.get("loading").setVisible(false);
			RootPanel.get("content").add(backend);
		}else{
			RootPanel.get("content").add(new Login());
		}
	}
}
