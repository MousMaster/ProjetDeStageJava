package modele.plateau;

import Tools.Aleatoire;

public class IA extends Heros {
    private Jeu jeu;
    private int max;
    private int deb;
    private boolean visible;

    private int difficulte;

    public int getDifficulte() {
        return difficulte;
    }

    private int nombreElementChange;

    public void setNombreElementChange(int nombreElementChange) {
        this.nombreElementChange = nombreElementChange;
    }

    public int getNombreElementChange() {
        return nombreElementChange;
    }

    private int orientation;


    private EntiteStatique[][] grilleEntitesStatiques ;

    public void setMax(int max) {
        this.max = max;
    }

    public IA(Jeu _jeu, int _x, int _y) {
        super(_jeu, _x, _y);
        this.jeu=_jeu;
        this.deb=0;
        this.visible=true;
        this.orientation=1;
    }

    public boolean isVisible() {
        return visible;
    }

    public int getDeb() {
        return deb;
    }

    @Override
    public void bas()
    {
        if(this.getY()>0)
        this.setY(this.getY()-1);
    }

    public void haut()
    {
        if(this.getY()>this.getY()-1)
        this.setY(this.getY()+1);
    }

    public void setGrilleEntitesStatiques(EntiteStatique[][] grilleEntitesStatiques) {
        this.grilleEntitesStatiques = grilleEntitesStatiques;
    }

    public int getMax() {
        return max;
    }
/*
    public void deplacer() throws InterruptedException {

        if(this.deb<this.max)
        {
            Aleatoire a =new Aleatoire();
            this.jeu.monster.setY(a.genereNombreBorne(this.jeu.getSizeY()-1));
            this.jeu.monster.setX(a.genereNombreBorne(jeu.getSizeX()-5));
            Thread.sleep(500);
        }
    }

 */

    public void setOrientation() {
        if(this.getX()==this.jeu.getSizeX()-1)
        {
            this.orientation=1;
        }

        if(this.getX()==1)
        {
            this.orientation=0;
        }
    }

    public void deplacer() throws InterruptedException {
            if(orientation==1)
            {
                this.setX(this.getX()-1);
                Thread.sleep(100);
            }

            if(orientation==0)
            {
                this.setX(this.getX()+1);
                Thread.sleep(100);
            }

            if(this.getX()==1)
            {
                Aleatoire a =new Aleatoire();
                this.setY(a.genereNombreBorne(5));
            }

        if(this.getX()==this.jeu.getSizeX()-6)
        {
            Aleatoire a =new Aleatoire();
            this.setY(a.genereNombreBorne(5));
            difficulte++;
        }

    }

    public void detruire()
    {
        Aleatoire a =new Aleatoire();
        this.setY(a.genereNombreBorne(5));
        this.setY(a.genereNombreBorne(this.jeu.getSizeX()-5));




        if(this.deb<this.max)
            if (this.jeu.getEntite(this.getX(),this.getY()) instanceof CaseNormale)
        {
            caseVide macCase =new caseVide(this.jeu);
            grilleEntitesStatiques[this.getX()][this.getY()]=macCase;
            macCase.setAjoute(true);
            this.deb++;

        }
            if(this.deb==this.max){
                this.visible=false;
            }
    }


public void changer()
{
    if (this.jeu.getEntite(this.getX(),this.getY()) instanceof DalleUnique)
    {
        ((DalleUnique) this.jeu.getEntite(this.getX(),this.getY())).incendier();
        nombreElementChange++;

        if(((DalleUnique) this.jeu.getEntite(this.getX(),this.getY())).isInflammee())
        {
            Aleatoire a =new Aleatoire();
            this.setY(a.genereNombreBorne(5));
        }
    }

    if (this.jeu.getEntite(this.getX(),this.getY()) instanceof caseVide)
    {
        ((caseVide)  this.jeu.getEntite(this.getX(),this.getY())).setAjoute(true);

        nombreElementChange++;

    }


    if (this.jeu.getEntite(this.getX(),this.getY()) instanceof PorteVerouille)
    {
        ((PorteVerouille)  this.jeu.getEntite(this.getX(),this.getY())).setOuverte(false);
        ((PorteVerouille)  this.jeu.getEntite(this.getX(),this.getY())).setType(2);
        ((PorteVerouille)  this.jeu.getEntite(this.getX(),this.getY())).setBlindage(difficulte);


        nombreElementChange++;
    }


    /*
    if(this.jeu.getEntite(this.getX(),this.getY()) instanceof CaseNormale)
    {
        if(((CaseNormale) this.jeu.getEntite(this.getX(),this.getY())).isJoueurDessus())
        {
            System.out.println("Nombre de clées décrémenté");
            this.jeu.getHeros().getInventaire().decNombreCle();
            this.jeu.getHeros().getInventaire().decNombreCle();

        }
    }

     */
}

};
