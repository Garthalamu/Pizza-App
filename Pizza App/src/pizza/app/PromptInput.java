
package pizza.app;

import pizza.app.entity.Order;
import java.util.Scanner;
import java.sql.*;
import io.bretty.console.table.*;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * Class provides methods to prompt the user and accept user input in various 
 * scenarios.
 */
public final class PromptInput {
    
    private static DatabaseService dataBaseService = new DatabaseService("localhost", "5432", "postgres", "postgres", "root");
    
    private PromptInput() {}
    
    
    /**
    * @param min minimum int value 
    * @param max max int value
    * @reutrn returns the user's entered int
    * 
    * Scans, validates, and returns the user's input when selecting a numbered menu option.
    */
    private static int getUserSelectedNum(int min, int max)
    {
        int input;
        
        Scanner scan = new Scanner(System.in);

        while (true)
        {
            if (! scan.hasNextInt())
            {
                String garbage;
                garbage = scan.nextLine();
                System.out.println("Please enter a valid number.");
                continue;
            }

            input = scan.nextInt();
            String garbage = scan.nextLine();

            if (input > max || input < min)
            {
                System.out.println("Please enter a valid number.");
            }

            else
            {
                break;
            }
        }
        
        return input;
    }
    
    /**
     * 
     * @return Scans and returns next token entered by user in the console. Returns token as a string.
     * Method does not attempt any input validation.
     */
    private static String getNextToken()
    {
        String input;
        
        Scanner scan = new Scanner(System.in);

        input = scan.next();
        
        return input;
    }
    
    
    /**
     * 
     * @return Scans and returns next big int entered by user in the console. Returns token as a big int.
     * Method does not attempt any input validation.
     */
    private static BigInteger getNextBigInt()
    {
        BigInteger input;
        
        Scanner scan = new Scanner(System.in);

        input = scan.nextBigInteger();
        
        return input;
    }
    
    
    /**
     * 
     * @return Scans and returns next line entered by user in the console. Returns token as a string.
     * Method does not attempt any input validation.
     */
    private static String getNextLine()
    {
        String input;
        
        Scanner scan = new Scanner(System.in);

        input = scan.nextLine();
        
        return input;
    }
    
    
    public static int home()
    {
        System.out.println("Select an option below by entering the relevant number:\n"
                            + "1 -- Place an order\n"
                            + "2 -- View orders\n"
                            + "3 -- View Customers\n");
        int userInput = getUserSelectedNum(1,3);
        return userInput;
    }
    
    
    public static String orderCrust()
    {
        String selectedCrustStr = "";
        
        System.out.println("Select a crust from the table below by typing the number of the crust.");
        Table crustOptionsTable = dataBaseService.GETAsPrintableTable("SELECT ROW_NUMBER() OVER (ORDER BY crustType) AS \"Number\", crustType AS \"Crust Type\" FROM crust;");
        System.out.println(crustOptionsTable);
        
        //Find max valid input num
        int maxInputNum;
        try 
        { 
            ResultSet maxInputNumRS = dataBaseService.GET("SELECT COUNT(*) AS \"Max Number\" FROM crust;");
            maxInputNumRS.next();
            maxInputNum = maxInputNumRS.getInt(1); 
            
            //Get and validate user input
            int userInput = getUserSelectedNum(1,maxInputNum);
            
            //Get the user's selection as a string
            ResultSet selectedCrustRS = dataBaseService.GET("SELECT sub.\"Crust Type\" FROM (SELECT ROW_NUMBER() OVER (ORDER BY crustType) AS \"Number\", crustType AS \"Crust Type\" FROM crust) AS sub WHERE sub.\"Number\" = " + userInput + ";");
            selectedCrustRS.next();
            selectedCrustStr = selectedCrustRS.getString(1);
            System.out.println(selectedCrustStr);
        } 
        catch (SQLException e) { e.printStackTrace(); }
        
        //Return the user's selection
        return selectedCrustStr;
    }
    
    
    public static String orderCheese()
    {
        String selectedCheeseStr = "";
        
        System.out.println("Select a cheese from the table below by typing the number of the cheese.");
        Table cheeseOptionsTable = dataBaseService.GETAsPrintableTable("SELECT ROW_NUMBER() OVER (ORDER BY cheeseType) AS \"Number\", cheeseType AS \"Cheese Type\" FROM cheese;");
        System.out.println(cheeseOptionsTable);
        
        //Find max valid input num
        int maxInputNum;
        try 
        { 
            ResultSet maxInputNumRS = dataBaseService.GET("SELECT COUNT(*) AS \"Max Number\" FROM cheese;");
            maxInputNumRS.next();
            maxInputNum = maxInputNumRS.getInt(1); 
            
            //Get and validate user input
            int userInput = getUserSelectedNum(1,maxInputNum);
            
            //Get the user's selection as a string
            ResultSet selectedCheeseRS = dataBaseService.GET("SELECT sub.\"Cheese Type\" FROM (SELECT ROW_NUMBER() OVER (ORDER BY cheeseType) AS \"Number\", cheeseType AS \"Cheese Type\" FROM cheese) AS sub WHERE sub.\"Number\" = " + userInput + ";");
            selectedCheeseRS.next();
            selectedCheeseStr = selectedCheeseRS.getString(1);
            System.out.println(selectedCheeseStr);
        } 
        catch (SQLException e) { e.printStackTrace(); }
        
        //Return the user's selection
        return selectedCheeseStr;
    }
    
    
    public static String orderTopping()
    {
        String selectedToppingStr = "";
        
        System.out.println("Select a topping from the table below by typing the number of the topping.");
        Table toppingOptionsTable = dataBaseService.GETAsPrintableTable("SELECT ROW_NUMBER() OVER (ORDER BY toppingType) AS \"Number\", toppingType AS \"Topping Type\" FROM toppings;");
        System.out.println(toppingOptionsTable);
        
        //Find max valid input num
        int maxInputNum;
        try 
        { 
            ResultSet maxInputNumRS = dataBaseService.GET("SELECT COUNT(*) AS \"Max Number\" FROM toppings;");
            maxInputNumRS.next();
            maxInputNum = maxInputNumRS.getInt(1); 
            
            //Get and validate user input
            int userInput = getUserSelectedNum(1,maxInputNum);
            
            //Get the user's selection as a string
            ResultSet selectedToppingRS = dataBaseService.GET("SELECT sub.\"Topping Type\" FROM (SELECT ROW_NUMBER() OVER (ORDER BY toppingType) AS \"Number\", toppingType AS \"Topping Type\" FROM toppings) AS sub WHERE sub.\"Number\" = " + userInput + ";");
            selectedToppingRS.next();
            selectedToppingStr = selectedToppingRS.getString(1);
            System.out.println(selectedToppingStr);
        } 
        catch (SQLException e) { e.printStackTrace(); }
        
        //Return the user's selection
        return selectedToppingStr;
    }
    
//    public static boolean requestMoreToppings()
//    {
//        System.out.println("Select an option below by entering the relevant number:\n"
//                            + "1 -- Add another topping\n"
//                            + "2 -- Continue with order\n");
//        int userInput = getUserSelectedNum(1,2);
//        
//        if (userInput == 1)
//            return true;
//        else
//            return false;
//    }
    
