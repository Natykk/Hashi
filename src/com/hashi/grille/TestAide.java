/*
 * Tests Unitaires pour les méthodes en lien avec la détection de situation où on peut fournir de l'aide, ainsi que la détection de voisins
 */

import java.util.ArrayList;

public class TestAide {

	static Grille g = new Grille(5);



	static void testGetVoisins() {
		// grille de 5 par 5

		g.initialiserTable();

		Ile i1 = new Ile(1, 0, 2, g);
		Ile i2 = new Ile(1, 2, 0, g);
		Ile i3 = new Ile(3, 0, 0, g);

		ArrayList<Ile> uneListeDIle = new ArrayList<>();
		

		g.ajouterIle(i3);
		g.ajouterIle(i1);
		g.ajouterIle(i2);


		uneListeDIle = i3.getVoisins();


		//==== affichage résultats

		System.out.println("la grille:");
		System.out.println(g.afficher());


		System.out.println("liste des voisins de i3:");
		for (Ile ile : uneListeDIle) {
			System.out.print(ile.afficher()+",");
		}
		System.out.println();
	}


	public static void main(String[] args) {
		

		testGetVoisins();

	}
}
