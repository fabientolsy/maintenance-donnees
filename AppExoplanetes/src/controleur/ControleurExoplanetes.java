package controleur;

import com.sun.media.jfxmedia.logging.Logger;

import architecture.Controleur;
import architecture.Vue;
import donnee.ExoplanetesDAO;
import vue.VueExoplanetes;

public class ControleurExoplanetes extends Controleur{

	public ControleurExoplanetes()
	{
		Logger.logMsg(Logger.INFO, "new ControleurExoplanetes()");
	}

	public void initialiser()
	{
		ExoplanetesDAO exoplaneteDAO = new ExoplanetesDAO();
		VueExoplanetes.getInstance().afficherExoplanetes(exoplaneteDAO.listerExoplanetes());
	}		

}
