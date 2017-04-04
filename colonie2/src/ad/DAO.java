package ad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {

	private Connection con = null;

	protected Connection getConnection() throws DAOException {

		// Chargement du pilote
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new DAOException("Impossible de charger le pilote");
		}

		// Connexion à la base de données

		try {
			String DBur1 = "jdbc:mysql://localhost:3306/colonie";
			con = DriverManager.getConnection(DBur1, "root", "root");
		} catch (SQLException e) {
			throw new DAOException("Connexion a la base de donnees impossible");
		}
		return con;

	}

	protected void closeConnection() throws DAOException {
		try {
			con.close();

		} catch (SQLException e) {
			throw new DAOException("Problème de fermeture de connexion");
		}

	}

}
