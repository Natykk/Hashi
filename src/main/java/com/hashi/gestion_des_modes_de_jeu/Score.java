public class Score{
    
    
    //la methode calculScoreHistoire prend en parametre le numero du niveau et le temp passer sur le niveau 
    //la methode retourne le nombre d'étoiles a afficher pour la partie
    //pour le bon fonctionnement de la methode il faut que les niveaux dans le mode histoire soient classés du plus facile au plus dure
    public int calculScoreHistoire(int niveau, int temp){ //temp est en secondes
        if(niveau <= 5){ //niveaux faciles
            if(temp <= 40){//bon temps
                return 3;
            }
            if(temp <= 60){//temps moyen
                return 2;
            }
            if(temp > 60){//temps mauvais
                return 1;
            }
        }
        if(niveau <= 10){//niveaux moyen
            if(temp <= 80){//bon temps
                return 3;
            }
            if(temp <= 120){//temps moyen
                return 2;
            }
            if(temp > 120){//temps mauvais
                return 1;
            }
        }
        if(niveau <= 15){
            if(temp <= 320){//bon temps
                return 3;
            }
            if(temp <= 400){//temps moyen
                return 2;
            }
            if(temp > 400){//temps mauvais
                return 1;
            }
        }
        return -1; //Erreur
    } 


    // Méthode pour calculer le score
    public static int calculScoreArcade(int[] completedDifficulties) {
        int score = 0;
        
        // Parcours des difficultés des grilles complétées
        for (int difficulty : completedDifficulties) {
            // Ajout du coefficient de difficulté au score
            score += difficulty;
        }
        
        return score;
    }
}