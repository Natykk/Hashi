package com.hashi.style;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * La classe {@link com.hashi.style.StyleManager} est un singleton qui permet de
 * changer le {@link com.hashi.style.Style} utiliser pour le rendue de
 * l'application.<br>
 * <br>
 * 
 * Le {@link com.hashi.style.Style} qui est instancié par défaut est le
 * {@link com.hashi.style.SchoolStyle}.
 */
public class StyleManager extends Style {
    private static StyleManager instance;
    private Style style;

    private StyleManager() {
        this.style = new SchoolStyle();
        this.bg_color = this.style.bg_color;
        this.fg_color = this.style.fg_color;
        this.font = this.style.font;
    }

    /**
     * Récupère l'instance de la classe {@link com.hashi.style.StyleManager} et la
     * créer si elle n'existe pas.
     * 
     * @return Retourne l'instance de {@link com.hashi.style.StyleManager}.
     */
    public static StyleManager getInstance() {
        if (instance == null)
            instance = new StyleManager();

        return instance;
    }

    /**
     * Définit le nouveau {@link com.hashi.style.Style} à utiliser.
     * 
     * @param style le {@link com.hashi.style.Style} à utiliser.
     */
    public static void setStyle(Style style) {
        getInstance().style = style;
        getInstance().bg_color = style.bg_color;
        getInstance().fg_color = style.fg_color;
        getInstance().font = style.font;
    }

    @Override
    public String getName() {
        return style.getName();
    }

    @Override
    public String getResourcePath(String res) {
        return style.getResourcePath(res);
    }

    @Override
    protected void initPanel(Panel panel) {
        style.initPanel(panel);
    }

    @Override
    protected void paintPanel(Panel panel, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        style.paintPanel(panel, g);
    }

    @Override
    protected void paintPanelBorder(Panel panel, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        style.paintPanelBorder(panel, g);
    }

    @Override
    protected void initButton(Button button) {
        style.initButton(button);
    }

    @Override
    protected void paintButton(Button button, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        style.paintButton(button, g);
    }

    @Override
    protected void paintButtonBorder(Button button, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        style.paintButtonBorder(button, g);
    }

    @Override
    protected void initLabel(Label label) {
        style.initLabel(label);
    }

    @Override
    protected void paintLabel(Label label, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        style.paintLabel(label, g);
    }

    @Override
    protected void paintLabelBorder(Label label, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        style.paintLabelBorder(label, g);
    }

    @Override
    protected <E> void initComboBox(ComboBox<E> comboBox) {
        style.initComboBox(comboBox);
    }

    @Override
    protected <E> void paintComboBox(ComboBox<E> combo_box, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        style.paintComboBox(combo_box, g);
    }

    @Override
    protected <E> void paintComboBoxBorder(ComboBox<E> combo_box, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        style.paintComboBoxBorder(combo_box, g);
    }

    @Override
    protected void initTextField(TextField text_field) {
        style.initTextField(text_field);
    }

    @Override
    protected void paintTextField(TextField text_field, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        style.paintTextField(text_field, g);
    }

    @Override
    protected void paintTextFieldBorder(TextField text_field, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        style.paintTextFieldBorder(text_field, g);
    }
}