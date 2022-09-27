package com.geekbrains;

import java.util.stream.IntStream;

public class Resolver {
    public int getMin(int[] array){
        if (array==null || array.length==0){
            throw new IllegalArgumentException("Array could not be empty");
        }

//        IntStream.of(array)
//                .min()
//                .orElseThrow(()-> new IllegalArgumentException("Array could not be empty");

        int min = array[0];
        for (int i : array){
            if (i < min){
                min= 1;
            }
        }
        return min;
    }

    public int[] sort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++){
            for (int j = 0; j <n; j++){
                if (array[j]>array[j+1]){
                    int cur = array[j];
                    array[j] = array[j+1];
                    array[j+1] = cur;
                }
            }
        }
        return array;
    }
}
