package edu.isen.fourierTest;

import edu.isen.fourier.Complexe;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComplexeTest {

    @org.junit.Test
    public void getIm() {
        Complexe a=new Complexe(10,12);
        assertEquals(a.getIm(),12,0);
        a=new Complexe(12,0);
        assertEquals(a.getIm(),0,0);
        a=new Complexe(0,0);
        assertEquals(a.getIm(),0,0);
    }

    @org.junit.Test
    public void setIm() {
        Complexe a= new Complexe(1,1);
        a.setIm(10);
        assertEquals(10,a.getIm(),0);
        a.setIm(0);
        assertEquals(0,a.getIm(),0);
        a.setIm(-10);
        assertEquals(-10,a.getIm(),0);
    }

    @org.junit.Test
    public void getRe() {
        Complexe a=new Complexe(10,12);
        assertEquals(a.getRe(),10,0);
        a=new Complexe(0,10);
        assertEquals(a.getRe(),0,0);
        a=new Complexe(0,0);
        assertEquals(a.getRe(),0,0);
    }

    @org.junit.Test
    public void setRe() {
        Complexe a= new Complexe(1,1);
        a.setRe(10);
        assertEquals(10,a.getRe(),0);
        a.setRe(0);
        assertEquals(0,a.getRe(),0);
        a.setRe(-10);
        assertEquals(-10,a.getRe(),0);
    }

    @org.junit.Test
    public void add() {
        Complexe a = new Complexe(1,1);
        Complexe b =new Complexe(10,10);
        Complexe c= new Complexe(11,11);
        assertEquals(a.add(b),c);
        assertEquals(b.add(a),a.add(b));
        a=new Complexe(0,0);
        assertEquals(a.add(c),c);
        assertEquals(c.add(a),c);
    }

    @org.junit.Test
    public void sub() {
        Complexe a = new Complexe(1,1);
        Complexe b =new Complexe(10,10);
        Complexe c= new Complexe(-9,-9);
        assertEquals(a.sub(b),c);
        assertEquals(b.sub(a),a.sub(b).multiply(new Complexe(-1,0)));
        a=new Complexe(0,0);
        assertEquals(a.sub(c).multiply(new Complexe(-1,0)),c);
        assertEquals(c.sub(a),c);
    }

    @org.junit.Test
    public void multiply() {
        Complexe a=new Complexe(1,-3);
        Complexe b=new Complexe(2,4);
        assertEquals(a.multiply(b),b.multiply(a));
        assertEquals(a.multiply(b),new Complexe(14,-2));
        Complexe c= new Complexe(0,0);
        assertEquals(a.multiply(c),c);
        assertEquals(c.multiply(a),c);
        Complexe id= new Complexe(1,0);
        assertEquals(a.multiply(id),a);
        assertEquals(id.multiply(a),a);
    }

    @Test
    public void constructArg(){
        Complexe a=new Complexe((float) (Math.PI/2));
        assertEquals(a.getRe(),0,Math.pow(10,-7));
        assertEquals(a.getIm(),1,Math.pow(10,-7));
    }

    @Test
    public void stringConstructor()
    {
        Complexe a=new Complexe("14 + i 4");
        assertEquals(14,a.getRe(),Math.pow(10,-5));
        assertEquals(4,a.getIm(),Math.pow(10,-5));

        try{
            new Complexe("14 + i j4");
            fail("NumberFormatExecption expected");
        }catch (IllegalArgumentException ignored){

        }

        try {
            Complexe b=new Complexe("1");
            assertEquals(1,b.getRe(),0);
            assertEquals(0,b.getIm(),0);
        }catch (NumberFormatException e)
        {
            fail(e.getMessage());
        }
    }
}