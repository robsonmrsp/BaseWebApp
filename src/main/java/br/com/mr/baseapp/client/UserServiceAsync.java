package br.com.mr.baseapp.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface UserServiceAsync {
  void save(String name, String pass, AsyncCallback<Boolean> callback);
}
