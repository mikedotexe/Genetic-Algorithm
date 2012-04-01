/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Mike
 */
class ValueComparator implements Comparator{
    
    
  Map base;
  public ValueComparator(Map base) {
      this.base = base;
  }



    @Override
    public int compare(Object o1, Object o2) {
        Integer val1 = (Integer) base.get(o1);
        Integer val2 = (Integer) base.get(o2);
        if (val1 > val2) {
            return -1;
        } else {
            return 1;
        }
    }
    
}
