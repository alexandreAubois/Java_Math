package edu.isen.IHM;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;


public class Fenetre extends JFrame{

    private ButtonGroup monGroupe = new ButtonGroup();
    private JRadioButton FFTR = new JRadioButton("FFT Réel");
    private JRadioButton FFTC = new JRadioButton("FFT Complexe");
    private JRadioButton iFFT = new JRadioButton("iFFT");

    //private JPanel container = new JPanel();

    JButton Pan1 = new JButton("Lancer");
    JButton Pan2 = new JButton("Ouvrir");


    JFileChooser chooser = new JFileChooser();

    public Fenetre(){

        this.setTitle("Projet Java-Math");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        //Bouton "Lancer"
        Pan1.addActionListener(new StartListener());

        //Bouton "ouvrir
        Pan2.addActionListener(new OpenListener());

        //CheckBox
        JPanel top = new JPanel();
        monGroupe.add(FFTR);
        FFTR.addActionListener(new CheckBoxListener());
        monGroupe.add(FFTC);
        FFTC.addActionListener(new CheckBoxListener());
        monGroupe.add(iFFT);
        iFFT.addActionListener(new CheckBoxListener());
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

    class CheckBoxListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            System.out.println("source : " + ((JRadioButton) e.getSource()).getText() + " - état : " + ((JRadioButton) e.getSource()).isSelected());

        }
    }
    class StartListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            System.out.println("Start");

        }
    }
    class OpenListener extends Component implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            System.out.println("Open");

            JFileChooser choix = new JFileChooser();
            FileNameExtensionFilter myFile = new FileNameExtensionFilter("Tableau", "csv");
            choix.addChoosableFileFilter(myFile);
            choix.setAcceptAllFileFilterUsed(false);
            int retour = choix.showOpenDialog(this);
            if(retour == JFileChooser.APPROVE_OPTION){
                String chemin =choix.getSelectedFile().getAbsolutePath();
                Fenetre.load(chemin);
            }

        }
    }

    public static void load(String chemin){

    }
}