package com.hashi.style;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;

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

    protected String getResourcePath(String res) {
        return ClassLoader.getSystemResource("res/" + name +  "/" + res).getFile();
    }

    public Font getFontResource(String font_res) {
        Font font = null;

        try {                
            font = Font.createFont(Font.TRUETYPE_FONT, new File(getResourcePath(font_res)));
        } catch (FontFormatException e) {
            System.err.println("Impossible de charger la police mauvais format <" + font_res + "> : " + e);
        } catch (IOException e) {
            System.err.println("Impossible de charger la police à partie de la ressource <" + font_res + "> : " + e);
        }

        return font;
    }

    public BufferedImage getImageResource(String image_res) {
        BufferedImage image = null;

        try {                
            image = ImageIO.read(new File(getResourcePath(image_res)));
        } catch (IOException e) {
            System.err.println("Impossible de charger l'image à partie de la ressource <" + image_res + "> : " + e);
        }

        return image;
    }

    public abstract void paintPanel(Panel panel, Graphics g);

    public abstract void paintButton(Button button, Graphics g);
    
    public abstract void paintLabel(Label label, Graphics g);

    public abstract <E> void paintComboBox(ComboBox<E> combo_box, Graphics g);

    public abstract void paintTextField(TextField text_field, Graphics g);
}