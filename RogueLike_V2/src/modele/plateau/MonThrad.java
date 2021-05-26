package modele.plateau;

import Tools.Aleatoire;

public class MonThrad extends Thread{

    private Jeu jeu ;

    MonThrad(Jeu _jeu)
    {
        this.jeu=_jeu;
        this.jeu.monster.setGrilleEntitesStatiques(this.jeu.getGrilleEntitesStatiques());
    }

    public void run() {
        // do something in a new thread if 'called' by super.start()
        Aleatoire a =new Aleatoire();

            //this.jeu.monster.bas();


        try {
            if(this.jeu.monster.getDeb()<this.jeu.monster.getMax())
            this.jeu.monster.deplacer();
            this.jeu.monster.setOrientation();

            //this.jeu.monster.detruire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

            //System.out.println(i);
           // System.lineSeparator();

    }

}
