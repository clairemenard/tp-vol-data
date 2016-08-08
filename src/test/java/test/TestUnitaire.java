package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import vol.model.Adresse;
import vol.model.Client;
import vol.model.ClientEI;
import vol.model.ClientMoral;
import vol.model.ClientPhysique;
import vol.model.Login;
import vol.model.dao.ClientDao;
import vol.model.dao.LoginDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class TestUnitaire {
	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private LoginDao loginDao;

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

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

		// UPDATE
		loginDao.update(login);

		// SELECT
		login = loginDao.findById(logId);

		Assert.assertNotNull(login);

		Assert.assertEquals("Logg", login.getLogin());
		Assert.assertEquals("MdP", login.getMotDePasse());
		Assert.assertFalse(login.getAdmin());

		// FINDALL
		List<Login> logins = loginDao.findAll();

		Assert.assertNotNull(login);

		Assert.assertEquals(1, logins.size());

		// DELETE
		loginDao.delete(login);

		// SELECT
		login = loginDao.findById(logId);

		Assert.assertNull(login);
	}

	@Test
	public void client() throws ParseException {

		//
		// Client Physique
		//
		ClientPhysique clientPhysique = new ClientPhysique();
		Adresse adressePhysique = new Adresse("2 rue Ernest Renan", "50100", "CHERBOURG", "FRANCE");
		clientPhysique.setNom("Quenneville");
		clientPhysique.setPrenom("Delmar");
		clientPhysique.setNumeroTel("02.19.75.71.11");
		clientPhysique.setNumeroFax("02.19.75.71.41");
		clientPhysique.setEmail("DelmarQuenneville@dayrep.com");
		clientPhysique.setAdresse(adressePhysique);

		// INSERT
		clientDao.create(clientPhysique);

		// SELECT
		clientPhysique = (ClientPhysique) clientDao.findById(clientPhysique.getIdCli());

		// Assert.assertEquals(depot, depotFind);
		Assert.assertNotNull(clientPhysique);

		Assert.assertEquals("Quenneville", clientPhysique.getNom());
		Assert.assertEquals("Delmar", clientPhysique.getPrenom());
		Assert.assertEquals("02.19.75.71.11", clientPhysique.getNumeroTel());
		Assert.assertEquals("02.19.75.71.41", clientPhysique.getNumeroFax());
		Assert.assertEquals("DelmarQuenneville@dayrep.com", clientPhysique.getEmail());
		Assert.assertEquals(adressePhysique.getRue(), clientPhysique.getAdresse().getRue());
		Assert.assertEquals(adressePhysique.getVille(), clientPhysique.getAdresse().getVille());
		Assert.assertEquals(adressePhysique.getCodePostal(), clientPhysique.getAdresse().getCodePostal());
		Assert.assertEquals(adressePhysique.getPays(), clientPhysique.getAdresse().getPays());

		clientPhysique.setNumeroTel("02.19.56.55.11");
		clientPhysique.setNumeroFax("02.19.75.45.02");

		// UPDATE
		clientDao.update(clientPhysique);

		// SELECT
		clientPhysique = (ClientPhysique) clientDao.findById(clientPhysique.getIdCli());

		Assert.assertNotNull(clientPhysique);

		Assert.assertEquals("02.19.56.55.11", clientPhysique.getNumeroTel());
		Assert.assertEquals("02.19.75.45.02", clientPhysique.getNumeroFax());

		List<Client> clientPhysiques = clientDao.findAll();

		Assert.assertEquals(1, clientPhysiques.size());

		clientDao.delete(clientPhysique);

		clientPhysique = (ClientPhysique) clientDao.findById(clientPhysique.getIdCli());

		Assert.assertNull(clientPhysique);

		//
		// Client EI
		//
		ClientEI clientEI = new ClientEI();
		Adresse adresseEI = new Adresse("46, rue du Faubourg National", "33400", "TALENCE", "FRANCE");
		clientEI.setNom("Quenneville");
		clientEI.setPrenom("Delmar");
		clientEI.setNumeroTel("02.19.75.71.11");
		clientEI.setNumeroFax("02.19.75.71.41");
		clientEI.setEmail("DelmarQuenneville@dayrep.com");
		clientEI.setAdresse(adresseEI);

		// INSERT
		clientDao.create(clientEI);

		// SELECT
		clientEI = (ClientEI) clientDao.findById(clientEI.getIdCli());

		// Assert.assertEquals(depot, depotFind);
		Assert.assertNotNull(clientEI);

		Assert.assertEquals("Quenneville", clientEI.getNom());
		Assert.assertEquals("Delmar", clientEI.getPrenom());
		Assert.assertEquals("02.19.75.71.11", clientEI.getNumeroTel());
		Assert.assertEquals("02.19.75.71.41", clientEI.getNumeroFax());
		Assert.assertEquals("DelmarQuenneville@dayrep.com", clientEI.getEmail());
		Assert.assertEquals(adresseEI.getRue(), clientEI.getAdresse().getRue());
		Assert.assertEquals(adresseEI.getVille(), clientEI.getAdresse().getVille());
		Assert.assertEquals(adresseEI.getCodePostal(), clientEI.getAdresse().getCodePostal());
		Assert.assertEquals(adresseEI.getPays(), clientEI.getAdresse().getPays());

		clientEI.setNumeroTel("02.19.56.55.11");
		clientEI.setNumeroFax("02.19.75.45.02");

		// UPDATE
		clientDao.update(clientEI);

		// SELECT
		clientEI = (ClientEI) clientDao.findById(clientEI.getIdCli());

		Assert.assertNotNull(clientEI);

		Assert.assertEquals("02.19.56.55.11", clientEI.getNumeroTel());
		Assert.assertEquals("02.19.75.45.02", clientEI.getNumeroFax());

		List<Client> clientEIs = clientDao.findAll();

		Assert.assertEquals(1, clientEIs.size());

		clientDao.delete(clientEI);

		clientEI = (ClientEI) clientDao.findById(clientEI.getIdCli());

		Assert.assertNull(clientEI);

		//
		// Client Moral
		//
		ClientMoral clientMoral = new ClientMoral();
		Adresse adresseMoral = new Adresse("55, place Stanislas", "92000", "NANTERRE", "FRANCE");
		clientMoral.setNom("Quenneville");
		clientMoral.setSiret(156651);
		clientMoral.setNumeroTel("02.19.75.71.11");
		clientMoral.setNumeroFax("02.19.75.71.41");
		clientMoral.setEmail("DelmarQuenneville@dayrep.com");
		clientMoral.setAdresse(adresseMoral);

		// INSERT
		clientDao.create(clientMoral);

		// SELECT
		clientMoral = (ClientMoral) clientDao.findById(clientMoral.getIdCli());

		// Assert.assertEquals(depot, depotFind);
		Assert.assertNotNull(clientMoral);

		Assert.assertEquals("Quenneville", clientMoral.getNom());
		Assert.assertEquals(156651, clientMoral.getSiret());
		Assert.assertEquals("02.19.75.71.11", clientMoral.getNumeroTel());
		Assert.assertEquals("02.19.75.71.41", clientMoral.getNumeroFax());
		Assert.assertEquals("DelmarQuenneville@dayrep.com", clientMoral.getEmail());
		Assert.assertEquals(adresseMoral.getRue(), clientMoral.getAdresse().getRue());
		Assert.assertEquals(adresseMoral.getVille(), clientMoral.getAdresse().getVille());
		Assert.assertEquals(adresseMoral.getCodePostal(), clientMoral.getAdresse().getCodePostal());
		Assert.assertEquals(adresseMoral.getPays(), clientMoral.getAdresse().getPays());

		clientMoral.setSiret(98889);

		// UPDATE
		clientDao.update(clientMoral);

		// SELECT
		clientMoral = (ClientMoral) clientDao.findById(clientMoral.getIdCli());

		Assert.assertNotNull(clientMoral);

		Assert.assertEquals(98889, clientMoral.getSiret());

		List<Client> clientMorals = clientDao.findAll();

		Assert.assertEquals(1, clientMorals.size());

		clientDao.delete(clientMoral);

		clientMoral = (ClientMoral) clientDao.findById(clientMoral.getIdCli());

		Assert.assertNull(clientMoral);

	}

}
