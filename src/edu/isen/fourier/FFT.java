package edu.isen.fourier;

import org.apache.log4j.Logger;

public class FFT {

    private Complexe valeurs[];
    private static final Logger log= Logger.getLogger(Complexe.class);


    public FFT(int size) throws IllegalArgumentException {
        if(size<0)
        {
            log.warn("mauvaise taille de tableau");
            throw new IllegalArgumentException("size can't be inferior to zero");
        }else {
            valeurs=new Complexe[size];
        }
    }

    public void calculeFFTReelle(float entree[])
    {
        if(entree.length==1)
        {
            valeurs[0]=new Complexe(entree[0],0);
        }
        int taille=(int) (Math.log(entree.length)/Math.log(2))-1;
        FFT paire=new FFT(taille);
        FFT impaire = new FFT(taille);
        float elementPaire[] =new float[taille];
        float elementImpaire[] =new float[taille];
        for (int i=0;i<entree.length;i++)
        {
            if(i%2==0)
            {
                elementPaire[i/2]=entree[i];
            }else {
                elementImpaire[(i-1)/2]=entree[i];
            }
        }
        paire.calculeFFTReelle(elementPaire);
        impaire.calculeFFTReelle(elementImpaire);

        for(int i=0;i<entree.length/2;i++)
        {
            Complexe M=new Complexe((float) (2*Math.PI*i/entree.length));
            this.valeurs[i]=paire.getValeursN(i).add(impaire.getValeursN(i).multiply(M));
            this.valeurs[i+entree.length/2]=paire.getValeursN(i).sub(impaire.getValeursN(i).multiply(M));
        }

    }

    public Complexe getValeursN(int n) {
        return valeurs[n];
    }

}
