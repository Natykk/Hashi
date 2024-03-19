package com.hashi.style;

/**
 * Interface gérant la taille de police d'un élément.
 * 
 * @param <T> Le type de l'élément qui implémente
 *            {@link com.hashi.style.FontSize}
 */
public interface FontSize<T> {
    /**
     * Définis la taille de police.
     * 
     * @param size la taille de police a utilisé.
     * @return Retourne un élément {@link com.hashi.style.FontSize} afin de pouvoir
     *         chainer les appels de fonctions.
     */
    public T setFontSize(int size);

    /**
     * Récupère la taille de police actuelle.
     * 
     * @return Retourne la taille de police actuelle.
     */
    public int getFontSize();
}
