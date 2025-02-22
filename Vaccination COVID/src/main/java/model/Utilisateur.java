package model;

public class Utilisateur {
	
	private long num_secu ;

	private String nom ;

	private String prenoms;

	private String civilite;

	private  String date_nais;

	private  String adresse;

	private  long tel;
	

	
	
	
	


	public Utilisateur(long num_secu, String nom, String prenoms, String civilite, String date_nais, String adresse,
			long tel) {
		super();
		this.num_secu = num_secu;
		this.nom = nom;
		this.prenoms = prenoms;
		this.civilite = civilite;
		this.date_nais = date_nais;
		this.adresse = adresse;
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Utilisateur n° : " + num_secu + ", Nom : " + nom + ", Prénom(s) : " + prenoms + ", Civilité : " + civilite
				+ ", Date de naisssance  : " + date_nais + ", Adresse : " + adresse + ", Tel : "+ tel ;
	}

	public long getNum_secu() {
		return num_secu;
	}

	public void setNum_secu(long num_secu) {
		this.num_secu = num_secu;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenoms() {
		return prenoms;
	}

	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public String getDate_nais() {
		return date_nais;
	}

	public void setDate_nais(String date_nais) {
		this.date_nais = date_nais;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public long getTel() {
		return tel;
	}

	public void setTel(long tel) {
		this.tel = tel;
	}


	
	
	

}
