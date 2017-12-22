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
     * @param nb chaine representant le nombre complexe
     * @throws IllegalArgumentException si le formatat n'est pas le bon
     */
    public Complexe(String nb) throws IllegalArgumentException
    {
        String delimiteur ="[ ]+[+][ ]+[i] ";
        String token[]=nb.split(delimiteur);
        try {

            this.Re=Float.parseFloat(token[0]);
            if(token.length==2)
            {
                this.Im=Float.parseFloat(token[1]);
            }else {
                this.Im=0;
            }
        }catch (ArrayIndexOutOfBoundsException|NumberFormatException e)
        {
            throw new IllegalArgumentException("mauvais format de la donnée"+System.getProperty("line.separator")+e);
        }

    }

    /**
     * Obtient la partie imaginaire d'un nombre complexe
     * @return la partie imaginaire du nombre complexe
     */
    public float getIm() {
        return Im;
    }

    /**
     * Initialise la partie imaginaire d'un nombre complexe
     * @param im partie imaginaire du flottant
     */
    public void setIm(float im) {
        Im = im;
    }

    /**
     * Obtient la partie réel d'un nombre complexe
     * @return la partie reel du complexe
     */
    public float getRe() {
        return Re;
    }

    /**
     * Initialise la partie réel d'un nombre complexe
     * @param re partie reel du nombre complexe
     */
    public void setRe(float re) {
        Re = re;
    }

    /**
     * Addtionne deux nombres complexe, et retourne un nouveau nombre complexe
     * @param nb nombre à ajouté à l'instance actuelle
     * @return resu une nouvelle instance de complexe sommant l'instance actuelle avec NB
     */
    public Complexe add(Complexe nb)
    {
        return new Complexe(this.getRe()+nb.getRe(),this.getIm()+nb.getIm());
    }

    /**
     * soustrait deux nombres complexe, et retourne un nouveau nombre complexe
     * @param nb Complexe à soustraire à l'instance actuelle
     * @return resu nouvelle instance d'un nombre complexe valant this-nb
     */
    public Complexe sub(Complexe nb)
    {
        return new Complexe(this.getRe()-nb.getRe(),this.getIm()-nb.getIm());
    }

    /**
     * Multiplie deux nombres complexe, et retourne un nouveau nombre complexe
     * @param nb Complexe à multiplier à l'instance actuel
     * @return resu nouvel instance d'un nombre complexe valant this * nb
     */
    public Complexe multiply(Complexe nb)
    {
        return  new Complexe(this.getRe()*nb.getRe()-this.getIm()*nb.getIm(),this.getRe()*nb.getIm()+nb.getRe()*this.getIm());
    }

    /**
     * Transforme l'objet en chaine de caractère
     * @return une forme lisible de l'objet Re + i +Im
     */
    @Override
    public String toString() {
        DecimalFormat df=new DecimalFormat();
        df.setMaximumFractionDigits(3);
        DecimalFormatSymbols dfs=new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        if(Im!=0)
        {
            return df.format(Re)+" + i "+df.format(Im);
        }else{
            return df.format(Re);
        }

    }

    /**
     * Test l'égalité entre deux nombres complexes
     * Retourne un bolleen
     * @param o objet à comparer
     * @return true si les deux objet ont leur partie imaginaire égale et leur partie réel égale
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
     * @return Module du nombre complexe (sqrt(Re*Re+Im*Im)
     */
    public float getModule()
    {
        return (float) Math.sqrt(this.Re*this.Re+this.Im*this.Im);
    }
}