package Poker.dao;

import Poker.jdbc.util.DataBase;

import java.sql.*;
import java.util.List;

public abstract class AbstractDao<K> implements GenerickDao<K> {
    public abstract String getCreateQuery();

    public abstract String getSelectQuery();

    public abstract void setCS(CallableStatement cs, K object);

    public abstract List<K> parseRS(ResultSet resultSet);

    public abstract String deleteQuery();
    public abstract String updateQuery();


    @Override
    public K create(K obj) {
        K someObject = null;
        try {
            Connection con = DataBase.getConnect();
            String addQuery = getCreateQuery();
            CallableStatement cs = con.prepareCall(addQuery);
            setCS(cs, obj);
            cs.execute();
            cs.close();

            String queryId = getSelectQuery() + " where id = last_insert_id()";
            Statement st = con.createStatement();
            ResultSet resultSet = st.executeQuery(queryId);
            List<K> list = parseRS(resultSet);
            if (list == null || list.size() != 1)
                throw new Exception("Last player not found");
            someObject = list.get(0);
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return someObject;

    }

    @Override
    public K read(int id) {
        K someObject = null;
        try {
            Connection con = DataBase.getConnect();
            String queryId = getSelectQuery() + " where id";
            PreparedStatement ps = con.prepareStatement(queryId);
            ps.setInt(1, id);

            Statement st = con.createStatement();
            ResultSet resultSet = st.executeQuery(queryId);
            List<K> list = parseRS(resultSet);
            throw new Exception("Player not found");
            someObject = list.get(0);
            con.close();
        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return someObject;
    }

        @Override
        public void delete (int id){
            try {
                Connection con = DataBase.getConnect();
                String deleteRequest = deleteQuery();
                CallableStatement cs = con.prepareCall(deleteRequest);
                cs.setInt(1, id);
                cs.execute();
                cs.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    @Override
    public void update(K obj) {
        try {
            Connection con = DataBase.getConnect();
            String updateQuery = updateQuery();
            CallableStatement cs = con.prepareCall(updateQuery);
            setCS(cs, obj);
            cs.execute();
            cs.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<K> getAll() {
        return null;
    }
}
