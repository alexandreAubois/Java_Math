package edu.isen.fourierTest;

import edu.isen.fourier.Complexe;
import edu.isen.fourier.FFT;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FFTTest {

    @Test
    public void construct(){
        try {
            new FFT(-1);
            fail("IllegalArgumentException expected");
        }catch (IllegalArgumentException e)
        {
            assertEquals(e.getMessage(),"size can't be inferior to zero");
        }

        new FFT(0);
        new FFT(15);
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
        float res[][]={{0,0},{0,-4},{0,0},{0,0},{0,0},{0,0},{0,0},{0,4}};
        for(int i=0;i<valeurSinus.length;i++)
        {
            assertEquals(res[i][0],sinus.getValeursN(i).getRe(),Math.pow(10,-5));
            assertEquals(res[i][1],sinus.getValeursN(i).getIm(),Math.pow(10,-5));
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
        float res[][]={{8,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0}};
        for(int i=0;i<valeurCst.length;i++)
        {
            assertEquals(res[i][0],cst.getValeursN(i).getRe(),Math.pow(10,-5));
            assertEquals(res[i][1],cst.getValeursN(i).getIm(),Math.pow(10,-5));
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
            assertEquals(1,dirac.getValeursN(i).getRe(),Math.pow(10,-5));
            assertEquals(0,dirac.getValeursN(i).getIm(),Math.pow(10,-5));
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
        float res[][]={{0,0},{4,0},{0,0},{0,0},{0,0},{0,0},{0,0},{4,0}};
        for(int i=0;i<valeurCosinus.length;i++) {
            assertEquals(res[i][0], cosinus.getValeursN(i).getRe(), Math.pow(10, -5));
            assertEquals(res[i][1], cosinus.getValeursN(i).getIm(), Math.pow(10, -5));
        }
    }

    @Test
    public void calculFFTImaginaireConstante(){
        Complexe valeurCst[] = new Complexe[8];
        for (int indice=0 ; indice<valeurCst.length; indice++){
            valeurCst[indice] = new Complexe(0,1);;
        }
        FFT imaginaire = new FFT(3);
        imaginaire.calculeFFTComplexe(valeurCst);
        float res[][]={{0,8},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0}};
        for (int i=0; i<valeurCst.length; i++ ){
            assertEquals(res[i][0], imaginaire.getValeursN(i).getRe(), Math.pow(10, -5));
            assertEquals(res[i][1], imaginaire.getValeursN(i).getIm(), Math.pow(10, -5));
        }
    }

    @Test
    public void calculFFTImaginaireExpo(){
        Complexe valeurExp[] = new Complexe[8];
        for (int indice=0 ; indice<valeurExp.length; indice++){
            valeurExp[indice] = new Complexe((float) (Math.PI * 2 *indice/(valeurExp.length)));
        }
        FFT imaginaire = new FFT(3);
        imaginaire.calculeFFTComplexe(valeurExp);
        float res[][]={{0,0},{8,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0}};
        for (int i=0; i<valeurExp.length; i++ ){
            assertEquals(res[i][0], imaginaire.getValeursN(i).getRe(), Math.pow(10, -5));
            assertEquals(res[i][1], imaginaire.getValeursN(i).getIm(), Math.pow(10, -5));
        }
    }

    @Test
    public void iFFTconstant()
    {
        float valeurCst[]=new float[8];
        for(int i=0;i<valeurCst.length;i++)
        {
            valeurCst[i]= 1;
        }
        FFT cst=new FFT(3);
        cst.calculeFFTReelle(valeurCst);
        FFT inverse=new FFT(3);
        inverse.inverseFFT(cst.getValeurs());
        for(int i=0;i<inverse.getValeurs().length;i++)
        {
            System.out.println(inverse.getValeursN(i));
        }
    }

    @Test public void IFFtnbAlea()
    {
        float val[]= new float[8];
        for (int i = 0; i <val.length ; i++) {
            val[i]=i;
        }
        FFT cst=new FFT(3);
        cst.calculeFFTReelle(val);


        FFT inverse=new FFT(3);
        inverse.inverseFFT(cst.getValeurs());
        for(int i=0;i<inverse.getValeurs().length;i++)
        {
            System.out.println(inverse.getValeursN(i));
        }


    }
}