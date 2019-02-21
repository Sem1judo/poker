package Poker.dao;

import Poker.jdbc.util.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractDao<K> implements GenerickDao<K> {
    public abstract String getCreateQuery();

    public abstract String getSelectQuery();

    public abstract String getDeleteQuery();

    public abstract String getUpdateQuery();

    public abstract String getSelectByQuery();

    public abstract K getUserFromCS(int id, CallableStatement cs);

    public abstract void setCSParam(CallableStatement cs);

    public abstract void setCS(CallableStatement cs, K object);

    public abstract void setUpdateCS(CallableStatement cs, K obj);

    public abstract List<K> parseRS(ResultSet resultSet);


    @Override
    public K create(K obj) {
        K someObject = null;
        CallableStatement cs = null;
        Statement st = null;
        ResultSet resultSet = null;
        String addQuery = getCreateQuery();
        String queryId = getSelectQuery() + " where id = last_insert_id()";
        try (Connection con = DataBase.getConnect()) {
            cs = con.prepareCall(addQuery);
            setCS(cs, obj);
            cs.execute();
            cs.close();

            st = con.createStatement();
            resultSet = st.executeQuery(queryId);
            List<K> list = parseRS(resultSet);
            if (list == null || list.size() != 1)
                throw new Exception("Last player not found");
            someObject = list.get(0);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cs.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return someObject;

    }

    @Override
    public K read(int id) {
        K someObject = null;
        String queryId = getSelectByQuery();
        CallableStatement cs = null;
        try (Connection con = DataBase.getConnect()) {
            cs = con.prepareCall(queryId);

            cs.setInt(1, id);
            setCSParam(cs);
            cs.execute();

            someObject = getUserFromCS(id, cs);
        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (
                Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return someObject;
    }

    @Override
    public void delete(int id) {
        String deleteRequest = getDeleteQuery();
        CallableStatement cs = null;
        try (Connection con = DataBase.getConnect()) {
            cs = con.prepareCall(deleteRequest);
            cs.setInt(1, id);
            cs.execute();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                cs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void update(K obj) {
        String updateQuery = getUpdateQuery();
        CallableStatement cs = null;
        try (Connection con = DataBase.getConnect()) {
            cs = con.prepareCall(updateQuery);
            setUpdateCS(cs, obj);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                cs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<K> getAll() {
        List<K> list = new ArrayList<>();
        String addQuery = getSelectQuery();
        Statement st = null;
        ResultSet resultSet = null;

        try (Connection con = DataBase.getConnect()) {
            st = con.createStatement();
            resultSet = st.executeQuery(addQuery);
            list = parseRS(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                st.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

}
