package test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import vol.model.Login;
import vol.model.dao.LoginDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class TestJunit {

	@Autowired
	private LoginDao loginDao;

	@Test
	public void login() {
		Login login = new Login();
		login.setLogin("Log");
		login.setMotDePasse("PassWord");
		login.setAdmin(true);
		int logId;

		// INSERT
		loginDao.create(login);
		
		logId = login.getIdLog();

		// SELECT
		login = loginDao.findById(logId);
		
		Assert.assertNotNull(login);

		Assert.assertEquals("Log", login.getLogin());
		Assert.assertEquals("PassWord", login.getMotDePasse());
		Assert.assertTrue(login.getAdmin());

		// MODIF
		login.setLogin("Logg");
		login.setMotDePasse("MdP");
		login.setAdmin(false);
		
		//UPDATE
		loginDao.update(login);

		// SELECT
		login = loginDao.findById(logId);
		
		Assert.assertNotNull(login);
		
		Assert.assertEquals("Logg", login.getLogin());
		Assert.assertEquals("MdP", login.getMotDePasse());
		Assert.assertFalse(login.getAdmin());
		
		//FINDALL
		List<Login> logins = loginDao.findAll();
		
		Assert.assertNotNull(login);

		Assert.assertEquals(1, logins.size());

		//DELETE
		loginDao.delete(login);

		//SELECT
		login = loginDao.findById(logId);

		Assert.assertNull(login);
	}

}
