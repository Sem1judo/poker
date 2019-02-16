package Poker.dao;

import Poker.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao  extends AbstractDao<User> {
    @Override
    public void getUserFromCS(int id, CallableStatement cs, User someObject) {
        try {
            someObject.setId(id);
            someObject.setLogin(cs.getString(2));
            someObject.setPassword(cs.getString(3));
            someObject.setEmail(cs.getString(4));
            someObject.setBalance(cs.getDouble(5));
            someObject.setCreated(cs.getTimestamp(6).toLocalDateTime());
            someObject.setUpdated(cs.getTimestamp(7).toLocalDateTime());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String updateQuery() {
        return "{call updatedUser(?,?,?,?,?)}";
    }

    @Override
    public String getSelectByQuery() {
        return "{call getUserbyId (?,?,?,?,?,?,?)}";
    }

    @Override
    public void setCSParametrs(CallableStatement cs) {
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
    public String getSelectQuery() {
        return "select *from user";
    }

    @Override
    public String deleteQuery() {
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
    public void setCS(CallableStatement cs, User user) {
        try {
            cs.setString(1, user.getLogin());
            cs.setString(2, user.getPassword());
            cs.setString(3, user.getEmail());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

