package Poker.dao;

import Poker.domain.Player;
import Poker.jdbc.util.DataBase;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class PlayerDao extends AbstractDao<Player> {
    @Override
    public String getUpdateQuery() {
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
    public String getDeleteQuery() {
        return "{call deletePlayer(?)}";
    }

    @Override
    public String getSelectByQuery() {
        return "{call getPlayerById(?,?)}";
    }


    @Override
    public void setUpdateCS(CallableStatement cs, Player player) {
        try {
            cs.setInt(1,player.getId());
            cs.setString(2,player.getNickName());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setCSParam(CallableStatement cs) {
        try {
            cs.registerOutParameter(2, Types.VARCHAR);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Player getUserFromCS(int id, CallableStatement cs) {
       Player somePlayer = null;
        try {
            somePlayer = new Player();
            somePlayer.setId(id);
            somePlayer.setNickName(cs.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return somePlayer;
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
