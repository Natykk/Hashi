import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainTest {
    public static void main(String[] args) {
        ArrayList<Ile2> iles = new ArrayList<>();
        ArrayList<Pont2> ponts = new ArrayList<>();

        Ile2 ile1 = new Ile2(1, 50, 50, 50, Color.YELLOW);
        Ile2 ile2 = new Ile2(2, 150, 150, 50, Color.YELLOW);
        Ile2 ile3 = new Ile2(3, 200, 150, 50, Color.YELLOW);
        Ile2 ile4 = new Ile2(2, 50, 150, 50, Color.YELLOW);
        Ile2 ile5 = new Ile2(4, 150, 200, 50, Color.YELLOW);
        Ile2 ile6 = new Ile2(4, 10, 200, 50, Color.YELLOW);
        Ile2 ile7 = new Ile2(4, 250, 20, 50, Color.YELLOW);

        iles.add(ile1);
        iles.add(ile2);
        iles.add(ile3);
        iles.add(ile4);
        iles.add(ile5);
        iles.add(ile6);
        iles.add(ile7);

        JFrame frame = new JFrame("Iles et Ponts");
        CustomPanel2 customPanel = new CustomPanel2(iles, ponts);
        frame.add(customPanel);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}