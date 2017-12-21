package edu.isen.controller;

import edu.isen.IHM.Fenetre;
import edu.isen.fourier.Complexe;
import edu.isen.fourier.FFT;
import edu.isen.persistance.CSVReader;
import edu.isen.persistance.CSVWriter;
import org.apache.log4j.Logger;

/**
 * classe servant de controller pour l'application
 */
public class Controller {

    private static final Logger log= Logger.getLogger(Controller.class);
    private FFT fft;
    private Fenetre fen;

    public void setFft(FFT fft) {
        this.fft = fft;
    }

    public void setFen(Fenetre fen) {
        this.fen = fen;
    }

    public Controller(FFT model){
        this.fft=model;
    }

    /**
     * methode appelée pour notifier le controlleur de l'action choisis par l'utilisateur
     * @param nomFichier nom du fichier à traiter
     * @param choix action choisis
     *              0 :lire un csv de reel et faire sa transformée de fourier
     *              1 :lire un csv de Complex et faire sa transformée de fourier
     *              2 :lire un csv de Complex et faire sa transformée de fourier inverse
     *              3 : enregister le dernier resultat effectué au format CSV
     */
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
        }catch (IllegalArgumentException|NullPointerException e) {
            fen.createPopUp(e.toString());
        }
    }




}
