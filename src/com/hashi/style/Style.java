package com.hashi.style;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;

public abstract class Style {
    protected Color bg_color;
    protected Color fg_color;
    protected Font font;

    public Color getBgColor() {
        return bg_color;
    }

    public Color getFgColor() {
        return fg_color;
    }

    public Font getFont() {
        return font;
    }

    public BufferedImage getImage(String image_res) {
        BufferedImage image = null;

        try {                
            image = ImageIO.read(new File(image_res));
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