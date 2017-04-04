package metier;

import java.util.List;

import ad.DAOException;
import entite.Atelier;
import entite.Enfant;

public interface AtelierService {

	Integer nbEnfant(Atelier a) throws DAOException;

	Integer nbPlace(Atelier A) throws DAOException;

	List<Atelier> atelierNonPlein(List<Atelier> listAtelier) throws DAOException;

	List<Enfant> enfantNonOccupe(List<Atelier> l, List<Enfant> lEnfant) throws DAOException;

	Boolean verifierAge(Atelier a, Enfant e) throws DAOException;

	Integer moyenneAge(Atelier a) throws DAOException;

	List<Enfant> getListEnfant() throws DAOException;

	List<Atelier> getListAtelier() throws DAOException;

}