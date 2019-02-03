package Poker.jdbc;

import Poker.domain.User;
import Poker.jdbc.util.DataBase;

import java.sql.*;

public class UserCRUD {


    public void createUser(User user) {
        try {
            Connection con = DataBase.getConnect();
            String addQuery = "{call addUser(?,?,?)}";
            CallableStatement cs = con.prepareCall(addQuery);
            cs.setString(1, user.getLogin());
            cs.setString(2, user.getPassword());
            cs.setString(3, user.getEmail());
            cs.execute();
            cs.close();

            String queryId = "select last_insert_id()";
            Statement st = con.createStatement();
            ResultSet resultSet = st.executeQuery(queryId);
            resultSet.next();
            int id = resultSet.getInt(1);
            if (id == 0)
                throw new Exception("Problem with creating user");
            else user.setId(id);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //return SET @last_id_in_table1 = LAST_INSERT_ID()
    }

    public User readUser(int id) {
        User user = null;
        try {
            Connection con = DataBase.getConnect();
            String readQuery = "{call getUserbyId(?)}";
            CallableStatement cs = con.prepareCall(readQuery);
            cs.setInt(1, id);

            ResultSet resultSet = cs.getResultSet();
            resultSet.next();
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            user.setBalance(resultSet.getDouble("balance"));

            resultSet.close();
            cs.close();
            con.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void deleteUser(int id) {
        try {
            Connection con = DataBase.getConnect();
            String deleteRequest = "{call deleteUser(?)}";
            CallableStatement cs = con.prepareCall(deleteRequest);
            cs.setInt(1, id);
            cs.execute();

            cs.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User updateUser(int id) {
        User user = null;
        try {

            Connection con = DataBase.getConnect();
            String updateRequest = "{call updatedUser(?,?,?,?}";
            CallableStatement cs = con.prepareCall(updateRequest);
            cs.setString(1, user.getLogin());
            cs.setString(2, user.getEmail());
            cs.setString(3, user.getPassword());
            cs.setInt(4, id);
            cs.execute();

            cs.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
