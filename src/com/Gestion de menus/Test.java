/*import javax.swing.*;

public class Test {
  public static void main(String[] args) {
    String[] optionsToChoose = {"Apple", "Orange", "Banana", "Pineapple", "None of the listed"};

    String getFavFruit = (String) JOptionPane.showInputDialog(null,
        "What fruit do you like the most?", "Choose Fruit", JOptionPane.QUESTION_MESSAGE, null,
        optionsToChoose, optionsToChoose[3]);

    System.out.println("Your chosen fruit: " + getFavFruit);
  }
}*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Test {
  public static void main(String[] args) {
    String[] optionsToChoose = {"Apple", "Orange", "Banana", "Pineapple", "None of the listed"};

    JFrame jFrame = new JFrame();

    JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose);
    jComboBox.setBounds(80, 50, 140, 20);

    JButton jButton = new JButton("Done");
    jButton.setBounds(100, 100, 90, 20);

    JLabel jLabel = new JLabel();
    jLabel.setBounds(90, 100, 400, 100);

    jFrame.add(jButton);
    jFrame.add(jComboBox);
    jFrame.add(jLabel);

    jFrame.setLayout(null);
    jFrame.setSize(350, 250);
    jFrame.setVisible(true);

    jButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String selectedFruit = "You selected " + jComboBox.getItemAt(jComboBox.getSelectedIndex());
        jLabel.setText(selectedFruit);
      }
    });
  }
}

