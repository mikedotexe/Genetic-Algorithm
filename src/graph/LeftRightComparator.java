/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.Comparator;
import java.util.Map;

/**
 *
 * @author Mike
 */
public class LeftRightComparator implements Comparator{
    Map base;
  public LeftRightComparator(Map base) {
      this.base = base;
  }    
  
@Override
public int compare(Object o1, Object o2) {
    
    boolean[] val1 = (boolean[]) base.get(o1);
    boolean[] val2 = (boolean[]) base.get(o2);
    if (total(val1) > total(val2)) {
        return -1;
    } else {
        return 1;
    }
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
