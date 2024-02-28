package com.hashi.style;

import java.awt.Image;

public interface ImageComponent<T> {
    public T setImage(String image_res);

    public Image getImage();
}
