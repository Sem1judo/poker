package stason.dev.jdbc_example;

import stason.dev.domain.*;
import java.sql.*;

    import java.lang.*;

public class ProductDBconnection {
    private static final String USER = "root";
    private static final String PAS = "root";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB = "jdbc:mysql://localhost:3307/onlineShop?autoReconnect=true&useSSL=false";

    private static Connection con = null;

    public static void main(String[] args) {
        Product product = new Product();
        product.setId();
        product.setProductName();
        product.setQuantityProduct();
        product.setPrice();
        addProduct(product);
        readData();




    }
    public static void getConnection() {
        try {
            // step 1(register driver)
            Class.forName(DRIVER);
            // step 2(open connection)
            System.out.println("Connecting to database");
            con = DriverManager.getConnection(DB, USER, PAS);
            System.out.println("Connection done");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void readData() {
        try {
            getConnection();
            Statement statement = con.createStatement();
            String querySelect = "select *from products";

            // step 4 (getting data from table)
            ResultSet resultSet = statement.executeQuery(querySelect);
            // step 5 (show data)
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setProductName(resultSet.getString("productName"));
                product.setQuantityProduct(resultSet.getInt("quantityProduct"));
                product.setPrice(resultSet.getInt("price"));
                System.out.println(product.toString());

            }
            resultSet.close();
            statement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addProduct(Product product) {

        getConnection();
        try {
            Statement statement = con.createStatement();
            String insertQuery = "insert into products(id,productName,quantityProduct,price)values" +
                    "("+product.getId()+",'"+product.getProductName()+"',"+product.getQuantityProduct()+","+product.getPrice()+")";
            statement.executeUpdate(insertQuery);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}

