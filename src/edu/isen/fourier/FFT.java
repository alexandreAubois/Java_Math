package edu.isen.fourier;

import org.apache.log4j.Logger;

public class FFT {

    private Complexe valeurs[];
    private static final Logger log= Logger.getLogger(Complexe.class);


    public FFT(int size) throws IllegalArgumentException {
        if(size<0)
        {
            log.warn("mauvaise taille de tableau pour la FFT");
            throw new IllegalArgumentException("size can't be inferior to zero");
        }else {
            valeurs=new Complexe[(int) Math.pow(2,size)];
            log.info("creation d'un tableau de taille "+valeurs.length);
        }
    }

    public void calculeFFTReelle(float entree[])
    {
        if(entree.length==1)
        {
            valeurs[0]=new Complexe(entree[0],0);
        }else {

            int taille = (int) (Math.log(entree.length) / Math.log(2)) - 1;
            log.debug("nouvelle puissance de 2 du tableau :"+taille);
            FFT paire = new FFT(taille);
            FFT impaire = new FFT(taille);
            float elementPaire[] = new float[(int) Math.pow(2,taille)];
            float elementImpaire[] = new float[(int) Math.pow(2,taille)];
            for (int i = 0; i < entree.length; i++) {
                if (i % 2 == 0) {
                    elementPaire[i / 2] = entree[i];
                } else {
                    elementImpaire[(i - 1) / 2] = entree[i];
                }
            }
            paire.calculeFFTReelle(elementPaire);
            impaire.calculeFFTReelle(elementImpaire);

            for (int i = 0; i < entree.length / 2; i++) {
                Complexe M = new Complexe((float) (2 * Math.PI * i / entree.length));
                this.valeurs[i] = paire.getValeursN(i).add(impaire.getValeursN(i).multiply(M));
                this.valeurs[i + entree.length / 2] = paire.getValeursN(i).sub(impaire.getValeursN(i).multiply(M));
            }
        }

    }

    public Complexe getValeursN(int n) {
        return valeurs[n];
    }

    public void calculeFFTComplexe(Complexe entree[])
    {
        if(entree.length==1)
        {
            valeurs[0] = entree[0];
        }else {

            int taille = (int) (Math.log(entree.length) / Math.log(2)) - 1;
            log.debug("nouvelle puissance de 2 du tableau :"+taille);
            FFT paire = new FFT(taille);
            FFT impaire = new FFT(taille);
            Complexe elementPaire[] = new Complexe[(int) Math.pow(2,taille)];
            Complexe elementImpaire[] = new Complexe[(int) Math.pow(2,taille)];
            for (int i = 0; i < entree.length; i++) {
                if (i % 2 == 0) {
                    elementPaire[i / 2] = entree[i];
                } else {
                    elementImpaire[(i - 1) / 2] = entree[i];
                }
            }
            paire.calculeFFTComplexe(elementPaire);
            impaire.calculeFFTComplexe(elementImpaire);

            for (int i = 0; i < entree.length / 2; i++) {
                Complexe M = new Complexe((float) (2 * Math.PI * i / entree.length));
                this.valeurs[i] = paire.getValeursN(i).add(impaire.getValeursN(i).multiply(M));
                this.valeurs[i + entree.length / 2] = paire.getValeursN(i).sub(impaire.getValeursN(i).multiply(M));
            }
        }

    }

}
