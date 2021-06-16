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
            if(niveau<10)
            {
                niveau++;
                monJeu.recharger(this.niveau*2+monJeu.getMesDalles().getNombreDalleParSalle(),this.niveau*2+monJeu.getInterdites().getNombreCaseInterditeParSalle(),1,niveau+1);
                monJeu.monster.reset();
                monJeu.getHeros().setX(1);
                monJeu.getHeros().setY(monJeu.getSizeY()-2);
            }else
            {
                System.out.println("Gagné !!!");
                monJeu.controleJeu(true);
            }

        }
    }

    public void gestionPartie()
    {
        changementNiveau();
    }

}
