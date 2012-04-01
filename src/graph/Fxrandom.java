/*
 * File: Fxrandom.java
 * Author: Michael R. Peterson
 * Created on July 11, 2001, 5:36 PM
 * Description: This random number generator is a Java implementation of
 * a C++ random generator written by Dr. Mateen Rizki
 * of the Department of Computer Science & Engineering,
 * Wright State University - Dayton, Ohio, U.S.A.
 * This class contains several useful statistical functions.
 *
 *
 *
 *
 *
 *
 */
package graph;

import java.io.*;
import java.util.*;
import java.lang.*;

/**
 *
 * @author  Mike Peterson
 * @version 
 */
public class Fxrandom
{
   // constant parameters
   private static final double PI = 3.1415926535897;
   private static final double ONEOVERPI = 1.0/PI;
   private static final double TWOPI = 2.0*PI;

   // private parameters
   private static final long modulus = 0x7fffffff;
   private static final long multiplier = 630360016;
   private static long seed;

  /* 
   *  Constructor: uses current time as the seed 
   */
   public Fxrandom() 
   {
      seed = System.currentTimeMillis();
   }

  /*
   *  Constructor: uses passed in parameter as the seed,
   *   if the parameter == 0 , current time is used as seed
   */
   public Fxrandom( final long initSeed)
   {
      if(initSeed == 0)
      {
         seed = System.currentTimeMillis();
      }
      else
      {
         seed = initSeed; 
      }
   }

   /*
    * function setSeed: reset the seed
    */
   public final void setSeed(long initSeed)
   {
      if(initSeed == 0)
      {
         seed = System.currentTimeMillis();
      }
      else
      {
         seed = initSeed; 
      }
   }

   /*
   * function random - returns a random integer 0...0x7fffffff
   */
   public final long random()
   {
      seed = (long) ((multiplier * seed) % modulus); 
      return Math.abs(seed);
   }

    /*
    * function - cauchy
    */
    public final double cauchy(double t)
    {
        double r = uniform(0.0,1.0);
        return t*Math.tan((PI*(r-0.5)));
        //return gaussian(1.0,1.0)/gaussian(1.0,1.0);
    }
    
    /*
    * function: intUniform - returns a random integer between min & max
    */
    public final long intUniform(long min, long max)
    {
        if(min >= max)
            throw new IllegalArgumentException ("min must be less than max.");
        return min + (random() % (max - min + 1));
    }
    
    /*
    * function: uniform - returns a random double between min & max
    */
    public final double uniform(double min, double max)
    {
        double val = 0.0;
        if(min >= max)
            throw new IllegalArgumentException ("min must be less than max.");
        val = (min + (max - min) * (random())/(modulus));
        return val;
    }
    
    /*
    * function: poisson - return a Possion distributed random variable with
    * the given mean
    */
    public final long poisson(long mean)
    {
        double a,b;
        int i;
        
        a = Math.exp(-mean);
        b = 1.0;
        i = 0;
        
        do {
            b *= uniform(0.0,1.0);
            i++;
        } while( a <= b);
        return(i-1);
    }
    
    /*
    * function: exponential - return an exponentially distributed random 
    * variable with the given mean
    */
    public final double exponential(double mean)
    {
        double x;
        
        while((x = uniform(0.0,1.0)) == 0);
        return -mean * Math.log(x);
    }
    
    /*
    * function: gaussian - return an normally distributed random 
    * variable mean + sigma * N(0,1)
    */
    public final double gaussian( double mean, double sigma)
    {
        boolean generate= false;
        double x2 = 0.0;
        double u1, u2, x1;
        
        if( !generate)
        {
            u1 = uniform(0.0,1.0);
            u2 = uniform(0.0,1.0);
            x1 = Math.sqrt( -2.0 * Math.log(u1))*Math.cos(2.0*PI*u2);
            x2 = Math.sqrt( -2.0 * Math.log(u1))*Math.cos(2.0*PI*u2);
        }
        else
        {
            x1 = x2;
        }
        generate = !generate;
        return mean + sigma * x1;
    }
    
    /*
    * function: bernoulli - return an Bernoulli distributed random 
    * variable with given probability
    */
    public final int bernoulli( double probability)
    {
        //return (uniform(0.0,1.0) <= probability);
        double num = uniform(0.0,1.0);
        if(num <= probability)
            return 1;
        else return 0;
    }
    
    /*
    * function: boolBernoulli - return an Bernoulli distributed random 
    * variable with given probability ( returns true or false)
    */
    public final boolean boolBernoulli( double probability)
    {
       int on = bernoulli(probability);
       if(on == 1)
           return true;
       else return false;
    }
    
    /*
    * function: bernoulli - return an Bernoulli distributed random 
    * variable with equal probability
    */
    public final int bernoulli()
    {
        double probability = 0.5;
        double num = uniform(0.0,1.0);
        if(num <= probability)
            return 1;
        else return 0;
    }
    
    /*
    * function: binomial - return an binomial distributed random 
    * variable
    */
    public final int binomial(int count, double probability)
    {
        double x = 0.0;
        
        while(count-- > 0) x += bernoulli(probability);
        return (int)(x);
    }
    
    /*
    * function: geometric - return a geometric distributed random 
    * variable
    */
    public final int geometric(double probability)
    {
        double u;
        if((probability < 0.0)||(probability > 1.0))
            throw new IllegalArgumentException (
                    "Probability out of vaild range.");
        while((u = uniform(0.0,1.0)) == 0);
        
        return (int)(Math.floor(Math.log(u)/Math.log(1.0-probability)));
    }
    
    /*
    *  Test the code
    */
    
    public static void main(String args[])
    {
        Fxrandom r;
        int i = 0;
        int num;
        //long num;
        //double num;
        
        r = new Fxrandom(0);
        //System.out.println(Math.sqrt(100.0));
        System.out.println("Here are 10 bernoulli(0.9) numbers:");
        for(i=0; i<10; i++)
        {
            System.out.print("Number: ");
            System.out.print(i);
            System.out.print(" = ");
            //num = r.uniform(0.0,1.0);
            //num = r.random();
            num = r.bernoulli(.5);
            //num = r.geometric(.01);
            //num = r.intUniform(0,100);
            System.out.print(num);
            System.out.print("\n");
        }
    }
}

/*************** End of File ********************************/
