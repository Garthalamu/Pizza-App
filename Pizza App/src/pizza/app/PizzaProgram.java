package pizza.app;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Pizza Program is a static class with one static method: start.
 * This class is designed to run the intended Pizza App.
 */
public final class PizzaProgram {
    
    private PizzaProgram() {}
    
    public static void start()
    {
        while (true)
        {
            int menuSelect;

            menuSelect = PromptInput.home();

            if (menuSelect == 1)
            {
                PizzaOrder pizzaOrder = new PizzaOrder();
                Order order = new Order();

                //Get crust selection
                String crust = PromptInput.orderCrust();
                pizzaOrder.setCrust(crust);

                //Get cheese selection
                String cheese = PromptInput.orderCheese();
                pizzaOrder.setCheese(cheese);

                //boolean requestMoreToppings = true;
                //Get toppings
                //while (requestMoreToppings == true)
                //{
                    String topping = PromptInput.orderTopping();
                    pizzaOrder.addTopping(topping);

                    //requestMoreToppings = PromptInput.requestMoreToppings();
                //}
                
                //Get customer info
                order = PromptInput.customerInfo();
                
                // SQL INSERT QUERY
                //Insert new customer after checking to see if there isn't already a customer
                int custid = 0;
                
                boolean alreadyCustomer = false;
                ArrayList<ArrayList<String>> customers = DatabaseService.GETAsMultiArray("SELECT firstname, lastname, customerid FROM customer");
                for (ArrayList<String> i : customers) {
                    if (i.get(0).contains(order.getFirstName()) && i.get(1).contains(order.getLastName())) {
                        alreadyCustomer = true;
                        custid = Integer.parseInt(i.get(2));
                    }
                }
                
                if (!alreadyCustomer) {
                    custid = Integer.parseInt(DatabaseService.GETAsMultiArray("SELECT MAX(customerid) FROM customer").get(0).get(0)) + 1;
                    DatabaseService.POST("INSERT INTO customer VALUES "
                            + "(" + custid + ", '" + order.getFirstName() + "', '"+order.getLastName()+"', "
                                    + "'"+ order.getStreet() +"', '"+ order.getCity() +"', "
                                    + "'"+ order.getAddressState() +"', '"+ order.getZip() +"', "
                                    + "'"+ order.getPaymentType() +"', "+ order.getCreditCard() +")"
                    );
                }
                
                // get a random chef and driver
                ArrayList<String> chefids = DatabaseService.GETAsMultiArray("SELECT employeeid FROM employee WHERE title='Cook'").get(0);
                ArrayList<String> driverids = DatabaseService.GETAsMultiArray("SELECT employeeid FROM employee WHERE title='Driver'").get(0);
                // get random id's
                int chefid, driverid;
                Random rand = new Random();
                chefid = Integer.parseInt(chefids.get(rand.nextInt(chefids.size())));
                driverid = Integer.parseInt(driverids.get(rand.nextInt(driverids.size())));
                
                int orderid = Integer.parseInt(DatabaseService.GETAsMultiArray("SELECT MAX(orderid) FROM orders").get(0).get(0))+1;
                
                DatabaseService.POST(
                        "INSERT INTO orders VALUES "
                            + "("+orderid+", "+chefid+", "+driverid+", "+custid+")"
                );
                
                DatabaseService.POST(
                        "INSERT INTO pizza_order VALUES "
                            + "("+orderid+", "+1+", "+1+", '"+pizzaOrder.getCrust()+"', '"+pizzaOrder.getCheese()+"')"
                );
                
                DatabaseService.POST(
                        "INSERT INTO pizza_order_toppings VALUES "
                            + "("+orderid+", "+1+", '"+pizzaOrder.getToppings().get(0)+"')"
                );
                
                System.out.println("***Order has been added to database, returning to main menu.***");
            }

            if (menuSelect == 2)
            {
                PromptInput.ViewOrders();
            }

        }
    }
    
}
