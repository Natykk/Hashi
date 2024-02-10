package com.hashi.style;

import java.awt.Color;
import java.awt.Graphics2D;

public class SummerStyle extends Style {
    public SummerStyle() {
        name = "summer";
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

    }

    public void paintLabel(Label label, Graphics2D g) {

    }

    public <E> void paintComboBox(ComboBox<E> combo_box, Graphics2D g) {

    }

    public void paintTextField(TextField text_field, Graphics2D g) {

    }
}