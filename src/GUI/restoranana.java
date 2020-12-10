package GUI;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Model.Restaurant;

public class restoranana extends JPanel {
	private Restaurant restaurant;
	private restaurantalt bottomPanel;

	public restoranana(Restaurant restaurant) {
		this.restaurant = restaurant;
		this.setLayout(new BoxLayout(this, 1));
		JButton listEmployeesButton = new JButton("List Employees");
		JButton addCookButton = new JButton("Add Cook");
		JButton addWaiterButton = new JButton("Add Waiter");
		JButton calculateExpenses = new JButton("Calculate Expenses");
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(listEmployeesButton);
		buttonsPanel.add(addCookButton);
		buttonsPanel.add(addWaiterButton);
		buttonsPanel.add(calculateExpenses);
		this.add(buttonsPanel);
		this.bottomPanel = new restaurantalt(restaurant);
		this.add(this.bottomPanel);
		listEmployeesButton.addActionListener(e -> this.bottomPanel.showListEmployeesPanel());
		addCookButton.addActionListener(e -> this.bottomPanel.showAddCookPanel());
		addWaiterButton.addActionListener(e -> this.bottomPanel.showAddWaiterPanel());
		calculateExpenses.addActionListener(e -> this.bottomPanel.showCalculateExpensesPanel());
	}
}