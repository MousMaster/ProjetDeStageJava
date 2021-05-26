package modele.plateau;

public class DalleUnique extends CaseNormale {

    int posX,posY;

    /* Boolean permettant d'avoir l"etat de la dalle*/
    boolean inflammee;


    /* Getters && Setters */
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

    public boolean isInflammee() {
        return inflammee;
    }


    /* Eteint la dalle en changean son etat */
    public void eteindreFeu()
    {
        this.inflammee =false;
    }

    /* Inflamme la dalle en changean son etat */
    public void incendier() {
        this.inflammee = true;
    }

    public DalleUnique(Jeu _jeu) {
        super(_jeu);
        this.inflammee =false;
    }
    public DalleUnique(Jeu _jeu,int px,int py) {
        super(_jeu);
        this.inflammee =false;
        this.posX=px;
        this.posY=py;
    }


    /* Redefinition de la methode traverssable */
    @Override
    public boolean traversable() {
        return !inflammee;
    }

    /*Si le joueur passe sur un dalle cette dernière est enflammée */

    public void inflammeDalle(Heros joueur)
    {
        if(joueur.getX()==this.posX && joueur.getY()==this.posY)
        {
            this.incendier();
        }
    }

    /* Si le joueur possede une capsule la dalle est eteinte et repasse a etat non enflammée */
    public void eteintFeuDall(Heros joueur)
    {
        if(joueur.getInventaire().getNombreCapsule()>0)
        {
            /* Test si joueur voisin à la dalle et orienté vers elle */
            if((this.isInflammee() && joueur.getX()==this.posX-1 && joueur.getY()==this.getPosY() && joueur.getOrientation()=="Est")
                    || (this.isInflammee() && joueur.getX()==this.posX+1 && joueur.getY()==this.getPosY() && joueur.getOrientation()=="Ouest")
                    || (this.isInflammee() && joueur.getX()==this.posX && joueur.getY()==this.getPosY()+1 && joueur.getOrientation()=="Nord")
                    || (this.isInflammee() && joueur.getX()==this.posX && joueur.getY()==this.getPosY()-1 && joueur.getOrientation()=="Sud"))
            {
               // System.out.println("Etindre feu");
                this.eteindreFeu();
                joueur.getInventaire().decNombreCapsules();
                //joueur.getInventaire().afficheInventaire();
            }
        }else
        {
            if(joueur.getInventaire().getNombreCapsule()==0)
            System.out.println("Pas de capsule");

        }
    }




    public void affichePosition()
    {
        System.out.println("Position dalle :"+this.posX+" , "+this.posY);
    }
}
