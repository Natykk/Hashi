package com.hashi.grille;

public enum Aide {
	RIEN, // pas d'aide trouvé
	FORCE1, // une île de valeur 1 avec un seul voisin libre = compléter l'île avec 1 pont
	FORCE2, // une île de valeur 2 avec un seul voisin libre = compléter l'île avec 2 ponts
	FORCE3, // une île de valeur 3 avec 2 voisins libre = on peut ajouter 1 pont pour chaque
			// voisin
	FORCE4, // une île de valeur 4 avec 2 voisins libre = compléter l'île avec 4 ponts
	FORCE5, // une île de valeur 5 avec 3 voisins libre = on peut ajouter 1 pont pour chaque
			// voisin
	FORCE6, // une île de valeur 6 avec 3 voisins libre = compléter l'île avec 6 ponts
	FORCE7, // une île de valeur 7 = on peut ajouter 1 pont pour chaque voisin
	FORCE8, // une île de valeur 8 = compléter l'île avec 8 ponts

	BLOQUE3, // une île de valeur 3 qui a 2 voisins, dont 1 libre et 1 pont avec un voisin qui est
				// complété = on peut compléter l'île avec 2 ponts sur son autre voisin (libre)
				// ou: 3 voisins, 1 libre, 1 pont avec chacun des 2 autres voisins complétés
				// = on peut compléter l'île avec 1 pont sur son autre voisin (libre)

	// ci-dessous: pas encore appliqués
	BLOQUE41, // une île de valeur 4 qui a 3 voisins, dont 2 libres et le 3e est complété 
				//avec 1 pont lié à cette Ile = on peut ajouter 1 Pont pour chaque autre voisin

	BLOQUE42, // une île de valeur 4 qui a 1 voisin libre et 1 pont avec 2 voisins qui sont
				// complétés = on peut compléter l'île avec 2 ponts sur son 3e et dernier voisin

	BLOQUE5, // une île de valeur 5 qui a 3 voisins libres et 1 pont avec un voisin qui est
				// complété = on peut compléter l'île avec 2 ponts sur ses 2 autres voisins

	BLOQUE6, // une île de valeur 6 qui a 4 voisins libres et 1 pont avec un voisin qui est
				// complété = on peut ajouter 1 pont pour chaque voisin

	BLOQUE7 // une île de valeur 7 qui a 1 pont avec un voisin qui est complété = on peut
			// compléter l'île avec 2 ponts sur ses 3 autres voisins
}
