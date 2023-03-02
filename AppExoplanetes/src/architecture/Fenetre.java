package architecture;

import javafx.application.Platform;
import javafx.stage.Stage;

public class Fenetre extends Navigateur {

	@Override
	public void start(Stage stade) throws Exception {
		this.stade = stade;
		stade.setScene(Controleur.selectionnerVuePrincipale()); // une vue est appliquée à la fenêtre
		stade.show();
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
        		Controleur.selectionnerVuePrincipale().getControleur().initialiser();
            }
        });
	}

}
