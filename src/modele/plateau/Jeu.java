/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.plateau;

import Tools.Voisinage;

import java.util.Observable;


public class Jeu extends Observable implements Runnable {
    /* Nombre de salle */
    public static final int NBRS =5 ;

    public static final int SIZE_X = 13 *NBRS ;
    public static final int SIZE_Y = 7;

    /* Les tresors , les dallles inflammables et les cases vides  du jeu  */

    private MesTresors mesTresors;
    private MesDalles mesDalles;

    public MesCasesInterdites getInterdites() {
        return interdites;
    }

    private MesCasesInterdites interdites;
    private Salle MesSalles;

    private Partie maPartie;

    public Partie getMaPartie() {
        return maPartie;
    }

    public void setMaPartie(Partie maPartie) {
        this.maPartie = maPartie;
    }

    public Salle getMesSalles() {
        return MesSalles;
    }

    public void setMesSalles(Salle mesSalles) {
        MesSalles = mesSalles;
    }

    public int nombreElments()
    {
        return mesDalles.getNomBreDalle()+interdites.getNomBre()+NBRS;
    }

    public IA monster;


    public MesTresors getMesTresors() {
        return mesTresors;
    }

    private EntiteStatique[][] grilleEntitesStatiques = new EntiteStatique[SIZE_X][SIZE_Y];

    private int pause = 200; // période de rafraichissement

    private Heros heros;

    public int getSizeY(){return this.SIZE_Y;}
    public int getSizeX(){return this.SIZE_X;}


    /* Getters && Setters */

    public EntiteStatique[][] getGrilleEntitesStatiques() {
        return grilleEntitesStatiques;
    }
    public MesDalles getMesDalles() {
        return mesDalles;
    }


    public int getNombreDalle() {
        return this.mesDalles.getNomBreDalle();
    }


    public int getNombreTresor()
    {
        return mesTresors.getNombreTresors();
    }

    public Heros getHeros() {
        return heros;
    }


    public EntiteStatique getEntite(int x, int y) {
        if (x < 0 || x >= SIZE_X || y < 0 || y >= SIZE_Y) {
            // L'entité demandée est en-dehors de la grille
            return null;
        }
        return grilleEntitesStatiques[x][y];
    }

    public Jeu() {
        initialisationDesEntites();
    }


    /* Retourne un tresor de l'enssemble des tresors */
    public Tresor accesTreso(int i)
    {
        return mesTresors.accesTresor(i);
    }


    private void initialisationDesEntites() {

        heros = new Heros(this, 4, 4);

        monster=new IA(this,2,2);



        /*initialise les parametres du joueur */
        heros.getInventaire().setNombreCapsule(1);
        heros.getInventaire().setNombreCle(0);
        heros.getInventaire().setNombrePieces(1);
        heros.getInventaire().setNombreSautRestant(20);
        heros.getInventaire().afficheInventaire();

        /*Appel des methodes d'initialisation */


        initSall();
        initDalles(3,3);
        initTresor();

        maPartie= new Partie(this);



        /* Affichage aide a l'utilisateur */
        help();

    }


    public void start() {
        new Thread(this).start();
    }

