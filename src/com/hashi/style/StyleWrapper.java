package com.hashi.style;

import java.awt.Graphics2D;
import java.net.URL;

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

    @Override
    public String getName() {
        return style.getName();
    }

    @Override
    public URL getResourcePath(String res) {
        return style.getResourcePath(res);
    }

    public void paintPanel(Panel panel, Graphics2D g) {
        style.paintPanel(panel, g);
    }

    public void paintButton(Button button, Graphics2D g) {
        style.paintButton(button, g);
    }
    
    public void paintLabel(Label label, Graphics2D g) {
        style.paintLabel(label, g);
    }
    
    public <E> void paintComboBox(ComboBox<E> combo_box, Graphics2D g) {
        style.paintComboBox(combo_box, g);
    }
    
    public void paintTextField(TextField text_field, Graphics2D g) {
        style.paintTextField(text_field, g);
    }
}