package edu.isen.persistance;

import edu.isen.fourier.Complexe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {

    public static void writeCSV(Complexe tab[],String nomFichier)
    {
        File f=new File(nomFichier);

        try {
            FileWriter fw=new FileWriter(f);
            for(int i=0;i<tab.length;i++)
            {
                fw.write(tab[i].toString());
                fw.write(",");
                fw.write(System.getProperty("line.separator"));
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
