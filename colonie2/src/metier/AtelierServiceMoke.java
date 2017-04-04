package metier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ad.DAOException;
import entite.Atelier;
import entite.Enfant;

public class AtelierServiceMoke implements AtelierService {

	
	/**
	 * connaitre le nombre d'enfant présent dans un atelier
	 */
	@Override
	public Integer nbEnfant (Atelier a) throws DAOException{
		Integer nb=5;
		return nb;
	}
	
	/**
	 * Nombre de place restantes dans un atelier
	 */
	@Override
	public Integer nbPlace(Atelier A) throws DAOException{
		return 10;
	}
	
	/**
	 * Liste des ateliers qui ne sont pas plein
	 */
	@Override
	public List<Atelier> atelierNonPlein(List<Atelier> listAtelier) throws DAOException{
		List<Atelier> l=new ArrayList<Atelier>();
		Atelier a=new Atelier("cuisine", 10, 12, 5);
		l.add(a);
		return l;
	}
	
	/**
	 * Liste des enfants qui ne sont pas dans un atelier
	 */
	@Override
	public List<Enfant> enfantNonOccupe(List<Atelier> l, List<Enfant> lEnfant) throws DAOException{
		List<Enfant> l1=new ArrayList<Enfant>();
		Enfant e=new Enfant("truc", "toto", new Date());
		l1.add(e);
		return l1;
	}
	
	/**
	 * Verifier si l'age d'un enfant correspond à celui de l'atelier
	 */
	@Override
	public Boolean verifierAge(Atelier a, Enfant e) throws DAOException{
		return true;
	}
	
	/**
	 * Connaitre la moyenne d'age des enfants dans un atelier
	 */
	@Override
	public Integer moyenneAge(Atelier a) throws DAOException{
		return 8;
	}

	@Override
	public List<Enfant> getListEnfant() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Atelier> getListAtelier() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ajoutEnfantBdd(Enfant e) throws DAOException {
		// TODO Auto-generated method stub
		
	}
}
