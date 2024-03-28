package com.hashi.style;

/**
 * Interface permettant de désactiver tous traitements fait sur le texte.
 */
public interface AsRawText<T> {
    /**
     * Désactive tous traitements fait sur le texte.
     * 
     * @return Retourne un élément {@link com.hashi.style.AsRawText} afin de pouvoir
     *         chainer les appels de fonctions.
     */
    public T setAsRawText();
}
