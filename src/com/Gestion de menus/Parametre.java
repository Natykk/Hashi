import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Parametre extends JFrame {
    private JPanel panel;
    /*private JComboBox<String> themeBox;
    private ArrayList<String> themes;*/


    public Parametre(){
        
        setTitle("Paramètre");

        panel=new JPanel();
        panel.add(new JLabel("Thèmes: "));
    }
    public JPanel getJPanel(){
        return panel;
    }
}