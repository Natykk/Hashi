package com.hashi.menu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.hashi.Hashi;
import com.hashi.game.mode.ModeHistoire;
import com.hashi.style.Panel;
import com.hashi.style.StyleManager;
import com.hashi.style.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Chapitre extends Panel {

    private int chapitre;
    public Chapitre(int chapitre) {
        super(new BorderLayout(), getBackgroundImage(chapitre) );
        this.chapitre = chapitre;
        choixchapitre();
    }

    private static String getBackgroundImage(int chapitre) {
        switch(chapitre){
            case 1:
                return "back2.jpg";
            case 2:
                return "back2.jpg";
            case 3:
                return "back2.jpg";
            default:
                return "back2.jpg";
        }
    }




    

    private void choixchapitre() {

        System.out.println("Chapitre "+chapitre);
        
                switch (chapitre){
                    case 1:
                        // Appel du constructeur de la classe Panel pour créer un JPanel avec une image d'arrière-plan

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

                // Création du texte
                JLabel textLabel = new JLabel("Chapitre 1 : Docteur Mohammed");
                // Affiche le texte au centre en haut de l'écran
                textLabel.setHorizontalAlignment(SwingConstants.CENTER);
                textLabel.setVerticalAlignment(SwingConstants.TOP);
                

                // Création du texte de dialogue
                JLabel textLabel2 = new JLabel("Bonjour, je suis le docteur Mohammed.");
                textLabel2.setForeground(Color.WHITE);
                
                

                // Ajout du texte de dialogue dans le panel principal
                contentPanel.add(textLabel2, BorderLayout.SOUTH);





                // Création d'un panel pour contenir l'image et le texte horizontalement
                Panel imageTextPanel = new Panel(new BorderLayout());
                imageTextPanel.add(textLabel, BorderLayout.CENTER);
                imageTextPanel.add(imageLabel, BorderLayout.EAST);

                // Ajout du panel contenant l'image et le texte dans le JPanel principal
                contentPanel.add(imageTextPanel, BorderLayout.SOUTH);
                add(contentPanel, BorderLayout.SOUTH);

                // Ajout d'un gestionnaire d'événements de clic de souris à ce panneau
                this.addMouseListener(new MouseAdapter() {
                    
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        PageManager.changerPage(new Hashi(new ModeHistoire(new HomeMenu(),false )));
                    }
                });
                break;
            case 2:
                System.out.println("Chapitre 2");
                break;
            case 3:
                System.out.println("Chapitre 3");
                break;
        }

    }
        

}
