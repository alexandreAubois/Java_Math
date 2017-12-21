package edu.isen.persistanceTest;

import edu.isen.fourier.Complexe;
import edu.isen.persistance.CSVReader;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class CSVReaderTest {

    @Test
    public void parseComplexeCSV() {
        try {
            CSVReader.parseComplexeCSV("CSV/out.csv");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            fail("fichier pas trouve");
        }
    }

    @Test
    public void parseReelCSV() {
        try {
            CSVReader.parseReelCSV("CSV/reel.csv");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            fail("fichier pas trouve");
        }
    }

    @Test
    public void parseErrorCsv()
    {
        try {
            CSVReader.parseReelCSV("CSV/error.csv");
            fail("IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }
}