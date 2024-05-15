package StackQueue;

import java.util.LinkedList;
import java.util.Queue;
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


    /*
This is a solution to the problem of removing outermost parentheses from a string containing only parentheses.

The approach used is to keep track of the parentheses using a stack. Whenever an opening parenthesis is encountered, it is pushed onto the stack. Whenever a closing parenthesis is encountered, the last opening parenthesis is popped from the stack.

If the stack size is greater than zero after pushing or popping, it means that the parenthesis is not an outer parenthesis, and it is added to the result string. If the stack size is zero, it means that the parenthesis is an outer parenthesis and it is not added to the result string.
*/
    //Leetcode 1021
    public String removeOuterParentheses(String s) {
        Stack<Character> bracket = new Stack<>();
        StringBuilder sb = new StringBuilder("");
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                if(bracket.size()>0){
                    sb.append(s.charAt(i));
                }
                bracket.push(s.charAt(i));
            }else{
                bracket.pop();
                if(bracket.size()>0){
                    sb.append(s.charAt(i));
                }
            }
        }
        return sb.toString();
    }


    //Leetcode 1700
    public int countStudents(int[] students, int[] sandwiches) {

        int len = students.length;

        Queue<Integer> studentQueue = new LinkedList<>();
        for (int student : students)
            studentQueue.add(student);

        Stack<Integer> sandwichStack = new Stack<>();
        for (int i = len - 1; i >= 0; i--)
            sandwichStack.push(sandwiches[i]);

        int wontBeAbleToServe = 0;

        while (!studentQueue.isEmpty() && wontBeAbleToServe < studentQueue.size()) {
            if (sandwichStack.peek().equals(studentQueue.peek())) {
                sandwichStack.pop();
                studentQueue.poll();
                wontBeAbleToServe = 0;
            } else {
                studentQueue.add(studentQueue.peek());
                studentQueue.poll();
                wontBeAbleToServe++;
            }
        }

        return studentQueue.size();
    }

    //Leetcode 1475
    public int[] finalPrices(int[] prices) {
        int[] ans = new int[prices.length];

        for(int i=0;i<prices.length;i++){
            int j = i;
            Stack<Integer> stack = new Stack();
            while(j!=prices.length){
                if(stack.size() > 0){
                    int top = stack.peek();
                    if(top >= prices[j]){
                        ans[i] = top - prices[j];
                        break;
                    }else{
                        ans[i] = top;
                    }
                }else{
                    stack.push(prices[j]);
                }
                j++;
            }
        }
        ans[prices.length-1] = prices[prices.length-1];
        return ans;
    }

    //Leetcode 682
    public int calPoints(String[] operations) {
        Stack<Integer> stack = new Stack();
        for(String s : operations){
            if(s.equals("C")){
                stack.pop();
                continue;
            }
            if(s.equals("D")){
                int top = stack.peek();
                stack.push(2*top);
                continue;
            }
            if(s.equals("+")){
                int first = stack.peek();
                stack.pop();
                int second = stack.peek();
                stack.push(first);
                stack.push(first+second);
                continue;
            }
            stack.push(Integer.valueOf(s));
        }

        int ans = 0;
        for(int val : stack){
            ans+=val;
        }

        return ans;
    }

}