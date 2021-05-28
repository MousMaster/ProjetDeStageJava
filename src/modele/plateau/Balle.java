package modele.plateau;

public class Balle extends Heros{
    public Balle(Jeu _jeu, int _x, int _y) {
        super(_jeu, _x, _y);
    }

    public void tire() throws InterruptedException {
        while(this.getY()>0)
        {
            //this.setY(this.getY()+1);
            Thread.sleep(100);
        }
    }
}
