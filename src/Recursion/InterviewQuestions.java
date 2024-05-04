package Recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class InterviewQuestions {

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
    }
}
