package com.hashi.style;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.*;

public class Panel extends JPanel {
    protected String image_res;
    protected URL image_url;
    protected BufferedImage image;
    private StyleWrapper style;

    public Panel(StyleWrapper style, LayoutManager layout) {
        super(layout);

        this.style = style;
    }

    public Panel(StyleWrapper style, LayoutManager layout, String image_res) {
        super(layout);

        this.style = style;
        this.image_res = image_res;
        this.image_url = style.getResourcePath(image_res);
        this.image = style.getImageResource(image_url);
    }

    public void setImage(String image_res) {
        this.image_res = image_res;
        
        repaint();
    }
    
    protected void paintComponent(Graphics g) {
        if (image_url != style.getResourcePath(image_res)) {
            image_url = style.getResourcePath(image_res);
            image = style.getImageResource(image_url);
        }

        style.paintPanel(this, (Graphics2D)g);
    }
}