    public void run() {

        while(true) {

            setChanged();
            notifyObservers();

            try {
                Thread.sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ThreadIA mon =new ThreadIA(this);
            mon.run();


        }

    }


    private void addEntiteStatique(EntiteStatique e, int x, int y) {
        grilleEntitesStatiques[x][y] = e;

        if (e instanceof PorteVerouille) {
            ((PorteVerouille) e).setPosX(x);
            ((PorteVerouille) e).setPosY(y);
        } else {
                if(e instanceof DalleInflammable)
                {
                    ((DalleInflammable) e).setPosX(x);
                    ((DalleInflammable) e).setPosY(y);
                }
            }
        }

    /* Les methodes d'initialisation */
    public void initDalles(int nbDalle,int nbCase)
    {
        interdites =new MesCasesInterdites(this,nbDalle);

        mesDalles= new MesDalles(this,nbCase);



        mesDalles.initialiser();
        interdites.initialiser();


        for(int i=0;i<mesDalles.getNomBreDalle();i++)
        {
            addEntiteStatique(mesDalles.accees(i),mesDalles.accees(i).posX,mesDalles.accees(i).posY );
            addEntiteStatique(interdites.accees(i),interdites.accees(i).posX,interdites.accees(i).posY );
        }
    }

    public void initTresor()
    {
        mesTresors = new MesTresors();
        mesTresors.setJeu(this);
        mesTresors.init();
    }

    public void initSall()
    {
        MesSalles = new Salle();
        MesSalles.setJeu(this);
        MesSalles.setGrilleEntitesStatiques(this.grilleEntitesStatiques);
        MesSalles.initMur();
        MesSalles.initPorte();
    }




    /* Parcours les differentes porte  */
    public void ouvrePorte() {
        Voisinage voisin =new Voisinage(SIZE_Y);

        if(heros.getInventaire().getNombreCle()>0)
        {
            for (int x = 0; x < SIZE_X; x++) {
                for (int y = 0; y < SIZE_Y; y++) {
                    if (grilleEntitesStatiques[x][y] instanceof PorteVerouille) {
                            PorteVerouille nouv =(PorteVerouille) grilleEntitesStatiques[x][y];
                            nouv.ouvrir(heros);
                        }
                    }
                }
            }
        }

/* Appel des fonctionnalités du jeu */
    public void recupererContenuTresor() {
        mesTresors.recupererContenuTresor();
    }

    public void afficheContenuTresor()
    {
        mesTresors.affichTresor();
    }

    public void enflammeDalles()
    {
        mesDalles.enflammeLesDalles();
    }

    public void eteindredalles()
    {
        mesDalles.eteintLesDalles();
    }


    /*Recommencer la partie */
    public void relancer()
    {
        System.lineSeparator();
        System.out.println("Initialisation du jeu");
        for (int x = 0; x < SIZE_X; x++) {
            for (int y = 0; y < SIZE_Y; y++) {
                grilleEntitesStatiques[x][y] =null;
            }
        }
        this.initSall();
        this.initTresor();
        this.initDalles(3,3);

        heros.setX(1);
        heros.setY(2);

        monster.reset();

    }

    public void recharger(int nbDalle,int nbCase)
    {
        System.lineSeparator();
        System.out.println("Passage niveau superieur");
        for (int x = 0; x < SIZE_X; x++) {
            for (int y = 0; y < SIZE_Y; y++) {
                grilleEntitesStatiques[x][y] =null;
            }
        }
        this.initSall();
        this.initDalles(nbDalle,nbCase);
        this.initTresor();
        this.monster.reset();
        heros.setX(1);
        heros.setY(this.getSizeY()-2);
    }


    public void commentJouer()
    {
        System.out.println("Appuyer sur H pour afficher l'aide");
    }

    /* Affiche les commandes du jeu */
    public void help()
    {
        System.out.println("---------Les commandes-----------");
        System.lineSeparator();
        System.out.println("Fleches : dirrige le prof");
        System.lineSeparator();
        System.out.println(" I :affiche inventaire");
        System.lineSeparator();
        System.out.println(" T :affiche contenu coffre");
        System.lineSeparator();
        System.out.println(" C : recupere contenu si nombre piece > 3 on recupere les capsules en +");
        System.lineSeparator();
        System.out.println(" O :ouvre porte si nombre cle > 0");
        System.lineSeparator();
        System.out.println(" W :Etient le feu si nombre capsule > 0");
        System.lineSeparator();
        System.out.println(" R :pour relancer le jeu");
        System.lineSeparator();
    }

    public void controleJeu(boolean b)
    {
        monster.setMettrePause(b);
    }
}
