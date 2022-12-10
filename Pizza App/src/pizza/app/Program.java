package pizza.app;

import java.sql.*;
import io.bretty.console.table.Table;

/**
 * Main method and starting point of program.
 */
public class Program {
    
    // Main Method
    public static void main(String[] args) {
        new DatabaseService("localhost", "5432", "postgres", "postgres", "root");
        
        Table query = DatabaseService.GETAsPrintableTable("SELECT employeeid as id, title FROM employee");
        
        System.out.println(query);
    }
}
