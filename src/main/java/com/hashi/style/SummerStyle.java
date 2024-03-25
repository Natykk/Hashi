package com.hashi.style;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 * La classe {@link com.hashi.style.SummerStyle} applique un thème été à la
 * plage aux éléments de l'application.
 */
public class SummerStyle extends Style {
    private Image button_background_image;

    public SummerStyle() {
        name = "summer";
        bg_color = Color.decode("#f99d03");
        fg_color = Color.decode("#8e1d00");
        font = getFontResource(getResourcePath("BowlbyOneSC-Regular.ttf"));
        button_background_image = getImageResource(getResourcePath("btn-bg.png")).getImage();
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
                        image_component);
            } else {
                int height = (int) (image_component.getWidth() / image_ratio);

                g.drawImage(image_component.getImage(), 0, (image_component.getHeight() - height) / 2,
                        image_component.getWidth(), height,
                        image_component);
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

        if (button.getImage() == null)
            g.drawImage(button_background_image, 0, 0, button.getWidth(), button.getHeight(), button);

        g.setFont(font.deriveFont(0, button.getFontSize()));
        g.setColor(fg_color);
        g.drawString(
                button.getText(),
                (button.getWidth() - g.getFontMetrics().stringWidth(button.getText())) / 2,
                (int) (button.getHeight() / 1.5));
    }

    protected void paintButtonBorder(Button button, Graphics2D g) {

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
                (int) (label.getHeight() / 1.5));
    }

    protected void paintLabelBorder(Label label, Graphics2D g) {

    }

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

    protected <E> void paintComboBox(ComboBox<E> combo_box, Graphics2D g) {
        g.drawImage(button_background_image, 0, 0, combo_box.getWidth(), combo_box.getHeight(), combo_box);
        g.setFont(font.deriveFont(0, combo_box.getFontSize()));
        g.setColor(fg_color);
        g.drawString(
                combo_box.getSelectedItem().toString(),
                20,
                (combo_box.getHeight() - g.getFontMetrics().getAscent()) / 2 + g.getFontMetrics().getAscent() - 5);
    }

    protected <E> void paintComboBoxBorder(ComboBox<E> combo_box, Graphics2D g) {

    }

    protected void initTextField(TextField text_field) {
        text_field.setFont(font.deriveFont(0, text_field.getFontSize()));
    }

    protected void paintTextField(TextField text_field, Graphics2D g) {
        int margin = 2;

        g.drawImage(button_background_image, 0, 0, text_field.getWidth(), text_field.getHeight(), text_field);
        g.setFont(font.deriveFont(0, text_field.getFontSize()));
        g.setColor(fg_color);
        g.drawString(
                text_field.getText(),
                text_field.getWidth() - Math.max(g.getFontMetrics().stringWidth(text_field.getText()) + margin * 2,
                        text_field.getWidth()) + margin,
                (int) (text_field.getHeight() / 1.5));

        text_field.getCaret().paint(g);
    }

    protected void paintTextFieldBorder(TextField text_field, Graphics2D g) {

    }
}