package edu.isen.controller;

import edu.isen.IHM.Fenetre;
import edu.isen.fourier.Complexe;
import edu.isen.fourier.FFT;
import edu.isen.persistance.CSVReader;

import java.io.FileNotFoundException;

public class Controller {

    public void setFft(FFT fft) {
        this.fft = fft;
        System.out.println(fft);
    }

    private FFT fft;

    public Fenetre getFen() {
        return fen;
    }

    public void setFen(Fenetre fen) {
        this.fen = fen;
    }

    private Fenetre fen;

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
                    break;
            }
        }catch (FileNotFoundException|NullPointerException|IllegalArgumentException e) {
            fen.createPopUp(e.toString());
        }
    }




}
