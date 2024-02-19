package com.hashi.style;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;

import javax.swing.*;

public class Panel extends JPanel {
    protected String image_res;
    protected String image_url;
    protected ImageIcon image;
    private StyleWrapper style;

    public Panel(StyleWrapper style) {
        super();
        init(style);
    }

    public Panel(StyleWrapper style, LayoutManager layout) {
        super(layout);
        init(style);
    }

    public Panel(StyleWrapper style, LayoutManager layout, String image_res) {
        super(layout);
        init(style);
        setImage(image_res);
    }

    private void init(StyleWrapper style) {
        this.style = style;

        style.initPanel(this);
    }

    public void setImage(String image_res) {
        if (image_res == null) {
            this.image = null;

            return;
        }

        this.image_res = image_res;
        this.image_url = style.getResourcePath(image_res);
        this.image = style.getImageResource(image_url);

        repaint();
    }

    protected void paintComponent(Graphics g) {
        if (image != null) {
            String new_image_url = style.getResourcePath(image_res);

            if (image_url != new_image_url) {
                image_url = new_image_url;
                image = style.getImageResource(image_url);
            }
        }

        style.paintPanel(this, (Graphics2D) g);
    }
}