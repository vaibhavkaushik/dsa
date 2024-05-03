package Recursion;

import java.util.ArrayList;
import java.util.List;

public class SubSetPatternQuestions {

    //Just like how subsets works, subset pattern is taking or not taking a element and exploring all possibilities
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

    public static void main(String[] args) {
        ArrayList<String> subsetsOfStringAns = new ArrayList<>();
        subsetsOfStringParameterAnswerApproach("abcd","",subsetsOfStringAns);
        System.out.println(subsetsOfStringAns);
        System.out.println(subsetsOfStringReturnTypeApproach("abcd"));
    }
}
