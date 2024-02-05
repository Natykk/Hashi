import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageManager {

    public static void changerPage(JFrame frame, JPanel nouvellePage) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(nouvellePage);
        frame.revalidate();
        frame.repaint();
    }
 
    public static void afficherMessage(JFrame frame, String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
}
