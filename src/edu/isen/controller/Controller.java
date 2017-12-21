package edu.isen.controller;

import edu.isen.fourier.Complexe;
import edu.isen.fourier.FFT;
import edu.isen.persistance.CSVReader;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;

public class Controller {

    private static final Logger log= Logger.getLogger(Complexe.class);
    private FFT fft;

    public void setFft(FFT fft) {
        this.fft = fft;
        System.out.println(fft);
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
                default:
                    log.warn("Choix d'action invalide");
                    break;
            }
        }catch (FileNotFoundException|NullPointerException e) {
            e.printStackTrace();
        }
    }




}
