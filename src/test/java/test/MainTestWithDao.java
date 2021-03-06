package test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import vol.model.Adresse;
import vol.model.Aeroport;
import vol.model.Client;
import vol.model.ClientPhysique;
import vol.model.CompagnieAerienne;
import vol.model.CompagnieAerienneVol;
import vol.model.Escale;
import vol.model.EtatReservation;
import vol.model.Login;
import vol.model.Passager;
import vol.model.Reservation;
import vol.model.Ville;
import vol.model.VilleAeroport;
import vol.model.Vol;
import vol.model.dao.AeroportDao;
import vol.model.dao.ClientDao;
import vol.model.dao.CompagnieAerienneDao;
import vol.model.dao.CompagnieAerienneVolDao;
import vol.model.dao.EscaleDao;
import vol.model.dao.LoginDao;
import vol.model.dao.PassagerDao;
import vol.model.dao.ReservationDao;
import vol.model.dao.VilleAeroportDao;
import vol.model.dao.VilleDao;
import vol.model.dao.VolDao;

public class MainTestWithDao {

	public static void main(String[] args) throws ParseException {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		AeroportDao aeroportDao = (AeroportDao) context.getBean("aeroportDaoJpa");
		ClientDao clientDao = (ClientDao) context.getBean("clientDaoJpa");
		CompagnieAerienneDao compagnieAerienneDao = (CompagnieAerienneDao) context.getBean("compagnieAerienneDaoJpa");
		CompagnieAerienneVolDao compagnieAerienneVolDao = (CompagnieAerienneVolDao) context.getBean("compagnieAerienneVolDaoJpa");
		EscaleDao escaleDao = (EscaleDao) context.getBean("escaleDaoJpa");
		LoginDao loginDao = (LoginDao) context.getBean("loginDaoJpa");
		PassagerDao passagerDao = (PassagerDao) context.getBean("passagerDaoJpa");
		ReservationDao reservationDao = (ReservationDao) context.getBean("reservationDaoJpa");
		VilleAeroportDao villeAeroportDao = (VilleAeroportDao) context.getBean("villeAeroportDaoJpa");
		VilleDao villeDao = (VilleDao) context.getBean("villeDaoJpa");
		VolDao volDao = (VolDao) context.getBean("volDaoJpa");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat shf = new SimpleDateFormat("hh:mm");
		
		//----------------LOG---------------
		
		//Cr�ation d'un login
		Login log1 = new Login();
		
		//Initialisation des attributs de log 1
		log1.setLogin("BestPlace");
		log1.setMotDePasse("ThePlaceToBe");
		
		//Cr�ation de log1 dans la BDD
		loginDao.create(log1);
		
		log1.setAdmin(true);
		
		//MAJ de log1
		loginDao.update(log1);
		
		Login log2 = new Login();
		
		log2.setLogin("Truc");
		log2.setMotDePasse("MachinChose");
		
		loginDao.create(log2);
		
		System.out.println("Login de log2 : " +loginDao.findById(log2.getIdLog()).getLogin());
		
		loginDao.delete(log2);
		
		List<Login> logins = new ArrayList<Login>();
		
		logins = loginDao.findAll();
		
		System.out.println("Nombre de logins : "+logins.size());
		
		Login logBoby = new Login();
		logBoby.setMotDePasse("mdp_de_gueudin");
		logBoby.setLogin("Je_me_log");
		
		loginDao.create(logBoby);
		
		//--------------------AEROPORT-------------------
		
		Aeroport aero1 = new Aeroport(201, "BPAirport");
		
		aeroportDao.create(aero1);
		
		Aeroport aero2 = new Aeroport(101, "CDG");
		
		aeroportDao.create(aero2);
		
		Aeroport aero3 = new Aeroport(301, "ORY");
		
		aeroportDao.create(aero3);
		
		aero2.setNom("Charles De Gaulles");
		
		aero2 = aeroportDao.update(aero2);
		
		aeroportDao.delete(aero3);
		
		//--------------------VOL--------------------
		
		Vol vol1 = new Vol(1546);
		
		vol1.setAeroportDepart(aero1);
		vol1.setAeroportArrivee(aero2);
		vol1.setDateArrivee(sdf.parse("06/08/2016"));
		vol1.setDateDepart(sdf.parse("04/08/2016"));
		vol1.setHeureDepart(shf.parse("04:00"));
		vol1.setHeureArrivee(shf.parse("18:30"));
		
		volDao.create(vol1);
		
		Vol vol2 = new Vol(52);
		
		vol2.setAeroportDepart(aero1);
		vol2.setAeroportArrivee(aero2);
		vol2.setDateArrivee(sdf.parse("12/03/2017"));
		vol2.setDateDepart(sdf.parse("10/03/2017"));
		vol2.setHeureDepart(shf.parse("04:00"));
		vol2.setHeureArrivee(shf.parse("18:30"));
		
		volDao.create(vol2);
		
		vol2.setHeureDepart(shf.parse("06:00"));
		
		volDao.update(vol2);
		
		Vol vol3 = new Vol(4948);
		
		volDao.create(vol3);
		
		volDao.delete(vol3);
		
		
		//-------------------ESCALE--------------------------
		
		Escale esc1 = new Escale();
		
		esc1.setAeroport(aero2);
		esc1.setDateArrivee(sdf.parse("05/08/2016"));
		esc1.setDateDepart(sdf.parse("05/08/2016"));
		esc1.setVol(vol1);
		
		escaleDao.create(esc1);
		
		
		//--------------------CLIENT--------------------------
		
		Client Boby = new ClientPhysique("Boby");
		Boby.setNom("Bybul");
		Boby.setEmail("leBGdu33@hotmail.fr");
		Boby.setNumeroTel("0606060606");
		Boby.setNumeroFax("0909090909");
		Boby.setAdresse(new Adresse("12 rue La Vache sur le to�t", "12345", "C'est Vachement Sympa", "France"));
		Boby.setLog(logBoby);
		
		clientDao.create(Boby);
		
		Boby.setNumeroTel("0707070707");
		
		clientDao.update(Boby);
		
		//-------------------------PASSAGER--------------------
		
		Passager Ronald = new Passager();
		Ronald.setAdresse(new Adresse("33 all�e Du Haaaaaaaan", "99999", "LeviosaaaCity", "Angleterre"));
		Ronald.setNom("Wisley");
		Ronald.setPrenom("Ronald");	
		
		passagerDao.create(Ronald);
		
		Passager floflo = new Passager();
		floflo.setPrenom("Florent");
		floflo.setNom("Darto");
		floflo.setAdresse(new Adresse("3 rue de l'�glise", "33700", "Bordeaux", "France"));
		
		passagerDao.create(floflo);
		
		Passager rob = new Passager();
		rob.setPrenom("Rob");
		rob.setNom("Robert");
		rob.setAdresse(new Adresse("5 rue de la poste", "75000", "Paris", "France"));
		
		passagerDao.create(rob);
		
		//Update :
		rob.setPrenom("Paul");
		passagerDao.update(rob);
		
		
		//Delete :
		passagerDao.delete(rob);
		
		//-----------------------VILLE---------------------------
		
		Ville Zouglouland = new Ville ("Zouglouland");
		
		villeDao.create(Zouglouland);
		
		Zouglouland.setNom("Zougglouland");
		
		villeDao.update(Zouglouland);
		
		//---------------------VILLE AEROPORT------------------------
		
		VilleAeroport VA = new VilleAeroport(Zouglouland, aero1);
		
		villeAeroportDao.create(VA);
		
		//----------------------------RESERVATION----------------------
		
		Reservation vacaaances = new Reservation();
		vacaaances.setDate(sdf.parse("04/08/2016"));
		vacaaances.setClient(Boby);
		vacaaances.setPassager(Ronald);
		
		reservationDao.create(vacaaances);
		
		vacaaances.setEtat(EtatReservation.ferme);
		
		reservationDao.update(vacaaances);
		
		//-------------------COMPAGNIE AERIENNE------------------
		
		CompagnieAerienne comp1 = new CompagnieAerienne();
		comp1.setNom("Air France");
		
		
		CompagnieAerienne comp2 = new CompagnieAerienne();
		comp2.setNom("Easy Jet");
		
		compagnieAerienneDao.create(comp1);
		
		compagnieAerienneDao.create(comp2);
		
		//Update :
		comp2.setNom("KLM");
		compagnieAerienneDao.update(comp2);
		
		//Delete :
		//Application.getInstance().getCompagnieAerienneDao().delete(comp2);
		
		//----------------------COMPAGNIE AERIENNE VOL---------------------
		
		CompagnieAerienneVol cav1 = new CompagnieAerienneVol("2456225");
		cav1.setOuvert(true);
		
		compagnieAerienneVolDao.create(cav1);
		cav1.setCompagnieAerienne(comp1);
		
		cav1 = compagnieAerienneVolDao.update(cav1);
		
		cav1.setVol(vol1);
		
		compagnieAerienneVolDao.update(cav1);
		
	}

}
