package modele.plateau;

import Tools.Aleatoire;

public class ThreadIA extends Thread{

    private Jeu jeu ;

    ThreadIA(Jeu _jeu)
    {
        this.jeu=_jeu;
        this.jeu.monster.setGrilleEntitesStatiques(this.jeu.getGrilleEntitesStatiques());
    }

    public void run() {

        // do something in a new thread if 'called' by super.start()
        Aleatoire a =new Aleatoire();

            //this.jeu.monster.bas();


        try {

            if(this.jeu.monster.getDeb()<jeu.nombreElments()) {
                jeu.monster.setPhase_1(true);
                jeu.monster.setPhase_2(false);

            }else
            {
                jeu.monster.setPhase_2(true);
                jeu.monster.setPhase_1(false);
            }

            this.jeu.monster.deplacer();
            this.jeu.monster.setOrientation();
            this.jeu.monster.changer();

            System.out.println("Le nombre de modif : "+this.jeu.monster.getDeb()+" ");


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

            //System.out.println(i);
           // System.lineSeparator();

    }

}
