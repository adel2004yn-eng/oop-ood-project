package project1;

import java.util.HashSet;
import java.util.Set;

public class ShoppingCart {
    private Set<Product> products;
    private float cartSum;

    // Constructor that accepts a Set<Product> and an initial cart sum
    public ShoppingCart(Set<Product> products, float cartSum) {
        this.products = (products != null) ? products : new HashSet<>();
        this.cartSum = cartSum;
    }

    // Static method to create an empty shopping cart
    public static ShoppingCart createShoppingCart() {
        return new ShoppingCart(new HashSet<>(), 0);
    }

    // Add a product to the cart
    public void addProductToCart(Product product) {
        if (product != null && products.add(product)) {
            cartSum += product.getPrice();
        }
    }

    // Getter for cart sum
    public float getCartSum() {
        return cartSum;
    }

    // Setter for cart sum
    public void setCartSum(float cartSum) {
        this.cartSum = cartSum;
    }

    // Getter for products in the cart
    public Set<Product> getProducts() {
        return products;
    }

    // Setter for products in the cart
    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    // Override toString to display cart contents
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("ShoppingCart contents:\n");
        int i = 1;
        for (Product product : products) {
            result.append("Product ").append(i++).append(") ").append(product).append("\n");
        }
        result.append("Total: $").append(cartSum);
        return result.toString();
    }
}
