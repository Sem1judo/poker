package Poker.jdbc;

import Poker.domain.Player;
import Poker.domain.User;
import Poker.jdbc.util.DataBase;

import java.sql.*;

public class UserCRUD {


    public void createUser (User user) {
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
}
