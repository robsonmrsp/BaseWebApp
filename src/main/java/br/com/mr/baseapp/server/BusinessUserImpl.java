package br.com.mr.baseapp.server;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class BusinessUserImpl implements BusinessUser {

	@Inject
	private DaoUser daoUser;

	public BusinessUserImpl() {

	}

	@Override
	public Boolean save(User user) {
		daoUser.save(user);
		return Boolean.TRUE;
	}

	public DaoUser getDaoUser() {
		return daoUser;
	}

	public void setDaoUser(DaoUser daoUser) {
		this.daoUser = daoUser;
	}

}
