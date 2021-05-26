package modele.plateau;

public class MesTresors {

    private Jeu jeu;
    private Tresor tabTresor[];
    private int nombreTresors;

    public Jeu getJeu() {
        return jeu;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }


    /*Retourne le tresor d'indice i*/
    public Tresor accesTresor(int i) {
        return tabTresor[i];
    }


    public int getNombreTresors() {
        return nombreTresors;
    }


    /*Initalise l'enssemble des tresors du jeu */

    public void init() {
        nombreTresors = 5;
        this.nombreTresors = nombreTresors;
        tabTresor = new Tresor[nombreTresors];
        for (int i = 0; i < nombreTresors; i++) {
            tabTresor[i] = new Tresor();
        }

        for (int i = 0; i < nombreTresors; i++) {
            tabTresor[i].init(jeu, i);
            tabTresor[i].setOuvert(false);
        }
    }

    public void affichePos() {
        for (int i = 0; i < this.nombreTresors; i++) {
            System.out.println("Pos T " + i + " " + tabTresor[i].getPosX() + " " + " " + tabTresor[i].getPosY());
        }
    }

    /*Parcours l'enssemble des tresors du jeu  et recupere le contenu de celui qui est à proximité du joueur */

    public void recupererContenuTresor() {
        for (int i = 0; i < jeu.SIZE_X - 1; i++) {
            for (int j = 0; j < jeu.SIZE_Y - 1; j++) {
                for (int k = 0; k < this.getNombreTresors(); k++) {

                    if (this.accesTresor(k).getPosX() == i && this.accesTresor(k).getPosY() == j) {
                        if (isVoisin(jeu.getHeros(), i, j))
                            this.accesTresor(k).recupererContenuTresor(jeu.getHeros());
                    }
                }

            }
        }
    }

    /*Parcours l'enssemble des tresors du jeu  et affiche le contenu de celui qui est à proximité du joueur */
    public void affichTresor() {
        for (int i = 0; i < jeu.SIZE_X - 1; i++) {
            for (int j = 0; j < jeu.SIZE_Y - 1; j++) {
                for (int k = 0; k < this.getNombreTresors(); k++) {
                    if (this.accesTresor(k).getPosX() == i && this.accesTresor(k).getPosY() == j) {
                        {
                            if (isVoisin(jeu.getHeros(), i, j)) {
                                jeu.getHeros().setNumTresor(i);
                                this.accesTresor(k).Visionner();
                            }
                        }
                    }
                }

            }
        }
    }

    /*Test voisinage tresor avec joueur*/
    private boolean isVoisin(Heros joueur, int px, int py) {
        return ((joueur.getX() == px - 1 && joueur.getY() == py)
                || (joueur.getX() == px + 1 && joueur.getY() == py)
                || (joueur.getX() == px && joueur.getY() == py + 1)
                || (joueur.getX() == px && joueur.getY() == py - 1));
    }
}