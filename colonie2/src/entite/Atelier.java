package entite;

import java.util.ArrayList;
import java.util.List;

public class Atelier {
	protected String nom;
	protected Integer idAtelier;
	protected Integer ageMini;
	protected Integer ageMaxi;
	protected Integer nbPlace;
	protected List<Enfant> liste=new ArrayList<Enfant>();
	
	public Atelier() {
	}

	public Atelier(String nom, Integer ageMini, Integer ageMaxi, Integer nbPlace) {
		super();
		this.nom = nom;
		this.ageMini = ageMini;
		this.ageMaxi = ageMaxi;
		this.nbPlace = nbPlace;
	}

	//Ajouter un enfant dans un atelier
	public void  addEnfant(Enfant e){
		this.liste.add(e);
	}
	
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getIdAtelier() {
		return idAtelier;
	}

	public void setIdAtelier(Integer idAtelier) {
		this.idAtelier = idAtelier;
	}

	public Integer getAgeMini() {
		return ageMini;
	}

	public void setAgeMini(Integer ageMini) {
		this.ageMini = ageMini;
	}

	public Integer getAgeMaxi() {
		return ageMaxi;
	}

	public void setAgeMaxi(Integer ageMaxi) {
		this.ageMaxi = ageMaxi;
	}

	public Integer getNbPlace() {
		return nbPlace;
	}

	public void setNbPlace(Integer nbPlace) {
		this.nbPlace = nbPlace;
	}

	public List<Enfant> getListe() {
		return liste;
	}

	public void setListe(List<Enfant> liste) {
		this.liste = liste;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(nom);
		builder.append(" n°");
		builder.append(idAtelier);
		builder.append(", ageMini=");
		builder.append(ageMini);
		builder.append(", ageMaxi=");
		builder.append(ageMaxi);
		builder.append(", nbPlace=");
		builder.append(nbPlace);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
