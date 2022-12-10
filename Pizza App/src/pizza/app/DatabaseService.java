package pizza.app;

import java.util.ArrayList;

import java.sql.*;
import io.bretty.console.table.*;

public class DatabaseService {
    private String _URL,
            _user,
            _pass;
    
    private static Connection _connection;
    
    /**
     * Setups up the connection to the database with the provided information.
     * 
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
     * Will GET information from the database.
     * 
     * @param query
     * @return 
     */
    public static ResultSet GET(String query) {
        try {
            Statement statement = _connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            return resultSet;
        } catch (SQLException e) { e.printStackTrace(); }
        
        return null;
    }
    
    /**
     * Returns a Table object which contains the query and can be directly
     * printed to screen.
     * @param headers
     * @param query
     * @return 
     */
    public static Table GETAsPrintableTable(String query) {
        ColumnFormatter<String> cf = ColumnFormatter.text(Alignment.CENTER, 15);
        
        ResultSet rs = GET(query);
        
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            
            String[] headers = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                headers[i-1] = rsmd.getColumnName(i).toUpperCase();
            }
            
            while (rs.next()) {
                ArrayList<String> row = new ArrayList<String>();
                for (int i = 0; i < columnCount; i++) {
                    row.add(rs.getString(i+1));
                }
                data.add(row);
            }
            
            // Convert Multidimensional ArrayList to Array
            String[][] dataAsArray = new String[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                ArrayList<String> row = data.get(i);
                dataAsArray[i] = row.toArray(new String[row.size()]);
            }
            
            return Table.of(headers, dataAsArray, cf);
            
        } catch (SQLException e) { e.printStackTrace(); }
        
        return null;
    }
    
    /**
     * Posts the query to the database.  Used for INSERT, UPDATE, and DROP
     * queries.
     * @param query
     * @return 
     */
    public static boolean POST(String query) {
        return false;
    }
    
}
