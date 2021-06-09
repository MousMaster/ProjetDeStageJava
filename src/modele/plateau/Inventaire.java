package modele.plateau;

public class Inventaire {

    /* Le nombre de chaque element dans l'inventaire */

    private int nombreCle;
    private int nombreCapsule;


    /* extension limite nombre saut + contrainte d'avoir un nombre de pieces suffisant pour acheter des capsules
        dans le tresor trouvé
     */
    private int nombrePieces;
    private int nombreSautRestant;

    public void setNombrePieces(int nombrePieces) {
        this.nombrePieces = nombrePieces;
    }

    public void setNombreSautRestant(int nombreSautRestant) {
        this.nombreSautRestant = nombreSautRestant;
    }
    /* Getters && Setters && Incrementeur && Decrementeur */

    public int getNombreSautRestant() {
        return nombreSautRestant;
    }

    public void decNombreSaut(int s) {
        this.nombreSautRestant-=s;
    }

    public void incNombreSaut(int s) {
        this.nombreSautRestant+=s;
    }

    public int getNombrePieces() {
        return nombrePieces;
    }

    public void incNombrePiece(int nombrePieces) {
        this.nombrePieces += nombrePieces;
    }

    public void decPiece()
    {
        nombrePieces=nombrePieces-3;
    }

    public int getNombreCapsule() {
        return nombreCapsule;
    }

    public void setNombreCapsule(int nombreCapsule) {
        this.nombreCapsule = nombreCapsule;
    }

    //ajou des setters et getters de de clé + fonction decremente
    public int getNombreCle(){ return nombreCle;}

    public void setNombreCle(int nbCle){ this.nombreCle =nbCle;}

    public void decNombreCle(){ if(this.nombreCle>0) this.nombreCle--;}
    public void decNombreCapsules(){ this.nombreCapsule--;}

    public String afficheInventaire(){
        System.out.println("----------Inventaire :-----------");
        System.lineSeparator();
        System.out.println("Cle :"+getNombreCle());
        System.lineSeparator();
        System.out.println("Capsules :"+getNombreCapsule());
        System.lineSeparator();
        System.out.println("Pieces :"+getNombrePieces());
        System.lineSeparator();
        System.out.println("Nombre sauts autorisés :"+getNombreSautRestant());
        System.lineSeparator();
        System.out.println("Pour avoir de l'aide appuyer sur H :"+getNombreSautRestant());
        System.lineSeparator();

        String inventaire= "Cle :"+getNombreCle() + "Capsules :"+getNombreCapsule() +"Pieces :"+getNombrePieces() + "Nombre sauts autorisés :"+getNombreSautRestant();
        return inventaire;
    }

}
