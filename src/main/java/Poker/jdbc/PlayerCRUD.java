package Poker.jdbc;

import Poker.domain.Player;
import Poker.jdbc.util.DataBase;

import java.sql.*;
public class PlayerCRUD {

    public int createPlayer(Player player) {

//        try {
//            Connection con = DataBase.getConnect();
//            String addQuery = "{call addPlayer(?)}";
//            CallableStatement cs = con.prepareCall(addQuery);
//            cs.setString(1, player.getNickName());
//            cs.execute();
//            cs.close();
//
//            String queryId = "select last_insert_id()";
//            Statement st = con.createStatement();
//            ResultSet resultSet = st.executeQuery(queryId);
//            resultSet.next();
//            int id = resultSet.getInt(1);
//            if (id == 0)
//                throw new Exception("Problem with creating player");
//            else player.setId(id);
//            con.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        return newid;
        //return SET @last_id_in_table1 = LAST_INSERT_ID()
    }

    public Player readPlayer(int id) {
        Player player = new Player();
        try {
            Connection con = DataBase.getConnect();
            CallableStatement cs = con.prepareCall("{call getPlayerbyId(?)}");
            cs.setInt(1, id);

            ResultSet resultSet = cs.getResultSet();
            resultSet.next();
            player.setId(resultSet.getInt("id"));
            player.setNickName(resultSet.getString("nickName"));

            resultSet.close();
            cs.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return player;
    }

    public void deletePlayer(int id) {
        try {
            Connection con = DataBase.getConnect();
            String queryDelete = "{call deletePlayer(?)}";
            CallableStatement cs = con.prepareCall(queryDelete);
            cs.setInt(1, id);
            cs.execute();
            cs.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePlayer(Player player) {
        try {
            Connection con = DataBase.getConnect();
            String updateQuery = "{call updatePlayer(?,?)}";
            CallableStatement cs = con.prepareCall(updateQuery);
            cs.setInt(1, player.getId());
            cs.setString(2, player.getNickName());
            cs.execute();
            cs.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
