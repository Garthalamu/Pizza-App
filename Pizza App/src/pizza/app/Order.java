package pizza.app;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * Class to hold the user's selected options for their order
 */
public class Order {
    
    private ArrayList<PizzaOrder> pizzas;
    
    private String firstname;
    private String lastname;
    private String street;
    private String city;
    private String addressState;
    private String zip;
    private String paymentType;
    private BigInteger creditCard;
    
    
    public ArrayList<PizzaOrder> getPizzas() { return pizzas; }
    
    public String getFirstName() { return firstname; }
    public String getLastName() { return lastname; }
    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getAddressState() { return addressState; }
    public String getZip() { return zip; }
    public String getPaymentType() { return paymentType; }
    public BigInteger getCreditCard() { return creditCard; }
    
    
    public void addPizza(PizzaOrder pizza) { this.pizzas.add(pizza); }
    
    public void setFirstName(String firstname) { this.firstname = firstname; }
    public void setLastName(String lastname) { this.lastname = lastname; }
    public void setStreet(String street) { this.street = street; }
    public void setCity(String city) { this.city = city; }
    public void setAddressState(String addressState) { this.addressState = addressState; }
    public void setZip(String zip) { this.zip = zip; }
    public void setPaymentType(String paymentType) { this.paymentType = paymentType; }
    public void setCreditCard(BigInteger creditCard) { this.creditCard = creditCard; }
}
