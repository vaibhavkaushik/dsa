package StackQueue;

import java.util.Stack;

public class StackQueue {

    //Leetcode 1614
    public int maxDepth(String s) {
        Stack<String> h = new Stack<>();
        int maxNumParentheses = 0;
        for (char i : s.toCharArray()){
            if (i == '('){
                h.push("(");
            }
            else if (i == ')'){
                h.pop();
            }
            maxNumParentheses = Math.max(maxNumParentheses, h.size());
        }
        return maxNumParentheses;
    }
}