    public static void ShowCustomers() {
        System.out.println(DatabaseService.GETAsPrintableTable("SELECT * FROM customer"));
    }
    
    public static void DeleteOrder() {
        String userInput;
        
        System.out.println("\nWould you like to delete an order? [Y/N]");
        userInput = getNextToken();
        
        if (userInput.toLowerCase().contains("y")) {
            System.out.println("Type the orderid you would like deleted.");
            int id = Integer.parseInt(getNextToken());
            DatabaseService.POST(
                    "DELETE FROM pizza_order_toppings WHERE orderid="+id
                  + "; DELETE FROM pizza_order WHERE orderid="+id
                  + "; DELETE FROM orders WHERE orderid="+id
            );
            System.out.println("Deleted orderid " + id + " from database.");
        } else {
            return;
        }
    }
    
    
    public static Order customerInfo()
    {
        Order order = new Order();
        String userInput;
        
        System.out.println("Enter your first name:");
        userInput = getNextToken();
        order.setFirstName(userInput);
        
        System.out.println("Enter your last name:");
        userInput = getNextToken();
        order.setLastName(userInput);
        
        // user already created
        ArrayList<ArrayList<String>> customers = DatabaseService.GETAsMultiArray("SELECT customerid,firstname,lastname FROM customer");
        
        for (ArrayList<String> customer : customers) {
            if (customer.get(1).contains(order.getFirstName()) && customer.get(2).contains(order.getLastName())) {
                System.out.println("Detected customer: "+order.getFirstName()+" "+order.getLastName());
                return order;
            }
        }
        
        System.out.println("Enter your street address, not including city, state, or zip:");
        userInput = getNextLine();
        order.setStreet(userInput);
        
        System.out.println("Enter your city:");
        userInput = getNextLine();
        order.setCity(userInput);
        
        System.out.println("Enter your state as a two character abbreviation:");
        userInput = getNextToken();
        order.setAddressState(userInput);
        
        System.out.println("Enter your zip:");
        userInput = getNextToken();
        order.setZip(userInput);
        
        System.out.println("Enter your payment type (Card or Cash):");
        userInput = getNextLine();
        order.setPaymentType(userInput);
        
        if (order.getPaymentType().toLowerCase().contains("card")) {
            BigInteger userCreditCard;
            System.out.println("Enter your credit card number:");
            userCreditCard = getNextBigInt();
            order.setCreditCard(userCreditCard);
        }
        
        return order;
    }
    
    
    public static void ViewOrders() {
        System.out.println(DatabaseService.GETAsPrintableTable(
                "SELECT po.orderid, po.crusttype \"crust\", " +
                "po.cheesetype \"cheese\", pot.toppingtype \"topping\", " +
                "CONCAT(chef.firstname,' ',chef.lastname) \"chef Name\", " +
                "CONCAT(driver.firstname,' ',driver.lastname) \"driver Name\", " +
                "CONCAT(cust.firstname,' ',cust.lastname) \"customer Name\" " +
                "FROM pizza_order po " +
                "INNER JOIN pizza_order_toppings pot " +
                "ON po.orderid=pot.orderid AND po.itemnum=pot.itemnum " +
                "INNER JOIN orders o ON po.orderid=o.orderid " +
                "INNER JOIN employee chef ON o.chefid=chef.employeeid " +
                "INNER JOIN employee driver ON o.driverid=driver.employeeid " +
                "INNER JOIN customer cust ON o.customerid=cust.customerid " +
                "ORDER BY po.orderid, po.itemnum"
        ));
    }
    
}
