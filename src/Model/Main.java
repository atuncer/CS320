package Model;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Order;
import Model.Product;
import Model.Restaurant;
import Model.Waiter;

public class Main {
	static Scanner scanner = new Scanner(System.in);
	static Restaurant restaurant = new Restaurant();

	public static void main(String[] args) {
		System.out.println("Welcome to OZU Restaurant!\n");
		do {
			System.out.println("-------------------------Main Menu-------------------------");
			System.out.println("1: Create Order");
			System.out.println("2: Manage Restaurant");
			System.out.println("3: Exit Program");
			System.out.println("-----------------------------------------------------------");
			System.out.print(">> ");
			String string1 = scanner.nextLine();
			int a = Integer.parseInt(string1);
			if (a == 1) {
				Main.createOrder();

			}
			if (a == 2) {
				Main.manageRestaurant();

			}
			if (a == 3)
				break;
			else {

				System.out.println("Try Again...");
			}
		}

		while (true);
		System.out.println("Bye");
	}

	private static void createOrder() {
		Waiter waiter = restaurant.assignWaiter();
		System.out.println("Hi, I am " + waiter.getName() + ".");
		System.out.println("I will be our waiter today.");
		System.out.println("What would you like to get today?");
		Order order = new Order();

		starter: do {
			System.out.println("-----------------------Create Order-----------------------");
			System.out.println("1: List Order");
			System.out.println("2: Add Product");
			System.out.println("3: Complete Order");
			System.out.println("4: Go back to Main Menu");
			System.out.println("-----------------------------------------------------------");
			System.out.print(">> ");
			String string2 = scanner.nextLine();
			int b = Integer.parseInt(string2);
			if (b == 1) {
				order.listOrder();
				continue;
			}
			else if (b == 2) {
				ArrayList<Product> products = restaurant.getProducts();
				do {
					System.out.println("-----------------------Add Product-----------------------");
					for (int i = 0; i < products.size(); ++i) {
						System.out.println(String.valueOf(i + 1) + ": " + products.get(i).getName());
					}
					System.out.println("-----------------------------------------------------------");
					System.out.print(">> ");
					string2 = scanner.nextLine();
					int productNumber = Integer.parseInt(string2);
					if (productNumber <= products.size() && productNumber > 0) {
						order.addProduct(products.get(productNumber - 1));
						order.listOrder();
						continue starter;
					}
					System.out.println("Try Again...");
				} while (true);
			}
			else if (b==3) {
				waiter.createOrder(order);
				System.out.println("Your order is complete!");
				System.out.println("Returning to Main Menu");
				break;
			}
			else if (b==4) {
				System.out.println("Returning to Main Menu");
				break;
			}
			else{
				System.out.println("Try Again...");
			}

		} while (true);
	}

	private static void manageRestaurant() {
		do {
			System.out.println("---------------------Manage Restaurant---------------------");
			System.out.println("1: List Employees");
			System.out.println("2: Add Employee");
			System.out.println("3: Calculate Expenses");
			System.out.println("4: Calculate Revenue");
			System.out.println("5: Go back to Main Menu");
			System.out.println("-----------------------------------------------------------");
			System.out.print(">> ");
			String string3 = scanner.nextLine();
			int c = Integer.parseInt(string3);
			if (c == 1) {
				restaurant.listEmployees();
				continue;
			}
			else if (c == 2) {
				Main.addEmployee();
				continue;
			}
			else if (c == 3) {
				System.out.println("Total Expense: " + restaurant.calculateExpenses());
				continue;
			}
			else if (c == 4) {
				System.out.println("Total Revenue: " + restaurant.calculateRevenue());
				continue;
			}
			else if (c == 5)
				break;
			else {
				System.out.println("Try Again...");
			}
		} while (true);
		System.out.println("Returning to Main Menu");
	}

	private static void addEmployee() {
		do {
			String emplo;
			System.out.println("---------------------Add Employee---------------------");
			System.out.println("1: Add Cook");
			System.out.println("2: Add Waiter");
			System.out.println("3: Go back to Manage Restaurant Menu");
			System.out.println("-----------------------------------------------------------");
			System.out.print(">> ");
			String string4 = scanner.nextLine();
			int d = Integer.parseInt(string4);
			if (d == 1) {
				System.out.println("Name of the Cook:");
				System.out.print(">> ");
				emplo = scanner.nextLine();
				System.out.println("Salary of the Cook:");
				System.out.print(">> ");
				String salary = scanner.nextLine();
				restaurant.addCook(emplo, Double.parseDouble(salary));
				continue;
			}
			else if (d == 2) {
				System.out.println("Name of the Waiter:");
				System.out.print(">> ");
				emplo = scanner.nextLine();
				restaurant.addWaiter(emplo);
				System.out.println("Returning to Main Menu");
				continue;
			}
			else if (d == 3)
				break;
			else {
				System.out.println("Try Again...");
			}
		} while (true);
		System.out.println("Returning to Manage Restaurant Menu");
	}
}