package com.hashi.style;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

public class TextField extends JTextField {
    private StyleWrapper style;

    public TextField(StyleWrapper style) {
        super();

        this.style = style;
    }
    
    public TextField(StyleWrapper style, String text) {
        super(text);

        this.style = style;
    }
    
    public TextField(StyleWrapper style, int columns) {
        super(columns);

        this.style = style;
    }
    
    public TextField(StyleWrapper style, String text, int columns) {
        super(text, columns);

        this.style = style;
    }

    protected void paintComponent(Graphics g) {
        style.paintTextField(this, (Graphics2D)g);
    }
}