package com.ediae.netflix.utils;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBManager {

    private static final Dotenv DOTENV = Dotenv.configure().ignoreIfMissing().load();
    private static final String USERNAME = DOTENV.get("DB_USERNAME", "root");
    private static final String PASSWORD = DOTENV.get("DB_PASSWORD", "");
    private static final String URL = DOTENV.get("DB_URL", "jdbc:mysql://localhost:3306/Netflix");
    private static final Connection connection = connect();

    public Connection getConnection() {
        return connection;
    }

    public static Connection connect() {

        try {
            // Create the database connection
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            System.out.println("Connected to the database successfully!");

            return conn;

        } catch (SQLException e) {
            System.err.println("Error while connecting to the database!");
            e.printStackTrace();
        }
        return null;
    }

    public static void disconnect(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection closed successfully.");
            } catch (SQLException e) {
                System.err.println("Error while closing the connection.");
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<String> getTableColumns(String tableName) {
        ArrayList<String> columns = new ArrayList<>();
        Connection conn = connect();
        // return columns;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " LIMIT 1");
            int columnCount = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columns.add(rs.getMetaData().getColumnName(i));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error while fetching columns for table " + tableName);
            e.printStackTrace();
        } finally {
            disconnect(conn);
        }
        return columns;
    }

}

