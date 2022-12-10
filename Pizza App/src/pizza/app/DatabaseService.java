package pizza.app;

import java.sql.*;

/**
 * Connects to a database and is to be used statically.
 */
public class DatabaseService {
    private String _URL,
            _user,
            _pass;
    
    private static Connection _connection;
    
    /**
     * Connects to the database and stores its connection statically.
     * @param host
     * @param port
     * @param db
     * @param user
     * @param pass 
     */
    public DatabaseService(String host, String port, String db, String user, String pass) {
        _URL = "jdbc:postgresql://" + host + ":" + port + "/"+db;
        _user = user;
        _pass = pass;
        
        try {
            _connection = DriverManager.getConnection(_URL,_user,_pass);
        } catch (SQLException e) { e.printStackTrace(); }
    }
    
    /**
     * Handles GET requests from the database.
     * @param query
     * @throws SQLException 
     */
    public static ResultSet GET(String query) {
        try {
            Statement statement = _connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            
            return resultSet;
        } catch (SQLException e) { e.printStackTrace(); }
        
        return null;
    }
    
}
