package Poker.dao;

import Poker.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao  extends AbstractDao<User> {

    @Override
    public String getUpdateQuery() {
        return "{call updatedUser(?,?,?,?,?)}";
    }

    @Override
    public String getSelectByQuery() {
        return "{call getUserbyId(?,?,?,?,?,?,?)}";
    }

    @Override
    public String getSelectQuery() {
        return "select *from user";
    }

    @Override
    public String getDeleteQuery() {
        return "{call deleteUser(?)}";
    }

    @Override
    public List<User> parseRS(ResultSet resultSet) {
        List<User> users = new ArrayList<>();
        try {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setPassword(resultSet.getString("password"));
                user.setLogin(resultSet.getString("login"));
                user.setEmail(resultSet.getString("email"));
                user.setBalance(resultSet.getDouble("balance"));
                user.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                user.setUpdated(resultSet.getTimestamp("updated").toLocalDateTime());
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public String getCreateQuery() {
        return "{call addUser(?,?,?)}";
    }

    @Override
    public void setCSParam(CallableStatement cs) {
        try {
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.registerOutParameter(4, Types.VARCHAR);
            cs.registerOutParameter(5, Types.DOUBLE);
            cs.registerOutParameter(6, Types.TIMESTAMP);
            cs.registerOutParameter(7, Types.TIMESTAMP);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void setCS(CallableStatement cs, User user) {
        try {
            cs.setString(1, user.getLogin());
            cs.setString(2, user.getPassword());
            cs.setString(3, user.getEmail());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void setUpdateCS(CallableStatement cs, User user) {
        try {
            cs.setString(1, user.getLogin());
            cs.setString(2, user.getPassword());
            cs.setString(3, user.getEmail());
            cs.setDouble(4, user.getBalance());
            cs.setInt(5, user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public User getUserFromCS(int id, CallableStatement cs) {
        User someUser = null;
        try {
            someUser = new User();
            someUser.setId(id);
            someUser.setLogin(cs.getString(2));
            someUser.setPassword(cs.getString(3));
            someUser.setEmail(cs.getString(4));
            someUser.setBalance(cs.getDouble(5));
            someUser.setCreated(cs.getTimestamp(6).toLocalDateTime());
            someUser.setUpdated(cs.getTimestamp(7).toLocalDateTime());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return someUser;
    }
}

