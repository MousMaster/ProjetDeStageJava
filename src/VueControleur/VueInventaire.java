package VueControleur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;


import modele.plateau.*;


/** Cette classe a deux fonctions :
 *  (1) Vue : proposer une représentation graphique de l'application (cases graphiques, etc.)
 *  (2) Controleur : écouter les évènements clavier et déclencher le traitement adapté sur le modèle (flèches direction, etc.))
 *
 */
public class VueInventaire extends JFrame implements Observer  {
    private Jeu jeu; // référence sur une classe de modèle : permet d'accéder aux données du modèle pour le rafraichissement, permet de communiquer les actions clavier (ou souris)

    private int sizeX; // taille de la grille affichée
    private int sizeY;




    // icones affichées dans la grille
    private ImageIcon icoHero;
    private ImageIcon icoTresorOuvert;
    private ImageIcon icoCle;
    private  ImageIcon icoTresor ;



    private ImageIcon icoSaut;
    private ImageIcon icoDalleFermee;
    private ImageIcon icoCapsule;
    private ImageIcon icoPiece;

    private ImageIcon icoMonster;
    private ImageIcon icoCaseVideAjoute;

    private ImageIcon icoPorteBlinde;

    private ImageIcon icoPorteNonTraverssable;

    private ImageIcon icoMortier;
    private ImageIcon icoBalle;







    private JLabel[][] tabJLabel; // cases graphique (au moment du rafraichissement, chaque case va être associée à une icône, suivant ce qui est présent dans le modèle)

    private JLabel[][] tabJLabel_1;

    public VueInventaire(Jeu _jeu) {
        sizeX = jeu.SIZE_X;
        sizeY = _jeu.SIZE_Y;
        jeu = _jeu;

        chargerLesIcones();
        placerLesComposantsGraphiques();
        ajouterEcouteurClavier();
    }

    private void ajouterEcouteurClavier() {
        addKeyListener(new KeyAdapter() { // new KeyAdapter() { ... } est une instance de classe anonyme, il s'agit d'un objet qui correspond au controleur dans MVC
            @Override
            public void keyPressed(KeyEvent e) {
                jeu.getHeros().getInventaire().afficheInventaire();
                //jeu.commentJouer();
                switch(e.getKeyCode()) {  // on regarde quelle touche a été pressée
                    case KeyEvent.VK_LEFT :
                    {
                        jeu.getHeros().gauche();
                    } break;
                    case KeyEvent.VK_RIGHT :
                    {
                        jeu.getHeros().droite();
                    }break;
                    case KeyEvent.VK_DOWN : jeu.getHeros().bas(); break;
                    case KeyEvent.VK_UP : jeu.getHeros().haut(); break;
                    case KeyEvent.VK_R:  jeu.relancer(); break;
                    case KeyEvent.VK_I:  jeu.getHeros().getInventaire().afficheInventaire(); break;
                    case KeyEvent.VK_O:  jeu.ouvrePorte(); break;
                    case KeyEvent.VK_H : jeu.help(); break;
                    case KeyEvent.VK_W : jeu.eteindredalles(); break;

                    case KeyEvent.VK_T:
                    {
                        if(jeu.getHeros().getInventaire().getNombreCapsule()>3)
                        {
                            System.out.println("Appuyer sur Z pour echanger vos capsuls contre 5 sauts");
                            if(e.getKeyCode()==KeyEvent.VK_Z)
                            {
                                /* extension echanger des capsules contre des sauts en plus */
                                jeu.accesTreso(jeu.getHeros().getNumTresor()).echanger(jeu.getHeros());
                            }
                        }
                        jeu.afficheContenuTresor();

                    } break;

                    case KeyEvent.VK_C:
                    {
                        jeu.recupererContenuTresor();
                    }break;


                }
            }
        });
    }


    private void chargerLesIcones() {
        icoHero = chargerIcone("Images/PacmanE.png");


        icoCle =chargerIcone("Images/clee.png");
        icoSaut = chargerIcone("Images/saut.png");
        icoDalleFermee = chargerIcone("Images/dalleFermee.png");
        icoCapsule = chargerIcone("Images/capsule.png");
        icoTresorOuvert = chargerIcone("Images/tresorOuvert.png");
        icoPiece = chargerIcone("Images/piece.png");

        icoTresor =chargerIcone("Images/tresor.png");


        icoMonster = chargerIcone("Images/Monster.png");
        icoCaseVideAjoute = chargerIcone("Images/forbiden.png");

        icoPorteBlinde = chargerIcone("Images/redDoor.png");

        icoMortier = chargerIcone("Images/mortier.png");

        icoBalle= chargerIcone("Images/balle.png");

        icoPorteNonTraverssable =chargerIcone("Images/porteFerme.png");

    }

