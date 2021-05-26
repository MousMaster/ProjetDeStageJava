package modele.plateau;

import Tools.Aleatoire;

public class Enemi extends Heros {
    private Jeu jeu;
    private int max;
    private int deb;
    private boolean visible;

    private int orientation;

    private EntiteStatique[][] grilleEntitesStatiques ;

    public void setMax(int max) {
        this.max = max;
    }

    public Enemi(Jeu _jeu, int _x, int _y) {
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
                Thread.sleep(500);
            }

            if(orientation==0)
            {
                this.setX(this.getX()+1);
                Thread.sleep(500);
            }
    }
/*
    public void detruire()
    {
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

 */
public void detruire()
{
    /*
        if (this.jeu.getEntite(this.getX(),this.getY()) instanceof Tresor)
        {
            caseVide macCase =new caseVide(this.jeu);
            grilleEntitesStatiques[this.getX()][this.getY()]=macCase;
            macCase.setAjoute(true);
            this.deb++;

        }
        
     */
    if(this.deb==this.max){
        this.visible=false;
    }
}

};
