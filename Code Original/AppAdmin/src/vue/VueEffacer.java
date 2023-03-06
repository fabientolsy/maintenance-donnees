package vue;

import com.sun.media.jfxmedia.logging.Logger;

import architecture.Navigateur;
import architecture.Vue;
import controleur.ControleurExoplanetes;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modele.Exoplanete;

public class VueEffacer extends Vue {

	protected ControleurExoplanetes controleur;
	protected static VueEffacer instance = null; 
	public static VueEffacer getInstance() {if(null==instance)instance = new VueEffacer();return VueEffacer.instance;}; 
	
	private VueEffacer() 
	{
		super("vue-effacer.fxml"); 
		super.controleur = this.controleur = new ControleurExoplanetes();
		Logger.logMsg(Logger.INFO, "new VueEffacer()");
	}
		
	public void activerControles()
	{
		super.activerControles();
		
		Button actionEffacerOui = (Button) lookup("#action-effacer-oui");
		actionEffacerOui.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Action Effacer OUI");
				controleur.notifierActionEffacerPourExoplanete(exoplanete, true);
			}}			
		);
		
		Button actionEffacerNon = (Button) lookup("#action-effacer-non");
		actionEffacerNon.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Action Effacer NON");
				controleur.notifierActionEffacerPourExoplanete(exoplanete, false);
			}}			
		);
		
	}
	
	protected Exoplanete exoplanete = null;
	
	public Exoplanete lireExoplaneteEntre()
	{		
		return exoplanete;
	}
	
	protected String messages = "Voulez-vous desinscrire %prenom% %nom% ?";
	public void afficherExoplanete(Exoplanete exoplanete)
	{
		this.exoplanete = exoplanete;
		Label question = (Label) lookup("#question-effacer");
		String questionEtVariables = messages.replace("%nom%", exoplanete.getNom());
		question.setText(questionEtVariables);
	}
}
