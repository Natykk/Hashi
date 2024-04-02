package com.hashi.menu;
import javax.swing.*;
import java.awt.*;
import com.hashi.style.Panel;
import com.hashi.style.StyleManager;
import com.hashi.style.Image;

public class History extends Panel {

    public History() {
        // Appel du constructeur de la classe Panel pour créer un JPanel avec une image d'arrière-plan
        super(new BorderLayout(), "back2.jpg");

        // Création du panel pour contenir l'image et le texte
        Panel contentPanel = new Panel(new BorderLayout());

        // Création de l'image
        Image imageComponent1 = new Image(contentPanel);
        imageComponent1.setImage("DocteurMohammed.png");

        // Redimensionnement de l'image
        ImageIcon originalIcon = new ImageIcon(imageComponent1.getImage());
        ImageIcon resizedIcon = new ImageIcon(originalIcon.getImage().getScaledInstance(600, 600, java.awt.Image.SCALE_SMOOTH));

        // Création d'une étiquette pour afficher l'image redimensionnée
        JLabel imageLabel = new JLabel(resizedIcon);

        // Création d'une étiquette pour afficher le texte avec HTML et CSS pour ajuster la position
        JLabel textLabel = new JLabel("<html><div style='text-align: center; margin-top: 300px; padding-left: 200px;  '>Salut l'interne ! <br> C'est quoi ton nom déjà ?</div></html>");
        textLabel.setFont(StyleManager.getInstance().getFont().deriveFont(0, 35));
        textLabel.setForeground(Color.RED);

        // Création d'un panel pour contenir l'image et le texte horizontalement
        Panel imageTextPanel = new Panel(new BorderLayout());
        imageTextPanel.add(textLabel, BorderLayout.CENTER);
        imageTextPanel.add(imageLabel, BorderLayout.EAST);

        // Ajout du panel contenant l'image et le texte dans le JPanel principal
        contentPanel.add(imageTextPanel, BorderLayout.SOUTH);
        add(contentPanel, BorderLayout.SOUTH);
    }
}


