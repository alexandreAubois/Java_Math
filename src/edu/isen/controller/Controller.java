package edu.isen.controller;

import edu.isen.IHM.Fenetre;
import edu.isen.fourier.Complexe;
import edu.isen.fourier.FFT;
import edu.isen.persistance.CSVReader;
import edu.isen.persistance.CSVWriter;
import org.apache.log4j.Logger;
import java.io.FileNotFoundException;

public class Controller {

    private static final Logger log= Logger.getLogger(Complexe.class);
    private FFT fft;
    private Fenetre fen;

    public void setFft(FFT fft) {
        this.fft = fft;
    }

    public Controller(FFT model){
        this.fft=model;
    }

    public void notifyAction(String nomFichier,int choix)
    {
        try {
            float valf[];
            Complexe valC[];
            switch (choix) {
                case 0:
                    valf = CSVReader.parseReelCSV(nomFichier);
                    this.fft.setSize(valf.length);
                    this.fft.calculeFFTReelle(valf);
                    break;
                case 1:
                    valC=CSVReader.parseComplexeCSV(nomFichier);
                    this.fft.setSize(valC.length);
                    this.fft.calculeFFTComplexe(valC);
                    break;
                case 2:
                    valC=CSVReader.parseComplexeCSV(nomFichier);
                    this.fft.setSize(valC.length);
                    this.fft.inverseFFT(valC);
                    break;
                case 3:
                    CSVWriter.writeCSV(fft.getValeurs(),nomFichier);
                    break;
                default:
                    log.warn("Choix d'action invalide");
                    break;
            }
        }catch (FileNotFoundException|NullPointerException e) {
            e.printStackTrace();
        }
    }




}
