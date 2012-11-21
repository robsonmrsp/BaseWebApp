package br.com.mr.baseapp.server;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import br.com.mr.baseapp.client.UserService;

@Transactional
@Named("userService")
public class UserServiceImpl implements UserService {

	@Inject
	BusinessUser businessUser;
	private static final Log logger = LogFactory.getLog(UserServiceImpl.class);

	@Override
	public Boolean save(String name, String pass) throws Exception {
		Boolean returnValue = Boolean.FALSE;
		try {
			User user = new User(name, pass);
			returnValue = businessUser.save(user);
			logger.debug("usuário " + name + " salvo com id " + user.getId());
		} catch (Exception e) {
			logger.error("Não foi possivel salvar o usuário: " + name, e);
			returnValue = Boolean.FALSE;
		}
		return returnValue;
	}
}
