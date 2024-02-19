package com.hashi.style;

import java.awt.Graphics2D;

import com.hashi.Language;

public class StyleWrapper extends Style {
    private Style style;
    private Language language;

    public StyleWrapper(Style style, Language language) {
        this.language = language;
        switchStyle(style);
    }

    public void switchStyle(Style style) {
        this.style = style;
        bg_color = style.bg_color;
        fg_color = style.fg_color;
        font = style.font;
    }

    public Language getLanguage() {
        return language;
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
        style.paintPanel(panel, g);
    }

    public void initButton(Button button) {
        style.initButton(button);
    }

    public void paintButton(Button button, Graphics2D g) {
        style.paintButton(button, g);
    }

    public void initLabel(Label label) {
        style.initLabel(label);
    }

    public void paintLabel(Label label, Graphics2D g) {
        style.paintLabel(label, g);
    }

    public <E> void initComboBox(ComboBox<E> comboBox) {
        style.initComboBox(comboBox);
    }

    public <E> void paintComboBox(ComboBox<E> combo_box, Graphics2D g) {
        style.paintComboBox(combo_box, g);
    }

    public void initTextField(TextField text_field) {
        style.initTextField(text_field);
    }

    public void paintTextField(TextField text_field, Graphics2D g) {
        style.paintTextField(text_field, g);
    }
}