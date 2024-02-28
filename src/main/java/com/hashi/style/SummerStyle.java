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

    protected void initPanel(Panel panel) {

    }

    protected void paintPanel(Panel panel, Graphics2D g) {
        if (panel.getImage() != null) {
            g.drawImage(panel.getImage(), 0, 0, panel.getWidth(), panel.getHeight(), null);
        }
    }

    protected void paintPanelBorder(Panel panel, Graphics2D g) {

    }

    protected void initButton(Button button) {

    }

    protected void paintButton(Button button, Graphics2D g) {

    }

    protected void paintButtonBorder(Button button, Graphics2D g) {

    }

    protected void initLabel(Label label) {

    }

    protected void paintLabel(Label label, Graphics2D g) {

    }

    protected void paintLabelBorder(Label label, Graphics2D g) {

    }

    protected <E> void initComboBox(ComboBox<E> combo_box) {

    }

    protected <E> void paintComboBox(ComboBox<E> combo_box, Graphics2D g) {

    }

    protected <E> void paintComboBoxBorder(ComboBox<E> combo_box, Graphics2D g) {

    }

    protected void initTextField(TextField text_field) {

    }

    protected void paintTextField(TextField text_field, Graphics2D g) {

    }

    protected void paintTextFieldBorder(TextField text_field, Graphics2D g) {

    }
}