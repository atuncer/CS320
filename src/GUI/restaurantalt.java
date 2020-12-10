package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Cook;
import Model.Employee;
import Model.Restaurant;

public class restaurantalt extends JPanel {
    private Restaurant restaurant;

    public restaurantalt(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.setLayout(new BorderLayout());
    }

    public void showListEmployeesPanel() {
        ArrayList<Employee> emplo = this.restaurant.getEmployees();
        JPanel panel = new JPanel(new GridLayout(emplo.size() + 1, 3));
        JLabel label1 = new JLabel("ID");
        JLabel label2 = new JLabel("Name");
        JLabel label3 = new JLabel("Job");
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        for (Employee employee : emplo) {
            panel.add(new JLabel(String.valueOf(employee.getId())));
            panel.add(new JLabel(employee.getName()));
            if (employee instanceof Cook) {
                panel.add(new JLabel("Cook"));
            } else
                panel.add(new JLabel("Waiter"));
        }
        this.removeAll();
        this.add(panel, "North");
        this.revalidate();
    }

    public void showAddWaiterPanel() {
        JPanel panel = new JPanel();
        GridLayout gridLayout = new GridLayout(3, 2);
        gridLayout.setVgap(5);
        panel.setLayout(gridLayout);
        JTextField name = new JTextField();
        JButton addWaiterButton = new JButton("Add");
        panel.add(new JLabel("Name:"));
        panel.add(name);
        panel.add(new JLabel());
        panel.add(addWaiterButton);
        addWaiterButton.addActionListener(e -> {
            if (name.getText() == null || name.getText().isEmpty()) {
                PopupMessage.error("Error: Enter a name");
                return;
            }
            this.restaurant.addWaiter(name.getText());
            PopupMessage.info("Waiter added successfuly");
        });
        this.removeAll();
        this.add((Component) panel, "North");
        this.revalidate();
        this.repaint();
    }

    public void showAddCookPanel() {
        JPanel panel = new JPanel();
        GridLayout gridLayout = new GridLayout(5, 2);
        gridLayout.setVgap(5);
        panel.setLayout(gridLayout);
        JTextField textname = new JTextField();
        JFormattedTextField salary = new JFormattedTextField(NumberFormat.getNumberInstance());
        JButton addCookButton = new JButton("Add");
        panel.add(new JLabel("Name:"));
        panel.add(textname);
        panel.add(new JLabel("Salary:"));
        panel.add(salary);
        panel.add(new JLabel());
        panel.add(addCookButton);
        addCookButton.addActionListener(e -> {
            if (textname.getText().isEmpty()) {
                PopupMessage.error("Error: Enter a name");
                return;
            }
            if (salary.getText().isEmpty()) {
                PopupMessage.error("Error: Enter a salary");
                return;
            }
            this.restaurant.addCook(textname.getText(), Converter.converter2(salary.getText()));
            PopupMessage.info("Cook added successfuly");
        });
        this.removeAll();
        this.add(panel, "North");
        this.revalidate();
        this.repaint();
    }

    public void showCalculateExpensesPanel() {
        JPanel panel = new JPanel();
        GridLayout gridLayout = new GridLayout(3, 2);
        gridLayout.setVgap(5);
        panel.setLayout(gridLayout);
        double expense = this.restaurant.calculateExpenses();
        double revenue = this.restaurant.calculateRevenue();
        double profit = revenue - expense;
        panel.add(new JLabel("Expenses:"));
        panel.add(new JLabel(Converter.converter(expense)));
        panel.add(new JLabel("Revenue:"));
        panel.add(new JLabel(Converter.converter(revenue)));
        panel.add(new JLabel("Profit:"));
        panel.add(new JLabel(Converter.converter(profit)));
        this.removeAll();
        this.add(panel, "North");
        this.revalidate();
        this.repaint();
    }
}