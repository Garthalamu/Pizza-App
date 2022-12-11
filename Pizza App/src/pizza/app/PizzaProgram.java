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

                boolean requestMoreToppings = true;
                //Get toppings
                while (requestMoreToppings == true)
                {
                    String topping = PromptInput.orderTopping();
                    pizzaOrder.addTopping(topping);

                    requestMoreToppings = PromptInput.requestMoreToppings();
                }
                
                //Get customer info
                order = PromptInput.customerInfo();
                
                //EXECUTE NECESSARY INSERTS HERE!!!
                System.out.println("***Order has been added to database, returning to main menu.***");
            }

            if (menuSelect == 2)
            {
                PromptInput.ViewOrders();
            }

        }
    }
    
}
