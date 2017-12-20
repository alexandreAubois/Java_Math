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
    private ButtonGroup monGroupe = new ButtonGroup();
    private JRadioButton FFTR = new JRadioButton("FFT Réel");
    private JRadioButton FFTC = new JRadioButton("FFT Complexe");
    private JRadioButton iFFT = new JRadioButton("iFFT");
    private JPanel PanF = new JPanel();
    private JFreeChart jc;
    private Controller controller;
    private ChartPanel cp;

    //private JPanel container = new JPanel();

    JButton Pan1 = new JButton("Lancer");
    JButton Pan2 = new JButton("Ouvrir");


    JFileChooser chooser = new JFileChooser();

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
        Pan1.addActionListener(e -> {
            log.info("Action à effectuer Fichier courant : "+this.nomFichier+"Choix actuel "+this.choixActuel);
            this.controller.notifyAction(this.nomFichier,this.choixActuel);
        });

        //Bouton "ouvrir
        Pan2.addActionListener(e -> {
            JFileChooser choix = new JFileChooser();
            FileNameExtensionFilter myFile = new FileNameExtensionFilter("Tableau", "csv");
            choix.addChoosableFileFilter(myFile);
            choix.setAcceptAllFileFilterUsed(false);
            int retour = choix.showOpenDialog(new Component() {
            });
            if(retour == JFileChooser.APPROVE_OPTION){
                this.nomFichier= choix.getSelectedFile().getAbsolutePath();
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

        PanF.setLayout(new BoxLayout(PanF, BoxLayout.PAGE_AXIS));

        PanF.add(Pan2);
        PanF.add(top);
        PanF.add(Pan1);
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
        PanF.add(cp);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();

        this.getContentPane().add(PanF);
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
            chart.

            this.cp.setChart(chart);
        }

    }
}