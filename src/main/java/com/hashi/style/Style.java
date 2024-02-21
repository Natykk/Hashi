package com.hashi.style;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.swing.ImageIcon;

public abstract class Style {
    protected String name;
    protected Color bg_color;
    protected Color fg_color;
    protected Font font;

    public String getName() {
        return name;
    }

    public Color getBgColor() {
        return bg_color;
    }

    public Color getFgColor() {
        return fg_color;
    }

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

    public abstract void initPanel(Panel panel);

    public abstract void paintPanel(Panel panel, Graphics2D g);

    public abstract void paintPanelBorder(Panel panel, Graphics2D g);

    public abstract void initButton(Button button);

    public abstract void paintButton(Button button, Graphics2D g);

    public abstract void paintButtonBorder(Button button, Graphics2D g);

    public abstract void initLabel(Label label);

    public abstract void paintLabel(Label label, Graphics2D g);

    public abstract void paintLabelBorder(Label label, Graphics2D g);

    public abstract <E> void initComboBox(ComboBox<E> combo_box);

    public abstract <E> void paintComboBox(ComboBox<E> combo_box, Graphics2D g);

    public abstract <E> void paintComboBoxBorder(ComboBox<E> combo_box, Graphics2D g);

    public abstract void initTextField(TextField text_field);

    public abstract void paintTextField(TextField text_field, Graphics2D g);

    public abstract void paintTextFieldBorder(TextField text_field, Graphics2D g);
}