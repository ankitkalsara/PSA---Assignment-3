package edu.neu.coe.info6205.sort.simple;

import static edu.neu.coe.info6205.sort.simple.Helper.*;

public class InsertionSort<X extends Comparable<X>> implements Sort<X> {
    @Override
    public void sort(X[] xs, int from, int to) {
        // TODO implement insertionSort
        int n = xs.length;
        for (int i = from+1; i < to; i++) { 
            for (int j = i; j > from && less(xs[j], xs[j - 1]); j--) {
                swap(xs, j, j - 1);
            }
        }       
    }
    
}