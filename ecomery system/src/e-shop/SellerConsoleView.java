package project1;

public class SellerConsoleView {
    public void displayProducts(Product[] products) {
        if(products == null) {
            System.out.println(" there is no products .");
        }
        else if(products.length == 0) {
            System.out.println(" there is no products");}

        else{
            System.out.println("\nthe products :");
            for(int i=0 ; i < products.length ; i++) {
                System.out.println(" " + (i+1) + ". " + products[i].getName() + " price : " + products[i].getPrice());
            }
        }
    }
}
