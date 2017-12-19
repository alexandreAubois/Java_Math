package edu.isen.persistance;

import edu.isen.fourier.Complexe;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static Complexe[] parseComplexeCSV(String nom)
    {
        BufferedReader br;
        String split=",";
        int ch;
        StringBuilder chaine= new StringBuilder();
        List<Complexe> ListComplexe=new ArrayList<Complexe>();
        try {
            br=new BufferedReader(new FileReader(nom));
            while ((ch=br.read())!=null)
            {
                if(ch==',')
                {
                    ListComplexe.add(new Complexe(chaine.toString()));
                    chaine =new StringBuilder();
                }else {
                    chaine.append(ch);
                }


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //todo verifié taille OK

        if(ListComplexe.size())
        for (int i = 0; i < ListComplexe.size(); i++) {
            
        }
    }
}
