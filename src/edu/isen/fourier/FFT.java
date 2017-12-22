package edu.isen.fourier;

import org.apache.log4j.Logger;

import java.util.Observable;

public class FFT extends Observable{

    private Complexe valeurs[];
    private static final Logger log= Logger.getLogger(Complexe.class);

    /**
     * Constructeur permettant de créer un objet "Complexe" en fonction de la taille passer en paramètre
     * @param size
     * @throws IllegalArgumentException
     *
     */
    public FFT(int size) throws IllegalArgumentException {
        if(size<0)
        {
            log.warn("mauvaise taille de tableau pour la FFT");
            throw new IllegalArgumentException("size can't be inferior to zero");
        }else {
            valeurs=new Complexe[(int) Math.pow(2,size)];
            log.info("creation d'un tableau de taille "+valeurs.length);
        }
    }

    /**
     * Initialise la taille
     * @param size
     * @throws IllegalArgumentException
     */
    public void setSize(int size) throws IllegalArgumentException
    {
        if(Integer.bitCount(size)!=1)
        {
            log.warn("mauvaise taille de tableau pour la FFT");
            throw new IllegalArgumentException("size can't be inferior to zero");
        }else {
            valeurs=new Complexe[size];
            log.info("creation d'un tableau de taille "+valeurs.length);
        }

    }

    /**
     * Calcul la fft de nombres réels
     * @param entree
     */
    public void calculeFFTReelle(float entree[])
    {
        if(entree.length==1)
        {
            valeurs[0]=new Complexe(entree[0],0);
        }else {

            int taille = (int) (Math.log(entree.length) / Math.log(2)) - 1;
            log.debug("nouvelle puissance de 2 du tableau :"+taille);
            FFT paire = new FFT(taille);
            FFT impaire = new FFT(taille);
            float elementPaire[] = new float[(int) Math.pow(2,taille)];
            float elementImpaire[] = new float[(int) Math.pow(2,taille)];
            for (int i = 0; i < entree.length; i++) {
                if (i % 2 == 0) {
                    elementPaire[i / 2] = entree[i];
                } else {
                    elementImpaire[(i - 1) / 2] = entree[i];
                }
            }
            paire.calculeFFTReelle(elementPaire);
            impaire.calculeFFTReelle(elementImpaire);

            for (int i = 0; i < entree.length / 2; i++) {
                Complexe M = new Complexe((float) (-2 * Math.PI * i / entree.length));
                this.valeurs[i] = paire.getValeursN(i).add(impaire.getValeursN(i).multiply(M));
                this.valeurs[i + entree.length / 2] = paire.getValeursN(i).sub(impaire.getValeursN(i).multiply(M));
            }
        }
        this.notifyObservers();
    }

    /**
     * Obtient le nombre complexe en fonction de l'indice passé en paramètre dans le tableau de valeur
     * @param n
     * @return
     */
    public Complexe getValeursN(int n) {
        return valeurs[n];
    }

    /**
     * Calcul la fft de nombres complexes
     * @param entree
     */
    public void calculeFFTComplexe(Complexe entree[])
    {
        if(entree.length==1)
        {
            valeurs[0] = entree[0];
        }else {

            int taille = (int) (Math.log(entree.length) / Math.log(2)) - 1;
            log.debug("nouvelle puissance de 2 du tableau :"+taille);
            FFT paire = new FFT(taille);
            FFT impaire = new FFT(taille);
            Complexe elementPaire[] = new Complexe[(int) Math.pow(2,taille)];
            Complexe elementImpaire[] = new Complexe[(int) Math.pow(2,taille)];
            for (int i = 0; i < entree.length; i++) {
                if (i % 2 == 0) {
                    elementPaire[i / 2] = entree[i];
                } else {
                    elementImpaire[(i - 1) / 2] = entree[i];
                }
            }
            paire.calculeFFTComplexe(elementPaire);
            impaire.calculeFFTComplexe(elementImpaire);

            for (int i = 0; i < entree.length / 2; i++) {
                Complexe M = new Complexe((float) (-2 * Math.PI * i / entree.length));
                this.valeurs[i] = paire.getValeursN(i).add(impaire.getValeursN(i).multiply(M));
                this.valeurs[i + entree.length / 2] = paire.getValeursN(i).sub(impaire.getValeursN(i).multiply(M));
            }
        }
        this.notifyObservers();
    }

    /**
     * Calcul la fft inverse de nombres complexes
     * @param entree
     */
    public void inverseFFT(Complexe entree[])
    {
        for(int i=0;i<entree.length;i++)
        {
            entree[i]=entree[i].Conjugue();
        }
        this.calculeFFTComplexe(entree);
        for(int i=0;i<this.valeurs.length;i++)
        {
            this.valeurs[i]=this.valeurs[i].Conjugue();
            this.valeurs[i]=this.valeurs[i].multiply(new Complexe(1/(float)this.valeurs.length,0));
        }
        this.notifyObservers();
    }

    /**
     * Obtient le tableau de complexe
     * @return les valeurs
     */
    public Complexe[] getValeurs() {
        return valeurs;
    }

    /**
     * Notifie les oberseveurs
     */
    @Override
    public void notifyObservers() {
        setChanged(); // Set the changed flag to true, otherwise observers won't be notified.
        super.notifyObservers();
    }
}
