package monceaux;

/**
 * Classe pour le monceau du jeu.
 */
public class Jeu {
    static class Joueur {
        String nom;
        int score;
        int priorite;
        int resultDe;
        
        /**
         * Noeud représentant un joueur avec un nom et une priorité spécifiés.
         *
         * @param nom Le nom du joueur.
         * @param priorite La priorité du joueur.
         */
        public Joueur(String nom, int priorite) {
            this.nom = nom;
            this.priorite =  priorite;
            this.score = 0;
        }
    
    
    /**
     * Simule le lancer d'un dé (entre -2 et 2) pour le joueur, mettant à jour son score.
     */    
    public void lancerDe() {
        int resultatDe = (int) (Math.random() * 5) - 2;
        score += resultatDe;
        resultDe = resultatDe;
    }
   }

    Joueur[] joueurs;
    int taille;
    int capacite;
    int tours;
    int toursMax;
    int scoreMax;
    boolean jeuFini = false;

    /**
     * Constructeur du jeu.
     *
     * @param capacite Capacité maximale de joueurs.
     * @param tourMax Nombre maximal de tours.
     * @param scoreMax Score nécessaire pour gagner le jeu.
     */
    public Jeu(int capacite, int tourMax, int scoreMax) {
        this.capacite = capacite;
        joueurs = new Joueur[capacite];
        taille = 0;
        this.toursMax = tourMax;
        this.scoreMax = scoreMax;
    }

    /**
     * Échange la position de deux joueurs dans le tableau de joueurs.
     *
     * @param i Index du premier joueur à échanger.
     * @param j Index du deuxième joueur à échanger.
     */
    private void echanger(int i, int j) {
        Joueur temp = joueurs[i];
        joueurs[i] = joueurs[j];
        joueurs[j] = temp;
    }

    /**
     * Réajuste la position d'un joueur dans le tas pour maintenir les priorités.
     *
     * @param index L'index du joueur à réajuster.
     */
    private void monter(int index) {
        while (index != 0 && joueurs[(index - 1) / 2].priorite < joueurs[index].priorite) {
            echanger((index - 1) / 2, index);
            index = (index - 1) / 2;
        }
    }

    /**
     * Ajoute un nouveau joueur au jeu (le monceau).
     *
     * @param nom Le nom du nouveau joueur.
     * @param priorite La priorité du nouveau joueur.
     */
    public void ajout(String nom, int priorite) {
        if (taille == capacite) {
            System.out.println("Le jeu est plein");
            return;
        }
        Joueur nouveauJoueur = new Joueur(nom, priorite);
        joueurs[taille] = nouveauJoueur;
        monter(taille);
        taille++;
    }

    /**
     * Réajuste la position d'un joueur dans le tas vers le bas pour maintenir les priorités.
     *
     * @param index L'index du joueur à réajuster vers le bas.
     */
    private void descendre(int index) {
        int plusGrande = index;
        int gauche = 2 * index + 1;
        int droite = 2 * index + 2;

        if (gauche < taille && joueurs[gauche].priorite > joueurs[plusGrande].priorite) {
            plusGrande = gauche;
        }

        if (droite < taille && joueurs[droite].priorite > joueurs[plusGrande].priorite) {
            plusGrande = droite;
        }

        if (plusGrande != index) {
            echanger(index, plusGrande);
            descendre(plusGrande);
        }
    }
 
    /**
     * Réorganise le tas de joueurs pour un nouveau tour, en s'assurant que la priorité est maintenue.
     *
     * @param joueursTemp Tableau temporaire de joueurs à réinsérer dans le tas.
     * @param compteurTemp Nombre de joueurs dans le tableau temporaire.
     */
    public void tour(Joueur[] joueursTemp, int compteurTemp) {
        taille = 0;
        for (int i = 0; i < compteurTemp; i++) {
            joueurs[taille++] = joueursTemp[i];
            
        }

        
        for (int i = taille / 2 - 1; i >= 0; i--) {
            descendre(i);
        }
    }

    /**
     * Simule le déroulement du jeu, en traitant chaque joueur par tour jusqu'à ce qu'un joueur atteigne
     * le score maximal ou que le nombre maximal de tours soit atteint.
     */
    public void jouer() {
        System.out.println("Début du jeu");
        Joueur[] joueursTemp = new Joueur[capacite]; 

        for (int tour = 1; tour <= toursMax && !jeuFini; tour++) {
            System.out.println("Tour " + tour);
            int compteurTemp = 0;

            while (taille > 0) {
                Joueur joueur = retirerRacine(); 
                joueur.lancerDe();
                System.out.println(joueur.nom + " lance le dé et obtient: " + joueur.resultDe + ". Nouveau score: " + joueur.score);

                if (joueur.score >= scoreMax) { 
                    System.out.println(joueur.nom + " remporte la partie avec " + joueur.score + " points!");
                    jeuFini = true;
                    break;
                }

                joueursTemp[compteurTemp++] = joueur;
            }

            if (!jeuFini) {
                tour(joueursTemp, compteurTemp);
            } else {
                break;
            }
        }

        if (!jeuFini) {
            System.out.println("Fin du jeu sans vainqueur. Nombre de tours maximum atteint.");
        }
    }


    /**
     * Retire le joueur de plus haute priorité pour le traiter dans le jeu.
     *
     * @return Le joueur retiré du tas.
     */
    private Joueur retirerRacine() {
        if (taille == 0) return null;
        Joueur racine = joueurs[0];
        joueurs[0] = joueurs[taille - 1];
        taille--;
        descendre(0);
        return racine;
    }



    /**
     * Fusionne un jeu avec un autre jeu, en ajoutant tous les joueurs de l'autre jeu.
     *
     * @param autreJeu Le jeu à fusionner avec le jeu initial.
     */
    public void fusion(Jeu autreJeu) {
        for (int i = 0; i < autreJeu.taille; i++) {
            Joueur joueur = autreJeu.joueurs[i];
            ajout(joueur.nom, joueur.priorite);
        }
    }



}
