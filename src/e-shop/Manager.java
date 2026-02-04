package project1;

import java.util.*;

public class Manager {


    static Map<String, Seller> sellers = new HashMap<>();
    static Map<String, Buyer> buyers = new HashMap<>();
    static int numOfSeller = 0;
    static int numOfBuyer = 0;
    static SellerConsoleView view = new SellerConsoleView();
    static BuyerConsole buyerView = new BuyerConsole();


    public static void addSeller(Scanner scanner) {
        String username;
        while (true) {
            System.out.print("Enter the username of the seller: ");
            try {
                username = scanner.nextLine();
                if (username.trim().isEmpty()) {
                    throw new Exception("Username cannot be empty.");
                } else if (sellers.containsKey(username)) {
                    throw new Exception("Username already exists. Please choose a new name.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        String password;
        while (true) {
            System.out.print("Enter the password: ");
            try {
                password = scanner.nextLine();
                if (password.trim().isEmpty()) {
                    throw new Exception("Password cannot be empty.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Seller " + username + " added successfully.");
        sellers.put(username, new Seller(username, password));
        numOfSeller++;
    }


    static void addBuyer(Scanner scanner) {
        String username;
        while (true) {
            System.out.print("Enter the username of the buyer: ");
            username = scanner.nextLine();
            try {
                if (buyers.containsKey(username)) {
                    throw new Exception("Username already exists. Please choose a new name.");
                } else if (username.trim().isEmpty()) {
                    throw new Exception("Username cannot be empty.");
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        String password;
        while (true) {
            System.out.print("Enter the password: ");
            password = scanner.nextLine();
            try {
                if (password.trim().isEmpty()) {
                    throw new Exception("Password cannot be empty.");
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        String address;
        while (true) {
            System.out.print("Enter the address: ");
            address = scanner.nextLine();
            try {
                if (address.trim().isEmpty()) {
                    throw new Exception("Address cannot be empty.");
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        buyers.put(username, new Buyer(username, password, address));
        numOfBuyer++;

        System.out.println("Buyer " + username + " added successfully.");
    }


    public static void addProductToSeller(Scanner scanner) {
        if (sellers.isEmpty()) {
            System.out.println("No sellers available.");
            return;
        }

        System.out.println("Available sellers:");
        for (String username : sellers.keySet()) {
            System.out.println("- " + username);
        }

        Seller seller;
        while (true) {
            System.out.print("Enter seller username: ");
            String sellerUsername = scanner.next();
            seller = sellers.get(sellerUsername);
            if (seller != null) break;
            System.out.println("Seller not found. Try again.");
        }

        System.out.print("Enter the item name: ");
        String productName = scanner.next();

        String category;
        Category cat;
        while (true) {
            try {
                System.out.print("Enter the item category (KIDS, OFFICE, ELECTRONICS, CLOTHING): ");
                category = scanner.next();
                 cat = Category.valueOf(category.toUpperCase());
                if (!categoryExist(category.toUpperCase())) {
                    throw new Exception("Category doesn't exist, please enter another category.");
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        float price;
        while (true) {
            try {
                System.out.print("Enter the item price: ");
                price = scanner.nextFloat();
                if (price <= 0) {
                    throw new Exception("Price must be greater than zero.");
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please try again.");
                scanner.next();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.next();
            }
        }

        float totalPrice = price;

        System.out.print("Do you want a special package? (yes or no): ");
        String choice = scanner.next();
        if (choice.equalsIgnoreCase("yes")) {
            while (true) {
                try {
                    System.out.print("Enter the price for the special package: ");
                    float packagePrice = scanner.nextFloat();
                    if (packagePrice <= 0) {
                        throw new Exception("Package price must be greater than zero.");
                }
                    totalPrice += packagePrice;
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input, please try again.");
                    scanner.next();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    scanner.next();
                }
            }
        }
        Product product = cat.create(productName, totalPrice);
        seller.addProduct(productName, totalPrice, Category.valueOf(category.toUpperCase()));

        System.out.printf("Product %s priced at %.2f added to seller %s\n",
                productName, totalPrice, seller.getUsername());
    }


    public static boolean categoryExist(String category) {
        for (Category c : Category.values()) {
            if (c.name().equalsIgnoreCase(category)) {
                return true;
            }
        }
        return false;
    }


    public static void addProductToBuyer(Scanner scanner) {
        if (buyers.isEmpty()) {
            System.out.println("No buyers available.");
            return;
        }
        if (sellers.isEmpty()) {
            System.out.println("No sellers available.");
            return;
        }

        System.out.println("Available buyers:");
        for (String username : buyers.keySet()) {
            System.out.println("- " + username);
        }

        String buyerUsername;
        Buyer buyer;
        while (true) {
            System.out.print("Enter buyer username: ");
            buyerUsername = scanner.next();
            buyer = buyers.get(buyerUsername);
            if (buyer != null) break;
            System.out.println("Buyer not found. Try again.");
        }

        System.out.println("Available sellers:");
        for (String username : sellers.keySet()) {
            System.out.println("- " + username);
        }

        String sellerUsername;
        Seller seller;
        while (true) {
            System.out.print("Enter seller username: ");
            sellerUsername = scanner.next();
            seller = sellers.get(sellerUsername);
            if (seller != null) break;
            System.out.println("Seller not found. Try again.");
        }

        view.displayProducts(seller.getProducts());

        while (true) {
            try {
                System.out.print("Enter product number to add to cart: ");
                int productNum = scanner.nextInt();
                if (productNum < 1 || productNum > seller.getProducts().length) {
                    throw new Exception("Invalid product number.");
                }
                buyer.addToCart(seller.getSpecificProduct(productNum - 1));
                System.out.println("Product added to cart for " + buyer.getUsername());
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again.");
                scanner.next();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.next();
            }
        }
    }


    public static void payOrderForBuyer(Scanner scanner) {
        if (buyers == null || buyers.isEmpty()) {
            System.out.println("No buyer available.");
            return;
        }

        String buyerName;
        while (true) {
            try {
                System.out.print("Choose the buyer for who you want to pay: ");
                displayBuyers();
                buyerName = scanner.next();
                if (!buyers.containsKey(buyerName)) {
                    throw new Exception("Invalid buyer.");
                } else if (buyers.get(buyerName).getCart() == null) {
                    throw new Exception("There are no products in cart.");
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please try again.");
                scanner.next();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.next();
            }
        }

        Buyer selectedBuyer = buyers.get(buyerName);
        System.out.println("Payment for buyer " + selectedBuyer.getUsername() + " processed for: " + selectedBuyer.getTotalCart());
        selectedBuyer.saveCart();
    }


    public static void changeCart(Scanner scanner) {
        if (buyers == null || buyers.isEmpty()) {
            System.out.println("There are no buyers.");
            return;
        }

        String buyerName;
        while (true) {
            try {
                System.out.println("Enter the buyer name: ");
                displayBuyers();
                buyerName = scanner.next();
                if (!buyers.containsKey(buyerName)) {
                    throw new Exception("Invalid buyer number.");
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please try again.");
                scanner.next();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.next();
            }
        }

        Buyer selectedBuyer = buyers.get(buyerName);
        if (selectedBuyer.getPreCarts() == null || selectedBuyer.getPreCarts().size() == 0) {
            System.out.println("There are no previous carts.");
        } else {
            for (int i = 0; i < selectedBuyer.getPreCarts().size(); i++) {
                System.out.println((i + 1) + ") ");
                if (selectedBuyer.getSpecificPreCart(i) != null) {
                    for (int j = 0; j < selectedBuyer.getSpecificPreCart(i).getProducts().size(); j++) {
                        System.out.println(selectedBuyer.getSpecificPreCart(i).getProducts() + ", ");
                    }
                }
            }

            scanner.nextLine();

            if (selectedBuyer.getCart() != null) {
                System.out.println("Do you want to replace your current cart (yes/no): ");
                String choice = scanner.nextLine().toUpperCase();
                while (!choice.equals("YES") && !choice.equals("NO")) {
                    System.out.println("Invalid choice, try again.");
                    choice = scanner.nextLine().toUpperCase();
                }
                if (choice.equals("YES")) {
                    replacingProgress(scanner, selectedBuyer);
                } else {
                    System.out.println("The cart will not be replaced.");
                }
            } else {
                replacingProgress(scanner, selectedBuyer);
            }
        }
    }


    public static void replacingProgress(Scanner scanner, Buyer selectedBuyer) {
        while (true) {
            try {
                System.out.println("Enter the cart number that you want to replace with: ");
                int cartNum = scanner.nextInt();
                if (cartNum < 1 || cartNum > selectedBuyer.getPreCarts().size()) {
                    throw new Exception("Invalid cart number.");
                }
                ShoppingCart preCart = selectedBuyer.getSpecificPreCart(cartNum - 1);
                if (preCart != null) {
                    selectedBuyer.setCart(preCart);
                    System.out.println("The cart was replaced.");
                } else {
                    System.out.println("There is no cart with this number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please try again.");
                scanner.next();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.next();
            }
            break;
        }
    }


    public static void displayBuyers() {
        if (buyers == null || buyers.isEmpty()) {
            System.out.println("No buyers.");
        } else {
            int i = 1;
            System.out.println("List of all buyers:");

            for (Buyer buyer : buyers.values()) {
                if (buyer != null) {
                    System.out.println(i + ") " + buyer.getUsername());
                    i++;
                }
            }
        }
    }


    public static void displayBuyersHistory() {
        if (buyers == null || buyers.isEmpty()) {
            System.out.println("No buyers.");
            return;
        }

        List<Buyer> buyerList = new ArrayList<>(buyers.values());
        buyerList.sort(Comparator.comparing(Buyer::getUsername));

        System.out.println("List of all buyers:");

        for (int i = 0; i < buyerList.size(); i++) {
            Buyer buyer = buyerList.get(i);
            if (buyer != null) {
                System.out.println((i + 1) + ") " + buyer.getUsername());
                if (buyer.getCart() == null || buyer.getCart().getProducts().size() == 0) {
                    System.out.println("There is no cart.");
                } else {
                    int length = buyer.getCart().getProducts().size();
                    System.out.println("The current cart:");

                    for (int j = 0; j < length; j++) {
                        System.out.println(buyer.getCart().getProducts() + " : " + buyer.getCart().getProducts());
                    }
                }
                buyerView.displayPreCarts(buyer.getPreCarts());
            }
        }
    }


    public static void displaySellers() {
            if (sellers == null || sellers.isEmpty()) {
                System.out.println("No sellers.");
                return;
            }

            System.out.println("Sellers sorted by number of products (descending):");
            final int[] i = {1};

            sellers.values().stream()
                    .filter(Objects::nonNull)
                    .sorted((s1, s2) -> Integer.compare(
                            s2.getProducts() != null ? s2.getProducts().length : 0,
                            s1.getProducts() != null ? s1.getProducts().length : 0
                    ))
                    .forEach(seller -> {
                        int productCount = seller.getProducts() != null ? seller.getProducts().length : 0;
                        System.out.println(i[0] + ") " + seller.getUsername() + " - " + productCount + " products");
                        view.displayProducts(seller.getProducts());
                        System.out.println("\n");
                        i[0]++;});
        }


    public static void displayProductsInCategory(Scanner scanner) {
        int count = 1;
        System.out.println("Choose a category name to display the products:");
        for (Category c : Category.values()) {
            System.out.println(count + ") " + c);
            count++;
        }

        while (true) {
            try {
                System.out.print("Enter the category number: ");
                int categoryNum = scanner.nextInt();
                if (categoryNum < 1 || categoryNum > Category.values().length) {
                    throw new Exception("Invalid category number.");
                }

                String categoryName = Category.values()[categoryNum - 1].name();
                count = 1;

                for (Seller seller : sellers.values()) {
                    if (seller != null && seller.getProducts() != null) {
                        Product[] products = seller.getProducts();
                        for (int j = 0; j < products.length; j++) {
                            Product p = seller.getSpecificProduct(j);
                            if (p.getCategoryName().equals(categoryName)) {
                                System.out.println(count + ") " + p.getName() + " (Seller: " + seller.getUsername() + ")");
                                count++;
                            }
                        }
                    }
                }

                if (count == 1) {
                    System.out.println("No products found in this category.");
                }

                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please try again.");
                scanner.next();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.next();
            }
        }
    }


}