    private ImageIcon chargerIcone(String urlIcone) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(urlIcone));
        } catch (IOException ex) {
            Logger.getLogger(VueControleur.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return new ImageIcon(image);
    }

    private void placerLesComposantsGraphiques() {

        setTitle("Inventaire");
        setSize(300 * Jeu.NBRS, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permet de terminer l'application à la fermeture de la fenêtre

        JComponent grilleJLabels = new JPanel(new GridLayout(sizeY, sizeX)); // grilleJLabels va contenir les cases graphiques et les positionner sous la forme d'une grille

        tabJLabel = new JLabel[sizeX][sizeY];

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                JLabel jlab = new JLabel();
                tabJLabel[x][y] = jlab; // on conserve les cases graphiques dans tabJLabel pour avoir un accès pratique à celles-ci (voir mettreAJourAffichage() )
                grilleJLabels.add(jlab);
            }
        }
        add(grilleJLabels);

        Clickprogram c =new Clickprogram();

    }










    /**
     * Il y a une grille du côté du modèle ( jeu.getGrille() ) et une grille du côté de la vue (tabJLabel)
     */
    private void mettreAJourAffichage() {

        tabJLabel[0][0].setIcon(icoHero);


        tabJLabel[1][1].setIcon(icoCle);
        tabJLabel[1][2].setIcon(icoPiece);
        tabJLabel[1][3].setIcon(icoCapsule);
        tabJLabel[1][4].setIcon(icoSaut);



        tabJLabel[2][1].setText(" "+jeu.getHeros().getInventaire().getNombreCle());
        tabJLabel[2][2].setText(" "+jeu.getHeros().getInventaire().getNombrePieces());
        tabJLabel[2][3].setText(" "+jeu.getHeros().getInventaire().getNombreCapsule());
        tabJLabel[2][4].setText(" "+jeu.getHeros().getInventaire().getNombreSautRestant());


        tabJLabel[4][0].setIcon(icoTresorOuvert);

        tabJLabel[5][1].setIcon(icoCle);
        tabJLabel[5][2].setIcon(icoCapsule);
        tabJLabel[5][3].setIcon(icoTresor);

        tabJLabel[6][1].setText(" "+jeu.getMesTresors().getNombreCle_tresor_courant());
        tabJLabel[6][2].setText(" "+jeu.getMesTresors().getNombreCapsule_tresor_courant());
        tabJLabel[6][3].setText(" "+jeu.getMesTresors().getNombreTresors_sous_tresor_dans_courant());



        tabJLabel[10][0].setText(" PN ");
        tabJLabel[10][1].setText(" "+jeu.getHeros().getInventaire().getNumPorteDernierementOuverte());

        tabJLabel[11][0].setText("PA");
        tabJLabel[12][0].setText("RT");
        tabJLabel[13][0].setText("IE");
        tabJLabel[11][1].setText(" "+jeu.getMaPartie().getNiveau());

        for(int x=0;x<jeu.getSizeX();x++)
            for(int y=0;y<jeu.getSizeY();y++)
            {
                if(jeu.getEntite(x,y) instanceof PorteVerouille)
                {
                    PorteVerouille p = (PorteVerouille) jeu.getEntite(x,y);
                    if(p.getType()==2)
                    {
                        tabJLabel[8][p.getNumPorte()].setIcon(icoPorteBlinde);
                        tabJLabel[9][p.getNumPorte()].setText(""+p.getNumPorte());
                        tabJLabel[10][p.getNumPorte()].setText(""+p.getBlindage());
                    }else
                    {
                        tabJLabel[8][p.getNumPorte()].setIcon(icoPorteNonTraverssable);
                        tabJLabel[9][p.getNumPorte()].setText(""+p.getNumPorte());
                        tabJLabel[10][p.getNumPorte()].setText(""+p.getBlindage());
                    }
                }
            }

      //  if(Menu.)






    }



    @Override
    public void update(Observable o, Object arg) {
        jeu.enflammeDalles();

        mettreAJourAffichage();
    }


    class Clickprogram extends JFrame{

        private JButton Relancer;
        private JButton Pause;
        private JButton Repprendre;

        public Clickprogram()
        {

            this.setSize(300,150);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Menu Du Jeu");

            Clickprogram.Clicklistener click= new Clickprogram.Clicklistener();

            JPanel panel1= new JPanel();
            Relancer = new JButton ("Relancer");
            Pause =new JButton("Pause");
            Repprendre =new JButton("Repprendre");


            Relancer.addActionListener(click);
            Pause.addActionListener(click);
            Repprendre.addActionListener(click);
            panel1.add(Pause);
            panel1.add(Repprendre);
            panel1.add(Relancer);

            this.setBounds(150,600,200,150);
            this.add(panel1);
            this.setVisible(true);
        }

        private class Clicklistener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                if (e.getSource() == Relancer)
                {
                    jeu.relancer();
                }

                if (e.getSource() == Pause)
                {
                    jeu.controleJeu(true);
                }

                if (e.getSource() == Repprendre)
                {
                    jeu.controleJeu(false);
                }
            }
        }
    }


}
