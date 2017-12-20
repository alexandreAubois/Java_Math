package edu.isen.persistance;

import edu.isen.fourier.Complexe;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

public class CSVReader{

    private static final Logger log= Logger.getLogger(CSVReader.class);

    public static Complexe[] parseComplexeCSV(String nom) throws IllegalArgumentException,FileNotFoundException
    {
        BufferedReader br;
        String ch;
        String tab[];
        List<Complexe> ListComplexe= new ArrayList<>();
        br=new BufferedReader(new FileReader(nom));
        try {
            while ((ch = br.readLine()) != null)
            {
                tab=ch.split(",");
               ListComplexe.add(new Complexe(tab[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        Complexe res[];
        if(Integer.bitCount(ListComplexe.size())==1)
        {
             res = new Complexe[ListComplexe.size()];
            for (int i = 0; i < ListComplexe.size(); i++) {
                res[i]=ListComplexe.get(i);
            }
        }else {
            throw new IllegalArgumentException("la taille des donnée n'est pas une puissance de deux");
        }

        return res;
    }

    public static float[] parseReelCSV(String nom) throws IllegalArgumentException,FileNotFoundException
    {
        BufferedReader br;
        String ch;
        String tab[];
        List<Float> ListFloat= new ArrayList<>();
        br=new BufferedReader(new FileReader(nom));
        try {
            while ((ch = br.readLine()) != null)
            {
                tab=ch.split(",");
                ListFloat.add(Float.parseFloat(tab[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        //todo verifié taille OK
        float res[];
        if(Integer.bitCount(ListFloat.size())==1)
        {
            res = new float[ListFloat.size()];
            for (int i = 0; i < ListFloat.size(); i++) {
                res[i]=ListFloat.get(i);
            }
        }else {
            throw new IllegalArgumentException("la taille des donnée n'est pas une puissance de deux");
        }

        return res;
    }
}
