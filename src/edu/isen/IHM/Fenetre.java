package edu.isen.IHM;

import edu.isen.controller.Controller;
import edu.isen.fourier.FFT;
import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;


public class Fenetre extends JFrame implements Observer{

    private static final Logger log= Logger.getLogger(Fenetre.class);
    private Controller controller;
    private ChartPanel cp;
    private JFrame message = new JFrame("Message d'erreur");
    //private JPanel container = new JPanel();

    private String nomFichier;
    private int choixActuel;


    public Fenetre(Controller controllerP){

        this.setTitle("Projet Java-Math");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.controller=controllerP;
        choixActuel=0;

        //Bouton "Lancer"
        JButton pan1 = new JButton("Lancer");
        pan1.addActionListener(e -> {
            log.info("Action à effectuer Fichier courant : "+this.nomFichier+"Choix actuel "+this.choixActuel);
            this.controller.notifyAction(this.nomFichier,this.choixActuel);
        });

        //Bouton "ouvrir
        JButton pan2 = new JButton("Ouvrir");
        pan2.addActionListener(e -> {
            JFileChooser choix = new JFileChooser();
            FileNameExtensionFilter myFile = new FileNameExtensionFilter("Tableau", "csv");
            choix.addChoosableFileFilter(myFile);
            choix.setAcceptAllFileFilterUsed(false);
            int retour = choix.showOpenDialog(new Component() {
            });
            if(retour == JFileChooser.APPROVE_OPTION){
                this.nomFichier= choix.getSelectedFile().getAbsolutePath();
            }
            //this.createPopUp("hello");
        });
        JButton saveAs = new JButton("Sauvegarder sous");
        saveAs.addActionListener(e ->{
            JFileChooser choix = new JFileChooser();
            FileNameExtensionFilter myFile = new FileNameExtensionFilter("Tableau", "csv");
            choix.addChoosableFileFilter(myFile);
            choix.setAcceptAllFileFilterUsed(false);
            int retour = choix.showSaveDialog(new Component() {
            });
            if(retour == JFileChooser.APPROVE_OPTION){
                this.controller.notifyAction(choix.getSelectedFile().getAbsolutePath(),3);
            }
        });
        //CheckBox
        JPanel top = new JPanel();
        ButtonGroup monGroupe = new ButtonGroup();
        JRadioButton FFTR = new JRadioButton("FFT Réel");
        monGroupe.add(FFTR);
        FFTR.addActionListener(e -> this.choixActuel=0);
        FFTR.setSelected(true);
        JRadioButton FFTC = new JRadioButton("FFT Complexe");
        monGroupe.add(FFTC);
        FFTC.addActionListener(e -> this.choixActuel=1);
        JRadioButton iFFT = new JRadioButton("iFFT");
        monGroupe.add(iFFT);
        iFFT.addActionListener(e -> this.choixActuel=2);
        top.add(FFTR);
        top.add(FFTC);
        top.add(iFFT);
        //container.add(top, BorderLayout.NORTH);

        JPanel panF = new JPanel();
        panF.setLayout(new BoxLayout(panF, BoxLayout.PAGE_AXIS));

        panF.add(pan2);
        panF.add(top);
        panF.add(pan1);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Analyse Spectrale", "N", "Magnitude",
                null, PlotOrientation.VERTICAL, true, true,false);
        cp= new ChartPanel(chart) {

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(320, 240);
            }
        };
        cp.setMouseWheelEnabled(true);
        panF.add(cp);
        panF.add(saveAs);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();

        this.getContentPane().add(panF);
        this.setSize(new Dimension(400,400));
        this.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof FFT) {
            FFT fft=(FFT)o;
            XYSeries Goals = new XYSeries("Transformée de Fourier");
            for (int i = 0; i<fft.getValeurs().length;i++)
            {
                Goals.add(i,fft.getValeursN(i).getModule());
            }
            XYDataset xyDataset = new XYSeriesCollection(Goals);
            JFreeChart chart = ChartFactory.createXYLineChart(
                    "Goals Scored Over Time", "N", "Magnitude",
                    xyDataset, PlotOrientation.VERTICAL, true, false, false);
            this.cp.setChart(chart);
        }

    }
    public void createPopUp(String message_error){
        log.info("ici");
        JOptionPane.showMessageDialog(message, message_error);
    }
}