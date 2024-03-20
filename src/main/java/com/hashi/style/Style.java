package com.hashi.style;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.swing.ImageIcon;

/**
 * Classe abstaite contenant toutes les fonctions pour afficher les éléments
 * stylisé.
 */
public abstract class Style {
    /**
     * Nom du dossier qui contient le style.
     */
    protected String name;
    /**
     * Couleur de fond.
     */
    protected Color bg_color;
    /**
     * Couleur d'avant plan.
     */
    protected Color fg_color;
    /**
     * Police d'écriture.
     */
    protected Font font;

    /**
     * Récupère le nom de dossier du style.
     * 
     * @return Retourne le nom de dossier.
     */
    public String getName() {
        return name;
    }

    /**
     * Récupère la couleur de fond.
     * 
     * @return Retourne la couleur de fond.
     */
    public Color getBgColor() {
        return bg_color;
    }

    /**
     * Récupère la couleur d'avant plan.
     * 
     * @return Retourne la couleur d'avant plan.
     */
    public Color getFgColor() {
        return fg_color;
    }

    /**
     * Récupère la police d'écriture.
     * 
     * @return Retourne la {@link java.awt.Font}.
     */
    public Font getFont() {
        return font;
    }

    /**
     * Récupère le chemin vers la ressource dépendamment du thème sélectionner.
     * 
     * @param res nom de la ressource.
     * @return Retourne le chemin de la ressource.
     */
    public String getResourcePath(String res) {
        return "/com/hashi/theme/" + name + "/" + res;
    }

    /**
     * Récupère une police d'écriture depuis une ressource.
     * 
     * @param font_url chemin de la ressource.
     * @return Retourne une {@link java.awt.Font}.
     */
    public Font getFontResource(String font_url) {
        Font font = null;

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Style.class.getResourceAsStream(font_url));
        } catch (FontFormatException e) {
            System.err.println("Impossible de charger la police mauvais format <" + font_url + "> : " + e);
        } catch (IOException e) {
            System.err.println("Impossible de charger la police à partie de la ressource <" + font_url + "> : " + e);
        }

        return font;
    }

    /**
     * Récupère une image depuis une ressource.
     * 
     * @param image_url chemin de la ressource.
     * @return Retourne une {@link javax.swing.ImageIcon}.
     */
    public ImageIcon getImageResource(String image_url) {
        return new ImageIcon(Style.class.getResource(image_url));
    }

    /**
     * Fonction appelée à l'initialisation d'un {@link com.hashi.style.Panel}.
     * 
     * @param panel le {@link com.hashi.style.Panel} en cour d'initialisation.
     */
    protected abstract void initPanel(Panel panel);

    /**
     * Fonction appelée quand un {@link com.hashi.style.Panel} est repeint.
     * 
     * @param panel le {@link com.hashi.style.Panel} à repeindre.
     * @param g     {@link java.awt.Graphics2D} permettant de dessiner l'élément.
     */
    protected abstract void paintPanel(Panel panel, Graphics2D g);

    /**
     * Fonction appelée quand la bordure d'un {@link com.hashi.style.Panel} est
     * repeinte.
     * 
     * @param panel le {@link com.hashi.style.Panel} à repeindre.
     * @param g     {@link java.awt.Graphics2D} permettant de dessiner l'élément.
     */
    protected abstract void paintPanelBorder(Panel panel, Graphics2D g);

    /**
     * Fonction appelée à l'initialisation d'un {@link com.hashi.style.Button}.
     * 
     * @param button le {@link com.hashi.style.Button} en cour d'initialisation.
     */
    protected abstract void initButton(Button button);

    /**
     * Fonction appelée à l'initialisation d'un {@link com.hashi.style.Button}.
     * 
     * @param button le {@link com.hashi.style.Button} en cour d'initialisation.
     */
    protected abstract void paintButton(Button button, Graphics2D g);

    /**
     * Fonction appelée quand la bordure d'un {@link com.hashi.style.Button} est
     * repeinte.
     * 
     * @param button le {@link com.hashi.style.Button} à repeindre.
     * @param g      {@link java.awt.Graphics2D} permettant de dessiner l'élément.
     */
    protected abstract void paintButtonBorder(Button button, Graphics2D g);

    /**
     * Fonction appelée à l'initialisation d'un {@link com.hashi.style.Label}.
     * 
     * @param label le {@link com.hashi.style.Label} en cour d'initialisation.
     */
    protected abstract void initLabel(Label label);

    /**
     * Fonction appelée à l'initialisation d'un {@link com.hashi.style.Label}.
     * 
     * @param label le {@link com.hashi.style.Label} en cour d'initialisation.
     */
    protected abstract void paintLabel(Label label, Graphics2D g);

    /**
     * Fonction appelée quand la bordure d'un {@link com.hashi.style.Label} est
     * repeinte.
     * 
     * @param label le {@link com.hashi.style.Label} à repeindre.
     * @param g     {@link java.awt.Graphics2D} permettant de dessiner l'élément.
     */
    protected abstract void paintLabelBorder(Label label, Graphics2D g);

    /**
     * Fonction appelée à l'initialisation d'un {@link com.hashi.style.ComboBox}.
     * 
     * @param combo_box le {@link com.hashi.style.ComboBox} en cour
     *                  d'initialisation.
     */
    protected abstract <E> void initComboBox(ComboBox<E> combo_box);

    /**
     * Fonction appelée à l'initialisation d'un {@link com.hashi.style.ComboBox}.
     * 
     * @param combo_box le {@link com.hashi.style.ComboBox} en cour
     *                  d'initialisation.
     */
    protected abstract <E> void paintComboBox(ComboBox<E> combo_box, Graphics2D g);

    /**
     * Fonction appelée quand la bordure d'un {@link com.hashi.style.ComboBox} est
     * repeinte.
     * 
     * @param combo_box le {@link com.hashi.style.ComboBox} à repeindre.
     * @param g         {@link java.awt.Graphics2D} permettant de dessiner
     *                  l'élément.
     */
    protected abstract <E> void paintComboBoxBorder(ComboBox<E> combo_box, Graphics2D g);

    /**
     * Fonction appelée à l'initialisation d'un {@link com.hashi.style.TextField}.
     * 
     * @param text_field le {@link com.hashi.style.TextField} en cour
     *                   d'initialisation.
     */
    protected abstract void initTextField(TextField text_field);

    /**
     * Fonction appelée à l'initialisation d'un {@link com.hashi.style.TextField}.
     * 
     * @param text_field le {@link com.hashi.style.TextField} en cour
     *                   d'initialisation.
     */
    protected abstract void paintTextField(TextField text_field, Graphics2D g);

    /**
     * Fonction appelée quand la bordure d'un {@link com.hashi.style.TextField} est
     * repeinte.
     * 
     * @param text_field le {@link com.hashi.style.TextField} à repeindre.
     * @param g          {@link java.awt.Graphics2D} permettant de dessiner
     *                   l'élément.
     */
    protected abstract void paintTextFieldBorder(TextField text_field, Graphics2D g);
}