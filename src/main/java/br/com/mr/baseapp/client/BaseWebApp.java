package br.com.mr.baseapp.client;

import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class BaseWebApp implements EntryPoint {

	@Override
	public void onModuleLoad() {
		FormUser formUser = new FormUser();
		formUser.setSize(310, 130);
		formUser.show();
	}
}
