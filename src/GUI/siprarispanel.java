package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.siparisalt;
import GUI.siprariust;
import Model.Product;
import Model.Restaurant;
import Model.Waiter;

public class siprarispanel extends JPanel {
	private Restaurant restaurant;
	private siprariust topPanel;
	private siparisalt bottomPanel;
	JButton newOrderButton = new JButton("New Order");
	
	public siprarispanel(Restaurant restaurant) {
		this.restaurant = restaurant;
		this.add(this.newOrderButton);
		this.newOrderButton.addActionListener(e -> this.newOrderActionPerformed());
	}

	private void newOrderActionPerformed() {
		this.removeAll();
		this.repaint();
		this.setLayout(new BoxLayout(this, 1));
		JPanel panell = new JPanel(new BorderLayout());
		this.topPanel = new siprariust(this, this.restaurant);
		panell.add(this.topPanel);
		this.add(panell);
		this.bottomPanel = new siparisalt(this, this.restaurant);
		this.add(this.bottomPanel);
		Waiter waiter = this.restaurant.assignWaiter();
		PopupMessage.info("Hi, I am " + waiter.getName() + ".\n What would you like to order?");
		this.bottomPanel.setAssignedWaiter(waiter);
	}

	public void addProduct(Product selectedItem, int count) {
		this.bottomPanel.addProduct(selectedItem, count);
	}

	public void clearPanel() {
		this.removeAll();
		this.setLayout(new FlowLayout());
		this.add(this.newOrderButton);
		this.repaint();
	}
}