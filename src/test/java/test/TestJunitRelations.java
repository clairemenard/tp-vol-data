package test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import vol.model.Client;
import vol.model.ClientMoral;
import vol.model.Login;
import vol.model.dao.ClientDao;
import vol.model.dao.LoginDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class TestJunitRelations {
	@Autowired
	private LoginDao loginDao;
	@Autowired
	private ClientDao clientDao;

	@Test
	public void loginClient() {
		Login login = new Login();
		int logId;

		// INSERT
		loginDao.create(login);

		logId = login.getIdLog();

		Client client = new ClientMoral();
		int clientId;

		// INSERT
		clientDao.create(client);

		clientId = client.getIdCli();

		client.setLog(login);

		// UPDATE
		clientDao.update(client);

		// SELECT
		login = loginDao.findById(logId);
		client = (ClientMoral) clientDao.findById(clientId);

		Assert.assertNotNull(login);
		Assert.assertNotNull(client);

		Assert.assertEquals(login.getIdLog(), client.getLog().getIdLog());

		// DELETE
		clientDao.delete(client);

		// SELECT
		client = clientDao.findById(clientId);

		Assert.assertNull(client);

		// DELETE
		loginDao.delete(login);

		// SELECT
		login = loginDao.findById(logId);

		Assert.assertNull(login);

	}

}
