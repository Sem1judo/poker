package Poker.dao;

import Poker.jdbc.util.DataBase;

import java.sql.*;
import java.util.List;

public abstract class AbstractDao<K> implements GenerickDao<K> {
    public abstract String getCreateQuery();

    public abstract String getSelectQuery();

    public abstract String getDeleteQuery();

    public abstract String getUpdateQuery();

    public abstract String getSelectByQuery();

    protected abstract String getSelectList();

    public abstract void setCSParam(CallableStatement cs);

    public abstract void setCS(CallableStatement cs, K object);

    public abstract void setUpdateCS(CallableStatement cs, K obj);

    public abstract List<K> parseRS(ResultSet resultSet);

    public abstract K getUserFromCS(int id, CallableStatement cs);


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
            String queryId = getSelectByQuery();
            CallableStatement cs = con.prepareCall(queryId);

            cs.setInt(1, id);
            setCSParam(cs);
            cs.execute();

            someObject = getUserFromCS(id, cs);

            cs.close();
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
    public void delete(int id) {
        try {
            Connection con = DataBase.getConnect();
            String deleteRequest = getDeleteQuery();
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
            String updateQuery = getUpdateQuery();
            CallableStatement cs = con.prepareCall(updateQuery);
            setUpdateCS(cs, obj);
            cs.execute();
            cs.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<K> getAll() {
        try {
            Connection con = DataBase.getConnect();
            String addQuery = getSelectQuery();
            Statement st = con.createStatement();
            ResultSet resultSet = st.executeQuery(addQuery);

            List<K> list = parseRS(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
