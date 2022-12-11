package pizza.app;

import java.sql.*;
import io.bretty.console.table.Table;

/**
 * Main method and starting point of program.
 */
public class Program {
    
    // Main Method
    public static void main(String[] args) {
        new DatabaseService("localhost", "5432", "finalproject", "postgres", "admin");
        
        StringBuilder queryString = new StringBuilder();
        queryString.append("SELECT o.orderid, e.firstname first, e.lastname last FROM orders o ")
                .append("INNER JOIN employee e ON o.chefid = e.employeeid ")
                .append("ORDER BY e.lastname");
        
        Table queryTable = DatabaseService.GETAsPrintableTable(queryString.toString());
        
        System.out.println(queryTable);
    }
}
