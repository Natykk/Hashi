package com.hashi.menu;
import javax.swing.*;
import java.awt.*;
import com.hashi.style.Panel; // Import de votre classe Panel
import com.hashi.style.Image;

public class History extends Panel {

    public History(){
        // Appel du constructeur de la classe Panel pour créer un JPanel avec une image d'arrière-plan
        super(new BorderLayout(), "bg-history-load-game.png");

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
        
        // Création d'une étiquette pour afficher le texte
        JLabel textLabel = new JLabel("Salut l interne ! C est quoi ton nom deja ?");

        // Création d'un panel pour contenir l'image et le texte horizontalement
        Panel imageTextPanel = new Panel(new BorderLayout());
        imageTextPanel.add(imageLabel, BorderLayout.EAST);
        imageTextPanel.add(textLabel, BorderLayout.CENTER);

        // Ajout du panel contenant l'image et le texte dans le JPanel principal
        contentPanel.add(imageTextPanel, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);
    }    
}
