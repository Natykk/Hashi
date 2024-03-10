package com.hashi.style;

import java.awt.Image;

/**
 * Interface pour définir un élément contenant une
 * {@link com.hashi.style.Image} et faire appel à celle-ci.
 * 
 * @param <T> Le type de l'élément qui implémente
 *            {@link com.hashi.style.ImageComponent}
 */
public interface ImageComponent<T> {
    /**
     * Définis le chemin relatif de l'image dans les ressources d'un
     * {@link com.hashi.style.Style} et la charge en mémoire.
     * 
     * @param image_res chemin relatif de l'image.
     * @return Retourne un élément {@link com.hashi.style.ImageComponent} afin de
     *         pouvoir chainer les appels de fonctions.
     */
    public T setImage(String image_res);

    /**
     * Récupère l'image selon le {@link com.hashi.style.Style} actif dans le
     * {@link com.hashi.style.StyleManager}.
     * 
     * @return L'{@link java.awt.Image} demandée.
     */
    public Image getImage();
}
