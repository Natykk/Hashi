package com.hashi.style;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

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
    public String getResourcePath(String res) {
        return style.getResourcePath(res);
    }

    public void initPanel(Panel panel) {
        style.initPanel(panel);
    }

    public void paintPanel(Panel panel, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        style.paintPanel(panel, g);
    }

    public void paintPanelBorder(Panel panel, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        style.paintPanelBorder(panel, g);
    }

    public void initButton(Button button) {
        style.initButton(button);
    }

    public void paintButton(Button button, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        style.paintButton(button, g);
    }

    public void paintButtonBorder(Button button, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        style.paintButtonBorder(button, g);
    }

    public void initLabel(Label label) {
        style.initLabel(label);
    }

    public void paintLabel(Label label, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        style.paintLabel(label, g);
    }

    public void paintLabelBorder(Label label, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        style.paintLabelBorder(label, g);
    }

    public <E> void initComboBox(ComboBox<E> comboBox) {
        style.initComboBox(comboBox);
    }

    public <E> void paintComboBox(ComboBox<E> combo_box, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        style.paintComboBox(combo_box, g);
    }

    public <E> void paintComboBoxBorder(ComboBox<E> combo_box, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        style.paintComboBoxBorder(combo_box, g);
    }

    public void initTextField(TextField text_field) {
        style.initTextField(text_field);
    }

    public void paintTextField(TextField text_field, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        style.paintTextField(text_field, g);
    }

    public void paintTextFieldBorder(TextField text_field, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        style.paintTextFieldBorder(text_field, g);
    }
}