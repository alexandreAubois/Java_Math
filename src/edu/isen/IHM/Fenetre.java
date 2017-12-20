package edu.isen.IHM;

import edu.isen.fourier.Complexe;
import edu.isen.fourier.FFT;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;


public class Fenetre extends JFrame{

    private static final Logger log= Logger.getLogger(Fenetre.class);
    private ButtonGroup monGroupe = new ButtonGroup();
    private JRadioButton FFTR = new JRadioButton("FFT Réel");
    private JRadioButton FFTC = new JRadioButton("FFT Complexe");
    private JRadioButton iFFT = new JRadioButton("iFFT");

    //private JPanel container = new JPanel();

    JButton Pan1 = new JButton("Lancer");
    JButton Pan2 = new JButton("Ouvrir");


    JFileChooser chooser = new JFileChooser();

    private String nomFichier;
    private int choixActuel;

    public Fenetre(){

        this.setTitle("Projet Java-Math");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        choixActuel=0;

        //Bouton "Lancer"
        Pan1.addActionListener(e -> {
            log.info("Fichier courant : "+this.nomFichier+"Choix actuel"+this.choixActuel);
        });

        //Bouton "ouvrir
        Pan2.addActionListener(e -> {

            System.out.println("Open");

            JFileChooser choix = new JFileChooser();
            FileNameExtensionFilter myFile = new FileNameExtensionFilter("Tableau", "csv");
            choix.addChoosableFileFilter(myFile);
            choix.setAcceptAllFileFilterUsed(false);
            int retour = choix.showOpenDialog(new Component() {
            });
            if(retour == JFileChooser.APPROVE_OPTION){
                String chemin =choix.getSelectedFile().getAbsolutePath();
                this.nomFichier=chemin;
            }
        });

        //CheckBox
        JPanel top = new JPanel();
        monGroupe.add(FFTR);
        FFTR.addActionListener(e -> this.choixActuel=0);
        FFTR.setSelected(true);
        monGroupe.add(FFTC);
        FFTC.addActionListener(e -> this.choixActuel=1);
        monGroupe.add(iFFT);
        iFFT.addActionListener(e -> this.choixActuel=2);
        top.add(FFTR);
        top.add(FFTC);
        top.add(iFFT);
        //container.add(top, BorderLayout.NORTH);

        JPanel PanF = new JPanel();
        PanF.setLayout(new BoxLayout(PanF, BoxLayout.PAGE_AXIS));

        PanF.add(Pan2);
        PanF.add(top);
        PanF.add(Pan1);

        this.getContentPane().add(PanF);
        this.setVisible(true);
    }

    public static void main(String[] args){
        Fenetre fen = new Fenetre();
    }


    /**
     * ActionListener pour le bouton start qui permet de lancer le programme
     */
    class StartListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            System.out.println("Start");

        }
    }
}