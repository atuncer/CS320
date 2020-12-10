package Model;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Model.Beverage;
import Model.Cook;
import Model.Dessert;
import Model.Employee;
import Model.MainDish;
import Model.MenuProduct;
import Model.Order;
import Model.Product;
import Model.Waiter;

public class Restaurant {
    private ArrayList<Employee> employees = new ArrayList();
    private ArrayList<Product> products = new ArrayList();

    public Restaurant() {
        this.initEmployees();
        this.initProducts();
    }

    private void initEmployees() {
        this.addCook("Monica", 100.0);
        this.addWaiter("Ross");
        this.addWaiter("Phobe");
        this.addWaiter("Rachel");
    }

    private void initProducts() {
        this.products.add(new MainDish("Pizza", 6.0, 2.0, 2.0));
        this.products.add(new MainDish("Burger", 5.0, 1.5, 2.0));
        this.products.add(new Beverage("Coke", 2.0, 0.5));
        this.products.add(new Beverage("Lemonade", 2.0, 0.3));
        this.products.add(new Dessert("Tiramusu", 4.0, 1.0, 1.0));
        this.products.add(new Dessert("Cake", 3.0, 0.5, 1.0));
        this.products.add(new Dessert("Ice Cream", 3.0, 0.5, 0.5));
        ArrayList<Product> HGproducts = new ArrayList<Product>();
        HGproducts.add(new MainDish("Pizza", 6.0, 2.0, 2.0));
        HGproducts.add(new Beverage("Coke", 2.0, 0.5));
        HGproducts.add(new Dessert("Tiramusu", 4.0, 1.0, 1.0));
        this.products.add(new MenuProduct("Hunger Games Menu", HGproducts));
        ArrayList<Product> Kidsproducts = new ArrayList<Product>();
        Kidsproducts.add(new MainDish("Burger", 5.0, 1.5, 2.0));
        Kidsproducts.add(new Beverage("Lemonade", 2.0, 0.3));
        Kidsproducts.add(new Dessert("Ice Cream", 3.0, 0.5, 0.5));
        this.products.add(new MenuProduct("Kids Menu", Kidsproducts));
    }

    public void listEmployees() {
        for (Employee emp : this.employees) {
            System.out.println(emp);
        }
    }

    public void addCook(String name, double salary) {
        int id = this.employees.size() + 1;
        this.employees.add(new Cook(id, name, salary));
    }

    public void addWaiter(String name) {
        int id = this.employees.size() + 1;
        this.employees.add(new Waiter(id, name));
    }

    public Waiter assignWaiter() {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(this.employees.size());
        while (!(this.employees.get(randomInt) instanceof Waiter)) {
            randomInt = randomGenerator.nextInt(this.employees.size());
        }
        return (Waiter)this.employees.get(randomInt);
    }

    public double calculateExpenses() {
        double employeeExpenses = 0.0;
        double orderExpenses = 0.0;
        for (Employee emp : this.employees) {
            employeeExpenses += emp.calculateExpense();
        }
        for (Employee emp : this.employees) {
            if (!(emp instanceof Waiter)) continue;
            ArrayList<Order> orders = ((Waiter)emp).getOrdersReceived();
            for (Order ord : orders) {
                ArrayList<Product> products = ord.getOrderedProducts();
                for (Product prd : products) {
                    orderExpenses += prd.calculateExpense();
                }
            }
        }
        return employeeExpenses + orderExpenses;
    }

    public double calculateRevenue() {
        double revenue = 0.0;
        for (Employee emp : this.employees) {
            if (!(emp instanceof Waiter)) continue;
            ArrayList<Order> orders = ((Waiter)emp).getOrdersReceived();
            for (Order ord : orders) {
                revenue += ord.calculateTotalPrice();
            }
        }
        return revenue;
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }

    public ArrayList<Employee> getEmployees() {
        return this.employees;
    }
}