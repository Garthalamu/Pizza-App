package pizza.app.entity;

import java.util.ArrayList;

/**
 *
 * Class to hold the user's selected options for their pizza order
 */
public class PizzaOrder {
    
    private String crust;
    private String cheese;
    private String topping;
    private int quantity;
    
    
    public String getCrust() { return crust; }
    public String getCheese() { return cheese; }
    public String getTopping() { return topping; }
    public int getQuantity() { return quantity; }
   
   
    public void setCrust(String crust) { this.crust = crust; }
    public void setCheese(String cheese) { this.cheese = cheese; }
    public void addTopping(String topping) { this.topping = topping; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
}
