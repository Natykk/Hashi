import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class MainTest {
    public static void main(String[] args) {
        ArrayList<Ile> iles = new ArrayList<>();
        ArrayList<Pont> ponts = new ArrayList<>();

        Ile ile1 = new Ile(1, 50, 50, 20, Color.BLUE);
        Ile ile2 = new Ile(2, 150, 150, 20,Color.BLUE);
        Pont pont = new Pont(ile1, ile2, 1);

        iles.add(ile1);
        iles.add(ile2);
        ponts.add(pont);

        JFrame frame = new JFrame("Iles et Ponts");
        CustomPanel customPanel = new CustomPanel(iles, ponts);
        frame.add(customPanel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}