package pizza.app;

import java.util.ArrayList;

/**
 *
 * Class to hold the user's selected options for their pizza order
 */
public class PizzaOrder {
    
    private String crust;
    private String cheese;
    private ArrayList<String> toppings = new ArrayList<String>();
    private int quantity;
    
    
    public String getCrust() { return crust; }
    public String getCheese() { return cheese; }
    public ArrayList<String> getToppings() { return toppings; }
    public int getQuantity() { return quantity; }
   
   
    public void setCrust(String crust) { this.crust = crust; }
    public void setCheese(String cheese) { this.cheese = cheese; }
    public void addTopping(String topping) { this.toppings.add(topping); }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
}
