package com.hashi.style;

import java.awt.Graphics;

public class StyleWrapper extends Style {
    private Style style;

    public StyleWrapper(Style style) {
        switchStyle(style);
    }

    public void switchStyle(Style style) {
        this.style = style;
        bg_color = style.bg_color;
        fg_color = style.fg_color;
        font = style.font;
    }

    public void paintPanel(Panel panel, Graphics g) {
        style.paintPanel(panel, g);
    }

    public void paintButton(Button button, Graphics g) {
        style.paintButton(button, g);
    }
    
    public void paintLabel(Label label, Graphics g) {
        style.paintLabel(label, g);
    }
    
    public <E> void paintComboBox(ComboBox<E> combo_box, Graphics g) {
        style.paintComboBox(combo_box, g);
    }
    
    public void paintTextField(TextField text_field, Graphics g) {
        style.paintTextField(text_field, g);
    }
}