package modele.plateau;

public class Capsule implements Elem {

/* Nombre de capsule */

    private int nombre;

    /*Getters && Setters */

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    int posX,posY;
    public int getPosX()
    {
        return posX;
    }
    public int getPosY()
    {
        return posY;
    }
    public void setPoX(int p)
    {
        this.posX=p;
    }
    public void setPoY(int p)
    {
        this.posY=p;
    }
}
