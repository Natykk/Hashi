package com.hashi.style;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class SchoolStyle extends Style {
    public SchoolStyle() {
        name = "school";
        bg_color = Color.white;
        fg_color = Color.black;
        font = getFontResource(getResourcePath("kindergarten.ttf"));
    }

    public void paintPanel(Panel panel, Graphics2D g) {
        if (panel.image != null) {
            g.drawImage(panel.image, 0, 0, panel.getWidth(), panel.getHeight(), null);
        }
    }

    public void paintButton(Button button, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(font.deriveFont(0, 20));
        g.setColor(fg_color);
        g.drawString(
            button.getText(),
            (button.getWidth() - g.getFontMetrics().stringWidth(button.getText())) / 2,
            (button.getHeight() - g.getFontMetrics().getAscent()) / 2 + g.getFontMetrics().getAscent()
        );
    }
    
    public void paintLabel(Label label, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(font.deriveFont(0, 15));
        g.setColor(fg_color);
        g.drawString(
            label.getText(),
            (label.getWidth() - g.getFontMetrics().stringWidth(label.getText())) / 2,
            (label.getHeight() - g.getFontMetrics().getAscent()) / 2 + g.getFontMetrics().getAscent()
        );
    }
    
    public <E> void paintComboBox(ComboBox<E> combo_box, Graphics2D g) {
        
    }
    
    public void paintTextField(TextField text_field, Graphics2D g) {
        
    }
}