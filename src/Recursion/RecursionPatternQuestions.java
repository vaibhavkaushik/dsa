package Recursion;

import java.util.Arrays;

public class RecursionPatternQuestions {
    //r/c 01234
    //4*****
    //3****
    //2***
    //1**
    //0*
    public static void printTrianglePatternUpsideDown(int r, int c){

        if(r==0){
            return;
        }

        if(c<r){
            System.out.print("*");
            printTrianglePatternUpsideDown(r,c+1);
        }
        else {
            System.out.println();
            printTrianglePatternUpsideDown(r-1,0);
        }
    }

    public static void printTrianglePattern(int r, int c){

        if(r==0){
            return;
        }

        if(c<r){
            printTrianglePattern(r,c+1);
            System.out.print("*");
        }
        else {
            printTrianglePattern(r-1,0);
            System.out.println();
        }
    }

    public static void bubblesort(int[] arr, int r, int c){

        if(r==0){
            return;
        }

        if(c<r){
            if(arr[c]>=arr[c+1]){
                int temp = arr[c];
                arr[c] = arr[c+1];
                arr[c+1] = temp;
            }
            bubblesort(arr,r,c+1);
        }else{
            bubblesort(arr,r-1,0);
        }

    }

    public static void main(String[] args) {
        printTrianglePatternUpsideDown(9,0);
        printTrianglePattern(9,0);
        int[] arr = new int[]{3,1,2,7,4,6,11,3};
        bubblesort(arr,arr.length-1,0);
        System.out.println(Arrays.toString(arr));
    }

}
