/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.Arrays;

/**
 *
 * @author Mike
 */
public class RandomIndividual {
    public boolean[] bitSet;
    private int sizeV;
    private Fxrandom rand;  // random number generator
    
    public RandomIndividual(int size, double howrandom, Fxrandom gaRand){
        sizeV = size;
        //rand = new Fxrandom(0);
        rand = gaRand;

        bitSet = new boolean[sizeV];
        
        for (int i = 0; i < size; i++) {
            bitSet[i] = rand.boolBernoulli(howrandom);
        }
        
        
        //for (boolean k : bitSet) {
        //    System.out.print(k);
        //}
        //System.out.print("\n");
        
        
        // for debugging 
        /*
        bitSet = new boolean[sizeV];
        Arrays.fill(bitSet, Boolean.FALSE);
        bitSet[0] = true;
        bitSet[1] = true;
         */
    }
    
    public RandomIndividual(int size){
        bitSet = new boolean[size];        
        Arrays.fill(bitSet, Boolean.FALSE);
    }
        
    
    
    
    public int total(boolean bitSet[])
    {
        int cnt = 0;
        for(int i = 0; i < bitSet.length; i++) // used to be i < sizeN
        {
            if(bitSet[i]) cnt++;
        }
        return cnt;
    }    
    
}
