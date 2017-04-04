package ad;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entite.Atelier;
import entite.Enfant;

public class EnfantDAOImpl extends DAO implements EnfantDAO {

	// partie singleton

	private static EnfantDAO instance = null;

	private EnfantDAOImpl() {
	}

	public static synchronized EnfantDAO getInstance() {
		if (instance == null) {
			instance = new EnfantDAOImpl();
		}
		return instance;

	}

	// Méthode qui retourne la liste de tous les enfants

	@Override
	public List<Enfant> selectAllEnfants() throws DAOException {

		List<Enfant> listeEnfant = new ArrayList<Enfant>();

		Connection con = getConnection();
		ResultSet resultats = null;
		String requete = " ";

		requete = "SELECT enfants.* FROM enfants";
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			resultats = stmt.executeQuery(requete);

		} catch (SQLException e) {
			throw new DAOException("Anomalie lors de l'éxécution de la requête");
		}

		try {
			while (resultats.next()) {
				System.out.println(resultats.getInt("ID_Enfant") + "-" + resultats.getString("Nom") + " "
						+ resultats.getString("Prenom") + " " + "(" + resultats.getDate("Ddn") + ")");
				Enfant enfant = new Enfant();
				enfant.setIdEnfant(resultats.getInt("ID_Enfant"));
				enfant.setNom(resultats.getString("Nom"));
				enfant.setPrenom(resultats.getString("Prenom"));
				enfant.setDdn(resultats.getDate("Ddn"));
				listeEnfant.add(enfant);
			}

		} catch (SQLException e) {
			throw new DAOException("Problème dans l'extraction du résultat");
		}

		closeConnection();

		return listeEnfant;
	}

	// Méthode pour sélectionner un enfant à partir de son identifiant

	@Override
	public Enfant selectEnfant(Integer idEnfant) throws DAOException {

		Enfant enfant = new Enfant();

		Connection con = getConnection();
		ResultSet resultats = null;
		String requete = " ";

		requete = "SELECT enfants.* FROM enfants " + "WHERE(enfants.ID_Enfant=" + idEnfant + ")";

		Statement stmt = null;
		try {
			stmt = con.createStatement();
			resultats = stmt.executeQuery(requete);

		} catch (SQLException e) {
			throw new DAOException("Anomalie lors de l'éxécution de la requête");
		}

		try {
			while (resultats.next()) {
				System.out.println(resultats.getString("nom") + " " + resultats.getString("prenom") + " " + ("ID : ")
						+ resultats.getInt("ID_Enfant") + " " + "(" + resultats.getDate("Ddn") + ")");
				enfant.setNom(resultats.getString("nom"));
				enfant.setPrenom(resultats.getString("prenom"));
				enfant.setIdEnfant(resultats.getInt("ID_Enfant"));
				enfant.setDdn(resultats.getDate("Ddn"));
			}
		} catch (SQLException e) {
			throw new DAOException("Problème dans l'extraction du résultat");
		}

		closeConnection();

		return enfant;

	}

	// Méthode pour insérer un enfant dans la base de données

	public void insert(Enfant enfant) throws DAOException {

		Connection con = getConnection();

		String requete = "";

		requete = "INSERT INTO ENFANTS (NOM, PRENOM, Ddn) " + "VALUES (?,?,?)";

		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(requete);
			
			stmt.setString(1, enfant.getNom());
			stmt.setString(2, enfant.getPrenom());
			stmt.setDate(3, new java.sql.Date(enfant.getDdn().getTime()));
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("Probleme dans l'ajout de votre requete SQL");
		}

		closeConnection();

	}
}
