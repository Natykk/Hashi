package com.hashi.style;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;

public class SchoolStyle extends Style {
    private Image button_border_image;

    public SchoolStyle() {
        name = "school";
        bg_color = Color.decode("#e4e099");
        fg_color = Color.decode("#585510");
        font = getFontResource(getResourcePath("kindergarten.ttf"));
        button_border_image = getImageResource(getResourcePath("btn-border.png")).getImage();
    }

    private <T extends Component & ImageComponent<?>> void drawImage(T image_component, Graphics2D g,
            boolean contained) {
        if (image_component.getImage() != null) {
            float panel_ratio = (float) image_component.getWidth() / (float) image_component.getHeight();
            float image_ratio = (float) image_component.getImage().getWidth(null)
                    / (float) image_component.getImage().getHeight(null);

            if ((!contained && panel_ratio < image_ratio) || (contained && panel_ratio >= image_ratio)) {
                int width = (int) (image_ratio * image_component.getHeight());

                g.drawImage(image_component.getImage(), (image_component.getWidth() - width) / 2, 0, width,
                        image_component.getHeight(),
                        null);
            } else {
                int height = (int) (image_component.getWidth() / image_ratio);

                g.drawImage(image_component.getImage(), 0, (image_component.getHeight() - height) / 2,
                        image_component.getWidth(), height,
                        null);
            }
        }
    }

    protected void initPanel(Panel panel) {

    }

    protected void paintPanel(Panel panel, Graphics2D g) {
        drawImage(panel, g, false);
    }

    protected void paintPanelBorder(Panel panel, Graphics2D g) {

    }

    protected void initButton(Button button) {
        button.setFont(font.deriveFont(0, button.getFontSize()));
    }

    protected void paintButton(Button button, Graphics2D g) {
        drawImage(button, g, true);

        g.setFont(font.deriveFont(0, button.getFontSize()));
        g.setColor(fg_color);
        g.drawString(
                button.getText(),
                (button.getWidth() - g.getFontMetrics().stringWidth(button.getText())) / 2,
                (button.getHeight() - g.getFontMetrics().getAscent()) / 2 + g.getFontMetrics().getAscent());
    }

    protected void paintButtonBorder(Button button, Graphics2D g) {
        if (button.getImage() == null)
            g.drawImage(button_border_image, 0, 0, button.getWidth(), button.getHeight(), null);
    }

    protected void initLabel(Label label) {
        label.setFont(font.deriveFont(0, label.getFontSize()));
    }

    protected void paintLabel(Label label, Graphics2D g) {
        g.setFont(font.deriveFont(0, label.getFontSize()));
        g.setColor(fg_color);
        g.drawString(
                label.getText(),
                (label.getWidth() - g.getFontMetrics().stringWidth(label.getText())) / 2,
                (label.getHeight() - g.getFontMetrics().getAscent()) / 2 + g.getFontMetrics().getAscent());
    }

    protected void paintLabelBorder(Label label, Graphics2D g) {

    }

    protected <E> void initComboBox(ComboBox<E> combo_box) {
        combo_box.setFont(font.deriveFont(0, combo_box.getFontSize()));
    }

    protected <E> void paintComboBox(ComboBox<E> combo_box, Graphics2D g) {
        g.setFont(font.deriveFont(0, combo_box.getFontSize()));
        g.setColor(fg_color);
        g.drawString(
                combo_box.getSelectedItem().toString(),
                20,
                (combo_box.getHeight() - g.getFontMetrics().getAscent()) / 2 + g.getFontMetrics().getAscent() - 5);
    }

    protected <E> void paintComboBoxBorder(ComboBox<E> combo_box, Graphics2D g) {
        g.drawImage(button_border_image, 0, 0, combo_box.getWidth(), combo_box.getHeight(), null);
    }

    protected void initTextField(TextField text_field) {
        text_field.setFont(font.deriveFont(0, text_field.getFontSize()));
    }

    protected void paintTextField(TextField text_field, Graphics2D g) {
        int margin = 2;

        g.setFont(font.deriveFont(0, text_field.getFontSize()));
        g.setColor(bg_color);
        g.fillRect(0, 0, text_field.getWidth(), text_field.getHeight());
        g.setColor(fg_color);
        g.drawString(
                text_field.getText(),
                text_field.getWidth() - Math.max(g.getFontMetrics().stringWidth(text_field.getText()) + margin * 2,
                        text_field.getWidth()) + margin,
                (text_field.getHeight() - g.getFontMetrics().getAscent()) / 2 + g.getFontMetrics().getAscent());

        text_field.getCaret().paint(g);
    }

    protected void paintTextFieldBorder(TextField text_field, Graphics2D g) {
        g.drawImage(button_border_image, -text_field.getColumns() / 2, 0,
                text_field.getWidth() + text_field.getColumns(), text_field.getHeight(), null);
    }
}