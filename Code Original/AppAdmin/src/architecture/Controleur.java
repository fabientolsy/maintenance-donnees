package architecture;

import donnee.ExoplanetesDAO;
import vue.VueExoplanetes;

//import vue.Navigateur;
//import vue.*;

public class Controleur {

	public static Vue selectionnerVuePrincipale()
	{
		return VueExoplanetes.getInstance();
	}		
	
	public void initialiser()
	{
		ExoplanetesDAO exoplaneteDAO = new ExoplanetesDAO();
		VueExoplanetes.getInstance().afficherExoplanetes(exoplaneteDAO.listerExoplanetes());
	}
	
}
