package modele.plateau;

import Tools.Aleatoire;

public class MesDalles {
    private int nomBreDalle;


    public int getNombreDalleParSalle() {
        return nombreDalleParSalle;
    }

    public void setNombreDalleParSalle(int nombreDalleParSalle) {
        this.nombreDalleParSalle = nombreDalleParSalle;
    }

    private int nombreDalleParSalle;

    private Jeu jeu;
    private DalleInflammable tabDalle[];
    private boolean tabDallesBool[];
    private EntiteStatique[][] grilleEntitesStatiques ;


    public MesDalles(Jeu jeu,int nb) {
        this.jeu = jeu;
        this.nombreDalleParSalle=nb;

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


        this.nomBreDalle = nombreDalleParSalle*jeu.NBRS ;
        tabDalle = new DalleInflammable[nomBreDalle];
        tabDallesBool =new boolean[nomBreDalle];

        for(int i=0;i<nomBreDalle;i++) {
            tabDallesBool[i] = false;
        }
        //empeche la superposition des dalles uniques
        for( int z = 0 ; z <jeu.NBRS ; z++){
            for (int i = 0 + z*nombreDalleParSalle ; i < nombreDalleParSalle+nombreDalleParSalle*z; i++) {
                if(!tabDallesBool[i])
                    tabDalle[i] = new DalleInflammable(jeu, a.genereNombreBorneMinMax(Salle.SIZE_X*z+Salle.SIZE_X-2,Salle.SIZE_X*z+2),a.genereNombreBorneMinMax(Salle.SIZE_Y-1,1));
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


