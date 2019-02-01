package stason.dev.domain;

import java.util.Scanner;

public class Product {
    private Scanner scanner = new Scanner(System.in);
    private int id;
    private String productName;
    private int quantityProduct;
    private int price;

    public Product() {
        this.id = 0;
        this.productName = "Product";
        this.quantityProduct = 0;
        this.price = 0;
    }

    public Product(int id, String productName, int quantityProduct, int price) {
        this.id = id;
        this.productName = productName;
        this.quantityProduct = quantityProduct;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setId() {
        System.out.println("Please enter id = ");
        this.id = scanner.nextInt();
    }


    public String getProductName() {
        return productName;
    }


    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setProductName() {
        System.out.println("Please enter ProductName = ");
        this.productName = scanner.next();
    }
    public int getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(int quantityProduct) {
        this.quantityProduct = quantityProduct;
    }
    public void setQuantityProduct() {
        System.out.println("Please enter QuantityProduct = ");
        this.quantityProduct = scanner.nextInt();
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public void setPrice() {
        System.out.println("Please enter price = ");
        this.price = scanner.nextInt();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", quantityProduct=" + quantityProduct +
                ", price=" + price +
                '}';
    }
}
