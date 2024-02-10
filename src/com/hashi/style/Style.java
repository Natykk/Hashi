package com.hashi.style;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
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

    public URL getResourcePath(String res) {
        return ClassLoader.getSystemResource("res/" + name +  "/" + res);
    }

    public Font getFontResource(URL font_url) {
        Font font = null;

        try {                
            font = Font.createFont(Font.TRUETYPE_FONT, new File(font_url.getFile()));
        } catch (FontFormatException e) {
            System.err.println("Impossible de charger la police mauvais format <" + font_url + "> : " + e);
        } catch (IOException e) {
            System.err.println("Impossible de charger la police à partie de la ressource <" + font_url + "> : " + e);
        }

        return font;
    }

    public BufferedImage getImageResource(URL image_url) {
        BufferedImage image = null;

        try {                
            image = ImageIO.read(new File(image_url.getFile()));
        } catch (IOException e) {
            System.err.println("Impossible de charger l'image à partie de la ressource <" + image_url + "> : " + e);
        }

        return image;
    }

    public abstract void paintPanel(Panel panel, Graphics2D g);

    public abstract void paintButton(Button button, Graphics2D g);
    
    public abstract void paintLabel(Label label, Graphics2D g);

    public abstract <E> void paintComboBox(ComboBox<E> combo_box, Graphics2D g);

    public abstract void paintTextField(TextField text_field, Graphics2D g);
}