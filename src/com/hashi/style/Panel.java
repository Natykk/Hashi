package com.hashi.style;

import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Panel extends JPanel {
    protected String image_res;
    protected BufferedImage image;
    protected String style_name;
    private StyleWrapper style;

    public Panel(StyleWrapper style, LayoutManager layout) {
        super(layout);

        this.style = style;
    }

    public Panel(StyleWrapper style, LayoutManager layout, String image_res) {
        super(layout);

        this.style_name = style.getName();
        this.style = style;
        this.image_res = image_res;
        this.image = style.getImageResource(image_res);
    }
    
    protected void paintComponent(Graphics g) {
        if (image_res != null && style_name != style.getName()) {
            image = style.getImageResource(image_res);
            style_name = style.getName();
        }

        style.paintPanel(this, g);
    }
}