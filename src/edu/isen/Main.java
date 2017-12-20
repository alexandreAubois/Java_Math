package edu.isen;

import edu.isen.IHM.Fenetre;
import edu.isen.controller.Controller;
import edu.isen.fourier.FFT;

public class Main {

    public static void main(String[] args) {
        FFT ff=new FFT(0);
        Controller controller=new Controller(ff);
        Fenetre fen = new Fenetre(controller);
        ff.addObserver(fen);
        controller.setFft(ff);
    }
}
