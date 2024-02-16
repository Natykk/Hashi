package com.hashi.style;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

public class TextField extends JTextField {
    private StyleWrapper style;

    public TextField(StyleWrapper style) {
        super();

        this.style = style;

        style.initTextField(this);
    }

    public TextField(StyleWrapper style, String text) {
        super(style.getLanguage().getString(text));

        this.style = style;

        style.initTextField(this);
    }

    public TextField(StyleWrapper style, int columns) {
        super(columns);

        this.style = style;

        style.initTextField(this);
    }

    public TextField(StyleWrapper style, String text, int columns) {
        super(style.getLanguage().getString(text), columns);

        this.style = style;

        style.initTextField(this);
    }

    protected void paintComponent(Graphics g) {
        style.paintTextField(this, (Graphics2D) g);
    }
}