package com.hashi.style;

import java.awt.Component;

import javax.swing.ImageIcon;

/**
 * Gère la récupération d'une image dépendent du
 * {@link com.hashi.style.Style} actif dans le
 * {@link com.hashi.style.StyleManager}.
 */
public class Image {
    private String image_res;
    private String image_url;
    private ImageIcon image;
    private Component parent;

    /**
     * Créer une image pour un {@link java.awt.Component}.
     * 
     * @param parent le {@link java.awt.Component} contenant cette {@link Image}.
     */
    public Image(Component parent) {
        this.parent = parent;
    }

    /**
     * Définis le chemin relatif de l'image dans les ressources d'un
     * {@link com.hashi.style.Style} et la charge en mémoire.
     * 
     * @param image_res chemin relatif de l'image.
     */
    public void setImage(String image_res) {
        if (image_res == null) {
            image = null;

            return;
        }

        this.image_res = image_res;
        image_url = StyleManager.getInstance().getResourcePath(image_res);
        image = StyleManager.getInstance().getImageResource(image_url);

        parent.repaint();
    }

    /**
     * Récupère l'image selon le {@link com.hashi.style.Style} actif dans le
     * {@link com.hashi.style.StyleManager}.
     * 
     * @return L'{@link java.awt.Image} demandée.
     */
    public java.awt.Image getImage() {
        if (image != null) {
            String new_image_url = StyleManager.getInstance().getResourcePath(image_res);

            if (image_url != new_image_url) {
                image_url = new_image_url;
                image = StyleManager.getInstance().getImageResource(image_url);
            }

            return image.getImage();
        }

        return null;
    }
}
