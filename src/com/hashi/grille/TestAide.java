/*
 * Tests Unitaires pour les méthodes en lien avec la détection de situation où on peut fournir de l'aide, ainsi que la détection de voisins
 */

import java.util.ArrayList;

public class TestAide {
	
	// grille de 5 par 5
	static Grille g = new Grille(5);


	static void testGetVoisins() {

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

	static void afficherNomAide( Aide a ) {
		switch (a) {
			case RIEN:
				System.out.println("RIEN");
				break;
			case FORCE1:
				System.out.println("FORCE1");
				break;
			case FORCE2:
				System.out.println("FORCE2");
				break;
			case FORCE3:
				System.out.println("FORCE3");
				break;
			case FORCE4:
				System.out.println("FORCE4");
				break;
			case FORCE5:
				System.out.println("FORCE5");
				break;
			case FORCE6:
				System.out.println("FORCE6");
				break;
			case FORCE7:
				System.out.println("FORCE7");
				break;
			case FORCE8:
				System.out.println("FORCE8");
				break;
			case BLOQUE3:
				System.out.println("BLOQUE3");
				break;
			case BLOQUE41:
				System.out.println("BLOQUE41");
				break;
			case BLOQUE42:
				System.out.println("BLOQUE42");
				break;
			case BLOQUE5:
				System.out.println("BLOQUE5");
				break;
			case BLOQUE6:
				System.out.println("BLOQUE6");
				break;
			case BLOQUE7:
				System.out.println("BLOQUE7");
				break;
		}
	}

	static void testIle3A2Voisins() {

		Ile i1 = new Ile(1, 0, 2, g);
		Ile i2 = new Ile(2, 2, 0, g);
		Ile i3 = new Ile(3, 0, 0, g);

		ArrayList<Ile> uneListeDIle = new ArrayList<>();
		

		g.ajouterIle(i3);
		g.ajouterIle(i1);
		g.ajouterIle(i2);


		//uneListeDIle = i3.getVoisins();


		




		//==== affichage résultats

		System.out.println("la grille:");
		System.out.println(g.afficher());

		System.out.println("i3.techniquePontsForces()");
		afficherNomAide( i3.techniquePontsForces() );

	}


	public static void main(String[] args) {
		
		g.initialiserTable();


		//testGetVoisins();

		testIle3A2Voisins();

	}
}
