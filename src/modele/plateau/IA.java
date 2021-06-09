package modele.plateau;

import Tools.Aleatoire;

public class IA extends Heros {
    private Jeu jeu;

    private  int NOMBRE_CHANGEMENT_MAX;

    private boolean visible;

    private boolean phase_1;
    private boolean phase_2;

    private int temps_pause;

    public boolean isPhase_1() {
        return phase_1;
    }

    public void setPhase_1(boolean phase_1) {
        this.phase_1 = phase_1;
    }

    public boolean isPhase_2() {
        return phase_2;
    }

    public void setPhase_2(boolean phase_2) {
        this.phase_2 = phase_2;
    }

    private int blindage_porte;


    private int nombreElementChange;



    private int orientation;


    private EntiteStatique[][] grilleEntitesStatiques ;



    public IA(Jeu _jeu, int _x, int _y) {
        super(_jeu, _x, _y);
        this.jeu=_jeu;
        this.visible=true;
        this.orientation=1;
        this.NOMBRE_CHANGEMENT_MAX=jeu.getSizeX()*jeu.getSizeY()/3;
    }

    public boolean isVisible() {
        return visible;
    }

    public int getDeb() {
        return nombreElementChange;
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

        if(phase_1)
        {
            temps_pause=100;
        }else
        {
            temps_pause=200;
        }

        if(phase_1)
        {
            if(orientation==1)
            {
                this.setX(this.getX()-1);
                Thread.sleep(temps_pause);
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
                if(blindage_porte <4)
                {
                    blindage_porte++;
                }
            }

        }

        if(phase_2)
        {
                Aleatoire a =new Aleatoire();
                this.setY(a.genereNombreBorne(5));
                this.setX(a.genereNombreBorne(jeu.getSizeX()-10));
                Thread.sleep(temps_pause);
        }

    }



public void changer()
{
    if(phase_1)
    {
        if (this.jeu.getEntite(this.getX(),this.getY()) instanceof DalleInflammable)
        {
            ((DalleInflammable) this.jeu.getEntite(this.getX(),this.getY())).incendier();
            nombreElementChange++;

            if(((DalleInflammable) this.jeu.getEntite(this.getX(),this.getY())).isInflammee())
            {
                Aleatoire a =new Aleatoire();
                this.setY(a.genereNombreBorne(5));
            }
        }

        if (this.jeu.getEntite(this.getX(),this.getY()) instanceof CaseInterdite)
        {
            ((CaseInterdite)  this.jeu.getEntite(this.getX(),this.getY())).setAjoute(true);

            nombreElementChange++;

        }


        if (this.jeu.getEntite(this.getX(),this.getY()) instanceof PorteVerouille)
        {
            ((PorteVerouille)  this.jeu.getEntite(this.getX(),this.getY())).setOuverte(false);
            ((PorteVerouille)  this.jeu.getEntite(this.getX(),this.getY())).setType(2);
            ((PorteVerouille)  this.jeu.getEntite(this.getX(),this.getY())).setBlindage(blindage_porte);

            nombreElementChange++;
        }
    }

    if(phase_2) {
        if(nombreElementChange<NOMBRE_CHANGEMENT_MAX-50)
        {
            if (this.jeu.getEntite(this.getX(), this.getY()) instanceof CaseNormale) {
                DalleInflammable maCase = new DalleInflammable(jeu, this.getX(), this.getY());
                grilleEntitesStatiques[this.getX()][this.getY()] = maCase;
                nombreElementChange++;
            }
        }else
        {
            if (this.jeu.getEntite(this.getX(), this.getY()) instanceof CaseNormale) {
                CaseInterdite maCase = new CaseInterdite(jeu, this.getX(), this.getY());
                grilleEntitesStatiques[this.getX()][this.getY()] = maCase;
                nombreElementChange++;
            }
        }

        }
    }

};
