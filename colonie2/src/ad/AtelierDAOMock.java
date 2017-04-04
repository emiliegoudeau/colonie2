package ad;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entite.Atelier;
import entite.Enfant;

public class AtelierDAOMock implements AtelierDAO {
		

	@Override
	public List<Enfant> selectAtelier(Integer idAtelier) throws DAOException {
		List<Enfant> list = new ArrayList<Enfant>();
		list.add(new Enfant("MIMI", "Titi", new Date()));
		return list;
	}

	@Override
	public List<Atelier> selectAllAtelier() throws DAOException {
		List<Atelier> list = new ArrayList<Atelier>();
		list.add(new Atelier ("Test", 12,15, 10));
		return list;
	}
	
	public Atelier selectUnAtelier(Integer idA) throws DAOException {
		Atelier a = new Atelier ("cuisine", 10, 12, 5);
		return a;
		
	}

	@Override
	public void insert(Atelier atelier) throws DAOException {
		
		
	}
	
	
	
	
	
	

}
