package modele.plateau;

import Tools.Aleatoire;

public class MesDalles {
    private int nomBreDalle;
    private Jeu jeu;
    private DalleInflammable tabDalle[];
    private boolean tabDallesBool[];
    private EntiteStatique[][] grilleEntitesStatiques ;


    public MesDalles(Jeu jeu) {
        this.jeu = jeu;
    }

    public int getNomBreDalle() {
        return this.nomBreDalle;
    }


    /*Retourne une dalle */

    public DalleInflammable accees(int i) {
        return this.tabDalle[i];
    }

    /*Initialise les dalles */

    public void initialiser() {
        grilleEntitesStatiques =new EntiteStatique[jeu.getSizeX()][jeu.getSizeY()];

        Aleatoire a=new Aleatoire();

        this.nomBreDalle = 5*jeu.NBRS ;
        tabDalle = new DalleInflammable[nomBreDalle];
        tabDallesBool =new boolean[nomBreDalle];

        for(int i=0;i<nomBreDalle;i++) {
            tabDallesBool[i] = false;
        }
        //empeche la superposition des dalles uniques
        for( int z = 0 ; z <jeu.NBRS ; z++){
            for (int i = 0 + z*5 ; i < 5+5*z; i++) {
                if(!tabDallesBool[i])
                    tabDalle[i] = new DalleInflammable(jeu, a.genereNombreBorneMinMax(Salle.SIZE_X*z+Salle.SIZE_X-1,Salle.SIZE_X*z+1),a.genereNombreBorneMinMax(Salle.SIZE_Y-1,1));
                tabDallesBool[i]=true;
            }
        }



    }
    /*Parcours lenssemble des dalles */
    public void enflammeLesDalles()
    {
        for(int i=0;i<jeu.getSizeX();i++)
        {
            for(int j=0;j<jeu.getSizeY();j++)
            {
                if(jeu.getGrilleEntitesStatiques()[i][j] instanceof DalleInflammable)
                {
                    ((DalleInflammable) jeu.getGrilleEntitesStatiques()[i][j]).inflammeDalle(jeu.getHeros());
                }

            }
        }
    }

    /*Parcours lenssemble des dalles */
    public void eteintLesDalles()
    {
        for(int i=0;i<jeu.getSizeX();i++)
        {
            for(int j=0;j<jeu.getSizeY();j++)
            {
                if(jeu.getGrilleEntitesStatiques()[i][j] instanceof DalleInflammable)
                {
                    ((DalleInflammable) jeu.getGrilleEntitesStatiques()[i][j]).eteintFeuDall(jeu.getHeros());
                }

            }
        }
    }
}


