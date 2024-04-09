package com.hashi.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;

import com.hashi.Hashi;
import com.hashi.game.mode.ModeHistoire;
import com.hashi.style.Panel;
import com.hashi.style.StyleManager;
import com.hashi.LanguageManager;

/**
 * La classe `Chapitre` represente les chapitres de la mode histoire
 * Elle étend la classe `Panel`.
 */
public class Chapitre extends Panel {

    private int chapitre;
    private List<String> texts = new ArrayList<>(); // Liste des textes
    private int currentTextIndex = 0;
    private Panel contentPanel;
    private Panel imagePanel;
    private JLabel textLabel;
    private Consumer<Integer> onTextChange;

    /**
     * Constructeur de la classe `Chapitre`.
     * Initialise le chapitre.
     * 
     * @param chapitre Le numéro du chapitre.
     */
    public Chapitre(ModeHistoire mode, int chapitre) {
        super(new BorderLayout(), getBackgroundImage(chapitre));
        this.chapitre = chapitre;

        // Création du panel pour contenir l'image et le texte
        contentPanel = new Panel(new BorderLayout());

        // Création du panneau d'arrière-plan du texte
        Panel textBackgroundPanel = new Panel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dessinez un rectangle semi-transparent
                g.setColor(new Color(0, 0, 0, 150)); // 150 pour l'opacité
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Création d'une étiquette pour afficher le texte avec HTML et CSS pour ajuster
        // la position
        textLabel = new JLabel();
        textLabel.setFont(StyleManager.getInstance().getFont().deriveFont(0, 35));
        textLabel.setForeground(Color.WHITE);

        // Ajout de l'étiquette au panneau d'arrière-plan du texte
        textBackgroundPanel.add(textLabel, BorderLayout.CENTER);

        // Ajout du panneau d'arrière-plan du texte au panneau de contenu
        contentPanel.add(textBackgroundPanel, BorderLayout.SOUTH);

        imagePanel = new Panel();

        imagePanel.setPreferredSize(new Dimension(600, 0));

        // Ajout du panneau contenant l'image dans le JPanel principal
        contentPanel.add(imagePanel, BorderLayout.EAST);
        add(contentPanel, BorderLayout.CENTER);

        onTextChange = x -> {
        };

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentTextIndex++;

                if (currentTextIndex == texts.size()) {
                    PageManager.changerPage(new Hashi(mode));
                } else {
                    setText();

                    onTextChange.accept(currentTextIndex);
                }
            }
        });

        choixchapitre();
        setText();
    }

    private void setText() {
        textLabel.setText("<html><div style='text-align: center; padding: 20px;'>"
                + LanguageManager.getString(texts.get(currentTextIndex)).replace("{name}",
                        PageManager.getProfil().getNomProfil())
                + "\n</div></html>");
    }

    /**
     * Renvoie l'image de fond correspondant au chapitre spécifié.
     * 
     * @param chapitre Le numéro du chapitre.
     * @return l'image de fond.
     */
    private static String getBackgroundImage(int chapitre) {
        switch (chapitre) {
            default:
                return "back2.jpg";
        }
    }

    /**
     * Choix du chapitre .
     * Sélectionne la méthode d'initialisation de chapitre appropriée en fonction du
     * numéro de chapitre.
     */

    private void choixchapitre() {

        switch (chapitre) {
            case 1:
                initialiserIntroduction();
                break;
            case 2:
                initialiserChapitre1();
                break;
            case 3:
                initialiserChapitre2();
                break;
            case 4:
                initialiserChapitre3();
                break;
            case 6:
                initialiserChapitre4();
                break;
            case 7:
                initialiserChapitre5();
                break;
            case 9:
                initialiserChapitre6();
                break;
            case 10:
                initialiserChapitre7();
                break;
            case 11:
                initialiserChapitre8();
                break;
            case 12:
                initialiserChapitre9();
                break;
            case 13:
                initialiserChapitre10();
                break;
        }

    }

    /**
     * Initialise le chapitre d'introduction.
     * Cette méthode configure le contenu du chapitre d'introduction, y compris le
     * texte affiché et l'image.
     * Le texte est présentée dans un panneau avec un arrière-plan semi-transparent.
     */
    private void initialiserIntroduction() {
        texts.add("chap_intro_1");

        imagePanel.setImage("DocteurMohammed.png");
    }

    /**
     * Initialise le chapitre 1.
     * Cette méthode configure le contenu du chapitre , y compris le texte affiché
     * et les images.
     * Le texte est présentée dans un panneau avec un arrière-plan semi-transparent.
     */
    private void initialiserChapitre1() {
        texts.add("chap_1_1");
        texts.add("chap_1_2");
        texts.add("chap_1_3");
        texts.add("chap_1_4");
        texts.add("chap_1_5");
        texts.add("chap_1_6");
        texts.add("chap_1_7");
        texts.add("chap_1_8");

        imagePanel.setImage("DocteurMohammed.png");

        onTextChange = currentTextIndex -> {
            if (currentTextIndex == 4 || currentTextIndex == 5 || currentTextIndex == 7) {
                imagePanel.setImage("makefile.png");
                contentPanel.add(imagePanel, BorderLayout.WEST);
            } else if (currentTextIndex == 1 || currentTextIndex == 3 || currentTextIndex == 6) {
                // Changer l'image pour le texte 3 et 4
                imagePanel.setImage("Interne.png");
                contentPanel.add(imagePanel, BorderLayout.EAST);
            } else {
                imagePanel.setImage("DocteurMohammed.png");
                contentPanel.add(imagePanel, BorderLayout.EAST);
            }
        };
    }

    /**
     * Initialise le chapitre 2.
     * Cette méthode configure le contenu du chapitre , y compris le texte affiché
     * et les images.
     * Le texte est présentée dans un panneau avec un arrière-plan semi-transparent.
     */
    private void initialiserChapitre2() {
        texts.add("chap_2_1");
        texts.add("chap_2_2");
        texts.add("chap_2_3");
        texts.add("chap_2_4");
        texts.add("chap_2_5");

        imagePanel.setImage("DocteurMohammed.png");

        onTextChange = currentTextIndex -> {
            if (currentTextIndex == 2 || currentTextIndex == 4) {
                imagePanel.setImage("makefile.png");
                contentPanel.add(imagePanel, BorderLayout.WEST);
            } else if (currentTextIndex == 3) {
                imagePanel.setImage("Interne.png");
                contentPanel.add(imagePanel, BorderLayout.EAST);
            } else {
                imagePanel.setImage("DocteurMohammed.png");
                contentPanel.add(imagePanel, BorderLayout.EAST);
            }
        };
    }

    /**
     * Initialise le chapitre 3.
     * Cette méthode configure le contenu du chapitre , y compris le texte affiché
     * et les images.
     * Le texte est présentée dans un panneau avec un arrière-plan semi-transparent.
     */
    private void initialiserChapitre3() {
        texts.add("chap_3_1");
        texts.add("chap_3_2");
        texts.add("chap_3_3");

        imagePanel.setImage("DocteurMohammed.png");

        onTextChange = currentTextIndex -> {
            if (currentTextIndex == 1) {
                imagePanel.setImage("Interne.png");
                contentPanel.add(imagePanel, BorderLayout.WEST);
            } else {
                imagePanel.setImage("DocteurMohammed.png");
                contentPanel.add(imagePanel, BorderLayout.EAST);
            }
        };
    }

    /**
     * Initialise le chapitre 4.
     * Cette méthode configure le contenu du chapitre , y compris le texte affiché
     * et les images.
     * Le texte est présentée dans un panneau avec un arrière-plan semi-transparent.
     */
    private void initialiserChapitre4() {
        texts.add("chap_4_1");
        texts.add("chap_4_2");
        texts.add("chap_4_3");
        texts.add("chap_4_4");

        imagePanel.setImage("Bijective.png");

        onTextChange = currentTextIndex -> {
            if (currentTextIndex == 1 || currentTextIndex == 3) {
                imagePanel.setImage("Interne.png");
                contentPanel.add(imagePanel, BorderLayout.WEST);
            } else {
                imagePanel.setImage("Bijective.png");
                contentPanel.add(imagePanel, BorderLayout.EAST);
            }
        };
    }

    /**
     * Initialise le chapitre 5.
     * Cette méthode configure le contenu du chapitre , y compris le texte affiché
     * et les images.
     * Le texte est présentée dans un panneau avec un arrière-plan semi-transparent.
     */
    private void initialiserChapitre5() {
        texts.add("chap_5_1");
        texts.add("chap_5_2");
        texts.add("chap_5_3");

        imagePanel.setImage("Interne.png");

        onTextChange = currentTextIndex -> {
            if (currentTextIndex == 1) {
                imagePanel.setImage("system.png");
                contentPanel.add(imagePanel, BorderLayout.WEST);
            } else {
                imagePanel.setImage("Interne.png");
                contentPanel.add(imagePanel, BorderLayout.EAST);
            }
        };
    }

    /**
     * Initialise le chapitre 6.
     * Cette méthode configure le contenu du chapitre , y compris le texte affiché
     * et les images.
     * Le texte est présentée dans un panneau avec un arrière-plan semi-transparent.
     */
    private void initialiserChapitre6() {
        texts.add("chap_6_1");
        texts.add("chap_6_2");

        imagePanel.setImage("DocteurMohammed.png");

        onTextChange = currentTextIndex -> {
            if (currentTextIndex == 1) {
                imagePanel.setImage("Interne.png");
                contentPanel.add(imagePanel, BorderLayout.WEST);
            } else {
                imagePanel.setImage("DocteurMohammed.png");
                contentPanel.add(imagePanel, BorderLayout.EAST);
            }
        };
    }

    /**
     * Initialise le chapitre 7.
     * Cette méthode configure le contenu du chapitre , y compris le texte affiché
     * et les images.
     * Le texte est présentée dans un panneau avec un arrière-plan semi-transparent.
     */
    private void initialiserChapitre7() {
        texts.add("chap_7_1");
        texts.add("chap_7_2");
        texts.add("chap_7_3");

        imagePanel.setImage("pip.png");

        onTextChange = currentTextIndex -> {
            if (currentTextIndex == 1) {
                imagePanel.setImage("DocteurMohammed.png");
                contentPanel.add(imagePanel, BorderLayout.WEST);
            } else {
                imagePanel.setImage("pip.png");
                contentPanel.add(imagePanel, BorderLayout.EAST);
            }
        };
    }

    /**
     * Initialise le chapitre 8.
     * Cette méthode configure le contenu du chapitre , y compris le texte affiché
     * et les images.
     * Le texte est présentée dans un panneau avec un arrière-plan semi-transparent.
     */
    private void initialiserChapitre8() {
        texts.add("chap_8_1");
        texts.add("chap_8_2");
        texts.add("chap_8_3");
        texts.add("chap_8_4");

        imagePanel.setImage("makefile.png");

        onTextChange = currentTextIndex -> {
            if (currentTextIndex == 1 || currentTextIndex == 3) {
                imagePanel.setImage("Interne.png");
                contentPanel.add(imagePanel, BorderLayout.WEST);
            } else if (currentTextIndex == 2) {
                imagePanel.setImage("Bijective.png");
                contentPanel.add(imagePanel, BorderLayout.EAST);
            } else {
                imagePanel.setImage("makefile.png");
                contentPanel.add(imagePanel, BorderLayout.EAST);
            }
        };
    }

    /**
     * Initialise le chapitre 9.
     * Cette méthode configure le contenu du chapitre , y compris le texte affiché
     * et les images.
     * Le texte est présentée dans un panneau avec un arrière-plan semi-transparent.
     */
    private void initialiserChapitre9() {
        texts.add("chap_9_1");
        texts.add("chap_9_2");
        texts.add("chap_9_3");
        texts.add("chap_9_4");

        imagePanel.setImage("Bijective.png");

        onTextChange = currentTextIndex -> {
            if (currentTextIndex == 1 || currentTextIndex == 3) {
                imagePanel.setImage("Interne.png");
                contentPanel.add(imagePanel, BorderLayout.WEST);
            } else {
                imagePanel.setImage("Bijective.png");
                contentPanel.add(imagePanel, BorderLayout.EAST);
            }
        };
    }

    /**
     * Initialise le chapitre 10.
     * Cette méthode configure le contenu du chapitre , y compris le texte affiché
     * et les images.
     * Le texte est présentée dans un panneau avec un arrière-plan semi-transparent.
     */
    private void initialiserChapitre10() {
        texts.add("chap_10_1");
        texts.add("chap_10_2");
        texts.add("chap_10_3");
        texts.add("chap_10_4");

        imagePanel.setImage("Interne.png");

        onTextChange = currentTextIndex -> {
            if (currentTextIndex == 1 || currentTextIndex == 3) {
                imagePanel.setImage("system.png");
                contentPanel.add(imagePanel, BorderLayout.WEST);
            } else {
                imagePanel.setImage("Interne.png");
                contentPanel.add(imagePanel, BorderLayout.EAST);
            }
        };
    }

}
