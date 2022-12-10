package pizza.app;

import java.sql.*;
import io.bretty.console.table.*;

/**
 * Main method and starting point of program.
 */
public class Program {
    
    // Main Method
    public static void main(String[] args) throws SQLException {
        // Establish connection to Database
        new DatabaseService("localhost", "5432", "finalproject", "postgres", "admin");
        
       ResultSet rs = DatabaseService.GET("SELECT * FROM toppings;");
    
       rs.next();
       
       String[] headers = {"Crust Type"};
       String[][] data = { {rs.getString(1)} };
       ColumnFormatter<String> cf = ColumnFormatter.text(Alignment.CENTER, 16);
       
       Table table = Table.of(headers, data, cf);
       System.out.println(table);
    }
}
