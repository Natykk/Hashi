package com.hashi.style;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class SchoolStyle extends Style {
    public SchoolStyle() {
        name = "school";
        bg_color = Color.decode("#e4e099");
        fg_color = Color.decode("#585510");
        font = getFontResource(getResourcePath("kindergarten.ttf"));
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

    public void initPanel(Panel panel) {

    }

    public void paintPanel(Panel panel, Graphics2D g) {
        drawImage(panel, g, false);
    }

    public void initButton(Button button) {
        button.setFont(font.deriveFont(0, button.getFontSize()));
    }

    public void paintButton(Button button, Graphics2D g) {
        drawImage(button, g, true);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(font.deriveFont(0, button.getFontSize()));
        g.setColor(fg_color);
        g.drawString(
                button.getText(),
                (button.getWidth() - g.getFontMetrics().stringWidth(button.getText())) / 2,
                (button.getHeight() - g.getFontMetrics().getAscent()) / 2 + g.getFontMetrics().getAscent());
    }

    public void initLabel(Label label) {
        label.setFont(font.deriveFont(0, label.getFontSize()));
    }

    public void paintLabel(Label label, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(font.deriveFont(0, label.getFontSize()));
        g.setColor(fg_color);
        g.drawString(
                label.getText(),
                (label.getWidth() - g.getFontMetrics().stringWidth(label.getText())) / 2,
                (label.getHeight() - g.getFontMetrics().getAscent()) / 2 + g.getFontMetrics().getAscent());
    }

    public <E> void initComboBox(ComboBox<E> combo_box) {
        combo_box.setFont(font.deriveFont(0, combo_box.getFontSize()));
    }

    public <E> void paintComboBox(ComboBox<E> combo_box, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(font.deriveFont(0, combo_box.getFontSize()));
        g.setColor(fg_color);
        g.drawString(
                combo_box.getSelectedItem().toString(),
                5,
                (combo_box.getHeight() - g.getFontMetrics().getAscent()) / 2 + g.getFontMetrics().getAscent());
    }

    public void initTextField(TextField text_field) {
        text_field.setFont(font.deriveFont(0, text_field.getFontSize()));
    }

    public void paintTextField(TextField text_field, Graphics2D g) {
        int margin = 2;

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
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
}