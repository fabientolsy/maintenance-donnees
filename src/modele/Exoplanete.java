package modele;

public class Exoplanete {

	protected int id;
	protected String nom;
	protected String etoile;
	protected String masse;
	protected String rayon;
	protected String flux;
	protected String temperature;
	
	public Exoplanete()
	{
		System.out.println("new Exoplanete()");
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEtoile() {
		return etoile;
	}
	public void setEtoile(String etoile) {
		this.etoile = etoile;
	}
	public String getMasse() {
		return masse;
	}
	public void setMasse(String masse) {
		this.masse = masse;
	}
	public String getRayon() {
		return rayon;
	}
	public void setRayon(String rayon) {
		this.rayon = rayon;
	}
	public String getFlux() {
		return flux;
	}
	public void setFlux(String flux) {
		this.flux = flux;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getPeriode() {
		return periode;
	}
	public void setPeriode(String periode) {
		this.periode = periode;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	protected String periode;
	protected String distance;
	
}
