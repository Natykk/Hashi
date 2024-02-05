import javax.swing.*;

public class EcranAcceuil {

    private JPanel panel; // Le panel qui représente l'écran d'accueil

    public EcranAcceuil() {
        // Initialisation du panel avec le contenu de l'écran d'accueil
        panel = new JPanel();
        panel.add(new JLabel("Bienvenue dans l'écran d'accueil!"));
    }

    // Méthode pour obtenir le panel de l'écran d'accueil
    public JPanel getPanel() {
        return panel;
    }
}
