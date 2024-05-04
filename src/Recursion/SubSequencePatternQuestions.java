package Recursion;

import java.util.ArrayList;

public class SubSequencePatternQuestions {

    //Just like how subsequence works, subsequence pattern is taking or not taking a element and exploring all possibilities
    //with that
    //example print all subsets of abcd
    public static void subsetsOfStringParameterAnswerApproach(String s, String subset, ArrayList<String> ans){
        if(s.isEmpty()){
            ans.add(subset.toString());
            return;
        }

        char elementAtThisCall = s.charAt(0);
        //take it
        subsetsOfStringParameterAnswerApproach(s.substring(1),subset+elementAtThisCall,ans);
        subsetsOfStringParameterAnswerApproach(s.substring(1),subset,ans);

    }

    public static ArrayList<String> subsetsOfStringReturnTypeApproach(String s){
        if(s.isEmpty()){
            ArrayList<String> emptyString = new ArrayList<String>();
            emptyString.add("");
            return emptyString;
        }

        ArrayList<String> answersFromBottomLevelCalls = subsetsOfStringReturnTypeApproach(s.substring(1));
        //don't take it
        ArrayList<String> currentLevelAnswers = new ArrayList<>(answersFromBottomLevelCalls);
        //take it
        char elementAtThisCall = s.charAt(0);
        for(String bottomLevelStringAnswers : answersFromBottomLevelCalls){
            bottomLevelStringAnswers = elementAtThisCall+bottomLevelStringAnswers;
            currentLevelAnswers.add(bottomLevelStringAnswers);
        }
        return currentLevelAnswers;
    }

    //Making answer in recursive calls along with return type approach
    static ArrayList<String> subseqRet(String p, String up) {
        if (up.isEmpty()) {
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        char ch = up.charAt(0);
        ArrayList<String> left = subseqRet(p + ch, up.substring(1));
        ArrayList<String> right = subseqRet(p, up.substring(1));

        left.addAll(right);
        return left;
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
        ArrayList<ArrayList<Integer>> thisLevelAnswer = new ArrayList<>(recursiveAnswerFromBottomCalls);
        for(ArrayList<Integer> list : recursiveAnswerFromBottomCalls){
            list.add(arr[idx]);
            thisLevelAnswer.add(list);
        }
        return thisLevelAnswer;
    }

    public static void main(String[] args) {
        ArrayList<String> subsetsOfStringAns = new ArrayList<>();
        subsetsOfStringParameterAnswerApproach("abcd","",subsetsOfStringAns);
        System.out.println(subsetsOfStringAns);
        System.out.println(subsetsOfStringReturnTypeApproach("abcd"));
    }
}
