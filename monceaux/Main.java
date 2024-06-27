package monceaux;

public class Main {
    public static void main(String[] args) {
        Jeu jeu1 = new Jeu(10,20,10);
        jeu1.ajout("Joueur 1", 5);
        jeu1.ajout("Joueur 5", 1);
        jeu1.ajout("Joueur 2", 4);
        
        Jeu jeu2 = new Jeu(10,20,10);
        
        jeu2.ajout("Joueur 3", 3);
        jeu2.ajout("Joueur 4", 2);
        
        jeu1.fusion(jeu2);
        
        jeu1.jouer();
    }
}
