package modele.plateau;

public class CaseNormale extends EntiteStatique {
    public CaseNormale(Jeu _jeu ) { super(_jeu); }
    int posX,posY;
    boolean traversable;

    private boolean joueurDessus;

    public boolean isJoueurDessus() {
        return joueurDessus;
    }

    public void setJoueurDessus(boolean joueurDessus) {
        this.joueurDessus = joueurDessus;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public boolean isTraverssable(){return traversable;};


    public CaseNormale(Jeu _jeu,int px,int py) {
        super(_jeu);
        this.posX=px;
        this.posY=py;

    }


    @Override
    public boolean traversable() {
        return true;
    }

}
