package project1;

import java.util.Random;

public class Product {
    private String name;
    private Float price;
    private Category category;

    public Product(String name , Float price , Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
        generateBarcode();

    }
    public void generateBarcode() {
        Random random = new Random();
        int randomNumber = random.nextInt(10000000) + 1;
        String barcode = String.valueOf(randomNumber);
        while (barcode.length() < 8) {
            barcode = "0" + barcode;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getCategoryName(){
        return category.name();
    }


    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

}
