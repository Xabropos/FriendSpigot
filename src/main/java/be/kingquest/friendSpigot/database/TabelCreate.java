package be.kingquest.friendSpigot.database;

import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static be.kingquest.friendSpigot.database.Mysql.getConnection;

public class TabelCreate {
    public static void friendsTabCreate(Player p){
        Connection connection = null;
        Statement statement = null;

        try {
            connection = getConnection(); // Verbindung zur Datenbank herstellen

            String createTableSQL = "CREATE TABLE IF NOT EXISTS `" + p.getName() + "` (" +
                    "    `Username` VARCHAR(255) NOT NULL," +
                    "    `playeruuid` VARCHAR(36) NOT NULL," +
                    "    `FreundeSeit` VARCHAR(255) NOT NULL," +
                    "    `Angenommen` VARCHAR(255) NOT NULL," +
                    "    PRIMARY KEY (`playeruuid`)" +
                    ") ENGINE=InnoDB;";

            statement = connection.createStatement();
            statement.execute(createTableSQL); // Tabelle erstellen
        } catch (SQLException e) {
            // Fehler in der Konsole protokollieren
            e.printStackTrace();
            // Fehlernachricht an den Spieler senden
            p.sendMessage("Error beim Erstellen der Tabelle: " + e.getMessage());
        } finally {
            // Ressourcen schließen
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Fehler beim Schließen protokollieren
            }
        }
    }
}
