
package project1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        while(true) {
            try {
                System.out.println("\nMenu:");
                System.out.println("0. Exit");
                System.out.println("1. Add a seller");
                System.out.println("2. Add a buyer");
                System.out.println("3. Add a product to a seller");
                System.out.println("4. Add a product to a buyer");
                System.out.println("5. Pay for an order for a buyer");
                System.out.println("6. Display details of all buyers");
                System.out.println("7. Display details of all sellers");
                System.out.println("8. Display all products in a specific category");
                System.out.println("9. change the current cart to previous cart");


                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 0:
                        System.out.println("Exiting the program.");
                        System.exit(0);
                    case 1:
                        Manager.addSeller(scanner);
                        break;
                    case 2:
                        Manager.addBuyer(scanner);
                        break;
                    case 3:
                        Manager.addProductToSeller(scanner);
                        break;
                    case 4:
                        Manager.addProductToBuyer(scanner);
                        break;
                    case 5:
                        Manager.payOrderForBuyer(scanner);
                        break;
                    case 6:
                        Manager.displayBuyersHistory();
                        break;
                    case 7:
                        Manager.displaySellers();
                        break;
                    case 8:
                        Manager.displayProductsInCategory(scanner);
                        break;
                    case 9:
                        Manager.changeCart(scanner);
                        break;
                    default:
                        System.out.println("Invalid option, please try again.");
                        break;

                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please try again.");
                scanner.next();
            } catch (Exception e) {
                System.out.println("An error occurred, please try again.");
            }
        }
    }
}
