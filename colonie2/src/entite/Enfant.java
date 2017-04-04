package entite;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Enfant {

	protected String nom;
	protected String prenom;
	protected Date ddn;
	protected Integer idEnfant;
	protected List<Atelier> listeEnfant=new ArrayList<Atelier>();
	
	
	public Enfant() {
	}

	public Enfant(String nom, String prenom, Date ddn) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.ddn = ddn;
	}
	
	public Integer getAge() {
		Integer age = 0;
		// année d'ajd
		Calendar ddjCal = Calendar.getInstance();
		Integer anneeJ = ddjCal.get(Calendar.YEAR);
		// année de naissance
		Calendar ddnCal = Calendar.getInstance();
		ddnCal.setTime(this.ddn);
		Integer anneeN = ddnCal.get(Calendar.YEAR);
		// calcul de l'age
		age = anneeJ - anneeN;
		return age;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDdn() {
		return ddn;
	}

	public void setDdn(Date ddn) {
		this.ddn = ddn;
	}

	public Integer getIdEnfant() {
		return idEnfant;
	}

	public void setIdEnfant(Integer idEnfant) {
		this.idEnfant = idEnfant;
	}

	public List<Atelier> getListeEnfant() {
		return listeEnfant;
	}

	public void setListeEnfant(List<Atelier> listeEnfant) {
		this.listeEnfant = listeEnfant;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Enfant : ");
		builder.append(idEnfant);
		builder.append(" ");
		builder.append(nom);
		builder.append(" ");
		builder.append(prenom);
		builder.append(", ");
		builder.append(getAge());
		return builder.toString();
	}
	
	
}
