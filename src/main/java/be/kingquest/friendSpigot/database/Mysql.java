package be.kingquest.friendSpigot.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mysql {

    private static Connection connection;

    public void DatabaseManager(Connection connection) throws SQLException {
        this.connection = connection;
    }

    private static final String URL = "jdbc:mysql://194.15.36.74:25569/friend"; // Datenbank-URL
    private static final String USERNAME = "Luckperms"; // Ersetze durch deinen Benutzernamen
    private static final String PASSWORD = "pMwfnwoKf3HYW2ER3F"; // Ersetze durch dein Passwort

    private static final String URLInfoPlayer = "jdbc:mysql://194.15.36.74:25569/InfoPlayer"; // Datenbank-URL
    private static final String USERNAMEInfoPlayer = "Luckperms"; // Ersetze durch deinen Benutzernamen
    private static final String PASSWORDInfoPlayer = "pMwfnwoKf3HYW2ER3F"; // Ersetze durch dein Passwort


    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            // Verbindung zur Datenbank herstellen
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("§cVerbindung erfolgreich hergestellt!");
        } catch (SQLException e) {
            System.out.println("Verbindung fehlgeschlagen!");
            e.printStackTrace();
        }
        return connection;
    }

    public static Connection getConnectionInfoPlayer() throws SQLException {
        Connection connection = null;
        try {
            // Verbindung zur Datenbank herstellen
            connection = DriverManager.getConnection(URLInfoPlayer, USERNAMEInfoPlayer, PASSWORDInfoPlayer);
            System.out.println("§cVerbindung erfolgreich hergestellt!");
        } catch (SQLException e) {
            System.out.println("Verbindung fehlgeschlagen!");
            e.printStackTrace();
        }
        return connection;
    }
}
