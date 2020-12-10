package Model;


import java.util.ArrayList;

import Model.Product;

public class Order {
    private ArrayList<Product> products = new ArrayList();
    public void addProduct(Product product) {
        this.products.add(product);
    }
    public void listOrder() {
        if (this.products.size() == 0) {
            System.out.println("You have not ordered anything yet!");
        } else {
            for (Product prd : this.products) {
                System.out.println(String.valueOf(prd.getName()) + ": " + prd.getSellingPrice());
            }
        }
    }
    public ArrayList<Product> getOrderedProducts() {
        return this.products;
    }
 public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Product prd : this.products) {
            totalPrice += prd.getSellingPrice();
        }
        return totalPrice;
    }
}