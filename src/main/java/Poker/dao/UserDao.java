package Poker.dao;

import Poker.domain.User;
import Poker.jdbc.util.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao  extends AbstractDao<User> {
    @Override
    public String updateQuery() {
        return "{call updatedUser(?,?,?,?,?)}";
    }

    @Override
    public String getSelectQuery() {
        return "select *from player";
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
                user.setCreated(resultSet.getDate("created").toLocalDate());
                user.setUpdated(resultSet.getDate("updated").toLocalDate());
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

