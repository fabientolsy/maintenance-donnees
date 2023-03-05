package donnee;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringBufferInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import modele.Exoplanete;

public class ExoplanetesDAO {
	public String SQL_LISTER_EXOPLANETES = "SELECT * from exoplanete";
	public String SQL_AJOUTER_EXOPLANETE = "INSERT into exoplanete(planete,etoile, masse, rayon, flux, temperature, periode, distance) values(?,?,?,?,?,?,?,?)";		
	public String SQL_EDITER_EXOPLANETE = "UPDATE exoplanete SET planete = ?, etoile = ?, masse=?, rayon=?, flux=?, temperature=?, periode=?, distance=? WHERE id = ?";		
	public String SQL_EFFACER_EXOPLANETE = "DELETE from exoplanete WHERE id = ?";		

	public List<Exoplanete> listerExoplanetes()
	{
		System.out.println("ExoplanetesDAO.listerExoplanetes()");
		
		List<Exoplanete> listeExoplanetes =  new ArrayList<Exoplanete>();
		
		try
		{
			//Connexion au nuage
			String ID_DB ="maintenance-donnees";
			Credentials credit = GoogleCredentials.fromStream(new FileInputStream("maintenance-donnees-firebase-adminsdk-ua52b-289bcba6aa.json"));
			Firestore nuage = FirestoreOptions.getDefaultInstance().toBuilder().setCredentials(credit).setProjectId(ID_DB).build().getService();
			
			System.out.println(nuage);
			
			QuerySnapshot resultat = nuage.collection("planetes").get().get();
			
			List<QueryDocumentSnapshot> exoplanetesNuage = resultat.getDocuments();
			System.out.println(exoplanetesNuage);
			
			System.out.println("Affichage resultat avant for");
			for(QueryDocumentSnapshot exoplaneteNuage : exoplanetesNuage)
			{
				/*System.out.println("Affichage resultat");
				System.out.println("Etoile: " + exoplaneteNuage.getString("etoile"));*/
				
				Exoplanete exoplanete = new Exoplanete();
				
				exoplanete.setPlanete(exoplaneteNuage.getString("planete"));
				exoplanete.setEtoile(exoplaneteNuage.getString("etoile"));
				exoplanete.setMasse(exoplaneteNuage.getString("masse"));
				exoplanete.setRayon(exoplaneteNuage.getString("rayon"));
				exoplanete.setFlux(exoplaneteNuage.getString("flux"));
				exoplanete.setTemperature(exoplaneteNuage.getString("temperature"));
				exoplanete.setPeriode(exoplaneteNuage.getString("periode"));
				exoplanete.setDistance(exoplaneteNuage.getString(ID_DB));
				
				System.out.println(exoplanete.getPlanete());
				
				listeExoplanetes.add(exoplanete);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return listeExoplanetes;
	}
	
	private int parseInt(String textContent) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void ajouterExoplanete(Exoplanete exoplanete)
	{		
		String URL_AJOUTER_EXOPLANETE = "http://51.79.67.33/service.exoplanetes/ajouter-exoplanete.php";
		String parametres = "planete=" + exoplanete.getPlanete() + "&etoile=" + exoplanete.getEtoile();
		try {
			URL url = new URL(URL_AJOUTER_EXOPLANETE);
			HttpURLConnection connexion = (HttpURLConnection) url.openConnection();
			connexion.setDoOutput(true);
			connexion.setRequestMethod("POST");
			connexion.setFixedLengthStreamingMode(parametres.getBytes().length);
			connexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			
			OutputStream flux = connexion.getOutputStream();
			OutputStreamWriter messager = new OutputStreamWriter(flux);
			
			messager.write(parametres);
			messager.close();
			connexion.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editerExoplanete(Exoplanete exoplanete)
	{		
		System.out.println("editer " + exoplanete.getPlanete());
		String parametres = "id="+ exoplanete.getId() +"&planete=" + exoplanete.getPlanete() + "&etoile=" + exoplanete.getEtoile();
		String URL_EDITER_EXOPLANETE = "http://51.79.67.33/service.exoplanetes/modifier-exoplanete.php?" + parametres;
		try {
			URL url = new URL(URL_EDITER_EXOPLANETE);
			HttpURLConnection connexion = (HttpURLConnection) url.openConnection();
			connexion.setDoOutput(true);
			connexion.setRequestMethod("POST");
			connexion.setFixedLengthStreamingMode(parametres.getBytes().length);
			connexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
	
			OutputStream flux = connexion.getOutputStream();
			OutputStreamWriter messager = new OutputStreamWriter(flux);
			
			messager.write(parametres);
			messager.close();
			connexion.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void effacerExoplanete(Exoplanete exoplanete)
	{		
		System.out.println("supprimer " + exoplanete.getPlanete());
		int id = exoplanete.getId();
		String URL_SUPPRIMER_EXOPLANETE = "http://51.79.67.33/service.exoplanetes/supprimer-exoplanete.php?" + id;
		
		try {
			URL url = new URL(URL_SUPPRIMER_EXOPLANETE);
			HttpURLConnection connexion = (HttpURLConnection) url.openConnection();
			connexion.setDoOutput(true);
			connexion.setRequestMethod("POST");

			connexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

			connexion.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
}
