package modele.plateau;
import Tools.Aleatoire;

import java.util.Stack;

public class Tresor implements Elem{
    int posX,posY;
    private Jeu jeu;

    /* Boolean representant l'etat du tresor si vu par le joueur ou pas */
    private boolean ouvert;
    /* Boolean representant : si tout le contenu du tresor a été recuperé ou pas */
    private boolean contenuRecupere;


    /*Pile d'élements representant l'ensemble du contenu du tresor */

    private Stack<Elem> contenu;

    /* Setters && Getters */

    public void setJeu(Jeu jeu){
        this.jeu=jeu;
    }
    public boolean isOuvert() {
        return ouvert;
    }

    public void setOuvert(boolean ouvert) {
        this.ouvert = ouvert;
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

    public int getTaille()
    {
        return contenu.size();
    }

    public boolean estVide()
    {
        return contenu.empty();
    }





    public Tresor() {
        contenu = new Stack<Elem>();
    }


    /* Rajoute un element au tresor de type Elem (soit Tresor ,Cle ou Capsule)*/

    public void ajouteElement(Elem composant){

        contenu.push(composant);
    }

    /* Supprime  element d'indice i du tresor */
    public void supprimeElem(int i){
        contenu.remove(i);
    }




/* Permet de voir le contenu du tresor */
    public void Visionner()
    {
        /* Si tresor pas vide */
        if (contenu.size() >0 )
        {
            System.out.println("-----------Contenu du tresor :-----------");
            System.lineSeparator();

            int nombreElments =contenu.size();

            /* Parcours de la pile du tresor */
            for(int i=0;i<nombreElments;i++)
            {
                /* Si element de type Cle */
                if(contenu.get(i) instanceof Cle)
                {
                    Cle nouVCle =(Cle) contenu.get(i);
                    System.out.println("Nombre de cle "+nouVCle.getNombreCle());
                    System.lineSeparator();
                    System.out.println("Appuyer sur C pour recupere le contenu");
                    this.ouvert=true;
                }else
                {
                    /* Si element de type Capsule */
                    if(contenu.get(i) instanceof Capsule)
                    {
                        Capsule cap =(Capsule) contenu.get(i);
                        System.out.println("Nombre de capsule "+cap.getNombre());
                        System.lineSeparator();
                        System.out.println("Appuyer sur C pour recuperer le premier");
                        this.ouvert=true;
                    }else
                        /* Si element de type Tresor on fait appel réccurssif */

                        if(contenu.get(i) instanceof Tresor)
                        {
                            System.out.println("-------Petit Tresor compris----");
                            ((Tresor) contenu.get(i)).Visionner();
                        }
                }
            }
        }else
        {
            System.out.println("--------Tresor vide -----");
        }
    }

    /* Recupere contenu du tresor */

    public void recupererContenuTresor(Heros heros) {
        /* S'asurre que le tresor a ete visionne avant son ouverture */
        if (this.ouvert)
            if (contenu.size() > 0) {
                /*Parcours la pille du tresor */
                for (int i = 0; i < contenu.size(); i++) {
                    if(contenu.get(i) instanceof Cle)
                    {
                        /* Si element de type cle on rajoute le nombre de cle a l'inventaire du joueur */
                        Cle nouvCle = (Cle) contenu.get(i);
                        int n = nouvCle.getNombreCle();
                        heros.getInventaire().setNombreCle(heros.getInventaire().getNombreCle() + nouvCle.getNombreCle());
                        heros.getInventaire().afficheInventaire();
                        this.supprimeElem(i);
                    }else if(contenu.get(i) instanceof Capsule) {

                        /* Si element de type capsule on rajoute le nombre de capsule a l'inventaire du joueur */
                        if (heros.getInventaire().getNombrePieces() >= 3) {
                            Capsule cap = (Capsule) contenu.get(i);
                            int n = cap.getNombre();
                            heros.getInventaire().setNombreCapsule(heros.getInventaire().getNombreCapsule() + cap.getNombre());
                            heros.getInventaire().afficheInventaire();
                            heros.getInventaire().decPiece();
                            this.supprimeElem(i);
                        }
                    }else if(contenu.get(i) instanceof Tresor)
                    {
                        System.out.println("-----Recuperation contenu petit tresor ----");
                        ((Tresor) contenu.get(i)).recupererContenuTresor(heros);
                        if(((Tresor) contenu.get(i)).estVide())
                        {
                            this.supprimeElem(i);
                        }
                    }
                    else
                        {
                            System.out.println("Nombre de piece insuffisant");
                        }

                    }
                }

                if(contenu.empty())
                {
                    this.contenuRecupere = true;
                }

            }
    public boolean getIsCleeRecuperee()
    {
        return contenuRecupere;
    }

    public void init(Jeu jeu,int numSall)
    {
        Aleatoire a=new Aleatoire();
        Cle c =new Cle();
        c.setNombreCle(a.genereNombreBorneMinMax(3,1));
        Capsule cap =new Capsule();
        cap.setNombre(a.genereNombreBorneMinMax(3,1));

        Tresor tre=new Tresor();
        tre.ajouteElement(c);

        int x=a.genereNombreBorneMinMax(Salle.SIZE_X+Salle.SIZE_X*numSall-1,Salle.SIZE_X+Salle.SIZE_X*(numSall-1)+1);
        int y=a.genereNombreBorneMinMax(Salle.SIZE_Y-1,1);

        this.ajouteElement(c);
        this.ajouteElement(cap);
        this.ajouteElement(tre);

        this.ouvert=false;
        this.setPoX(x);
        this.setPoY(y);
    }



    public void echanger(Heros heros)
    {
        if(heros.getInventaire().getNombreCapsule()>0)
        {
            System.out.println("test");
            heros.getInventaire().setNombreCapsule(0);
            heros.getInventaire().incNombreSaut(5);
        }
    }
}
