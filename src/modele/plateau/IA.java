package modele.plateau;

import Tools.Aleatoire;

public class IA extends Heros {
    private Jeu jeu;

    private  int NOMBRE_CHANGEMENT_MAX;

    private boolean visible;

    private boolean phase_1;
    private boolean entrePhase;
    private boolean phase_2;

    public boolean isEntrePhase() {
        return entrePhase;
    }

    public void setEntrePhase(boolean entrePhase) {
        this.entrePhase = entrePhase;
    }

    public int getTemps_pause() {
        return temps_pause;
    }

    public void setTemps_pause(int temps_pause) {
        this.temps_pause = temps_pause;
    }

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

    public void setNombreElementChange(int nombreElementChange) {
        this.nombreElementChange = nombreElementChange;
    }

    public int getNomBreDeChangement() {
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

    private void deplacer_phase_1() throws InterruptedException {
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

    private void deplacer_phase_2()
    {
        if(!entrePhase)
        {
            this.setX(1);
            this.setY(1);
            entrePhase=true;

        }

        if(this.getY()<jeu.getSizeY()-1)
        {
            this.setY(this.getY()+1);
        }

        if(this.getY()==jeu.getSizeY()-1)
        {
            this.setX(this.getX()+1);
            this.setY(0);
        }

        if(this.getY()==jeu.getSizeY()-1 && this.getX()==jeu.getSizeX()-5)
        {
            phase_2=false;
        }

    }

    public void deplacer() throws InterruptedException {
        if(phase_1)
        {
            deplacer_phase_1();
        }

        if (phase_2 )
        {
            deplacer_phase_2();
        }

    }

    private void changer_phase_1()
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


public void changer() {
    if (phase_1) {
        changer_phase_1();
    }

    if (phase_2) {
        changer_phase_2();
    }
}

private void changer_phase_2()
{
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

};
