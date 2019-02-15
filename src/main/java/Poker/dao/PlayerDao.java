package Poker.dao;

import Poker.domain.Player;
import Poker.jdbc.util.DataBase;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDao extends AbstractDao<Player> {


    @Override
    public String updateQuery() {
        return "{call updatePlayer(?,?)}";
    }

    @Override
    public String getSelectQuery() {
        return "select *from player";
    }

    @Override
    public String getCreateQuery() {
        return "{call addPlayer(?)}";
    }

    @Override
    public String deleteQuery() {
        return "{call deletePlayer(?)}";
    }

    @Override
    public List<Player> parseRS(ResultSet resultSet) {
        List<Player> players = new ArrayList<>();
        try {

            while (resultSet.next()) {
                Player player = new Player();
                player.setId(resultSet.getInt("id"));
                player.setNickName(resultSet.getString("nickName"));
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }
    @Override
    public void setCS(CallableStatement cs, Player player) {
        try {
            cs.setString(1, player.getNickName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
