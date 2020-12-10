package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PopupMessage {
    public static void error(String message) {
        JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void info(String message) {
        JOptionPane.showMessageDialog(new JFrame(), message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}