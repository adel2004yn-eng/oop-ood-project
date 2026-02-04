package project1;

public class Seller extends User implements Comparable<Seller>{
    private Product[] products;
    private String username;
    private String password;
    private int productLen = 0;

    public Seller(String username , String password) {
        this.username = username;
        this.password = password;
        this.products = new Product[0];
    }

    public void addProduct(String name, float price , Category category) {
        if (productLen == 0) {
            products = new Product[1];
            productLen ++;
        } else {
            Product[] newProducts = new Product[productLen * 2];
            for (int i = 0; i < productLen; i++) {
                newProducts[i] = products[i];
            }
            products = newProducts;
            productLen ++;
        }
        products[productLen - 1] = new Product(name, price, category);
    }

    public Product getSpecificProduct(int number) {
        return products[number];

    }

    public Product[] getProducts() {
        return products;
    }

    public int getProductLen() {
        return productLen;
    }

    public void setProductLen(int productLen) {
        this.productLen = productLen;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return username;
    }

    @Override
    public int compareTo(Seller s1) {
        return Integer.compare(this.productLen, s1.getProductLen());
    }
}
