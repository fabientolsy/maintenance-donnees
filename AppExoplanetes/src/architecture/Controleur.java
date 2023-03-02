package architecture;

import vue.*;

public class Controleur {

	public static Vue selectionnerVuePrincipale()
	{
		return VueExoplanetes.getInstance();
		//return VueFlotte.getInstance();
	}

	public void initialiser()
	{
		VueExoplanetes.getInstance().getControleur().initialiser();
	}
}


