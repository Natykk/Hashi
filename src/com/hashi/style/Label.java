package com.hashi.style;

import java.awt.Graphics;
import javax.swing.*;

public class Label extends JLabel {
    private StyleWrapper style;

    public Label(StyleWrapper style) {
        super();

        this.style = style;
    }
    
    public Label(StyleWrapper style, String text) {
        super(text);

        this.style = style;
    }
    
    public Label(StyleWrapper style, String text, int horizontal_alignment) {
        super(text, horizontal_alignment);

        this.style = style;
    }

    protected void paintComponent(Graphics g) {
        style.paintLabel(this, g);
    }
}