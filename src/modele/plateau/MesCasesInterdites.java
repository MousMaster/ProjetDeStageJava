package modele.plateau;

import Tools.Aleatoire;

public class MesCasesInterdites {
    private int nomBreCase;
    private Jeu jeu;
    private CaseInterdite tabCase[];
    private boolean tabCasesBool[];
    private EntiteStatique[][] grilleEntitesStatiques ;

    public int getNombreCaseInterditeParSalle() {
        return nombreCaseInterditeParSalle;
    }

    public void setNombreCaseInterditeParSalle(int nombreCaseInterditeParSalle) {
        this.nombreCaseInterditeParSalle = nombreCaseInterditeParSalle;
    }

    private int nombreCaseInterditeParSalle;



    public MesCasesInterdites(Jeu jeu,int nb) {
        this.jeu = jeu;
        this.nombreCaseInterditeParSalle=nb;
    }

    public int getNomBre() {
        return this.nomBreCase;
    }

    public CaseInterdite MesCasesInterdites(int i) {
        return this.tabCase[i];
    }

    /*Initailise toutes les cases vide du jeu*/
    public void initialiser() {
        grilleEntitesStatiques = new EntiteStatique[jeu.getSizeX()][jeu.getSizeY()];

        Aleatoire a = new Aleatoire();


        this.nomBreCase = nombreCaseInterditeParSalle * jeu.NBRS;
        tabCase = new CaseInterdite[nomBreCase];
        tabCasesBool = new boolean[nomBreCase];

        for (int i = 0; i < nomBreCase; i++) {
            tabCasesBool[i] = false;
        }
        //empeche la superposition des dalles uniques
        for (int z = 0; z < jeu.NBRS; z++) {
            for (int i = 0 + z * nombreCaseInterditeParSalle; i < nombreCaseInterditeParSalle + nombreCaseInterditeParSalle * z; i++) {
                if (!tabCasesBool[i])
                    tabCase[i] = new CaseInterdite(jeu, a.genereNombreBorneMinMax(Salle.SIZE_X * z + Salle.SIZE_X - 2, Salle.SIZE_X * z + 2), a.genereNombreBorneMinMax(Salle.SIZE_Y - 1, 1));
                tabCasesBool[i] = true;
            }
        }
    }



    public CaseInterdite accees(int i) {
            return this.tabCase[i];
    }
}


