package modele.plateau;

public class Cle implements Elem{
    int posX,posY;

    /* Nombre de clés */
    private int nombreCle;

    /* Setters && Getters */
    public int getNombreCle()
    {
        return nombreCle;
    }

    public void setNombreCle(int n)
    {
        nombreCle=n;
    }

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
