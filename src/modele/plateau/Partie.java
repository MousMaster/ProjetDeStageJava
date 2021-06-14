package modele.plateau;

import modele.plateau.Jeu;

public class Partie {
    private Jeu monJeu;


    Partie(Jeu _jeu)
    {
        this.monJeu =_jeu;
    }

    private int niveau=0;

    public int getNiveau() {
        return niveau;
    }


    private void changementNiveau()
    {
        if(monJeu.getMesSalles().isDernierePorteOuverte())
        {
            niveau++;
            monJeu.getHeros().setX(1);
            monJeu.monster.setTemps_pause(monJeu.monster.getTemps_pause()/2);
            monJeu.getMesSalles().setDernierePorteOuverte(false);
            //monJeu.getMesDalles().setNomBreDalle(monJeu.getMesDalles().getNomBreDalle()+5);


            monJeu.initSall();
            monJeu.getMesDalles().setNombreDalleParSalle((monJeu.getMesDalles().getNombreDalleParSalle()+2));
            monJeu.initDalles();

            monJeu.initTresor();
            monJeu.monster.setNombreElementChange(0);
        }
    }

    public void gestionPartie()
    {
        changementNiveau();
    }

}
