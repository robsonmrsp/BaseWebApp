package br.com.mr.baseapp.server;

import javax.inject.Named;

@Named("daoUser")
public class DaoUser extends Dao<User>{

	public DaoUser() {
		super(User.class);
	}
}
