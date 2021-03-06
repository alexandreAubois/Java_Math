package edu.isen.persistance;

import edu.isen.fourier.Complexe;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVReader{

    private static final Logger log= Logger.getLogger(CSVReader.class);

    /**
     * Crée un tableau de complexe à partir d'un fichier CSV
     * @param nom chemin complet du fichier à traiter
     * @return tableau de complexe  contenant les données du fichier
     * @throws IllegalArgumentException si le fichier n'est pas trouvé
     * @throws IllegalArgumentException si la taille des données n'est pas une puissance de deux
     *
     * Format des complexes dans le CSV : Re + i Im
     */
    public static Complexe[] parseComplexeCSV(String nom) throws IllegalArgumentException
    {
        try
        {
        BufferedReader br;
        String ch;
        String tab[];
        List<Complexe> ListComplexe= new ArrayList<>();
        br=new BufferedReader(new FileReader(nom));
            while ((ch = br.readLine()) != null)
            {
                tab=ch.split(",");
               ListComplexe.add(new Complexe(tab[0]));
            }


        Complexe res[];
        if(Integer.bitCount(ListComplexe.size())==1)
        {
             res = new Complexe[ListComplexe.size()];
            for (int i = 0; i < ListComplexe.size(); i++) {
                res[i]=ListComplexe.get(i);
            }
        }else {
            log.warn("La taille n'est pas une puissance de 2 : "+ListComplexe.size());
            throw new IllegalArgumentException("la taille des donnée n'est pas une puissance de deux");
        }

        return res;
        } catch (IOException e) {
            e.printStackTrace();
        }catch (NullPointerException e)
        {
            log.warn("Pas de fichier trouvé " +e);
            throw new IllegalArgumentException("Pas de fichier trouvé");
        }

        return null;
    }

    /**
     * Crée un tableau de flottant à partir d'un fichier CSV
     * @param nom chemin complet du fichier à traiter
     * @return Tableau de Float contenant les données
     * @throws IllegalArgumentException Si le fichier n'est pas accessible
     * @throws IllegalArgumentException Si les données ne sont pas au bon format ( taille qui n'est pas un puissance de 2, données qui ne sont pas des flottant, separateur decimale autres que le .)
     */
    public static float[] parseReelCSV(String nom) throws IllegalArgumentException
    {
        BufferedReader br;
        String ch;
        String tab[];
        List<Float> ListFloat= new ArrayList<>();
        try {
            br=new BufferedReader(new FileReader(nom));
            while ((ch = br.readLine()) != null)
            {
                tab=ch.split(",");
                ListFloat.add(Float.parseFloat(tab[0]));
            }
            float res[];
            if(Integer.bitCount(ListFloat.size())==1)
            {
                res = new float[ListFloat.size()];
                for (int i = 0; i < ListFloat.size(); i++) {
                    res[i]=ListFloat.get(i);
                }
            }else {
                log.warn("La taille n'est pas une puissance de 2 :"+ListFloat.size());
                throw new IllegalArgumentException("la taille des donnée n'est pas une puissance de deux");
            }
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }catch (NullPointerException e)
        {
            log.warn("Pas de fichier trouvé " +e);
            throw new IllegalArgumentException("Pas de fichier trouvé");
        }

        return null;
    }
}
