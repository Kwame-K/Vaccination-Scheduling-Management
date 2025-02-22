package model;

public class Centre {
	
	private double id_c;
	private String nom_c;
	private String date_ouverture;
	private String date_fermeture;
	private String tel_c;
	private String Mod_rdv;
	private double lat_c;
	private double long_c;
	private String rdv_lundi;
	private String rdv_mardi;
	private String rdv_mercredi;
	private String rdv_jeudi;
	private String rdv_vendredi;
	private String rdv_samedi;
	private String rdv_dimanche;
	private Adresse adresse;
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "Centre : " + nom_c + ", Date d'ouverture : " + date_ouverture + ", Date de fermeture : "
				+ date_fermeture + ", Telephone : " + tel_c + ", Modalité du rdv=" + Mod_rdv + ", Lundi : " + rdv_lundi + ", Mardi : " + rdv_mardi + ", Mercredi : " + rdv_mercredi
				+ ", Jeudi : " + rdv_jeudi + ", Vendredi : " + rdv_vendredi + ", Samedi : " + rdv_samedi
				+ ", Dimanche :" + rdv_dimanche + ", Adresse :" + adresse ;
	}
	public Centre(double id_c, String nom_c, String date_ouverture, String date_fermeture, String tel_c, String mod_rdv,
			double lat_c, double long_c, String rdv_lundi, String rdv_mardi, String rdv_mercredi, String rdv_jeudi,
			String rdv_vendredi, String rdv_samedi, String rdv_dimanche, Adresse adresse) {
		super();
		this.id_c = id_c;
		this.nom_c = nom_c;
		this.date_ouverture = date_ouverture;
		this.date_fermeture = date_fermeture;
		this.tel_c = tel_c;
		Mod_rdv = mod_rdv;
		this.lat_c = lat_c;
		this.long_c = long_c;
		this.rdv_lundi = rdv_lundi;
		this.rdv_mardi = rdv_mardi;
		this.rdv_mercredi = rdv_mercredi;
		this.rdv_jeudi = rdv_jeudi;
		this.rdv_vendredi = rdv_vendredi;
		this.rdv_samedi = rdv_samedi;
		this.rdv_dimanche = rdv_dimanche;
		this.adresse = adresse;
	}
	public double getId_c() {
		return id_c;
	}
	public void setId_c(double id_c) {
		this.id_c = id_c;
	}
	public String getNom_c() {
		return nom_c;
	}
	public void setNom_c(String nom_c) {
		this.nom_c = nom_c;
	}
	public String getDate_ouverture() {
		return date_ouverture;
	}
	public void setDate_ouverture(String date_ouverture) {
		this.date_ouverture = date_ouverture;
	}
	public String getDate_fermeture() {
		return date_fermeture;
	}
	public void setDate_fermeture(String date_fermeture) {
		this.date_fermeture = date_fermeture;
	}
	public String getTel_c() {
		return tel_c;
	}
	public void setTel_c(String tel_c) {
		this.tel_c = tel_c;
	}
	public String getMod_rdv() {
		return Mod_rdv;
	}
	public void setMod_rdv(String mod_rdv) {
		Mod_rdv = mod_rdv;
	}
	public double getLat_c() {
		return lat_c;
	}
	public void setLat_c(double lat_c) {
		this.lat_c = lat_c;
	}
	public double getLong_c() {
		return long_c;
	}
	public void setLong_c(double long_c) {
		this.long_c = long_c;
	}
	public String getRdv_lundi() {
		return rdv_lundi;
	}
	public void setRdv_lundi(String rdv_lundi) {
		this.rdv_lundi = rdv_lundi;
	}
	public String getRdv_mardi() {
		return rdv_mardi;
	}
	public void setRdv_mardi(String rdv_mardi) {
		this.rdv_mardi = rdv_mardi;
	}
	public String getRdv_mercredi() {
		return rdv_mercredi;
	}
	public void setRdv_mercredi(String rdv_mercredi) {
		this.rdv_mercredi = rdv_mercredi;
	}
	public String getRdv_jeudi() {
		return rdv_jeudi;
	}
	public void setRdv_jeudi(String rdv_jeudi) {
		this.rdv_jeudi = rdv_jeudi;
	}
	public String getRdv_vendredi() {
		return rdv_vendredi;
	}
	public void setRdv_vendredi(String rdv_vendredi) {
		this.rdv_vendredi = rdv_vendredi;
	}
	public String getRdv_samedi() {
		return rdv_samedi;
	}
	public void setRdv_samedi(String rdv_samedi) {
		this.rdv_samedi = rdv_samedi;
	}
	public String getRdv_dimanche() {
		return rdv_dimanche;
	}
	public void setRdv_dimanche(String rdv_dimanche) {
		this.rdv_dimanche = rdv_dimanche;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
	

}
