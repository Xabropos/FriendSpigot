package be.kingquest.friendSpigot.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static be.kingquest.friendSpigot.database.Mysql.getConnectionInfoPlayer;

public class MysqlVorgaenge {

    public static boolean istUser(String Username) throws SQLException {
        try {
            Connection connection = getConnectionInfoPlayer();
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            String query = "SELECT * FROM PlayerInfo WHERE Username = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, Username);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }





}
