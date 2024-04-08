package com.hashi.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import com.hashi.Hashi;
import com.hashi.game.mode.ModeHistoire;
import com.hashi.style.Panel;
import com.hashi.style.StyleManager;

/**
 * La classe `Chapitre` represente les chapitres de la mode histoire
 * Elle étend la classe `Panel`.
 */
public class Chapitre extends Panel {

    private int chapitre;
    private List<String> texts = new ArrayList<>(); // Liste des textes
    private int currentTextIndex = 0;
    private ModeHistoire mode;
    private Panel contentPanel;
    private Panel imagePanel;
    private JLabel textLabel;

    /**
     * Constructeur de la classe `Chapitre`.
     * Initialise le chapitre.
     * 
     * @param chapitre Le numéro du chapitre.
     */
    public Chapitre(ModeHistoire mode, int chapitre) {
        super(new BorderLayout(), getBackgroundImage(chapitre));
        this.chapitre = chapitre;
        this.mode = mode;

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

        choixchapitre();
        setText();
    }

    private void setText() {
        textLabel.setText("<html><div style='text-align: center; padding: 20px;'>" + texts.get(currentTextIndex)
                + "</div></html>");
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
        texts.add(
                "Docteur Mohammed : Bienvenue dans l’Institut Informatique CanCérologique IC²,  cher interne! Ici, nous soignons tout type de client, même les plus toxique… je parle des cancers, bien sûr. Je vais t’apprendre à soigner le cancer.\n");
        texts.add("\n");

        imagePanel.setImage("DocteurMohammed.png");

        // Ajout d'un gestionnaire d'événements de clic de souris à ce panneau
        // Ajout d'un gestionnaire d'événements de clic de souris à ce panneau
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentTextIndex = (currentTextIndex + 1) % texts.size();

                setText();
                if (currentTextIndex == texts.size() - 1) {

                    PageManager.changerPage(new Hashi(mode));
                }
            }
        });
    }

    /**
     * Initialise le chapitre 1.
     * Cette méthode configure le contenu du chapitre , y compris le texte affiché
     * et les images.
     * Le texte est présentée dans un panneau avec un arrière-plan semi-transparent.
     */
    private void initialiserChapitre1() {
        texts.add(
                "Docteur Mohammed : Salut l’interne ! C’est quoi ton nom déjà ?\n");
        texts.add(
                "Interne : "
                        + PageManager.getProfil().getNomProfil()
                        + ".\n");
        texts.add(
                "Docteur Mohammed : Mais ça, c’est ton prénom ! Je te demande ton nom !\n");
        texts.add(
                "Interne : C'est toujours "
                        + PageManager.getProfil().getNomProfil()
                        + ".\n");
        texts.add(
                "Infirmière Makefile : Interne ! Un nouveau client est arrivé ! Il a besoin de soin en urgence ! Venez vite !\n");
        texts.add(
                "Infirmière Makefile : Voici Monsieur Pip ! Il est atteint d’un cancer de la ventilation ! C’est un  sujet d’étude très intéressant, mais il faudrait le soigner un jour. L’éthique de la science, le serment d’Hypocrite…</div></html>");
        texts.add(
                "Interne : C’est Hypocrate, Madame Makefile.\n");
        texts.add(
                "Infirmière Makefile : Contente-toi de faire ton travail !\n");
        texts.add("\n");

        imagePanel.setImage("DocteurMohammed.png");

        // Ajout d'un gestionnaire d'événements de clic de souris à ce panneau
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentTextIndex = (currentTextIndex + 1) % texts.size();

                setText();
                if (currentTextIndex == texts.size() - 1) {

                    PageManager.changerPage(new Hashi(mode));
                } else {

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
                }
            }
        });
    }

    /**
     * Initialise le chapitre 2.
     * Cette méthode configure le contenu du chapitre , y compris le texte affiché
     * et les images.
     * Le texte est présentée dans un panneau avec un arrière-plan semi-transparent.
     */
    private void initialiserChapitre2() {
        texts.add(
                "Docteur Mohammed : Alors, interne ? Comment ça s’est passé ?\n");
        texts.add(
                "Docteur Mohammed : Hm… C’est vraiment du travail d’interne ça…\n");
        texts.add(
                "Infirmière Makefile : Interne ! Monsieur Pip est revenu ! Vous avez soigné son cancer mais il vient de perdre son orteil dans un accident de tronçonneuse ! Maintenant il faut soigner son pied !\n");
        texts.add(
                "Interne : Mais je ne soigne que les cancers !\n");
        texts.add(
                "Infirmière Makefile : Le désert médical impose ! Au boulot ! Hop hop hop !\n");
        texts.add("\n");

        imagePanel.setImage("DocteurMohammed.png");

        // Ajout d'un gestionnaire d'événements de clic de souris à ce panneau
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentTextIndex = (currentTextIndex + 1) % texts.size();

                setText();
                if (currentTextIndex == texts.size() - 1) {

                    PageManager.changerPage(new Hashi(mode));
                } else {

                    if (currentTextIndex == 2 || currentTextIndex == 4) {

                        imagePanel.setImage("makefile.png"); // Spécifiez le chemin de la nouvelle image
                        contentPanel.add(imagePanel, BorderLayout.WEST);
                    } else if (currentTextIndex == 3) {
                        imagePanel.setImage("Interne.png"); // Spécifiez le chemin de la nouvelle image
                        contentPanel.add(imagePanel, BorderLayout.EAST);
                    } else {
                        imagePanel.setImage("DocteurMohammed.png");
                        contentPanel.add(imagePanel, BorderLayout.EAST);
                    }
                }
            }
        });
    }

    /**
     * Initialise le chapitre 3.
     * Cette méthode configure le contenu du chapitre , y compris le texte affiché
     * et les images.
     * Le texte est présentée dans un panneau avec un arrière-plan semi-transparent.
     */
    private void initialiserChapitre3() {
        texts.add(
                "Docteur Mohammed : Bonne nouvelle ! Nous avons un donneur d’orteil sain compatible avec Pip ! Plus qu’à le recoller ! \n");
        texts.add("Interne : Amen.\n");
        texts.add(
                "Docteur Mohamed : Mais non ! C’est sain S-A-I-N, pas S-A-I-N-T ! Ah les internes de nos jours, ils ne savent même plus écrire !\n");
        texts.add("\n");

        imagePanel.setImage("DocteurMohammed.png");

        // Ajout d'un gestionnaire d'événements de clic de souris à ce panneau
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentTextIndex = (currentTextIndex + 1) % texts.size();

                setText();
                if (currentTextIndex == texts.size() - 1) {

                    PageManager.changerPage(new Hashi(mode));
                } else {

                    if (currentTextIndex == 1) {
                        imagePanel.setImage("Interne.png"); // Spécifiez le chemin de la nouvelle image
                        contentPanel.add(imagePanel, BorderLayout.WEST);
                    } else {
                        imagePanel.setImage("DocteurMohammed.png");
                        contentPanel.add(imagePanel, BorderLayout.EAST);
                    }
                }
            }
        });
    }

    /**
     * Initialise le chapitre 4.
     * Cette méthode configure le contenu du chapitre , y compris le texte affiché
     * et les images.
     * Le texte est présentée dans un panneau avec un arrière-plan semi-transparent.
     */
    private void initialiserChapitre4() {
        texts.add(
                "Infirmière Bijective : Bonjour interne ! Prêt à faire de l’IA ? \n");
        texts.add(
                "Interne : Mais on est en cancérologie ici !\n");
        texts.add(
                "Infirmière Bijective : Mais non ! IA pour Intervention Anatomique ! Un nouveau client est arrivé ! Il s’appelle Bretobjet Connecté, mais il est en hypoglycémie et en hyponatrémie critique. \n");
        texts.add(
                "Interne : Vous ne soignez jamais de cancer ici, en fait.\n");
        texts.add("\n");

        imagePanel.setImage("Bijective.png");

        // Ajout d'un gestionnaire d'événements de clic de souris à ce panneau
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentTextIndex = (currentTextIndex + 1) % texts.size();

                setText();
                if (currentTextIndex == texts.size() - 1) {

                    PageManager.changerPage(new Hashi(mode));
                } else {

                    if (currentTextIndex == 1 || currentTextIndex == 3) {
                        imagePanel.setImage("Interne.png"); // Spécifiez le chemin de la nouvelle image
                        contentPanel.add(imagePanel, BorderLayout.WEST);
                    } else {
                        imagePanel.setImage("Bijective.png");
                        contentPanel.add(imagePanel, BorderLayout.EAST);
                    }
                }
            }
        });
    }

    /**
     * Initialise le chapitre 5.
     * Cette méthode configure le contenu du chapitre , y compris le texte affiché
     * et les images.
     * Le texte est présentée dans un panneau avec un arrière-plan semi-transparent.
     */
    private void initialiserChapitre5() {
        texts.add(
                "Interne : Je pense que j’ai besoin de vacances.\n");
        texts.add(
                "Secrétaire toString() : C’est impossible, cela ne fait que six parties que vous jouez. Il faut au moins en faire douze pour avoir droit à un jour de congé.\n");
        texts.add("Interne : C’est reparti alors…\n");
        texts.add("\n");

        imagePanel.setImage("Interne.png");

        // Ajout d'un gestionnaire d'événements de clic de souris à ce panneau
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentTextIndex = (currentTextIndex + 1) % texts.size();

                setText();
                if (currentTextIndex == texts.size() - 1) {

                    PageManager.changerPage(new Hashi(mode));
                } else {

                    if (currentTextIndex == 1) {
                        imagePanel.setImage("system.png"); // Spécifiez le chemin de la nouvelle image
                        contentPanel.add(imagePanel, BorderLayout.WEST);
                    } else {
                        imagePanel.setImage("Interne.png");
                        contentPanel.add(imagePanel, BorderLayout.EAST);
                    }
                }
            }
        });
    }

    /**
     * Initialise le chapitre 6.
     * Cette méthode configure le contenu du chapitre , y compris le texte affiché
     * et les images.
     * Le texte est présentée dans un panneau avec un arrière-plan semi-transparent.
     */
    private void initialiserChapitre6() {
        texts.add(
                "Docteur Mohammed : Interne ! Il est grand temps que je vous apprenne la différence entre les méthodes privées et les méthodes publiques !\n");
        texts.add(
                "Interne : Ahh… la privatisation des services, c’est bien un truc de capitaliste.\n");
        texts.add("\n");

        imagePanel.setImage("DocteurMohammed.png");

        // Ajout d'un gestionnaire d'événements de clic de souris à ce panneau
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentTextIndex = (currentTextIndex + 1) % texts.size();

                setText();
                if (currentTextIndex == texts.size() - 1) {

                    PageManager.changerPage(new Hashi(mode));
                } else {

                    if (currentTextIndex == 1) {
                        imagePanel.setImage("Interne.png"); // Spécifiez le chemin de la nouvelle image
                        contentPanel.add(imagePanel, BorderLayout.WEST);
                    } else {
                        imagePanel.setImage("DocteurMohammed.png");
                        contentPanel.add(imagePanel, BorderLayout.EAST);
                    }
                }
            }
        });
    }

    /**
     * Initialise le chapitre 7.
     * Cette méthode configure le contenu du chapitre , y compris le texte affiché
     * et les images.
     * Le texte est présentée dans un panneau avec un arrière-plan semi-transparent.
     */
    private void initialiserChapitre7() {
        texts.add(
                "Patient Pip (dort en pleine opération) : zzz \n");
        texts.add(
                "Docteur Mohammed : Je reviens, je vais aux toilettes.\n");
        texts.add(
                "Patient Pip (se réveille brusquement) : Comment ça vous allez aux toilettes ?! Vous ne me demandez pas avant ? Vous ne pouviez pas y aller avant ? Vous êtes en plein service ! Quand j’étais à la matern…\n");
        texts.add("\n");

        imagePanel.setImage("pip.png");

        // Ajout d'un gestionnaire d'événements de clic de souris à ce panneau
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentTextIndex = (currentTextIndex + 1) % texts.size();

                setText();
                if (currentTextIndex == texts.size() - 1) {

                    PageManager.changerPage(new Hashi(mode));
                } else {

                    if (currentTextIndex == 1) {
                        imagePanel.setImage("DocteurMohammed.png"); // Spécifiez le chemin de la nouvelle image
                        contentPanel.add(imagePanel, BorderLayout.WEST);
                    } else {
                        imagePanel.setImage("pip.png");
                        contentPanel.add(imagePanel, BorderLayout.EAST);
                    }
                }
            }
        });
    }

    /**
     * Initialise le chapitre 8.
     * Cette méthode configure le contenu du chapitre , y compris le texte affiché
     * et les images.
     * Le texte est présentée dans un panneau avec un arrière-plan semi-transparent.
     */
    private void initialiserChapitre8() {
        texts.add(
                "Infirmière Makefile : Connaître son sujet est nécessaire mais pas suffisant. Par ailleurs, il existe une connaissance sur la pédagogie très développée, mais qui n’est pas facilement accessible par des enseignants novices, qui pour autant aimeraient avoir une aide dans ce domaine. Ainsi, les enseignants pédagogues…\n");
        texts.add("Interne : Que fait-elle ?\n");
        texts.add(
                "Infirmière Bijective : Elle invoque les méthodes de conception sacrées.\n");
        texts.add(
                "Interne : Elles sont publiques ou privées ?\n");
        texts.add("\n");

        imagePanel.setImage("makefile.png");

        // Ajout d'un gestionnaire d'événements de clic de souris à ce panneau
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentTextIndex = (currentTextIndex + 1) % texts.size();

                setText();
                if (currentTextIndex == texts.size() - 1) {

                    PageManager.changerPage(new Hashi(mode));
                } else {

                    if (currentTextIndex == 1 || currentTextIndex == 3) {
                        imagePanel.setImage("Interne.png"); // Spécifiez le chemin de la nouvelle image
                        contentPanel.add(imagePanel, BorderLayout.WEST);
                    } else if (currentTextIndex == 2) {
                        imagePanel.setImage("Bijective.png"); // Spécifiez le chemin de la nouvelle image
                        contentPanel.add(imagePanel, BorderLayout.EAST);
                    } else {
                        imagePanel.setImage("makefile.png");
                        contentPanel.add(imagePanel, BorderLayout.EAST);
                    }
                }
            }
        });
    }

    /**
     * Initialise le chapitre 9.
     * Cette méthode configure le contenu du chapitre , y compris le texte affiché
     * et les images.
     * Le texte est présentée dans un panneau avec un arrière-plan semi-transparent.
     */
    private void initialiserChapitre9() {
        texts.add(
                "Infirmière Bijective : Saviez-vous qu’une injection automorphique est une bijection ? Une fonction bijective est une application linéaire de A dans B où tous les éléments de A ont une seule image de B et tous les éléments de B ont un seul antécédent dans A. \n");
        texts.add(
                "Interne : Donc les orteils de Pip ne sont pas bijectifs avec son pied.\n");
        texts.add(
                "Infirmière Bijective : Mais ils sont surjectifs !\n");
        texts.add("Interne : Et ils sont publics ou privés ?\n");
        texts.add("\n");

        imagePanel.setImage("Bijective.png");

        // Ajout d'un gestionnaire d'événements de clic de souris à ce panneau
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentTextIndex = (currentTextIndex + 1) % texts.size();

                setText();
                if (currentTextIndex == texts.size() - 1) {

                    PageManager.changerPage(new Hashi(mode));
                } else {

                    if (currentTextIndex == 1 || currentTextIndex == 3) {
                        imagePanel.setImage("Interne.png"); // Spécifiez le chemin de la nouvelle image
                        contentPanel.add(imagePanel, BorderLayout.WEST);
                    } else {
                        imagePanel.setImage("Bijective.png");
                        contentPanel.add(imagePanel, BorderLayout.EAST);
                    }
                }
            }
        });
    }

    /**
     * Initialise le chapitre 10.
     * Cette méthode configure le contenu du chapitre , y compris le texte affiché
     * et les images.
     * Le texte est présentée dans un panneau avec un arrière-plan semi-transparent.
     */
    private void initialiserChapitre10() {
        texts.add("Interne : JPP, il me faut des vacances.\n");
        texts.add(
                "Secrétaire toString() : Suis-je une méthode privée ou publique ?\n");
        texts.add("Interne : Publique…\n");
        texts.add(
                "Secrétaire toString()  : Félicitations ! Vous avez réussi votre examen d’interne ! Vous allez pouvoir partir en vacances !\n");
        texts.add("\n");

        imagePanel.setImage("Interne.png");

        // Ajout d'un gestionnaire d'événements de clic de souris à ce panneau
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentTextIndex = (currentTextIndex + 1) % texts.size();

                setText();
                if (currentTextIndex == texts.size() - 1) {

                    PageManager.getProfil().resetHistoire();
                    PageManager.changerPage(new HomeMenu());
                } else {

                    if (currentTextIndex == 1 || currentTextIndex == 3) {
                        imagePanel.setImage("system.png"); // Spécifiez le chemin de la nouvelle image
                        contentPanel.add(imagePanel, BorderLayout.WEST);
                    } else {
                        imagePanel.setImage("Interne.png");
                        contentPanel.add(imagePanel, BorderLayout.EAST);
                    }
                }
            }
        });
    }

}
