package br.com.mr.baseapp.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("services/userService")
public interface UserService extends RemoteService {
	Boolean save(String name, String pass) throws Exception;

	public static class Util {
		public static UserServiceAsync create() {
			return GWT.create(UserService.class);
		}
	}
}
