package Recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InterviewQuestions {

    //Another way of solving this will be for looping over all keypad values for a given digit and then calling
    //recursion accordingly
    public static ArrayList<String> letterCombinations(String digits, int idx, HashMap<Character,String> dict){
        if(idx==digits.length()){
            ArrayList<String> ans = new ArrayList<>();
            ans.add("");
            return ans;
        }

        ArrayList<String> letterCombinationsFromBelowCalls = letterCombinations(digits,idx+1,dict);
        ArrayList<String> finalAnsOfThisLevel = new ArrayList<>();
        String idxKeyPadLetters = dict.get(digits.charAt(idx));
        for(Character c : idxKeyPadLetters.toCharArray()){
            for(String ans : letterCombinationsFromBelowCalls){
                finalAnsOfThisLevel.add(c+ans);
            }
        }
        return finalAnsOfThisLevel;
    }

    //Ways to generate a number using dice
    //Much similar to subset sum problem
    //Added int return type to check total calls
    //Adding the check of negative number reduces the recursive calls
    //This can be also solved by for looping over die values
    private static int subSetSumVariantByDice(int arr[], int idx, int sum, String ans, ArrayList<String> completeAns, int recursiveCall){

        if(idx >= arr.length){
            return 1;
        }

        if(sum < 0){
            return 1;
        }

        if(sum == 0){
            completeAns.add(ans);
            return 1;
        }


        int curr_num = arr[idx];
        int count = 0;
        if(sum - curr_num >=0) {
        count+=subSetSumVariantByDice(arr, 0, sum - curr_num, ans + curr_num, completeAns, recursiveCall + 1);
        }
        count+=subSetSumVariantByDice(arr,idx+1,sum,ans,completeAns,recursiveCall+1);

        return count;
    }

    public static void main(String[] args) {
        HashMap<Character,String> dict = new HashMap<>();
        dict.put('2',"abc");
        dict.put('3',"def");
        dict.put('4',"ghi");
        dict.put('5',"jkl");
        dict.put('6',"mno");
        dict.put('7',"pqrs");
        dict.put('8',"tuv");
        dict.put('9',"wxyz");
        System.out.println(letterCombinations("274",0,dict));
        ArrayList<String> ans = new ArrayList<>();
        System.out.println(subSetSumVariantByDice(new int[]{1,2,3,4,5,6},0,5,"",ans,0));
        System.out.println(ans);
    }
}
