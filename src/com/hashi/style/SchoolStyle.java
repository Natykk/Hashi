package com.hashi.style;

import java.awt.Color;
import java.awt.Graphics;

public class SchoolStyle extends Style {
    public SchoolStyle() {
        name = "school";
        bg_color = Color.white;
        fg_color = Color.black;
        font = getFontResource("kindergarten.ttf");
    }

    public void paintPanel(Panel panel, Graphics g) {
        if (panel.image != null) {
            g.drawImage(panel.image, 0, 0, panel.getWidth(), panel.getHeight(), null);
        }
    }

    public void paintButton(Button button, Graphics g) {

    }
    
    public void paintLabel(Label label, Graphics g) {
        
    }
    
    public <E> void paintComboBox(ComboBox<E> combo_box, Graphics g) {
        
    }
    
    public void paintTextField(TextField text_field, Graphics g) {
        
    }
}