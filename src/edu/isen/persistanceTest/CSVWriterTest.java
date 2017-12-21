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
}