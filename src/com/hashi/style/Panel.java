package com.hashi.style;

import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Panel extends JPanel {
    protected BufferedImage image;
    private StyleWrapper style;

    public Panel(StyleWrapper style, LayoutManager layout) {
        super(layout);

        this.style = style;
    }

    public Panel(StyleWrapper style, LayoutManager layout, String image) {
        super(layout);

        this.style = style;
    }
    
    protected void paintComponent(Graphics g) {
        style.paintPanel(this, g);
    }
}