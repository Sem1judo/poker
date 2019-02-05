package Poker.jdbc;

import Poker.domain.User;
import Poker.jdbc.util.DataBase;

import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.time.LocalDate;

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
        User user = new User();


        try {
            Connection con = DataBase.getConnect();
            String readQuery = "select *from user where id = ?";
            PreparedStatement ps = con.prepareStatement(readQuery);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            resultSet.next();

            user.setId(id);
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            user.setBalance(resultSet.getDouble("balance"));
            user.setCreated(resultSet.getDate("created").toLocalDate());
            user.setUpdated(resultSet.getDate("updated").toLocalDate());

            resultSet.close();
            ps.close();
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

    public User updateUser(User user) {

        try {

            Connection con = DataBase.getConnect();
            String updateRequest = "{call updatedUser(?,?,?,?,?)}";
            CallableStatement cs = con.prepareCall(updateRequest);
            cs.setString(1, user.getLogin());
            cs.setString(2, user.getEmail());
            cs.setString(3, user.getPassword());
            cs.setDouble(4, user.getBalance());
            cs.setInt(5, user.getId());

            cs.execute();

            cs.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User loginUser(String log, String pass) {
        User user = new User();
        try {

            Connection con = DataBase.getConnect();
            String readQuery = "select *from user where login = ? and password = ? ";
            PreparedStatement ps = con.prepareStatement(readQuery);
            ps.setString(1, log);
            ps.setString(2, pass);

            ResultSet resultSet = ps.executeQuery();
            resultSet.next();

            user.setLogin(log);
            user.setPassword(pass);
            user.setId(resultSet.getInt("id"));
            user.setEmail(resultSet.getString("email"));
            user.setBalance(resultSet.getDouble("balance"));
            user.setCreated(resultSet.getDate("created").toLocalDate());
            user.setUpdated(resultSet.getDate("updated").toLocalDate());


            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
