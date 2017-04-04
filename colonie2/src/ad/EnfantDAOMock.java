package ad;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entite.Enfant;

public class EnfantDAOMock implements EnfantDAO {
	

	@Override
	public Enfant selectEnfant(Integer idEnfant) throws DAOException {
		Enfant e = new Enfant ("MAMA", "Tata", new Date());
		return e;
	}

	@Override
	public List<Enfant> selectAllEnfants() throws DAOException {
		List<Enfant> list = new ArrayList<Enfant>();
		list.add(new Enfant("MOMO", "Hugo", new Date()));
		return list;
	}

	@Override
	public void insert(Enfant enfant) throws DAOException {
		
	}
	
	
	
	
	
	
	
	
	

}
