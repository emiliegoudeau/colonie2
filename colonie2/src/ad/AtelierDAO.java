package ad;

import java.util.List;

import entite.Atelier;
import entite.Enfant;


public interface AtelierDAO {

	public List<Enfant> selectAtelier (Integer idAtelier) throws DAOException;
	public List<Atelier> selectAllAtelier() throws DAOException;
	public Atelier selectUnAtelier(Integer idA) throws DAOException;
	public void insert(Atelier atelier) throws DAOException;
	
}
