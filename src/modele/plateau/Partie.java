package modele.plateau;

import modele.plateau.Jeu;

public class Partie {
    private Jeu monJeu;


    Partie(Jeu _jeu)
    {
        this.monJeu =_jeu;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
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
            monJeu.recharger(this.niveau*2+monJeu.getMesDalles().getNombreDalleParSalle(),this.niveau*2+monJeu.getInterdites().getNombreCaseInterditeParSalle());
            monJeu.monster.reset();
        }
    }

    public void gestionPartie()
    {
        changementNiveau();
    }

}
