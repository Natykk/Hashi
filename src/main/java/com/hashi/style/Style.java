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
     * @return le nom de dossier.
     */
    public String getName() {
        return name;
    }

    /**
     * Récupère la couleur de fond.
     * 
     * @return la couleur de fond.
     */
    public Color getBgColor() {
        return bg_color;
    }

    /**
     * Récupère la couleur d'avant plan.
     * 
     * @return la couleur d'avant plan.
     */
    public Color getFgColor() {
        return fg_color;
    }

    /**
     * Récupère la police d'écriture.
     * 
     * @return la police d'écriture.
     */
    public Font getFont() {
        return font;
    }

    public String getResourcePath(String res) {
        return "/com/hashi/theme/" + name + "/" + res;
    }

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

    public ImageIcon getImageResource(String image_url) {
        return new ImageIcon(Style.class.getResource(image_url));
    }

    protected abstract void initPanel(Panel panel);

    protected abstract void paintPanel(Panel panel, Graphics2D g);

    protected abstract void paintPanelBorder(Panel panel, Graphics2D g);

    protected abstract void initButton(Button button);

    protected abstract void paintButton(Button button, Graphics2D g);

    protected abstract void paintButtonBorder(Button button, Graphics2D g);

    protected abstract void initLabel(Label label);

    protected abstract void paintLabel(Label label, Graphics2D g);

    protected abstract void paintLabelBorder(Label label, Graphics2D g);

    protected abstract <E> void initComboBox(ComboBox<E> combo_box);

    protected abstract <E> void paintComboBox(ComboBox<E> combo_box, Graphics2D g);

    protected abstract <E> void paintComboBoxBorder(ComboBox<E> combo_box, Graphics2D g);

    protected abstract void initTextField(TextField text_field);

    protected abstract void paintTextField(TextField text_field, Graphics2D g);

    protected abstract void paintTextFieldBorder(TextField text_field, Graphics2D g);
}