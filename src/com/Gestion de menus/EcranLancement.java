import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcranLancement extends JFrame {

    private JComboBox<String> utilisateurBox;
    private JPanel panel1, panel2;
    private EcranAcceuil ecranAcceuil;

    public EcranLancement() {
        super("Hashi");

        JButton bouton = new JButton("Valider");

        // Ajout d'une action lorsque le bouton est cliqué
        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String utilisateurChoisi = utilisateurBox.getItemAt(utilisateurBox.getSelectedIndex());
                // Vérifier si "Nouvel utilisateur" est sélectionné
                if (utilisateurChoisi.equals("Nouvel utilisateur")) {
                    changerVersNouvellePage();
                    
                }
                else{
                    System.out.println("Vous avez choisi : " + utilisateurChoisi);
                    PageManager.changerPage(EcranLancement.this, ecranAcceuil.getPanel());
                }
            }
        });

        String[] utilisateurs = {"Meow", "Corazon", "Nouvel utilisateur"};
        utilisateurBox = new JComboBox<>(utilisateurs);
        panel1 = new JPanel();
        panel1.add(utilisateurBox);
        panel1.add(bouton);

        // Initialiser panel2 avec un champ JTextField vide
        panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        ecranAcceuil= new EcranAcceuil();
        add(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);
    }
    //verifier si la page est vidde
    private boolean estPageVide() {
        return panel2.getComponentCount() == 0;
    }
    private void changerVersNouvellePage() {
        // si elle est vide on va creer la page du nouvel utilisateur 
        if (estPageVide()) {
            JTextField nouveauUtilisateurField = new JTextField(15);
            panel2.add(new JLabel("Créer un nouvel utilisateur : "));
            panel2.add(nouveauUtilisateurField);
    
            JButton validerNouveauUtilisateur = new JButton("Valider");
            validerNouveauUtilisateur.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nouvelUtilisateur = nouveauUtilisateurField.getText();
                    System.out.println("Nouvel utilisateur créé : " + nouvelUtilisateur);
                    // Afficher un message de confirmation pour le nouvel utilisateur créé
                    PageManager.afficherMessage(EcranLancement.this, "Nouvel utilisateur créé : " + nouvelUtilisateur);
                    PageManager.changerPage(EcranLancement.this, ecranAcceuil.getPanel());
                }
            });
            panel2.add(validerNouveauUtilisateur);
    
            JButton annuler = new JButton("Annuler");
            annuler.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Retour à la page précédente (panel1)
                    PageManager.changerPage(EcranLancement.this, panel1);
                }
            });
            panel2.add(annuler);
    
            // Changer de page vers panel2
            PageManager.changerPage(this, panel2);
        } else {
            // La page n'est pas vide, simplement changer de page vers panel2
            PageManager.changerPage(this, panel2);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EcranLancement();
            }
        });
    }
}





























 // Méthode pour changer vers la nouvelle page
    /*private void changerVersNouvellePage() {
        JTextField nouveauUtilisateurField = new JTextField(15);
        panel2.add(new JLabel("Créer un nouvel utilisateur : "));
        panel2.add(nouveauUtilisateurField);
        JButton validerNouveauUtilisateur = new JButton("Valider");
        JButton annuler=new JButton("Annuler");
        validerNouveauUtilisateur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nouvelUtilisateur = nouveauUtilisateurField.getText();
                System.out.println("Nouvel utilisateur créé : " + nouvelUtilisateur);
                // Afficher un message de confirmation pour le nouvel utilisateur créé
                PageManager.afficherMessage(EcranLancement.this, "Nouvel utilisateur créé : " + nouvelUtilisateur);
                
            }
        });
        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                // Retour à la page précédente (panel1)
                PageManager.changerPage(EcranLancement.this,panel1);
            }
        });
        panel2.add(validerNouveauUtilisateur);
        panel2.add(annuler);
        // Changer de page vers panel2
        PageManager.changerPage(this, panel2);
    }*/