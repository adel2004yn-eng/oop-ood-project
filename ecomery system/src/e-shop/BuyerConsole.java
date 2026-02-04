package project1;

import java.util.List;

public class BuyerConsole {
    public void displayPreCarts(List<ShoppingCart> preCarts) {
        if (preCarts.isEmpty()) {
            System.out.println("There is no history.");
        } else {
            System.out.println("The cart's history:");
            for (int i = 0; i < preCarts.size(); i++) {
                System.out.println("Cart " + (i + 1) + ":");
                for (Product p : preCarts.get(i).getProducts()) {
                    System.out.println(" - " + p); // Assuming Product has a meaningful toString method
                }
            }
        }
    }
}
