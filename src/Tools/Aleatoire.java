package Tools;

import java.util.Random;

public class Aleatoire {
    int max;
    int min;

    int Size_X,Size_y;

    public  Aleatoire()
    {

    }

    public int genereNombreBorne(int leMax)
    {
        int y = (int)Math.floor(Math.random()*(leMax-2+1)+2);
        return y;
    }

    /* Genere nombre borne*/
    public int genereNombreBorneMinMax(int leMax,int leMin)
    {
        Random random = new Random();
        int nb;
        nb = leMin+random.nextInt(leMax-leMin);
        return nb;
    }
}
