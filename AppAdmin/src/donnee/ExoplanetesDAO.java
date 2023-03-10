package donnee;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.google.api.core.ApiFuture;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

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
				
				System.out.println("Reference: " + exoplaneteNuage.getReference().getId().toString());
				
				exoplanete.setReference(exoplaneteNuage.getReference().getId().toString());
				exoplanete.setId(exoplaneteNuage.getString("id"));
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
		String ID_DB ="maintenance-donnees";
		Credentials credit;
		try 
		{
			credit = GoogleCredentials.fromStream(new FileInputStream("maintenance-donnees-firebase-adminsdk-ua52b-289bcba6aa.json"));
			Firestore nuage = FirestoreOptions.getDefaultInstance().toBuilder().setCredentials(credit).setProjectId(ID_DB).build().getService();
			
			System.out.println(nuage);
			
			Map<String, Object> nouvellePlanete = new HashMap<>();
			nouvellePlanete.put("planete", exoplanete.getPlanete());
			nouvellePlanete.put("etoile", exoplanete.getEtoile());
			nouvellePlanete.put("masse", exoplanete.getMasse());
			nouvellePlanete.put("rayon", exoplanete.getRayon());
			nouvellePlanete.put("flux", exoplanete.getFlux());
			nouvellePlanete.put("temperature", exoplanete.getTemperature());
			nouvellePlanete.put("periode", exoplanete.getPeriode());
			nouvellePlanete.put("distance", exoplanete.getDistance());
			
			System.out.println(nouvellePlanete);
			
			DocumentReference nouvellePlaneteNuage = nuage.collection("planetes").document();
			
			ApiFuture<WriteResult> resultat = nouvellePlaneteNuage.set(nouvellePlanete);
			
			
			System.out.println("Update time= " + resultat.get().getUpdateTime());
			
			
		}  
		
		catch (FileNotFoundException | InterruptedException | ExecutionException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void editerExoplanete(Exoplanete exoplanete)
	{		
		//Connexion au nuage
		String ID_DB ="maintenance-donnees";
		Credentials credit;
		try 
		{
			credit = GoogleCredentials.fromStream(new FileInputStream("maintenance-donnees-firebase-adminsdk-ua52b-289bcba6aa.json"));
			Firestore nuage = FirestoreOptions.getDefaultInstance().toBuilder().setCredentials(credit).setProjectId(ID_DB).build().getService();
			
			System.out.println(nuage);
			
			nuage.collection("planetes").document("test").delete();
			
			Map<String, Object> exoplaneteModifiee = new HashMap<>();
			exoplaneteModifiee.put("planete", exoplanete.getPlanete());
			exoplaneteModifiee.put("etoile", exoplanete.getEtoile());
			exoplaneteModifiee.put("masse", exoplanete.getMasse());
			exoplaneteModifiee.put("rayon", exoplanete.getRayon());
			exoplaneteModifiee.put("flux", exoplanete.getFlux());
			exoplaneteModifiee.put("temperature", exoplanete.getTemperature());
			exoplaneteModifiee.put("periode", exoplanete.getPeriode());
			exoplaneteModifiee.put("distance", exoplanete.getDistance());
			
			System.out.println(exoplaneteModifiee);
			
			nuage.collection("planetes").document(exoplanete.getReference()).set(exoplaneteModifiee);
			
			System.out.println("Ca a marche !");
		}  
		
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void effacerExoplanete(Exoplanete exoplanete)
	{		
		//Connexion au nuage
		String ID_DB ="maintenance-donnees";
		Credentials credit;
		try {
			credit = GoogleCredentials.fromStream(new FileInputStream("maintenance-donnees-firebase-adminsdk-ua52b-289bcba6aa.json"));
			Firestore nuage = FirestoreOptions.getDefaultInstance().toBuilder().setCredentials(credit).setProjectId(ID_DB).build().getService();
			
			System.out.println(nuage);
			
			nuage.collection("planetes").document(exoplanete.getReference()).delete();
			
			listerExoplanetes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}	
}
