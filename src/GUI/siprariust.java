package GUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import GUI.siprarispanel;
import Model.Product;
import Model.Restaurant;

public class siprariust extends JPanel {
	public siprariust(siprarispanel opanel, Restaurant restoran) {
		GridLayout gridLayout = new GridLayout(6, 2);
		gridLayout.setVgap(5);
		this.setLayout(gridLayout);
		this.add(new JLabel("Add Product"));
		this.add(new JLabel());
		this.add(new JLabel("Product :"));
		JComboBox<Product> comboboxx = new JComboBox<Product>();
		comboboxx.setMaximumSize(new Dimension(200, 30));
		comboboxx.addItem(null);;
		for (Product product : restoran.getProducts()) {
			comboboxx.addItem(product);
		}
		this.add(comboboxx);
		this.add(new JLabel("Count :"));
		JSpinner countSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
		countSpinner.setEnabled(false);
		this.add(countSpinner);
		this.add(new JLabel("Price :"));
		JLabel pricelabel = new JLabel("0.0 TL");
		pricelabel.setHorizontalAlignment(JLabel.RIGHT);
		this.add(pricelabel);
		this.add(new JLabel());
		JButton addProductButton = new JButton("Add");
		addProductButton.setEnabled(false);
		this.add(addProductButton);
		comboboxx.addActionListener(e -> {
			Product food = (Product) comboboxx.getSelectedItem();
			this.comboBoxActionPerformed(food, countSpinner,addProductButton);
			countSpinner.setValue(1);
			if (food != null) {
				double price = food.getSellingPrice();
				pricelabel.setText(Converter.converter(price));
			} else {
				pricelabel.setText("0.0 TL");
			}
		});
		addProductButton.addActionListener(e -> {
			Product selectedItem = (Product) comboboxx.getSelectedItem();
			int count = (Integer) countSpinner.getValue();
			opanel.addProduct(selectedItem, count);
		});
	}

	private void comboBoxActionPerformed(Product product,JSpinner spinner,JButton button) {
		if (product == null) {
			spinner.setEnabled(false);
			button.setEnabled(false);
		}
		else {
			spinner.setEnabled(true);
			button.setEnabled(true);
		}
	}
}