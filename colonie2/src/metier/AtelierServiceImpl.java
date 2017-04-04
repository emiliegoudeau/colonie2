package metier;

import java.util.ArrayList;
import java.util.List;

//import org.apache.log4j.Logger;

import ad.AtelierDAOImpl;
import ad.DAOException;
import ad.EnfantDAOImpl;
import entite.Atelier;
import entite.Enfant;

public class AtelierServiceImpl implements AtelierService {

	//private static final Logger logger = Logger.getLogger("log4j.AtelierServiceImpl");
	
	protected List<Atelier> lAtelier = new ArrayList<Atelier>();

	private static AtelierService instance = null;

	private AtelierServiceImpl() {
	}

	public static synchronized AtelierService getInstance() {
		if (instance == null) {
			instance = new AtelierServiceImpl();
		}
		return instance;
	}

	/**
	 * nb d'enfant dans un atelier
	 */
	@Override
	public Integer nbEnfant(Atelier a) throws DAOException {
		Integer nb = 0;
		List<Enfant> l = new ArrayList<Enfant>();
		try {
			l = AtelierDAOImpl.getInstance().selectAtelier(a.getIdAtelier());
		} catch (DAOException e) {
			System.out.println("le numero d'atelier demand� n'existe pas");
		}
		nb = l.size();
		return nb;
	}

	/*
	 * donnee le nombre de place disponible dans un atelier
	 */
	@Override
	public Integer nbPlace(Atelier a) throws DAOException {
		Integer nb = 0;
		try {
			System.out.println(a);
			nb = a.getNbPlace() - nbEnfant(a);
		} catch (DAOException e) {
			System.out.println("l'atelier demand� n'existe pas");
		}
		return nb;
	}

	/*
	 * donne la liste des ateliers o� il reste des places
	 */
	@Override
	public List<Atelier> atelierNonPlein(List<Atelier> listAtelier) throws DAOException {
		List<Atelier> l = new ArrayList<Atelier>();
		try {
			for (Atelier atelier : listAtelier) {
				if (nbPlace(atelier) != 0) {
					l.add(atelier);
				}
			}
		} catch (DAOException e) {
			System.out.println("mauvaise liste d'atelier");
		}
		return l;
	}

	@Override
	public List<Enfant> enfantNonOccupe(List<Atelier> l, List<Enfant> lEnfant) {
		List<Enfant> lE = new ArrayList<Enfant>();
		for (Enfant enfant : lEnfant) {
			Boolean bool = false;
			while (bool == false) {
				for (Atelier atelier : l) {
					for (Enfant e : atelier.getListe())
						if (enfant.getIdEnfant() == e.getIdEnfant()) {
							bool = true;
						}
				}
			}
			lE.add(enfant);

		}
		return lE;
	}

	@Override
	public Boolean verifierAge(Atelier a, Enfant e) {
		Boolean bool = false;
		if ((e.getAge() >= a.getAgeMini()) && (e.getAge() <= a.getAgeMaxi())) {
			bool = true;
		}
		return bool;
	}

	@Override
	public Integer moyenneAge(Atelier a) {
		Integer somme = 0;
		for (Enfant enfant : a.getListe()) {
			somme += enfant.getAge();
		}
		Integer moyenne = somme / (a.getListe().size());
		return moyenne;
	}

	public List<Enfant> getListEnfant() throws DAOException{
		return EnfantDAOImpl.getInstance().selectAllEnfants();
		
	}
	
	public List<Atelier> getListAtelier() throws DAOException{
		return AtelierDAOImpl.getInstance().selectAllAtelier();
	}
	
	public void ajoutEnfantBdd(Enfant e) throws DAOException {
		EnfantDAOImpl.getInstance().insert(e);
	}
}
