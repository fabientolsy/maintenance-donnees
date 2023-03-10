package donnee;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import modele.Exoplanete;

public class ExoplanetesDAO {

	public List<Exoplanete> listerExoplanetes()
	{
		System.out.println("ExoplanetesDAO.listerExoplanetes()");
		
		List<Exoplanete> listeExoplanetes =  new ArrayList<Exoplanete>();	
		
		String URL_LISTE_EXO_PLANETES = "http://51.79.67.33/service.exoplanetes/exoplanetes.php";
		
		String xml = "";

		
		try {
			URL url = new URL(URL_LISTE_EXO_PLANETES);
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
				String nom = noeudExopPlanete.getElementsByTagName("nom").item(0).getTextContent();
				String etoile = noeudExopPlanete.getElementsByTagName("etoile").item(0).getTextContent();
				String masse = noeudExopPlanete.getElementsByTagName("masse").item(0).getTextContent();
				String rayon = noeudExopPlanete.getElementsByTagName("rayon").item(0).getTextContent();
				String flux = noeudExopPlanete.getElementsByTagName("flux").item(0).getTextContent();
				String temperature = noeudExopPlanete.getElementsByTagName("temperature").item(0).getTextContent();
				String periode = noeudExopPlanete.getElementsByTagName("periode").item(0).getTextContent();
				String distance = noeudExopPlanete.getElementsByTagName("distance").item(0).getTextContent();
				
				System.out.println(nom + " - " + etoile);
				
				Exoplanete exoplanete = new Exoplanete();
				exoplanete.setNom(nom);
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
}