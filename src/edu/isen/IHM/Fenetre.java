package edu.isen.IHM;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JCheckBox;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import java.io.File;


public class Fenetre extends JFrame{

    private JCheckBox FFTR = new JCheckBox("FFT Réel");
    private JCheckBox FFTC = new JCheckBox("FFT Complexe");
    private JCheckBox iFFT = new JCheckBox("iFFT");
    private JPanel container = new JPanel();




    public Fenetre(){

        this.setTitle("Projet Java-Math");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        //Bouton "Lancer"
        JPanel Pan1 = new JPanel();

        Pan1.setLayout(new BoxLayout(Pan1, BoxLayout.LINE_AXIS));
        Pan1.add(new JButton("Lancer"));

        //Bouton "ouvrir
        JPanel Pan2 = new JPanel();

        Pan2.setLayout(new BoxLayout(Pan2, BoxLayout.LINE_AXIS));
        Pan2.add(new JButton("Ouvrir"));



        //CheckBox
        JPanel top = new JPanel();
        FFTR.addActionListener(new StateListener());
        FFTC.addActionListener(new StateListener());
        iFFT.addActionListener(new StateListener());
        top.add(FFTR);
        top.add(FFTC);
        top.add(iFFT);
        container.add(top, BorderLayout.NORTH);

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

    class StateListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            System.out.println("source : " + ((JCheckBox) e.getSource()).getText() + " - état : " + ((JCheckBox) e.getSource()).isSelected());

        }
    }
}