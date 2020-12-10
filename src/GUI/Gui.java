package GUI;

import java.awt.Dimension;
import javax.swing.*;

import Model.Restaurant;

public class Gui {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
        JFrame frame = new JFrame("Project");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(650, 500);
        frame.setMinimumSize(new Dimension(650, 500));
        JTabbedPane tabbedPane = new JTabbedPane();
        siprarispanel orderPanel = new siprarispanel(restaurant);
        tabbedPane.add("Order", orderPanel);
        restoranana restaurantPanel = new restoranana(restaurant);
        tabbedPane.add("Restaurant", restaurantPanel);
        frame.add(tabbedPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}