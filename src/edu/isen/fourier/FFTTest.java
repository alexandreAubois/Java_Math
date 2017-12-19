package edu.isen.fourier;

import org.junit.Test;

import java.sql.SQLOutput;

import static org.junit.Assert.*;

public class FFTTest {

    @Test
    public void construct(){
        try {
            FFT error=new FFT(-1);
            fail("IllegalArgumentException expected");
        }catch (IllegalArgumentException e)
        {
            assertEquals(e.getMessage(),"size can't be inferior to zero");
        }

        FFT zero=new FFT(0);
        FFT positif=new FFT(15);
    }

    @Test
   public void calculeFFTReelleSinus() {
            float valeurSinus[]=new float[8];
        for(int i=0;i<valeurSinus.length;i++)
        {
            valeurSinus[i]= (float) Math.sin(2*Math.PI*i/valeurSinus.length);
        }
        FFT sinus=new FFT(3);
        sinus.calculeFFTReelle(valeurSinus);
        for(int i=0;i<valeurSinus.length;i++)
        {
            System.out.println("valeur pour i ="+i+" : "+sinus.getValeursN(i));
        }
    }

    @Test
    public void calculeFFTReelleConstante() {
        float valeurCst[]=new float[8];
        for(int i=0;i<valeurCst.length;i++)
        {
            valeurCst[i]= 1;
        }
        FFT cst=new FFT(3);
        cst.calculeFFTReelle(valeurCst);
        for(int i=0;i<valeurCst.length;i++)
        {
            System.out.println("valeur pour i ="+i+" : "+cst.getValeursN(i));
        }
    }

    @Test
    public void calculeFFTReelleDirac() {
        float valeurDirac[]=new float[8];
        valeurDirac[0]=1;
        FFT dirac=new FFT(3);
        dirac.calculeFFTReelle(valeurDirac);
        for(int i=0;i<valeurDirac.length;i++)
        {
            System.out.println("valeur pour i ="+i+" : "+dirac.getValeursN(i));
        }
    }

    @Test
    public void calculeFFTReelleCosinus() {
        float valeurCosinus[]=new float[8];
        for(int i=0;i<valeurCosinus.length;i++)
        {
            valeurCosinus[i]= (float) Math.cos(2*Math.PI*i/valeurCosinus.length);
        }
        FFT cosinus=new FFT(3);
        cosinus.calculeFFTReelle(valeurCosinus);
        for(int i=0;i<valeurCosinus.length;i++)
        {
            System.out.println("valeur pour i ="+i+" : "+cosinus.getValeursN(i));
        }
    }

}