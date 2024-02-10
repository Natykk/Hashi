package com.hashi.style;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

public class Button extends JButton {
    private StyleWrapper style;

    public Button(StyleWrapper style) {
        super();

        this.style = style;
    }
    
    public Button(StyleWrapper style, Action a) {
        super(a);

        this.style = style;
    }
    
    public Button(StyleWrapper style, String text) {
        super(text);

        this.style = style;
    }

    protected void paintComponent(Graphics g) {
        style.paintButton(this, (Graphics2D)g);
    }
}