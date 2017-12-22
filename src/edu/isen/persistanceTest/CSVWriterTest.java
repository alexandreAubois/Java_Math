package edu.isen.persistanceTest;

import edu.isen.fourier.Complexe;
import edu.isen.persistance.CSVWriter;
import org.junit.Test;

public class CSVWriterTest {

    @Test
    public void writeCSV() {
        Complexe valeurExp[] = new Complexe[8];
        for (int indice=0 ; indice<valeurExp.length; indice++){
            valeurExp[indice] = new Complexe((float) (Math.PI * 2 *indice/(valeurExp.length)));
        }
        CSVWriter.writeCSV(valeurExp,"CSV/out.csv");
    }

    @Test
    public void generateSinus()
    {
        Complexe sin[]=new Complexe[16];
        for(int i=0;i<sin.length;i++)
        {
            sin[i]= new Complexe((float) Math.sin(2*Math.PI*i/sin.length),0);
        }
        CSVWriter.writeCSV(sin,"CSV/sin.csv");
    }

    @Test
    public void generateCosinus()
    {
        Complexe cos[]=new Complexe[16];
        for(int i=0;i<cos.length;i++)
        {
            cos[i]= new Complexe((float) Math.cos(2*Math.PI*i/cos.length),0);
        }
        CSVWriter.writeCSV(cos,"CSV/cos.csv");
    }

    @Test
    public void generateExpoComplexe()
    {
        Complexe valeurExpo[] = new Complexe[16];
        for (int indice=0 ; indice<valeurExpo.length; indice++){
            valeurExpo[indice] = new Complexe(0,1);
        }
        CSVWriter.writeCSV(valeurExpo,"CSV/expoComplexe.csv");

    }

    public void generateDirac()
    {
        Complexe valeurDirac[]=new Complexe[8];
        valeurDirac[0]=new Complexe(1,0);
        for(int i=0;i<valeurDirac.length;i++)
        {
            valeurDirac[i]= new Complexe(0,0);
        }
        CSVWriter.writeCSV(valeurDirac,"CSV/dirac.csv");
    }
}