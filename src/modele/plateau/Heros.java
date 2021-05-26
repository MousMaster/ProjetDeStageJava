/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.plateau;

/**
 * Héros du jeu
 */
public class Heros {
    private int x;
    private int y;

    private Jeu jeu;

    /* Inventaire du joueur */
    private Inventaire inventaire;

    /* Entier reppresentant l'orientation du personnage
    1 => NORD
    2 => SUD
    3=> EST
    4=> OUEST
     */
    private int orientation;

    /*Numero du tresor interagissant avec le joueur a l'instant t*/
    private int numTresor ;

    /*Setters && Getters */

    public int getNumTresor() {
        return numTresor;
    }

    public void setNumTresor(int numTresor) {
        this.numTresor = numTresor;
    }



    void setOrientationN() {
        this.orientation = 1;
    }

    void setOrientationS() {
        this.orientation = 2;
    }

    void setOrientationE() {
        this.orientation = 3;
    }

    void setOrientationO() {
        this.orientation = 4;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Inventaire getInventaire() {
        return inventaire;
    }

    public String getOrientation() {
        if (orientation == 1) {
            return "Nord";
        } else if (orientation == 2) {
            return "Sud";
        } else if (orientation == 3) {
            return "Est";
        } else {
            return "Ouest";
        }
    }


    /* Constructeur */

    public Heros(Jeu _jeu, int _x, int _y) {
        jeu = _jeu;
        x = _x;
        y = _y;
        inventaire = new Inventaire();
        orientation = 3;
    }

    /* Les methodes de deplacement du joueur */

    public void droite() {
        if (traverssable(x + 1, y)) {
                x++;
            this.setOrientationE();

            /*Extension saut + Extenssion limiter nombre saut */
        } else if (ifCaseVide(x + 1, y)) {
            if (jeu.getEntite(x + 2, y) instanceof CaseNormale && ((CaseNormale) jeu.getEntite(x + 2, y)).traversable() && this.getInventaire().getNombreSautRestant()>0){
                x = x + 2;
                this.getInventaire().decNombreSaut(1);
                this.setOrientationE();

            }
        }
    }

    public void gauche() {
        if (traverssable(x - 1, y)) {
                this.setX(x - 1);
                this.setOrientationO();

            /*Extension saut + Extenssion limiter nombre saut */
        } else if (ifCaseVide(x - 1, y)) {
            if (jeu.getEntite(x - 2, y) instanceof CaseNormale && ((CaseNormale) jeu.getEntite(x - 2, y)).traversable()&& this.getInventaire().getNombreSautRestant()>0) {
                this.setX(x - 2);
                this.getInventaire().decNombreSaut(1);
                this.setOrientationO();


            }
        }
    }

    public void bas() {
        if (traverssable(x, y + 1)) {
                this.setY(y + 1);
                this.setOrientationS();

            /*Extension saut + Extenssion limiter nombre saut */
        } else if (ifCaseVide(x, y + 1)) {
            if (jeu.getEntite(x, y + 2) instanceof CaseNormale && ((CaseNormale) jeu.getEntite(x, y + 2)).traversable()&& this.getInventaire().getNombreSautRestant()>0) {
                this.setY(y + 2);
                this.getInventaire().decNombreSaut(1);
                this.setOrientationS();

            }
        }
    }

    public void haut() {
        if (traverssable(x, y - 1)) {
                this.setY(y - 1);
                this.setOrientationN();

            /*Extension saut + Extenssion limiter nombre saut  + */
        } else if (ifCaseVide(x, y - 1))
        {
            if (jeu.getEntite(x, y - 2) instanceof CaseNormale && ((CaseNormale) jeu.getEntite(x, y - 2)).traversable()&& this.getInventaire().getNombreSautRestant()>0) {
                y = y - 2;
                this.getInventaire().decNombreSaut(1);
                this.setOrientationN();
            }
        }

    }


    /* Extension saut + Extenssion limiter nombre saut  */

    private boolean ifCaseVide(int x, int y) {

        if(jeu.getEntite(x,y) instanceof caseVide)
        {
            if( ((caseVide) jeu.getEntite(x,y)).isAjoute())
            {
                return false;
            }
        }
        if (x >= 0 && x <= jeu.SIZE_X && y > 0 && y <= jeu.SIZE_Y) {
            if (jeu.getEntite(x, y) instanceof caseVide) {
                return true;

            } else {
                return false;
            }

        } else {
            return false;
        }
    }

    /* Extenssion Saut */

    /* Dalle herite de case normal donc il est important de  tester si la case normal est traverssable ou pas
        dans le cas de la dalle on va donc tester si est elle traverssable => enflamme ou pas
     */
    private boolean traverssable(int x, int y) {
        if (x >= 0 && x <= jeu.getSizeX() && y > 0 && y < jeu.getSizeY()) {
            if (jeu.getEntite(x, y) instanceof CaseNormale) {
                return ((CaseNormale) jeu.getEntite(x, y)).traversable();
            }
            if (jeu.getEntite(x, y) instanceof PorteVerouille) {
                return ((PorteVerouille) jeu.getEntite(x, y)).traversable();
            }

        }
        return false;
    }

    public void affichePos()
    {
        System.out.println("PosX: "+this.getX()+" PosY :"+this.getY()+this.getOrientation() );
    }


}
