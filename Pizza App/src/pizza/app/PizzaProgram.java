package pizza.app;

/**
 *
 * Pizza Program is a static class with one static method: start.
 * This class is designed to run the intended Pizza App.
 */
public final class PizzaProgram {
    
    private PizzaProgram() {}
    
    public static void start()
    {
        int menuSelect;
        //Add logic here for flow of control between prompts/user input
        menuSelect = PromptInput.home();
        
        if (menuSelect == 1)
        {
            PizzaOrder pizzaOrder = new PizzaOrder();
            Order order = new Order();
            String crust = PromptInput.orderCrust();
            pizzaOrder.setCrust(crust);
        }
        
        if (menuSelect == 2)
        {
            PromptInput.ViewOrders();
        }
            
    }
    
}
