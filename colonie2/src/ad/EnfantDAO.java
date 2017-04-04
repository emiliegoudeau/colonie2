package ad;

import java.util.List;

import entite.Enfant;

public interface EnfantDAO {
	
	public Enfant selectEnfant (Integer idEnfant) throws DAOException;
	public List<Enfant> selectAllEnfants() throws DAOException;
	public void insert(Enfant enfant) throws DAOException;
	
	
	
}
