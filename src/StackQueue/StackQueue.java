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
        ListNode() {};
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
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

    //Leetcode 2487 (Takes 63ms) Monotonic stack approach
    //https://leetcode.com/problems/remove-nodes-from-linked-list/solutions/5118366/detailed-explanation-3-approaches-stack-recursion-reversal-o-1-space-efficient/
    public ListNode removeNodesMono(ListNode head) {
        ListNode cur = head;
        Stack<ListNode> stack = new Stack<>();

        while (cur != null) {
            while (!stack.isEmpty() && stack.peek().val < cur.val) {
                stack.pop();
            }
            stack.push(cur);
            cur = cur.next;
        }

        ListNode nxt = null;
        while (!stack.isEmpty()) {
            cur = stack.pop();
            cur.next = nxt;
            nxt = cur;
        }

        return cur;
    }

    //Leetcode 1963 (128 ms)
    public int minSwaps(String s) {

        Stack<Character> stack = new Stack<>();

        //The idea in this question is to remove the brackets that are already balanced
        for(Character c : s.toCharArray()){
            if(c==']'){
                if(!stack.isEmpty() && stack.peek()=='['){
                    stack.pop();
                    continue;
                }
            }
            stack.push(c);
        }
        int totalUnclosedBrackets = stack.size()/2;
        return (totalUnclosedBrackets) % 2 == 0 ? totalUnclosedBrackets/2 : totalUnclosedBrackets/2 + 1;
    }

    //Leetcode 1963 (87 ms)
    public int minSwapsStringBuilder(String s) {

        StringBuilder stack = new StringBuilder();

        //The idea in this question is to remove the brackets that are already balanced
        for(Character c : s.toCharArray()){
            if(c==']'){
                if(!stack.isEmpty() && stack.charAt(stack.length()-1)=='['){
                    stack.setLength(stack.length() - 1);
                    continue;
                }
            }
            stack.append(c);
        }
        int totalUnclosedBrackets = stack.length()/2;
        return (totalUnclosedBrackets) % 2 == 0 ? totalUnclosedBrackets/2 : totalUnclosedBrackets/2 + 1;
    }

    //Leetcode 1963 (17 ms)
    public int minSwapsSmart(String s) {
        int openBracket = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (openBracket > 0 && c == ']') {
                --openBracket;
            }else if (c == '[') {
                ++openBracket;
            }
        }
        return (openBracket + 1) / 2;
    }

    //Leetcode 2816 (Worst Code)
    public ListNode doubleIt(ListNode head) {

        Stack<ListNode> stack = new Stack<>();
        ListNode curr = head;

        while(curr!=null){
            stack.push(curr);
            curr = curr.next;
        }

        ListNode temp = stack.pop();
        ListNode node = new ListNode();
        int carry = 0;

        if(stack.isEmpty()){
            int doubleValue = 2*temp.val;
            temp.val = doubleValue%10 + carry;
            carry = doubleValue/10;

            if(carry!=0){
                ListNode carryAns = new ListNode(carry);
                carryAns.next = temp;
                return carryAns;
            }else{
                return temp;
            }
        }
        else {
            while(!stack.isEmpty() || temp!=null){
                int doubleValue = 2*temp.val;
                temp.val = doubleValue%10 + carry;
                carry = doubleValue/10;
                if(stack.size()>0){
                    node = stack.pop();
                    node.next = temp;
                    temp = node;
                }else{
                    temp = null;
                }
            }
        }

        if(carry!=0){
            ListNode carryAns = new ListNode(carry);
            carryAns.next = node;
            return carryAns;
        }

        return node;
    }

    //https://leetcode.com/problems/double-a-number-represented-as-a-linked-list/solutions/5122942/beats-100-2ms-detailed-explanation-1-pointer-and-stack-tc-o-n-sc-o-1/
    //Leetcode 2816 (Better code flow)
    public ListNode doubleItBetter(ListNode head) {
        // Initialize a stack to store the values of the linked list
        Stack<Integer> values = new Stack<>();
        int val = 0;

        // Traverse the linked list and push its values onto the stack
        while (head != null) {
            values.push(head.val);
            head = head.next;
        }

        ListNode newTail = null;

        // Iterate over the stack of values and the carryover
        while (!values.isEmpty() || val != 0) {
            // Create a new ListNode with value 0 and the previous tail as its next node
            newTail = new ListNode(0, newTail);

            // Calculate the new value for the current node
            // by doubling the last digit, adding carry, and getting the remainder
            if (!values.isEmpty()) {
                val += values.pop() * 2;
            }
            newTail.val = val % 10;
            val /= 10;
        }

        // Return the tail of the new linked list
        return newTail;
    }

    //Leetcode 946
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        boolean canPush = true;
        boolean canPop = false;
        Stack<Integer> stack = new Stack<>();
        int i=0;
        int j=0;

        while(i<pushed.length || j<popped.length){

            if(i < pushed.length){
                stack.push(pushed[i]);
                i++;
                canPush = true;
            }else{
                canPush = false;
            }

            while(j < popped.length){
                if(!stack.isEmpty() && stack.peek()==popped[j]){
                    stack.pop();
                    j++;
                    canPop = true;
                }else{
                    canPop = false;
                    break;
                }
            }

            if(!canPop && !canPush){
                return false;
            }
        }

        return i==j && j==popped.length;
    }

    //Leetcode 946
    //Better Code
    class Solution {
        public boolean validateStackSequences(int[] pushed, int[] popped) {
            Stack<Integer> st = new Stack<>(); // Create a stack

            int j = 0; // Intialise one pointer pointing on popped array

            for(int val : pushed){
                st.push(val); // insert the values in stack
                while(!st.isEmpty() && st.peek() == popped[j]){ // if st.peek() values equal to popped[j];
                    st.pop(); // then pop out
                    j++; // increment j
                }
            }
            return st.isEmpty(); // check if stack is empty return true else false
        }
    }

    //Best Code
    //Leetcode 946
    class SolutionBest {
        public boolean validateStackSequences(int[] pushed, int[] popped) {
            int i = 0; // Intialise one pointer pointing on pushed array
            int j = 0; // Intialise one pointer pointing on popped array

            for(int val : pushed){
                pushed[i++] = val; // using pushed as the stack.
                while(i > 0 && pushed[i - 1] == popped[j]){ // pushed[i - 1] values equal to popped[j];
                    i--; // decrement i
                    j++; // increment j
                }
            }
            return i == 0; // Since pushed is a permutation of popped so at the end we are supposed to be left with an empty stack
        }
    }


    //Leetcode 1249
        //https://www.youtube.com/watch?v=NNxaYz0nrk0
        public String minRemoveToMakeValid(String s) {
            Stack <Integer> st=new Stack<>();
            for(int i=0;i<s.length();i++){
                if(s.charAt(i)=='(') st.push(i);
                else if(s.charAt(i)==')'){
                    if(!st.isEmpty()&&s.charAt((int)st.peek())=='('){
                        st.pop();
                    }
                    else st.push(i);
                }
                else continue;
            }
            StringBuilder sb=new StringBuilder();
            for(int i=s.length()-1;i>=0;i--){
                if(!st.isEmpty()&&(int)st.peek()==i) {
                    st.pop();
                    continue;
                }
                else sb.append(s.charAt(i));
            }
            return sb.reverse().toString();
        }

        //Leetcode 1190
    public String reverseParentheses(String s) {
        Stack<StringBuilder> stack = new Stack<>();
        for(Character c : s.toCharArray()){
            if(c=='('){
                StringBuilder ans = new StringBuilder();
                ans.append("");
                stack.push(ans);
            }else if(c==')'){
                StringBuilder reverseIt = stack.pop();
                StringBuilder reverseStr = reverseIt.reverse();
                StringBuilder finalAns = stack.isEmpty() ? reverseStr : stack.pop().append(reverseStr);
                //System.out.println(finalAns);
                stack.push(finalAns);
            }else{
                StringBuilder topOfStack = stack.isEmpty() ? new StringBuilder() : stack.pop();
                topOfStack.append(c);
                stack.push(topOfStack);
            }
        }

        return stack.pop().toString();
    }

    //Leetcode 933
    class RecentCounter {
        Queue<Integer> q;
        public RecentCounter() {
            q = new LinkedList<>();
        }

        public int ping(int t) {
            q.add(t);
            while (q.peek() < t - 3000)
                q.poll();
            return q.size();
        }
    }
}
