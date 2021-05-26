package modele.plateau;

public class caseVide extends EntiteStatique{
    public caseVide(Jeu _jeu ) {
        super(_jeu);
        System.out.println(this.posX +" "+this.posY);
    }

    int posX,posY;

    private boolean ajoute;

/* Setters && Getters */
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

    public boolean isAjoute() {
        return ajoute;
    }

    public void setAjoute(boolean ajoute) {
        this.ajoute = ajoute;
    }
    /*Cpnstructeur */

    public caseVide(Jeu _jeu, int px, int py) {
        super(_jeu);
        this.posX=px;
        this.posY=py;
    }

/* Redefinition de la methode traverssable toujours non traverssable */
    @Override
    public boolean traversable() {
        return false;
    }
}

