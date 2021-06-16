package modele.plateau;

public class MesTresors {

    private Jeu jeu;
    private Tresor tabTresor[];
    private int nombreTresors;

    private int nombreCle_tresor_courant;
    private int nombreCapsule_tresor_courant;
    private int nombreTresors_sous_tresor_dans_courant;

    public int getNombreCle_tresor_courant() {
        return nombreCle_tresor_courant;
    }

    public void setNombreCle_tresor_courant(int nombreCle_tresor_courant) {
        this.nombreCle_tresor_courant = nombreCle_tresor_courant;
    }

    public int getNombreCapsule_tresor_courant() {
        return nombreCapsule_tresor_courant;
    }

    public void setNombreCapsule_tresor_courant(int nombreCapsule_tresor_courant) {
        this.nombreCapsule_tresor_courant = nombreCapsule_tresor_courant;
    }

    public int getNombreTresors_sous_tresor_dans_courant() {
        return nombreTresors_sous_tresor_dans_courant;
    }

    public void setNombreTresors_sous_tresor_dans_courant(int nombreTresors_sous_tresor_dans_courant) {
        this.nombreTresors_sous_tresor_dans_courant = nombreTresors_sous_tresor_dans_courant;
    }

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

    public void init(int nombreCleMax,int nomCapsuleMax) {
        nombreTresors = 5;
        this.nombreTresors = nombreTresors;
        tabTresor = new Tresor[nombreTresors];
        for (int i = 0; i < nombreTresors; i++) {
            tabTresor[i] = new Tresor();
        }

        for (int i = 0; i < nombreTresors; i++) {
            tabTresor[i].init(jeu, i,nombreCleMax,nomCapsuleMax);
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
                                this.nombreCle_tresor_courant=this.accesTresor(k).getNb_cle();
                                this.nombreCapsule_tresor_courant=this.accesTresor(k).getNb_capsules();
                                this.nombreTresors_sous_tresor_dans_courant=this.accesTresor(k).getNb_tesors();
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