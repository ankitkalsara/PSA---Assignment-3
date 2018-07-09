package edu.neu.coe.info6205.sort.simple;

import static edu.neu.coe.info6205.sort.simple.Helper.less;
import static edu.neu.coe.info6205.sort.simple.Helper.swap;

public class SelectionSort<X extends Comparable<X>> implements Sort<X> {

    @Override
    public void sort(X[] xs, int from, int to)  {
        // TODO implement selection sort
        int n = xs.length;
        for(int i = from; i < to ; i++){
            int minIndex  = i;
            for(int j = i+1 ; j < to ; j++){
                if(Helper.less(xs[j], xs[minIndex]))
                    minIndex = j;
            }
                       Helper.swap(xs, i, minIndex);                
        }        
    }
    
}