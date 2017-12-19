package edu.isen.fourier;


import org.apache.log4j.Logger;

import java.text.DecimalFormat;
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
        log.info("nouveaux complexe :"+this.toString());
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
        return df.format(Re)+" + "+df.format(Im)+" i";
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
}
