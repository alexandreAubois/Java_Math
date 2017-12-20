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
     *
     * @param arg argument du nombre complexe
     *
     *crée un nombre complexe de module unitaire et d'argument arg
     */
    public Complexe(float arg)
    {
        Re= (float) Math.cos(arg);
        Im= (float) Math.sin(arg);
    }

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
    public float getIm() {
        return Im;
    }

    public void setIm(float im) {
        Im = im;
    }

    public float getRe() {
        return Re;
    }

    public void setRe(float re) {
        Re = re;
    }

    public Complexe add(Complexe nb)
    {
        return new Complexe(this.getRe()+nb.getRe(),this.getIm()+nb.getIm());
    }

    public Complexe sub(Complexe nb)
    {
        return new Complexe(this.getRe()-nb.getRe(),this.getIm()-nb.getIm());
    }

    public Complexe multiply(Complexe nb)
    {
        return  new Complexe(this.getRe()*nb.getRe()-this.getIm()*nb.getIm(),this.getRe()*nb.getIm()+nb.getRe()*this.getIm());
    }

    @Override
    public String toString() {
        DecimalFormat df=new DecimalFormat();
        df.setMaximumFractionDigits(3);
        DecimalFormatSymbols dfs=new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        return df.format(Re)+" + i "+df.format(Im);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complexe complexe = (Complexe) o;
        return Float.compare(complexe.getIm(), getIm()) == 0 &&
                Float.compare(complexe.getRe(), getRe()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIm(), getRe());
    }

    /**
     *retourne le complexe conjugué
     * @return le complexe conjugue
     */
    public Complexe Conjugue()
    {
        return new Complexe(this.getRe(),-this.getIm());
    }
}
