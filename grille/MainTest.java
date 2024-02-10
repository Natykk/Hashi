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

        Ile ile1 = new Ile(1, 50, 50, 50, Color.YELLOW);
        Ile ile2 = new Ile(2, 150, 150, 50,Color.YELLOW);
        Ile ile3 = new Ile(3, 200, 150, 50,Color.YELLOW);
        Ile ile4 = new Ile(2, 50, 150, 50,Color.YELLOW);
        Ile ile5 = new Ile(4, 150, 200, 50,Color.YELLOW);
        Ile ile6 = new Ile(4, 10, 200, 50,Color.YELLOW);
        Ile ile7 = new Ile(4, 250, 20, 50,Color.YELLOW);


        iles.add(ile1);
        iles.add(ile2);
        iles.add(ile3);
        iles.add(ile4);
        iles.add(ile5);
        iles.add(ile6);
        iles.add(ile7);



        JFrame frame = new JFrame("Iles et Ponts");
        CustomPanel customPanel = new CustomPanel(iles, ponts);
        frame.add(customPanel);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}