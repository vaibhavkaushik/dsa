package Recursion;

import java.util.ArrayList;
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

    public static int[] selectionSort(int[] arr,int lastIndex){
        if(lastIndex==0){
            return arr;
        }

        //find the largest onto lastIndex
        int max = arr[0];
        int maxIdx = 0;
        for(int i=0;i<=lastIndex;i++){
            if(arr[i]>max){
                max = arr[i];
                maxIdx = i;
            }
        }
        //swap with last index
        int temp = arr[lastIndex];
        arr[lastIndex] = max;
        arr[maxIdx] = temp;
        return selectionSort(arr,lastIndex-1);
    }

    public static void selectionSortBetter(int[] arr,int r,int c, int max_idx){

        if(r==0){
            return;
        }

        if(c<r){
            if(arr[c]>arr[max_idx]){
                selectionSortBetter(arr,r, c+1,c);
            }else{
                selectionSortBetter(arr,r, c+1,max_idx);
            }
        }else{
            int temp = arr[max_idx];
            arr[max_idx] = arr[c-1];
            arr[c-1] = temp;
            selectionSortBetter(arr, r-1,0, 0);
        }

    }

    //look at inplace approach as well
    public static int[] mergeSort(int[] arr){
        if(arr.length == 1){
            return arr;
        }

        int mid = arr.length/2;
        int[] leftSorted = mergeSort(Arrays.copyOfRange(arr,0,mid));
        int[] rightSorted = mergeSort(Arrays.copyOfRange(arr,mid,arr.length));

        return mergeTwoSortedArrays(leftSorted,rightSorted);

    }

    private static int[] mergeTwoSortedArrays(int[] arr1, int[] arr2){

        //1,5,6,7
        int lengthA = arr1.length;
        //2,4,5,7,8,9
        int lengthB = arr2.length;

        int lengthC = lengthA+lengthB;
        int[] ans = new int[lengthC];

        if(lengthA == 0 || lengthB == 0){
            return lengthA == 0 ? arr2 : arr1;
        }

        int k=0;
        int i=0;
        int j=0;

        while(i<lengthA && j<lengthB){
            if(arr1[i] <= arr2[j]){
                ans[k++] = arr1[i++];
            }else{
                ans[k++] = arr2[j++];
            }
        }

        while(i<lengthA){
            ans[k++] = arr1[i++];
        }

        while(j<lengthB){
            ans[k++] = arr2[j++];
        }

        return ans;
    }

    private static void setsOfArray(int[] arr, int idx, ArrayList<Integer> localAnsWith, ArrayList<ArrayList<Integer>> ans){
        if(idx == arr.length){
            ArrayList<Integer> recursionAns = new ArrayList<>(localAnsWith);
            ans.add(recursionAns);
            return;
        }

        int val = arr[idx];
        //take this value
        localAnsWith.add(val);
        setsOfArray(arr,idx+1,localAnsWith,ans);
        localAnsWith.remove(localAnsWith.size()-1);
        setsOfArray(arr,idx+1,localAnsWith,ans);
    }

    private static ArrayList<ArrayList<Integer>> setsOfArrayOriginalRecursion(int[] arr, int idx){
        if(idx == arr.length){
            ArrayList<ArrayList<Integer>> biggerAns = new ArrayList<>();
            biggerAns.add(new ArrayList<>());
            return biggerAns;
        }

        ArrayList<ArrayList<Integer>> recursiveAnswerFromBottomCalls = setsOfArrayOriginalRecursion(arr,idx+1);
        ArrayList<ArrayList<Integer>> withAdding = new ArrayList<>();
        for(ArrayList<Integer> list : recursiveAnswerFromBottomCalls){
            ArrayList<Integer> withToAdd = new ArrayList<>(list);
            withToAdd.add(0,arr[idx]);
            withAdding.add(withToAdd);
        }
        ArrayList<ArrayList<Integer>> finalAnswerOfThisLevel = new ArrayList<>(recursiveAnswerFromBottomCalls);
        finalAnswerOfThisLevel.addAll(withAdding);
        return finalAnswerOfThisLevel;
    }

    private static void setsOfDuplicateArray(int[] arr, int idx, ArrayList<Integer> localAnsWith, ArrayList<ArrayList<Integer>> ans){
        if(idx == arr.length){
            ArrayList<Integer> recursionAns = new ArrayList<>(localAnsWith);
            ans.add(recursionAns);
            return;
        }


        int val = arr[idx];
        //take this value
        localAnsWith.add(val);
        setsOfDuplicateArray(arr, idx + 1, localAnsWith, ans);
        localAnsWith.remove(localAnsWith.size() - 1);
        while((idx+1)< arr.length && arr[idx]==arr[idx+1]){
            idx++;
        }
        setsOfDuplicateArray(arr, idx + 1, localAnsWith, ans);

    }

    private static void permutationOfStringParameterType(String s,int idx,String ans,ArrayList<String> permutations){

        if(s.isEmpty()){
            //prevent duplication, unoptimized way
            if(!permutations.contains(ans))
                permutations.add(ans);
            return;
        }

        for(int i=0;i<s.length();i++){
            char appendChar = s.charAt(i);
            permutationOfStringParameterType(s.substring(0,i)+s.substring(i+1),idx+1,ans+appendChar,permutations);
        }
    }

    private static void permutationOfDuplicateStringParameterType(String s,int idx,String ans,ArrayList<String> permutations){

        if(s.isEmpty()){
            permutations.add(ans);
            return;
        }

        for(int i=0;i<s.length();i++){
            while((i+1)<s.length() && s.charAt(i)==s.charAt(i+1)){
                i++;
            }
            char appendChar = s.charAt(i);
            permutationOfDuplicateStringParameterType(s.substring(0,i)+s.substring(i+1),idx+1,ans+appendChar,permutations);
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
        int[] subsetArray = new int[]{1,2,3};
        System.out.println(setsOfArrayOriginalRecursion(subsetArray,0));
        ArrayList<ArrayList<Integer>> subsetList = new ArrayList<>();
        setsOfArray(subsetArray,0,new ArrayList<>(),subsetList);
        System.out.println(subsetList);
        ArrayList<ArrayList<Integer>> subsetListDuplicates = new ArrayList<>();
        int[] subsetArrayDuplicates = new int[]{1,2,2,3};
        setsOfDuplicateArray(subsetArrayDuplicates,0,new ArrayList<>(),subsetListDuplicates);
        System.out.println(subsetListDuplicates);
        System.out.println(setsOfArrayOriginalRecursion(subsetArrayDuplicates,0));
        ArrayList<String> permutations = new ArrayList<>();
        long startTime = System.nanoTime();
        permutationOfStringParameterType("aabbc",0,"",permutations);
        long endTime = System.nanoTime();
        long executionTime
                = (endTime - startTime);
        System.out.println(permutations);
        System.out.println(executionTime+" ns");
        ArrayList<String> permutationDuplicates = new ArrayList<>();
        long startTimeOptimized = System.nanoTime();
        permutationOfDuplicateStringParameterType("aabbc",0,"",permutationDuplicates);
        long endTimeOptimized = System.nanoTime();
        long executionTimeOptimized
                = (endTimeOptimized - startTimeOptimized);
        System.out.println(permutationDuplicates);
        System.out.println(executionTimeOptimized+" ns");
    }

}
