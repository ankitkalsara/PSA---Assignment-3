/*
 * Copyright (c) 2018. Phasmid Software
 */

package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.simple.InsertionSort;
import edu.neu.coe.info6205.sort.simple.SelectionSort;
import edu.neu.coe.info6205.sort.simple.Sort;
import java.util.Random;

import java.util.function.Function;

//assignment 3 - final
public class Benchmark<T> {

    public Benchmark(Function<T, Void> f) {
        this.f = f;
    }

    public double run(T t, int n) {
        // TODO
        long start_time = System.nanoTime();
        for(int i = 0; i < n; i++)
        {            
            T x= t;
            f.apply(x);
        }
        long end_time = System.nanoTime() - start_time;
        double avg = end_time / n;
        //avg = avg / 1e6;
        avg = avg / 1000000;
        return avg;
        //return 0;
    }

    private final Function<T, Void> f;
    
    //generate random array
    public static Integer[] randomArrayGenerator(){
        Integer[] random_Array = new Integer[1000];
        //initialise random array
        Random rand = new Random();
        for(int i = 0; i < random_Array.length ; i++){            
            random_Array[i] = rand.nextInt(i+1);
        }
        return random_Array;
    }
    
    //generate ordered array
    public static Integer[] orderedArrayGenerator(){
        Integer[] ordered_Array = new Integer[1000];
        //initialise ordered array
        for(int i = 0; i < ordered_Array.length ; i++){
            ordered_Array[i] = i;
        }
        return ordered_Array;
    }
    
    //generate partially ordered array
    public static Integer[] partiallyOrderedArrayGenerator(){
        Integer[] partiallyOrdered_Array = new Integer[1000];
        Random rand = new Random();
        //initialise partially-ordered array
        int half_length = partiallyOrdered_Array.length / 2; //500
        for(int i = 0; i <= half_length; i++){
            partiallyOrdered_Array[i] = i;
        }
        int remaining_half = half_length + 1; //501
        //put the remaining values in random order
        for(int i = remaining_half ; i < partiallyOrdered_Array.length ; i++){            
            partiallyOrdered_Array[i] = rand.nextInt(partiallyOrdered_Array.length - i) + i;
        }
        return partiallyOrdered_Array;
    }
    
    //generate  reverse ordered array
    public static Integer[] reverseOrderedArrayGenerator(){
        Integer[] reverseOrdered_Array = new Integer[1000];
        //initialise reverse-ordered array
        int last_Value = reverseOrdered_Array.length - 1;
        for(int i = 0 ; i < reverseOrdered_Array.length && last_Value >= 0; i++){
            reverseOrdered_Array[i] = last_Value;
            last_Value--;
        }
        return reverseOrdered_Array;
    }
    
    //shuffle the entire array
    public static void shuffle(Integer[] a){
        Random rand = new Random();
        int N = a.length;
        for(int i = 0; i < N ; i++){
            //generate a random number between 0 and i+1;
            int randomNo = rand.nextInt(i+1);
            swap(a, i, randomNo);
        }
    }
    
    //swap the elements
    public static void swap(Integer[] a, int i, int j){
        Integer swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
               

    public static void main(String[] args) {
        int m = 100; // This is the number of repetitions: sufficient to give a good mean value of timing
        Integer[] array = new Integer[1000];
        
        Integer[] random_Array      = new Integer[1000];
        Integer[] ordered_Array     = new Integer[1000];
        Integer[] partially_Ordered = new Integer[1000];
        Integer[] reverse_Ordered   = new Integer[1000];        
        
        int n = 50;    
        for(int i = 1 ; i <= 5; i++){   //run for 5 different values of n
            
            random_Array      = randomArrayGenerator();
            ordered_Array     = orderedArrayGenerator();
            partially_Ordered = partiallyOrderedArrayGenerator();
            reverse_Ordered   = reverseOrderedArrayGenerator();
        
            //now call the benchmark method for all different types of array        
            benchmarkSort(random_Array, n, "SelectionSort for Random Array", new SelectionSort<>(), m);
            benchmarkSort(random_Array, n, "InsertionSort for Random Array", new InsertionSort<>(), m);
            benchmarkSort(ordered_Array, n, "SelectionSort for Ordered Array", new SelectionSort<>(), m);
            benchmarkSort(ordered_Array, n, "InsertionSort for Ordered Array", new InsertionSort<>(), m);
            benchmarkSort(partially_Ordered, n, "SelectionSort for Partially Ordered Array", new SelectionSort<>(), m);
            benchmarkSort(partially_Ordered, n, "InsertionSort for Partially Ordered Array", new InsertionSort<>(), m);
            benchmarkSort(reverse_Ordered, n, "SelectionSort for Reverse Ordered Array", new SelectionSort<>(), m);
            benchmarkSort(reverse_Ordered, n, "InsertionSort for Reverse Ordered Array", new InsertionSort<>(), m);

            //once the timing is recorded, shuffle all the arrays
            shuffle(random_Array);
            shuffle(ordered_Array);
            shuffle(partially_Ordered);
            shuffle(reverse_Ordered);

            n = n * 2; //double the value of n
        }
        
            
        /*for (int i = 0; i < 1000; i++) 
            array[i] = i; // TODO populate the array with real random data
        int n = 200;
        // TODO You need to apply doubling to n
        benchmarkSort(array, n, "SelectionSort", new SelectionSort<>(), m);
        benchmarkSort(array, n, "InsertionSort", new InsertionSort<>(), m);*/
    }

    private static void benchmarkSort(Integer[] xs, Integer n, String name, Sort<Integer> sorter, int m) {
        Function<Integer, Void> sortFunction = (x) -> {
            sorter.sort(xs, 0, x);
            return null;
        };
        Benchmark<Integer> bm = new Benchmark<>(sortFunction);
        double x = bm.run(n, m);
        System.out.println(name + ": " + x + " millisecs for n = " + n);
    }
}