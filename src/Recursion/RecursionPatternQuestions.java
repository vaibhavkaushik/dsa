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

    //String based recursion questions
    //Given string : baccad remove a from it : bccd

    public static void removeCharacterWithResultParameter(String s,int idx,StringBuilder ans,char toRemove){
        if(idx==s.length()){
            return;
        }

        if(s.charAt(idx)!=toRemove){
            ans.append(s.charAt(idx));
        }

        removeCharacterWithResultParameter(s,idx+1,ans,toRemove);
    }

    public static StringBuilder removeCharacterWithoutResultParameter(String s,int idx,char toRemove){

        if(idx==s.length()){
            return new StringBuilder();
        }

        StringBuilder ansFromBottomCalls = removeCharacterWithoutResultParameter(s,idx+1,toRemove);
        if(s.charAt(idx)!=toRemove){
            ansFromBottomCalls.insert(0,s.charAt(idx));
        }

        return ansFromBottomCalls;
    }

    public static void removeStringWithResultParameter(String s,StringBuilder ans,String toRemove){
        if(s.isEmpty()){
            return;
        }

        if(s.startsWith(toRemove)){
            removeStringWithResultParameter(s.substring(toRemove.length()),ans,toRemove);
        }else {
            removeStringWithResultParameter(s.substring(1),ans.append(s.charAt(0)),toRemove);
        }
    }

    public static void main(String[] args) {
        printTrianglePatternUpsideDown(9,0);
        printTrianglePattern(9,0);
        int[] arr = new int[]{3,1,2,7,4,6,11,3};
        bubblesort(arr,arr.length-1,0);
        System.out.println(Arrays.toString(arr));
        StringBuilder ans = new StringBuilder();
        removeCharacterWithResultParameter("bacaababaahahabfabaad",0,ans,'a');
        System.out.println(ans);
        System.out.println(removeCharacterWithoutResultParameter("bacaababaahahabfabaad",0,'a'));
        StringBuilder stringRemoveAns = new StringBuilder();
        removeStringWithResultParameter("iloveappleaday",stringRemoveAns,"apple");
        System.out.println(stringRemoveAns);
    }

}
