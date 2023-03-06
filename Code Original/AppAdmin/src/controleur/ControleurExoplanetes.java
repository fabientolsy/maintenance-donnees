package controleur;

import com.sun.media.jfxmedia.logging.Logger;

import architecture.Controleur;
import architecture.Navigateur;
import donnee.ExoplanetesDAO;
import modele.Exoplanete;
import vue.VueAjouter;
import vue.VueEditer;
import vue.VueEffacer;
import vue.VueExoplanetes;

public class ControleurExoplanetes extends Controleur{

	protected ExoplanetesDAO dao;
	public ControleurExoplanetes()
	{
		Logger.logMsg(Logger.INFO, "new ControleurExoplanetes()");
		this.dao = new ExoplanetesDAO();
	}
	
	public void notifierEnregistrerAjout()
	{
		Exoplanete exoplanete = VueAjouter.getInstance().lireExoplaneteEntree();
		dao.ajouterExoplanete(exoplanete);
		Navigateur.getInstance().afficherVue(VueExoplanetes.getInstance());
		VueExoplanetes.getInstance().afficherExoplanetes(dao.listerExoplanetes());
	}
	public void notifierEnregistrerEdition()
	{
		Exoplanete exoplanete = VueEditer.getInstance().lireExoplaneteEntree();
		dao.editerExoplanete(exoplanete);
		Navigateur.getInstance().afficherVue(VueExoplanetes.getInstance());
		VueExoplanetes.getInstance().afficherExoplanetes(dao.listerExoplanetes());
	}
	public void notifierActionAjouter() {
		Navigateur.getInstance().afficherVue(VueAjouter.getInstance());		
	}
	public void notifierActionEditerPourExoplanete(Exoplanete exoplanete)
	{
		VueEditer.getInstance().afficherExoplanete(exoplanete);
		Navigateur.getInstance().afficherVue(VueEditer.getInstance());
	}
	public void notifierActionEffacerPourExoplanete(Exoplanete exoplanete)
	{
		VueEffacer.getInstance().afficherExoplanete(exoplanete);
		Navigateur.getInstance().afficherVue(VueEffacer.getInstance());
	}
	public void notifierActionEffacerPourExoplanete(Exoplanete exoplanete, boolean reponse)
	{
		if(reponse == true)
		{
			dao.effacerExoplanete(exoplanete);
			VueExoplanetes.getInstance().afficherExoplanetes(dao.listerExoplanetes());
		}
		Navigateur.getInstance().afficherVue(VueExoplanetes.getInstance());
	}


}
