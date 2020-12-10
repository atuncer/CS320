package Model;

import java.util.ArrayList;

import Model.Employee;
import Model.Order;

public class Waiter
extends Employee {
    private double orderRate = 0.1;
    private ArrayList<Order> ordersReceived = new ArrayList();

    public Waiter(int id, String name) {
        super(id, name);
    }


    public double calculateExpense() {
        double expense = 0.0;
        for (Order order : this.ordersReceived) {
            expense += order.calculateTotalPrice() * this.orderRate;
        }
        return expense;
    }

    public void createOrder(Order order) {
        this.ordersReceived.add(order);
    }

    public ArrayList<Order> getOrdersReceived() {
        return this.ordersReceived;
    }
}