package com.hashi.style;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 * La classe {@link com.hashi.style.SchoolStyle} applique thème un écolier,
 * papier crayon aux éléments de l'application.
 */
public class SchoolStyle extends Style {
    private Image button_border_image;

    public SchoolStyle() {
        name = "school";
        bg_color = Color.decode("#e4e099");
        fg_color = Color.decode("#585510");
        font = getFontResource(getResourcePath("kindergarten.ttf"));
        button_border_image = getImageResource(getResourcePath("btn-border.png")).getImage();
    }

    @Override
    protected void initPanel(Panel panel) {

    }

    @Override
    protected void paintPanel(Panel panel, Graphics2D g) {
        drawImage(panel, g, false);
    }

    @Override
    protected void paintPanelBorder(Panel panel, Graphics2D g) {

    }

    @Override
    protected void initButton(Button button) {
        button.setFont(font.deriveFont(0, button.getFontSize()));
    }

    @Override
    protected void paintButton(Button button, Graphics2D g) {
        drawImage(button, g, true);

        g.setFont(font.deriveFont(0, button.getFontSize()));
        g.setColor(fg_color);
        g.drawString(
                button.getText(),
                (button.getWidth() - g.getFontMetrics().stringWidth(button.getText())) / 2,
                (button.getHeight() - g.getFontMetrics().getAscent()) / 2 + g.getFontMetrics().getAscent());
    }

    @Override
    protected void paintButtonBorder(Button button, Graphics2D g) {
        if (button.getImage() == null)
            g.drawImage(button_border_image, 0, 0, button.getWidth(), button.getHeight(), button);
    }

    @Override
    protected void initLabel(Label label) {
        label.setFont(font.deriveFont(0, label.getFontSize()));
    }

    @Override
    protected void paintLabel(Label label, Graphics2D g) {
        g.setFont(font.deriveFont(0, label.getFontSize()));
        g.setColor(fg_color);
        g.drawString(
                label.getText(),
                (label.getWidth() - g.getFontMetrics().stringWidth(label.getText())) / 2,
                (label.getHeight() - g.getFontMetrics().getAscent()) / 2 + g.getFontMetrics().getAscent());
    }

    @Override
    protected void paintLabelBorder(Label label, Graphics2D g) {

    }

    @Override
    protected <E> void initComboBox(ComboBox<E> combo_box) {
        combo_box.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                return new JButton() {
                    @Override
                    public int getWidth() {
                        return 0;
                    }
                };
            }
        });
        combo_box.setFont(font.deriveFont(0, combo_box.getFontSize()));
    }

    @Override
    protected <E> void paintComboBox(ComboBox<E> combo_box, Graphics2D g) {
        g.setFont(font.deriveFont(0, combo_box.getFontSize()));
        g.setColor(fg_color);
        g.drawString(
                combo_box.getSelectedItem().toString(),
                20,
                (combo_box.getHeight() - g.getFontMetrics().getAscent()) / 2 + g.getFontMetrics().getAscent() - 5);
    }

    @Override
    protected <E> void paintComboBoxBorder(ComboBox<E> combo_box, Graphics2D g) {
        g.drawImage(button_border_image, 0, 0, combo_box.getWidth(), combo_box.getHeight(), combo_box);
    }

    @Override
    protected void initTextField(TextField text_field) {
        text_field.setFont(font.deriveFont(0, text_field.getFontSize()));
    }

    @Override
    protected void paintTextField(TextField text_field, Graphics2D g) {
        int margin = 2;

        g.setFont(font.deriveFont(0, text_field.getFontSize()));
        g.setColor(fg_color);
        g.drawString(
                text_field.getText(),
                text_field.getWidth() - Math.max(g.getFontMetrics().stringWidth(text_field.getText()) + margin * 2,
                        text_field.getWidth()) + margin,
                (text_field.getHeight() - g.getFontMetrics().getAscent()) / 2 + g.getFontMetrics().getAscent());

        text_field.getCaret().paint(g);
    }

    @Override
    protected void paintTextFieldBorder(TextField text_field, Graphics2D g) {
        g.drawImage(button_border_image, -text_field.getColumns() / 2, 0,
                text_field.getWidth() + text_field.getColumns(), text_field.getHeight(), text_field);
    }
}