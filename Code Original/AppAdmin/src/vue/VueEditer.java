package vue;

import com.sun.media.jfxmedia.logging.Logger;

import architecture.Navigateur;
import architecture.Vue;
import controleur.ControleurExoplanetes;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modele.Exoplanete;

public class VueEditer extends Vue {

	protected ControleurExoplanetes controleur;
	protected static VueEditer instance = null; 
	public static VueEditer getInstance() {if(null==instance)instance = new VueEditer();return VueEditer.instance;}; 
	
	private VueEditer() 
	{
		super("vue-editer.fxml"); 
		super.controleur = this.controleur = new ControleurExoplanetes();
		Logger.logMsg(Logger.INFO, "new VueEditer()");
	}
		
	public void activerControles()
	{
		super.activerControles();
		
		Button actionEnregistrer = (Button) lookup("#action-enregistrer");
		actionEnregistrer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Action Enregistrer");
				controleur.notifierEnregistrerEdition();
			}}			
		);
	}
	
	protected Exoplanete exoplanete = null;
	
	public Exoplanete lireExoplaneteEntree()
	{		
		if(null != exoplanete)
		{			
			TextField champsNom = (TextField) lookup("#champs-nom");
			TextField champsEtoile = (TextField) lookup("#champs-etoile");
			TextField champsMasse = (TextField) lookup("#champs-masse");
			TextField champsRayon = (TextField) lookup("#champs-rayon");
			TextField champsFlux = (TextField) lookup("#champs-flux");		
			TextField champsTemperature = (TextField) lookup("#champs-temperature");
			TextField champsPeriode = (TextField) lookup("#champs-periode");
			TextField champsDistance = (TextField) lookup("#champs-distance");
			
			exoplanete.setNom(champsNom.getText());
			exoplanete.setEtoile(champsEtoile.getText());
			exoplanete.setMasse(champsMasse.getText());
			exoplanete.setRayon(champsRayon.getText());
			exoplanete.setFlux(champsFlux.getText());
			exoplanete.setTemperature(champsTemperature.getText());
			exoplanete.setPeriode(champsTemperature.getText());
			exoplanete.setDistance(champsDistance.getText());
		}
		
		return exoplanete;
	}
	
	public void afficherExoplanete(Exoplanete exoplanete)
	{
		this.exoplanete = exoplanete;
		
		TextField champsNom = (TextField) lookup("#champs-nom");
		TextField champsEtoile = (TextField) lookup("#champs-etoile");
		TextField champsMasse = (TextField) lookup("#champs-masse");
		TextField champsRayon = (TextField) lookup("#champs-rayon");
		TextField champsFlux = (TextField) lookup("#champs-flux");		
		TextField champsTemperature = (TextField) lookup("#champs-temperature");
		TextField champsPeriode = (TextField) lookup("#champs-periode");
		TextField champsDistance = (TextField) lookup("#champs-distance");
		champsNom.setText(exoplanete.getNom());
		champsEtoile.setText(exoplanete.getEtoile());
		champsMasse.setText(exoplanete.getMasse());
		champsRayon.setText(exoplanete.getRayon());
		champsFlux.setText(exoplanete.getFlux());
		champsTemperature.setText(exoplanete.getTemperature());
		champsPeriode.setText(exoplanete.getPeriode());
		champsDistance.setText(exoplanete.getDistance());
	}

}
