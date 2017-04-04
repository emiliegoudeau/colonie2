package ad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entite.Atelier;
import entite.Enfant;

public class AtelierDAOImpl extends DAO implements AtelierDAO {

	// partie singleton

	private static AtelierDAO instance = null;

	private AtelierDAOImpl() {
	}

	public static synchronized AtelierDAO getInstance() {
		if (instance == null) {
			instance = new AtelierDAOImpl();
		}
		return instance;
	}

	/**
	 * Méthode qui permet de récupérer 1 atelier à partir de son identifiant
	 * avec la liste des enfants participants à cet atelier
	 * 
	 */

	public List<Enfant> selectAtelier(Integer idAtelier) throws DAOException {

		List<Enfant> list = new ArrayList<Enfant>();

		Connection con = getConnection();
		ResultSet resultats = null;
		String requete = " ";

		requete = "SELECT enfants.Nom, enfants.Prenom,enfants.ID_Enfant FROM enfants "
				+ "INNER JOIN detailsatelier ON detailsatelier.ID_Enfant=enfants.ID_Enfant "
				+ "INNER JOIN ateliers ON ateliers.ID_Ateliers=detailsatelier.ID_DetailsAtelier "
				+ "WHERE(detailsatelier.ID_Ateliers=" + idAtelier + ")";
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			resultats = stmt.executeQuery(requete);

		} catch (SQLException e) {
			throw new DAOException("Anomalie lors de l'éxécution de la requête");
		}

		try {
			while (resultats.next()) {
				System.out.println(resultats.getString("nom") + " " + resultats.getString("prenom") + " "
						+ resultats.getInt("ID_Enfant"));
				Enfant enfant = new Enfant();
				enfant.setNom(resultats.getString("nom"));
				enfant.setPrenom(resultats.getString("prenom"));
				enfant.setIdEnfant(resultats.getInt("ID_Enfant"));
				list.add(enfant);
			}

		} catch (SQLException e) {
			throw new DAOException("Problème dans l'extraction du résultat");
		}

		closeConnection();

		return list;
	}

	/**
	 * Méthode qui permet de récupérer la liste de tous les ateliers
	 */

	public List<Atelier> selectAllAtelier() throws DAOException {

		List<Atelier> listeAtelier = new ArrayList<Atelier>();

		Connection con = getConnection();
		ResultSet resultats = null;
		String requete = " ";

		requete = "SELECT ateliers.* FROM ateliers";
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			resultats = stmt.executeQuery(requete);

		} catch (SQLException e) {
			throw new DAOException("Anomalie lors de l'éxécution de la requête");
		}

		try {
			while (resultats.next()) {
				System.out.println(resultats.getInt("ID_Ateliers") + "-" + resultats.getString("nom") + " "
						+ resultats.getInt("AgeMini") + "/" + resultats.getInt("AgeMaxi") + "/"
						+ resultats.getInt("NbPlaces"));
				Atelier atelier = new Atelier();
				atelier.setIdAtelier(resultats.getInt("ID_Ateliers"));
				atelier.setNom(resultats.getString("nom"));
				atelier.setAgeMini(resultats.getInt("AgeMini"));
				atelier.setAgeMaxi(resultats.getInt("AgeMaxi"));
				atelier.setNbPlace(resultats.getInt("NbPlaces"));
				listeAtelier.add(atelier);
			}

		} catch (SQLException e) {
			throw new DAOException("Problème dans l'extraction du résultat");
		}

		closeConnection();

		return listeAtelier;
	}

	/**
	 * Méthode qui permet de récupérer un atelier à partir de son identifiant
	 */

	@Override
	public Atelier selectUnAtelier(Integer idAtelier) throws DAOException {

		Atelier atelier = new Atelier();

		Connection con = getConnection();
		ResultSet resultats = null;
		String requete = " ";

		requete = "SELECT ateliers.* FROM ateliers " + "WHERE(ateliers.ID_Ateliers=" + idAtelier + ")";

		Statement stmt = null;
		try {
			stmt = con.createStatement();
			resultats = stmt.executeQuery(requete);

		} catch (SQLException e) {
			throw new DAOException("Anomalie lors de l'éxécution de la requête");
		}

		try {
			while (resultats.next()) {
				System.out.println(resultats.getInt("ID_Ateliers") + "-" + resultats.getString("Nom") + " "
						+ resultats.getInt("AgeMini") + "/" + resultats.getInt("AgeMaxi") + "/"
						+ resultats.getInt("NbPlaces"));
				atelier.setIdAtelier(resultats.getInt("ID_Ateliers"));
				atelier.setNom(resultats.getString("Nom"));
				atelier.setAgeMini(resultats.getInt("AgeMini"));
				atelier.setAgeMaxi(resultats.getInt("AgeMaxi"));
				atelier.setNbPlace(resultats.getInt("NbPlaces"));
			}
		} catch (SQLException e) {
			throw new DAOException("Problème dans l'extraction du résultat");
		}

		closeConnection();

		return atelier;

	}

	/**
	 * Méthode qui permet d'ajouter un atelier à la BDD
	 * 
	 */

	public void insert(Atelier atelier) throws DAOException {

		Connection con = getConnection();

		String requete = "";

		requete = "INSERT INTO ATELIERS (NOM, AgeMini, AgeMaxi, NbPlaces) " + "VALUES (?,?,?,?)";

		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(requete);

			stmt.setString(1, atelier.getNom());
			stmt.setInt(2, atelier.getAgeMini());
			stmt.setInt(3, atelier.getAgeMaxi());
			stmt.setInt(4, atelier.getNbPlace());
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("Probleme dans l'ajout de votre requete SQL");
		}

		closeConnection();
	}

}
