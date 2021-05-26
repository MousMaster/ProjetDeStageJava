package modele.plateau;

import Tools.Aleatoire;

public class MesCasesInterdites {
    private int nomBreCase;
    private Jeu jeu;
    private caseVide tabCase[];
    private boolean tabCasesBool[];
    private EntiteStatique[][] grilleEntitesStatiques ;


    public MesCasesInterdites(Jeu jeu) {
        this.jeu = jeu;
    }

    public int getNomBre() {
        return this.nomBreCase;
    }

    public caseVide MesCasesInterdites(int i) {
        return this.tabCase[i];
    }

    /*Initailise toutes les cases vide du jeu*/
    public void initialiser() {
        grilleEntitesStatiques =new EntiteStatique[jeu.getSizeX()][jeu.getSizeY()];

        Aleatoire a=new Aleatoire();

        this.nomBreCase = 10*jeu.NBRS ;
        tabCase = new caseVide[nomBreCase];
        tabCasesBool =new boolean[nomBreCase];

        for(int i=0;i<nomBreCase;i++) {
            tabCasesBool[i] = false;
        }

        //empeche la supperposition des cases vides entre elle
        for( int z = 0 ; z <jeu.NBRS ; z++){
            for (int i = 0 + z*5 ; i < 5+5*z; i++) {
                if(!tabCasesBool[i])
                    tabCase[i] = new caseVide(jeu, a.genereNombreBorne(Salle.SIZE_X-1)+Salle.SIZE_X*z, a.genereNombreBorne(Salle.SIZE_Y-2));
                tabCasesBool[i]=true;
            }
        }
    }


    public caseVide accees(int i) {
        return this.tabCase[i];
    }
}


