import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
public class MainTest {
    public static void main(String[] args) {
        ArrayList<Ile> iles = new ArrayList<>();
        ArrayList<Pont> ponts = new ArrayList<>();

        Ile ile1 = new Ile(1, 50, 50, 30, Color.BLUE);
        Ile ile2 = new Ile(2, 150, 150, 30,Color.BLUE);
        Ile ile3 = new Ile(3, 200, 150, 30,Color.BLUE);
        Ile ile4 = new Ile(2, 50, 150, 30,Color.BLUE);
        Ile ile5 = new Ile(4, 150, 200, 30,Color.BLUE);


        iles.add(ile1);
        iles.add(ile2);
        iles.add(ile3);
        iles.add(ile4);
        iles.add(ile5);


        JFrame frame = new JFrame("Iles et Ponts");
        CustomPanel customPanel = new CustomPanel(iles, ponts);
        frame.add(customPanel);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}