package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import GUI.siprarispanel;
import Model.Order;
import Model.Product;
import Model.Restaurant;
import Model.Waiter;

public class siparisalt extends JPanel {

	private DefaultTableModel tableModel;
	private Order currentOrder = new Order();
	private static Restaurant restaurant;
	private JButton finalizeOrderButton;
	Waiter waiter1;

	public siparisalt(siprarispanel ordpanel, Restaurant restaurant) {
		this.restaurant = restaurant;
		this.setLayout(new BorderLayout(2, 2));
		Object[] columnNames = new Object[] { "Product Name", "Count", "Price" };
		this.tableModel = new DefaultTableModel(columnNames, 0);
		JTable table = new JTable(this.tableModel);
		JScrollPane rightScrollPane = new JScrollPane(table);
		this.add(rightScrollPane);
		JPanel panell = new JPanel(new FlowLayout(2));
		this.finalizeOrderButton = new JButton("Finalize");
		panell.add(this.finalizeOrderButton);
		this.add((Component) panell, "South");
		this.finalizeOrderButton.addActionListener(e -> {
			this.waiter1.createOrder(this.currentOrder);
			PopupMessage.info("Your order is completed.\nTotal price is "
					+ Converter.converter(this.currentOrder.calculateTotalPrice()));
			ordpanel.clearPanel();
		});
	}

	public void setAssignedWaiter(Waiter assignedWaiter) {
		this.waiter1 = assignedWaiter;
	}

	public void addProduct(Product selectedItem, int count) {
		this.finalizeOrderButton.setEnabled(true);
		for (int i = 0; i < count; ++i) {
			this.currentOrder.addProduct(selectedItem);
		}
		Object[] rowData = new Object[] { selectedItem.getName(), String.valueOf(count),
				Converter.converter(selectedItem.getSellingPrice() * (double) count) };
		this.tableModel.addRow(rowData);
	}
}