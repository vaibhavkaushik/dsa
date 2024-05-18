package StackQueue;

import LinkedList.SinglyLinkedList.Node;

import java.util.*;

public class StackQueue {

    public class ListNode {
        int val;
        ListNode next;
        public ListNode(int data){
            this.val = data;
        }
    }

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode next;

        TreeNode (int val){
            this.val = val;
        }
    }

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

    //Leetcode 496
    /*
    Suppose we have a decreasing sequence followed by a greater number
    For example [5, 4, 3, 2, 1, 6] then the greater number 6 is the next greater element for all previous numbers in the sequence

    We use a stack to keep a decreasing sub-sequence, whenever we see a number x greater than stack.peek() we pop all elements less than x and for all the popped ones, their next greater element is x
    For example [9, 8, 7, 3, 2, 1, 6]
    The stack will first contain [9, 8, 7, 3, 2, 1] and then we see 6 which is greater than 1 so we pop 1 2 3 whose next greater element should be 6
     */
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(); // map from x to next greater element of x
        Stack<Integer> stack = new Stack<>();
        for (int num : nums) {
            while (!stack.isEmpty() && stack.peek() < num)
                map.put(stack.pop(), num);
            stack.push(num);
        }
        for (int i = 0; i < findNums.length; i++)
            findNums[i] = map.getOrDefault(findNums[i], -1);
        return findNums;
    }

    //Leetcode 1047
    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }

        }
        StringBuilder ans = new StringBuilder();
        while (!stack.isEmpty()) {
            ans.insert(0,stack.pop());
        }
        return ans.toString();

    }

    //Leetcode 2696
    public int minLength(String s) {
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()){
            if(!stack.isEmpty() && c=='B' && stack.peek()=='A'){
                stack.pop();
                continue;
            }
            if(!stack.isEmpty() && c=='D' && stack.peek()=='C'){
                stack.pop();
                continue;
            }
            stack.push(c);
        }
        return stack.size();
    }

    //Leetcode 232
    class MyQueue {
        Stack<Integer> stack;
        Stack<Integer> helperStack;

        public MyQueue() {
            this.stack = new Stack();
            this.helperStack = new Stack();
        }

        public void push(int x) {
            this.stack.push(x);
        }

        public int pop() {
            while (stack.size() > 0) {
                this.helperStack.push(stack.pop());
            }
            int front = this.helperStack.pop();
            while (helperStack.size() > 0) {
                stack.push(helperStack.pop());
            }
            return front;
        }

        public int peek() {
            while (stack.size() > 0) {
                this.helperStack.push(stack.pop());
            }
            int front = this.helperStack.peek();
            while (helperStack.size() > 0) {
                stack.push(helperStack.pop());
            }
            return front;
        }

        public boolean empty() {
            return stack.isEmpty();
        }
    }

    //Leetcode 844
    public boolean backspaceCompare(String s, String t) {
        Stack<Character> stackS = new Stack();
        Stack<Character> stackT = new Stack();

        for(Character c : s.toCharArray()){
            if(stackS.size()>0 && c=='#'){
                stackS.pop();
            }else if(c!='#'){
                stackS.push(c);
            }
        }

        for(Character c : t.toCharArray()){
            if(stackT.size()>0 && c=='#'){
                stackT.pop();
            }else if(c!='#'){
                stackT.push(c);
            }
        }

        StringBuilder ansS = new StringBuilder();
        StringBuilder ansT = new StringBuilder();

        for(Character c : stackS){
            ansS.append(c);
        }

        for(Character c : stackT){
            ansT.append(c);
        }
        // System.out.println(ansS);
        // System.out.println(ansT);
        return ansS.toString().equals(ansT.toString());

    }

    /*
LETS DO THIS:

so we follow steps:
1>we create the node
2>we traverse the array for values which are less than the current node!-- these values will become our left subtree.we stop whenever we get a value larger than the current root of the subtree!
3>we take the rest of the array(values whuch are greater than the value of the current root)-these are the values which will make out right subtree!

so we make a root!
make the left subtree(recursively)
then make right subtree(recursively)
*/

    //Leetcode 1008
        public TreeNode bstFromPreorder(int[] preorder) {
            return helper(preorder, 0, preorder.length - 1);
        }
        private TreeNode helper(int[] preorder, int start, int end) {
            if(start > end) return null;

            TreeNode node = new TreeNode(preorder[start]);
            int i;
            for(i=start;i<=end;i++) {
                if(preorder[i] > node.val)
                    break;
            }

            node.left = helper(preorder, start+1, i-1);
            node.right = helper(preorder, i, end);
            return node;
        }

        //Leetcode 2130
        public int pairSum(ListNode head) {
            if(head == null || head.next == null){
                return -1;
            }

            //Find mid of linked list
            ListNode slow = head;
            ListNode fast = head;

            while(fast.next!=null && fast.next.next!=null){
                slow = slow.next;
                fast = fast.next.next;
            }

            //Current slow pointer is at pointing at the last node of first half linked list
            ListNode secondHalf = slow.next;
            slow.next = null;
            ListNode firstHalf = head;

            Stack<ListNode> stack = new Stack<>();
            ListNode temp  = secondHalf;
            while(temp!=null){
                stack.push(temp);
                temp = temp.next;
            }

            int max = -1;
            temp = firstHalf;
            while(temp!=null){
                int val1 = stack.pop().val;
                int val2 = temp.val;
                max = Math.max(max,val1+val2);
                temp = temp.next;
            }

            return max;
        }


        //Leetcode 1381 (Unoptimized)
        class CustomStack {
            Stack<Integer> stack;
            Stack<Integer> helperStack;
            int size;
            public CustomStack(int maxSize) {
                this.stack = new Stack<>();
                this.size = maxSize;
            }

            public void push(int x) {
                if(stack.size() < size){
                    stack.push(x);
                }
            }

            public int pop() {
                if(stack.isEmpty()){
                    return -1;
                }
                return stack.pop();
            }

            public void increment(int k, int val) {
                helperStack = new Stack<>();
                while(!stack.isEmpty()){
                    helperStack.push(stack.pop());
                }

                while(!helperStack.isEmpty()){
                    if(k>0){
                        stack.push(helperStack.pop()+val);
                        k--;
                        continue;
                    }
                    stack.push(helperStack.pop());
                }
            }
        }

    //Leetcode 1381 (Optimized)
    class CustomStackOptimized {
        int[] stack;
        int size;
        int index;
        public CustomStackOptimized(int maxSize) {
            stack = new int[maxSize];
            size = maxSize;
            index=0;
        }

        public void push(int x) {
            if (index<size){
                stack[index++] = x;
            }
        }

        public int pop() {
            if(index == 0) return -1;
            return stack[--index];
        }

        public void increment(int k, int val) {
            if(stack.length == 0) return;
            for(int i = 0;i<k && i<stack.length;i++)
                stack[i] = stack[i] + val;
        }
    }

    //Leetcode 2375
    //https://www.youtube.com/watch?v=GOCbsY7Arw4
    public String smallestNumber(String pattern) {

        boolean[] isUsed = new boolean[10];
        long[] finalAns = new long[]{Long.MAX_VALUE};
        smallestNumberHelper(pattern,0,0,finalAns,'-',isUsed,-1);
        return String.valueOf(finalAns[0]);
    }

    public long smallestNumberHelper(String pattern, int idx, long currAns, long[] minimumAns, Character lastChar, boolean[] isUsed, long lastNumberUsed){
        if(idx == pattern.length()+1){
            // System.out.println(currAns);
            return currAns;
        }

        long minAnsFromBottomCall = Long.MAX_VALUE;
        for(int i=1;i<=9;i++){
            if(!isUsed[i]){
                if(lastChar=='I' && i < lastNumberUsed) continue;
                if(lastChar=='D' && i > lastNumberUsed) continue;
                long ans = currAns*10 + i;
                isUsed[i] = true;
                Character c = idx >= pattern.length() ? '-' : pattern.charAt(idx);
                minAnsFromBottomCall = smallestNumberHelper(pattern,idx+1,ans,minimumAns,c,isUsed,i);
                isUsed[i] = false;
                minimumAns[0] = Math.min(minimumAns[0],minAnsFromBottomCall);
            }
        }

        return minAnsFromBottomCall;
    }

    //Leetcode 921
    public int minAddToMakeValid(String s) {
        Stack<Character> stack = new Stack<>();
        int count = 0;
        for(Character c : s.toCharArray()){
            if(c==')'){
                if(stack.isEmpty()){
                    count++;
                    continue;
                }
                if(stack.peek()=='('){
                    stack.pop();
                    count--;
                    continue;
                }
            }
            if(c=='('){
                stack.push(c);
                count++;
            }
        }

        return count;
    }

    //Leetcode 2390
    public String removeStars(String l) {
        // Create a new stack to keep track of characters encountered so far
        Stack<Character> s = new Stack<>();

        // Iterate over each character in the input string
        for (char c : l.toCharArray()) {
            // If the current character is a star, remove the topmost character from the stack
            if (c == '*') {
                s.pop();
            }
            // If the current character is not a star, add it to the stack
            else {
                s.push(c);
            }
        }

        // Create a new StringBuilder to store the characters in the stack
        StringBuilder sb = new StringBuilder();

        // Iterate over each character in the stack and append it to the StringBuilder
        for (char c : s) {
            sb.append(c);
        }

        // Convert the StringBuilder to a string and return it as the output
        return sb.toString();
    }

    //Leetcode 2487 (Takes 63ms)
    public ListNode removeNodes(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode curr = head;
        while(curr!=null){
            stack.push(curr);
            curr = curr.next;
        }

        //No one can be bigger than this, so we can start from last
        ListNode temp = stack.pop();
        while(!stack.isEmpty()){
            if(stack.peek().val >= temp.val){
                stack.peek().next = temp;
                temp = stack.peek();
            }
            stack.pop();
        }

        return temp;
    }

}
