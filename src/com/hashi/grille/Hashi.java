import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Hashi extends JFrame implements ActionListener {

    private JPanel panneauPrincipal;
    private JButton[][] boutons;
    private JLabel lblTemps, lblAide, lblVerification, lblRecommencer, lblRevenirMenu;
    private JTextField txtTemps;
    private int temps = 0;
    private Timer timer;
    private Grille grilleJeu;

    public Hashi() throws IOException {
        setTitle("Hashi");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panneauPrincipal = new JPanel();
        panneauPrincipal.setLayout(new GridLayout(10, 10));
        add(panneauPrincipal, BorderLayout.CENTER);

        boutons = new JButton[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                boutons[i][j] = new JButton();
                boutons[i][j].setBackground(Color.WHITE);
                boutons[i][j].addActionListener(this);
                panneauPrincipal.add(boutons[i][j]);
            }
        }

        lblTemps = new JLabel("Temps :");
        txtTemps = new JTextField(5);
        txtTemps.setEditable(false);
        lblAide = new JLabel("Aide");
        lblVerification = new JLabel("Vérification");
        lblRecommencer = new JLabel("Recommencer");
        lblRevenirMenu = new JLabel("Revenir au menu");

        JPanel panneauBoutons = new JPanel();
        panneauBoutons.setLayout(new BoxLayout(panneauBoutons, BoxLayout.Y_AXIS));
        panneauBoutons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout d'un JLabel vide pour le titre
        panneauBoutons.add(new JLabel(" "));

        // Création d'un JPanel pour chaque bouton et centrage du contenu
        JPanel panneauTemps = new JPanel();
        panneauTemps.setLayout(new BorderLayout());
        panneauTemps.setPreferredSize(new Dimension(100, 25)); // **Réduction de la taille**
        panneauTemps.add(lblTemps, BorderLayout.WEST);
        panneauTemps.add(txtTemps, BorderLayout.EAST);

        JPanel panneauAide = new JPanel();
        panneauAide.add(lblAide);

        JPanel panneauVerification = new JPanel();
        panneauVerification.add(lblVerification);

        JPanel panneauRecommencer = new JPanel();
        panneauRecommencer.add(lblRecommencer);

        JPanel panneauRevenirMenu = new JPanel();
        panneauRevenirMenu.add(lblRevenirMenu);

        // Ajout des panneaux avec un espace vertical entre chaque
        panneauBoutons.add(panneauTemps);
        panneauBoutons.add(Box.createVerticalStrut(10));
        panneauBoutons.add(panneauAide);
        panneauBoutons.add(Box.createVerticalStrut(5));
        panneauBoutons.add(panneauVerification);
        panneauBoutons.add(Box.createVerticalStrut(5));
        panneauBoutons.add(panneauRecommencer);
        panneauBoutons.add(Box.createVerticalStrut(5));
        panneauBoutons.add(panneauRevenirMenu);

        add(panneauBoutons, BorderLayout.WEST);

        timer = new Timer(1000, this);
        timer.start();

        Jeu jeu = new Jeu();
        jeu.genererGrilleDepuisFichier("grille.txt");
        this.grilleJeu = jeu.listeGrille.get(0); // Commencer avec la première grille

        // Affichage initial de la grille
        for (int i = 0; i < grilleJeu.getTaille(); i++) {
            for (int j = 0; j < grilleJeu.getTaille(); j++) {
                Case caseJeu = grilleJeu.getCase(i, j);
                if (caseJeu != null) {
                    boutons[i][j].setText(caseJeu.afficher());
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            // Gestion des clics sur les boutons
            JButton bouton = (JButton) e.getSource();
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (bouton == boutons[i][j]) {
                        // Gestion du clic sur le bouton
                        // ...
                    }
                }
            }
        } else if (e.getSource() == timer) {
            // Gestion du timer
            temps++;
            txtTemps.setText(temps + "");
        }
    }

    public static void main(String[] args) throws IOException {
        Hashi fenetre = new Hashi();
        fenetre.setVisible(true);
    }
}