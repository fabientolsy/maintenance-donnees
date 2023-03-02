
import architecture.Fenetre;
import donnee.ExoplanetesDAO;

public class App {

	public static void main(String[] parametres) {
		
		ExoplanetesDAO etudiantDAO = new ExoplanetesDAO();
		etudiantDAO.listerExoplanetes();
		
		Fenetre.launch(Fenetre.class, parametres);	
	}

}
