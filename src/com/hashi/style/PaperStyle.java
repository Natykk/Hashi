package com.hashi.style;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;

public class PaperStyle extends Style {
    public PaperStyle() {
        bg_color = Color.white;
        fg_color = Color.black;
        font = new Font(Font.SANS_SERIF, Font.BOLD, 12);
    }

    public void paintPanel(Panel panel, Graphics g) {
        
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