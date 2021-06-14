package modele.plateau;

import Tools.Voisinage;

public class PorteVerouille extends EntiteStatique{
    private int posX,posY;
    private int numPorte;


    private int type;

    private int blindage;

    public int getBlindage() {
        return blindage;
    }

    public void setBlindage(int blindage) {
        this.blindage = blindage;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setOuverte(boolean ouverte) {
        this.ouverte = ouverte;
    }

    /* Etat porte */
    private boolean ouverte;

    /* Getters && Setters */
    public boolean isOuverte() {
        return ouverte;
    }

    public int getNumPorte() {
        return numPorte;
    }

    public void setNumPorte(int numPorte) {
        this.numPorte = numPorte;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public PorteVerouille(Jeu _jeu) { super(_jeu);
    this.type=0;
    }

    public PorteVerouille(Jeu _jeu,int posX,int posY)
    {
        super(_jeu);
        this.posX=posX; this.posY=posY;
        this.ouverte=false;
        this.blindage=1;
    }

    /*Redefinition de traverssable */
    @Override
    public boolean traversable() {

        return ouverte;
    }

    /*Ouvre porte si joueur possede nombre cle suffisant et a proximite */
    public void ouvrir(Heros heros) {
        Voisinage voisin =new Voisinage(jeu.getSizeY());

        if(heros.getInventaire().getNombreCle()>this.blindage)
        {
            if (voisin.voisinJouerPorte(heros, this) && heros.getInventaire().getNombreCle() > 0) {
                            System.out.println("Ouverture porte");
                            System.out.println(" " + this.getNumPorte());
                            //heros.getInventaire().setNombreCapsule(0);
                            heros.getInventaire().incNombrePiece(2);
                            heros.getInventaire().decNombreSaut(blindage);
                            if(this.type==2)
                            {
                                heros.getInventaire().decNombreCle();
                            }
                            heros.getInventaire().decNombreCle();
                            heros.getInventaire().setNumPorteDernierementOuverte(this.numPorte);
                            if(this.numPorte==5)
                            {
                                jeu.getMesSalles().setDernierePorteOuverte(true);
                            }
                            this.ouverte = true;

            }
        }else
        {
            System.out.println("Manque de cle Blindage "+blindage+" clees");
        }
    }
}
