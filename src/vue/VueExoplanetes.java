package vue;

import java.util.List;

import com.sun.media.jfxmedia.logging.Logger;

import architecture.Vue;
import controleur.ControleurExoplanetes;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modele.Exoplanete;

public class VueExoplanetes extends Vue{
	protected ControleurExoplanetes controleur;
	protected static VueExoplanetes instance = null; 
	public static VueExoplanetes getInstance() {if(null==instance)instance = new VueExoplanetes();return VueExoplanetes.instance;}; 
	
	private VueExoplanetes() 
	{
		super("exoplanetes.fxml"); 
		super.controleur = this.controleur = new ControleurExoplanetes();
		Logger.logMsg(Logger.INFO, "new VueExoplanetes()");
	}
		
	public void activerControles()
	{
		super.activerControles();
	}
	
	public void afficherExoplanetes(List<Exoplanete> exoplanetes)
	{	
		System.out.println("VueExoplanetes.afficherExoplanetes() avec " + exoplanetes.size() + " elements");
		
		// Recuperation de l'objet dans lequel afficher
		TableView tableau = (TableView)lookup("#liste-exoplanetes");
		
		// Association des champs de l'objet avec les colonnes du tableau		
		TableColumn colonneNom = (TableColumn) tableau.getColumns().get(0);
		TableColumn colonneEtoile = (TableColumn) tableau.getColumns().get(1);
		TableColumn colonneMasse = (TableColumn) tableau.getColumns().get(2);
		TableColumn colonneRayon = (TableColumn) tableau.getColumns().get(3);
		TableColumn colonneFlux = (TableColumn) tableau.getColumns().get(4);
		TableColumn colonneTemperature = (TableColumn) tableau.getColumns().get(5);
		TableColumn colonnePeriode = (TableColumn) tableau.getColumns().get(6);
		TableColumn colonneDistance = (TableColumn) tableau.getColumns().get(7);
		
		colonneNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		colonneEtoile.setCellValueFactory(new PropertyValueFactory<>("etoile"));
		colonneMasse.setCellValueFactory(new PropertyValueFactory<>("masse"));
		colonneRayon.setCellValueFactory(new PropertyValueFactory<>("rayon"));
		colonneFlux.setCellValueFactory(new PropertyValueFactory<>("flux"));
		colonneTemperature.setCellValueFactory(new PropertyValueFactory<>("temperature"));
		colonnePeriode.setCellValueFactory(new PropertyValueFactory<>("periode"));
		colonneDistance.setCellValueFactory(new PropertyValueFactory<>("distance"));
		
		// Ajout des donnees
		for(Exoplanete exoplanete : exoplanetes)
		{
			System.out.println(exoplanete.getNom());
			tableau.getItems().add(exoplanete);
		}
	}

}
