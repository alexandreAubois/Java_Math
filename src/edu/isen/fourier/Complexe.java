package edu.isen.fourier;


import org.apache.log4j.Logger;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Objects;
public class Complexe {

    private float Im;
    private float Re;
    private static final Logger log= Logger.getLogger(Complexe.class);

    /**
     * crée un nombre complexe de partie Réele Re et de partie Imaginaire im
     * @param re partie réelle du nombre complexe
     * @param im partie imaginiare du nombre complexe
     */
    public Complexe(float re,float im) {
        Im = im;
        Re = re;
        log.info("nouveau complexe :"+this.toString());
    }

    /**
     *crée un nombre complexe de module unitaire et d'argument arg
     * @param arg argument du nombre complexe
     */
    public Complexe(float arg)
    {
        Re= (float) Math.cos(arg);
        Im= (float) Math.sin(arg);
    }

    /**
     * Créer un nombre complexe à partir d'une chaine au format "PARTIEREEL + i PARTIEIMAGINAIRE"
     * @param nb
     * @throws IllegalArgumentException
     */
    public Complexe(String nb) throws IllegalArgumentException
    {
        String delimiteur ="[ ]+[+][ ]+[i] ";
        String token[]=nb.split(delimiteur);
        try {

            this.Re=Float.parseFloat(token[0]);
            this.Im=Float.parseFloat(token[1]);
        }catch (ArrayIndexOutOfBoundsException|NumberFormatException e)
        {
            throw new IllegalArgumentException("mauvais format de la donnée"+e);
        }

    }

    /**
     * Obtient la partie imaginaire d'un nombre complexe
     * @return
     */
    public float getIm() {
        return Im;
    }

    /**
     * Initialise la partie imaginaire d'un nombre complexe
     * @param im
     */
    public void setIm(float im) {
        Im = im;
    }

    /**
     * Obtient la partie réel d'un nombre complexe
     * @return
     */
    public float getRe() {
        return Re;
    }

    /**
     * Initialise la partie réel d'un nombre complexe
     * @param re
     */
    public void setRe(float re) {
        Re = re;
    }

    /**
     * Addtionne deux nombres complexe, et retourne un nouveau nombre complexe
     * @param nb
     * @return resu
     */
    public Complexe add(Complexe nb)
    {
        return new Complexe(this.getRe()+nb.getRe(),this.getIm()+nb.getIm());
    }

    /**
     * soustrait deux nombres complexe, et retourne un nouveau nombre complexe
     * @param nb
     * @return resu
     */
    public Complexe sub(Complexe nb)
    {
        return new Complexe(this.getRe()-nb.getRe(),this.getIm()-nb.getIm());
    }

    /**
     * Multiplie deux nombres complexe, et retourne un nouveau nombre complexe
     * @param nb
     * @return resu
     */
    public Complexe multiply(Complexe nb)
    {
        return  new Complexe(this.getRe()*nb.getRe()-this.getIm()*nb.getIm(),this.getRe()*nb.getIm()+nb.getRe()*this.getIm());
    }

    /**
     * Transforme l'objet en chaine de caractère
     * @return
     */
    @Override
    public String toString() {
        DecimalFormat df=new DecimalFormat();
        df.setMaximumFractionDigits(3);
        DecimalFormatSymbols dfs=new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        return df.format(Re)+" + i "+df.format(Im);
    }

    /**
     * Test l'égalité entre deux nombres complexes
     * Retourne un bolleen
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complexe complexe = (Complexe) o;
        return Float.compare(complexe.getIm(), getIm()) == 0 &&
                Float.compare(complexe.getRe(), getRe()) == 0;
    }

    /**
     *  le complexe conjugué
     * @return le complexe conjugue
     */
    public Complexe Conjugue()
    {
        return new Complexe(this.getRe(),-this.getIm());
    }

    /**
     * Obtient le module d'un nombre complexe
     * @return
     */
    public float getModule()
    {
        return (float) Math.sqrt(this.Re*this.Re+this.Im*this.Im);
    }
}
