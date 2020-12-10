package Model;
import Model.Expense;
public abstract class Product implements Expense {
	private String name;
	private double purchasePrice;
	private double sellingPrice;
	private double utilityCost;

	public Product(String name, double sellingPrice, double purchasePrice, double utilityCost) {
		this.name = name;
		this.sellingPrice = sellingPrice;
		this.purchasePrice = purchasePrice;
		this.utilityCost = utilityCost;
	}

	public Product(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double price) {
		this.sellingPrice = price;
	}

	public double getUtilityCost() {
		return utilityCost;
	}

	public String toString() {
		return name;
	}
}
