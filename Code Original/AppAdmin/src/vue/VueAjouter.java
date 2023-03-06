package vue;

import com.sun.media.jfxmedia.logging.Logger;

import architecture.Vue;
import controleur.ControleurExoplanetes;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modele.Exoplanete;

public class VueAjouter extends Vue {

	protected ControleurExoplanetes controleur;
	protected static VueAjouter instance = null; 
	public static VueAjouter getInstance() {if(null==instance)instance = new VueAjouter();return VueAjouter.instance;}; 
	
	private VueAjouter() 
	{
		super("vue-ajouter.fxml"); 
		super.controleur = this.controleur = new ControleurExoplanetes();
		Logger.logMsg(Logger.INFO, "new VueAjouter()");
	}
		
	public void activerControles()
	{
		super.activerControles();
		
		Button actionEnregistrer = (Button) lookup("#action-enregistrer");
		actionEnregistrer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Action Enregistrer");
				controleur.notifierEnregistrerAjout();
			}}			
		);
	}
	
	public Exoplanete lireExoplaneteEntree()
	{
		Exoplanete exoplanete = new Exoplanete();
		
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
		
		return exoplanete;
	}
	
}
