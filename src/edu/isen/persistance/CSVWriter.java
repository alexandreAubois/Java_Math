package edu.isen.persistance;

import edu.isen.fourier.Complexe;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {

    private static final Logger log= Logger.getLogger(CSVWriter.class);

    /**
     * Methode statique permettant d'écrire un tableau de nombre complexe dans un tableau.
     *
     * @param tab tableau de complexe à ecrire dans un fichier
     * @param nomFichier chemin absolue ou ecrire le fichier
     */
    public static void writeCSV(Complexe tab[],String nomFichier) throws IllegalArgumentException
    {
        try {
            File f=new File(nomFichier);
            FileWriter fw=new FileWriter(f);
            for (Complexe aTab : tab) {
                fw.write(aTab.toString());
                fw.write(",");
                fw.write(System.getProperty("line.separator"));
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (NullPointerException e)
        {
            log.warn("Pas de fichier trouvé"+e.getMessage());
            throw new IllegalArgumentException("Impossible de créer le fichier");
        }

    }
}
