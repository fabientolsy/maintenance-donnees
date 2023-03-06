package donnee;

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
		
		String SQL_LISTER_EXOPLANETES = "http://51.79.67.33/service.exoplanetes/exoplanetes.php";
		
		String xml = "";

		
		try {
			URL url = new URL(SQL_LISTER_EXOPLANETES);
			InputStream flux = url.openConnection().getInputStream();
			Scanner lecteur = new Scanner(flux);
			lecteur.useDelimiter("\\A");
			xml = lecteur.next();
			lecteur.close();

			xml = new String(xml.getBytes("UTF-8"), "ISO-8859-1");
			System.out.println(xml);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			DocumentBuilder parseur  = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = parseur.parse(new StringBufferInputStream(xml));
			NodeList listeNoeudsExoPlanetes = document.getElementsByTagName("planete");
			
			for(int i = 0; i <listeNoeudsExoPlanetes.getLength(); i++) 
			{
				Element noeudExopPlanete = (Element)listeNoeudsExoPlanetes.item(i);
				int id = parseInt(noeudExopPlanete.getElementsByTagName("id").item(0).getTextContent());
				String planete = noeudExopPlanete.getElementsByTagName("nom").item(0).getTextContent();
				String etoile = noeudExopPlanete.getElementsByTagName("etoile").item(0).getTextContent();
				String masse = noeudExopPlanete.getElementsByTagName("masse").item(0).getTextContent();
				String rayon = noeudExopPlanete.getElementsByTagName("rayon").item(0).getTextContent();
				String flux = noeudExopPlanete.getElementsByTagName("flux").item(0).getTextContent();
				String temperature = noeudExopPlanete.getElementsByTagName("temperature").item(0).getTextContent();
				String periode = noeudExopPlanete.getElementsByTagName("periode").item(0).getTextContent();
				String distance = noeudExopPlanete.getElementsByTagName("distance").item(0).getTextContent();
				
				System.out.println(nom + " - " + etoile);
				
				Exoplanete exoplanete = new Exoplanete();
				exoplanete.setId(id);
				exoplanete.setPlanete(planete);
				exoplanete.setEtoile(etoile);
				exoplanete.setMasse(masse);
				exoplanete.setRayon(rayon);
				exoplanete.setFlux(flux);
				exoplanete.setTemperature(temperature);
				exoplanete.setPeriode(periode);
				exoplanete.setDistance(distance);
				
				listeExoplanetes.add(exoplanete);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		System.out.println("supprimer " + exoplanete.getNom());
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
