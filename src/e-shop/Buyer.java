package project1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Buyer extends User implements Comparable<Buyer> {
    private String username;
    private String password;
    private String address;
    private ShoppingCart cart;
    private List<ShoppingCart> preCarts; // Use a List for dynamic cart history

    public Buyer(String username, String password, String address) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.cart = new ShoppingCart(new HashSet<>(), 0); // Initialize the current cart
        this.preCarts = new ArrayList<>(); // Use ArrayList for previous carts
    }

    public void addToCart(Product p) {
        cart.addProductToCart(p); // Directly add the product to the cart
    }

    public void saveCart() {
        preCarts.add(cart); // Add the current cart to the list
        cart = new ShoppingCart(new HashSet<>(), 0); // Create a new empty cart
    }



    public Float getTotalCart() {
        return cart.getCartSum();
    }

    public ShoppingCart getSpecificPreCart(int number) {
        if (number >= 0 && number < preCarts.size()) {
            return preCarts.get(number);
        }
        return null; // Return null if the index is out of bounds
    }

    @Override
    public int compareTo(Buyer b1) {
        return this.username.compareTo(b1.username);
    }

    @Override
    public String toString() {
        return username;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public List<ShoppingCart> getPreCarts() {
        return preCarts;
    }

    public void setPreCarts(List<ShoppingCart> preCarts) {
        this.preCarts = preCarts;
    }
}
