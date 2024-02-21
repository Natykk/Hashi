package com.hashi.style;

import java.awt.Component;

import javax.swing.ImageIcon;

public class Image {
    private String image_res;
    private String image_url;
    private ImageIcon image;
    private Component parent;

    public Image(Component parent) {
        this.parent = parent;
    }

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
