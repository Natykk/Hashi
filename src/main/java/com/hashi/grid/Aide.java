package com.hashi.grid;

import java.util.HashMap;
import java.util.Map;

public enum Aide {

	// pas d'aide trouvé
	RIEN, 

	/* 
	 * Technique 2
	 * une île de valeur 1 avec un seul voisin libre = compléter l'île avec 1 pont
	 * exemple: (@ = nombre quelconque)
	 * 1   @ -> 1 - @
	 */
	FORCE1,

	/*
	 * Technique 2
	 * une île de valeur 2 avec un seul voisin libre = compléter l'île avec 2 ponts
	 * exemple: (@ = nombre quelconque)
	 * 2   @ -> 2 = @
	 */
	FORCE2,

	/*
	 * Technique 3
	 * une île de valeur 3 avec 2 voisins libre = on peut ajouter 1 pont pour chaque voisin
	 * exemple: (@ = nombre quelconque)
	 * 	3   @		3 - @
	 * 			->	|
	 * 	@			@
	 */
	FORCE3,

	/*
	 * Technique 1
	 * une île de valeur 4 avec 2 voisins libre = compléter l'île avec 4 ponts
	 * exemple: (@ = nombre quelconque)
	 * 	4   @		4 = @
	 * 			->  ||
	 * 	@			@
	 */
	FORCE4,

	/*
	 * Technique 3
	 * une île de valeur 5 avec 3 voisins libre = on peut ajouter 1 pont pour chaque voisin
	 * exemple: (@ = nombre quelconque)
	 * 	@   5   @		@ - 5 - @
	 * 				->		|
	 * 		@				@
	 */
	FORCE5,

	/*
	 * Technique 1
	 * une île de valeur 6 avec 3 voisins libre = compléter l'île avec 6 ponts
	 * exemple: (@ = nombre quelconque)
	 * 	@   6   @		@ = 6 = @
	 * 				->		||
	 * 		@				@
	 */
	FORCE6,

	/*
	 * Technique 3
	 * une île de valeur 7 = on peut ajouter 1 pont pour chaque voisin
	 * exemple: (@ = nombre quelconque)
	 * 		@				@
	 * 						|
	 * 	@   7   @	->	@ - 7 - @
	 * 						|
	 * 		@				@
	 */
	FORCE7,

	/*
	 * Technique 1
	 * une île de valeur 8 = compléter l'île avec 8 ponts
	 * exemple: (@ = nombre quelconque)
	 * 		@				@
	 * 						||
	 * 	@   8   @	->	@ = 8 = @
	 * 						||
	 * 		@				@
	 */
	FORCE8,  
			


	/*
	 * Technique 4
	 * une île de valeur 3 qui a 2 voisins, dont 1 libre et 1 pont avec un voisin qui est complété
	 *  = on peut compléter l'île avec 2 ponts sur son autre voisin (libre)
	 * exemple: (@ = nombre quelconque)
	 * 	3 - 1		3 - 1		3 = 2		3 = 2
	 * 			->	||		ou			->	|
	 * 	@			@			@			@
	 *
	 * ou:
	 * une île de valeur 3 qui a 3 voisins, 1 libre, 1 pont avec chacun des 2 autres voisins complétés
	 *  = on peut compléter l'île avec 1 pont sur son autre voisin (libre)
	 * exemple:  (@ = nombre quelconque)
	 * 	1 - 3 - 1		1 - 3 - 1
	 * 				->		|
	 * 		@				@
	 */
	BLOQUE3, 



	// ci-dessous: pas encore appliqués

	/*
	 * une île de valeur 4 qui a 3 voisins, dont 2 libres et le 3e est complété avec 1 pont lié à cette Ile
	 *  = on peut ajouter 1 Pont pour chaque autre voisin
	 * exemple:
	 * 1 - 4   @	1 - 4 - @
	 * 			 ->		|
	 *     @			@
	 */
	BLOQUE41,

	/*
	 * Technique 5
	 * une île de valeur 4 qui a 1 voisin libre et 1 pont avec 2 voisins qui sont complétés
	 *  = on peut compléter l'île avec 2 ponts sur son 3e et dernier voisin
	 * exemple: 
	 * 1 - 4 - 1	1 - 4 - 1
	 * 			 ->		||
	 *     @			@
	 */
	BLOQUE42,

	/*
	 * Technique 4
	 * une île de valeur 5 qui a 3 voisins libres et 1 pont avec un voisin qui est complété
	 *  = on peut compléter l'île avec 2 ponts sur ses 2 autres voisins
	 */
	BLOQUE5,

	/*
	 * Technique 6
	 * une île de valeur 6 qui a 4 voisins libres et 1 pont avec un voisin qui est complété
	 *  = on peut ajouter 1 pont pour chaque voisin
	 */
	BLOQUE6,

	/*
	 * Technique 4
	 * une île de valeur 7 qui a 1 pont avec un voisin qui est complété
	 *  = on peut compléter l'île avec 2 ponts sur ses 3 autres voisins
	 */
	BLOQUE7, 

	/*
	 * Technique 7
	 * une île de valeur 1 qui a 2 voisins libres, mais un de ces voisins est une île de valeur 1
	 * si un pont est placé entre ces deux îles, elles se retrouvent isolées
	 *  = il ne peut PAS y avoir de pont entre ces deux îles, donc il y en a forcément un avec son autre voisin libre
	 * exemple:
	 * 1 - 1					1   1
	 * 		 = isolé	donc:	|
	 * @						@
	 */
	ISOLE1, 

	/*
	 * Technique 7
 	 * une île de valeur 2 qui a 2 voisins libres, mais un de ces voisins est une île de valeur 2
	 * si un pont double est placé entre ces deux îles, elles se retrouvent isolées
	 *  = il ne peut PAS y avoir deux ponts entre ces deux îles, donc il y en a forcément un avec son autre voisin libre
	 * 2 = 2					2   2
	 * 		 = isolé	donc:	|
	 * @						@
	 */
	ISOLE2, 

	/*
	 * Technique 7
 	 * une île de valeur 2 qui a 2 voisins libres, mais ces DEUX voisins sont des îles de valeur 2
	 * si un pont double est placé entre n'importe laquelle de ces deux îles, elles se retrouvent isolées
	 *  = il y a forcément un pont simple avec ses deux voisins
	 * 2 = 2				2   2					2 - 2
	 * 		 = isolé	et	||	  = isolé	donc:	|
	 * 2					2						2
	 */
	ISOLE22;



    // Méthode pour obtenir une valeur d'Aide à partir d'une chaîne
    public static Aide fromString(String text) {
        if (text != null) {
            text = text.trim().toUpperCase(); // Convertit la chaîne en majuscules et supprime les espaces inutiles

            // Crée une carte de correspondance entre les chaînes et les valeurs d'Aide
            Map<String, Aide> map = new HashMap<>();
            for (Aide aide : Aide.values()) {
                map.put(aide.name(), aide);
            }

            // Vérifie si la chaîne correspond à une valeur d'Aide connue
            if (map.containsKey(text)) {
                return map.get(text);
            }
        }
        // Si la chaîne ne correspond à aucune valeur d'Aide connue, retourne RIEN
        return Aide.RIEN;
    }

}
