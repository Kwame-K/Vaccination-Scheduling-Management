package model;

public class Rdv {
	
	private long id_rdv;
	private String nom_centre;
	private long num_secu_rdv;
	private String date;
	
	
	
	
	public Rdv(long id_rdv, String nom_centre, long num_secu_rdv, String date) {
		super();
		this.id_rdv = id_rdv;
		this.nom_centre = nom_centre;
		this.num_secu_rdv = num_secu_rdv;
		this.date = date;
	}




	public long getId_rdv() {
		return id_rdv;
	}




	public void setId_rdv(long id_rdv) {
		this.id_rdv = id_rdv;
	}




	public String getNom_centre() {
		return nom_centre;
	}




	public void setNom_centre(String nom_centre) {
		this.nom_centre = nom_centre;
	}




	public long getNum_secu_rdv() {
		return num_secu_rdv;
	}




	public void setNum_secu_rdv(long num_secu_rdv) {
		this.num_secu_rdv = num_secu_rdv;
	}




	public String getDate() {
		return date;
	}




	public void setDate(String date) {
		this.date = date;
	}




	@Override
	public String toString() {
		return "Rendez-vous n°"+id_rdv + ", Centre : " + nom_centre + ", Date : "+ date;
	}
	
	

}
