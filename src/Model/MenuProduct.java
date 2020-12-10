package Model;

import java.util.ArrayList;


public class MenuProduct
extends Product {
    private ArrayList<Product> products;

    public MenuProduct(String name, ArrayList<Product> products) {
        super(name);
        this.products = products;
        super.setSellingPrice(this.calculateSellingPrice());
    }
    public double calculateExpense() {
        double totalPrice = 0.0;
        for (Product product : this.products) {
            totalPrice += product.calculateExpense();
        }
        return totalPrice;
    }
    private double calculateSellingPrice() {
        double totalSellingPrice = 0.0;
        for (Product product : this.products) {
            if (product instanceof MainDish) {
                totalSellingPrice += product.getSellingPrice() * 0.9;
                continue;
            }
            if (product instanceof Beverage) {
                totalSellingPrice += product.getSellingPrice() * 0.5;
                continue;
            }
            if (!(product instanceof Dessert)) continue;
            totalSellingPrice += product.getSellingPrice() * 0.8;
        }
        return totalSellingPrice;
    }
}