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

    //Leetcode 225
    class MyStack
    {
        Queue<Integer> queue;

        public MyStack()
        {
            this.queue=new LinkedList<Integer>();
        }

        // Push element x onto stack.
        public void push(int x)
        {
            queue.add(x);
            for(int i=0;i<queue.size()-1;i++)
            {
                queue.add(queue.poll());
            }
        }

        // Removes the element on top of the stack.
        public int pop()
        {
            return queue.poll();
        }

        // Get the top element.
        public int top()
        {
            return queue.peek();
        }

        // Return whether the stack is empty.
        public boolean empty()
        {
            return queue.isEmpty();
        }
    }


    //Leetcode 950 (https://www.youtube.com/watch?v=S6MGVmkMz2c)
    public int[] deckRevealedIncreasing(int[] deck) {
        int n = deck.length;
        int[] result = new int[n];

        boolean skip = false;

        int i = 0; // deck
        int j = 0; // result

        Arrays.sort(deck);

        while (i < n) {
            if (result[j] == 0) { // khali hai
                if (!skip) {
                    result[j] = deck[i];
                    i++;
                }

                skip = !skip; // alternate
            }

            j = (j + 1) % n;
        }

        return result;
    }

    //Leetcode 950 (https://www.youtube.com/watch?v=S6MGVmkMz2c)
    public int[] deckRevealedIncreasingQueueSimulation(int[] deck) {
        int n = deck.length;

        Queue<Integer> que = new LinkedList<>();
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            que.offer(i);
        }

        Arrays.sort(deck);

        for (int i = 0; i < n; i++) {
            int idx = que.poll();
            result[idx] = deck[i];

            if (!que.isEmpty()) {
                que.offer(que.peek());
                que.poll();
            }
        }

        return result;
    }

    //Leetcode 1823 (49 ms)
    public int findTheWinner(int n, int k) {
        Queue<Integer> queue = new LinkedList<>();

        for(int i=1;i<=n;i++){
            queue.offer(i);
        }

        while(queue.size()!=1){
            int popTimes = k;
            while(popTimes!=1){
                int frontVal = queue.poll();
                queue.offer(frontVal);
                popTimes--;
            }
            //Remove one friend after k next friends
            queue.poll();
        }

        return queue.poll();

    }

    //Leetcode 2073
    public int timeRequiredToBuy(int[] tickets, int k) {
        int count = 0;
        while(tickets[k]!=0){
            for(int i=0;i<tickets.length;i++){
                if(tickets[i]!=0){
                    tickets[i]-=1;
                    count++;
                }
                if(tickets[k]==0){
                    break;
                }
            }
        }
        return count;
    }

    /*
The idea of this solution is to think about the problem differently. Let's reformulate it in another way.

The persons who are queued in front of the selected person will at most buy the same ticket as the person in index k. If they should buy more tickets, person k will buy his tickets before that.
The persons behind person k will buy at most the (number of tickets of person k) - 1.
In our implementation we will implement it in a way that we pre-calculate how much tickets each person will buy without simulating the whole process.

*/

    //Leetcode 2073
        public int timeRequiredToBuyOptimized(int[] tickets, int k) {
            int value = tickets[k];
            int seconds = 0;
            for(int i = 0; i<tickets.length; i++) {
                int max = (i<=k)? value: value-1;
                seconds+= Math.min(tickets[i], max);
            }
            return seconds;
        }

    //https://www.youtube.com/watch?v=Bk-HxzaooqM
    //Leetcode 2444
        public long countSubarrays(int[] nums, int minK, int maxK) {
            long res = 0;
            int badIdx = -1, leftIdx = -1, rightIdx = -1;

            for (int i = 0; i < nums.length; ++i) {
                if (!(minK <= nums[i] && nums[i] <= maxK)) {
                    badIdx = i;
                }

                if (nums[i] == minK) {
                    leftIdx = i;
                }

                if (nums[i] == maxK) {
                    rightIdx = i;
                }

                res += Math.max(0, Math.min(leftIdx, rightIdx) - badIdx);
            }

            return res;
        }

        //Leetcode 387
    public int firstUniqChar(String s) {
        // Base case...
        if(s.length() == 0)  return -1;
        // To keep track of the count of each character, we initialize an int[]store with size 26...
        int[] store = new int[26];
        // Traverse string to keep track number of times each character in the string appears...
        for(char ch : s.toCharArray()){
            store[ch - 'a']++;      // To access the store[] element representative of each character, we subtract ‘a’ from that character...
        }
        // Traverse string again to find a character that appears exactly one time, return it’s index...
        for(int idx = 0; idx < s.length(); idx++){
            if(store[s.charAt(idx) - 'a'] == 1){
                return idx;
            }
        }
        return -1;      // if no character appeared exactly once...
    }

    //Leetcode 341
    public class NestedIterator implements Iterator<Integer> {
        Queue<Integer> list;
        public NestedIterator(List<NestedInteger> nestedList) {
            list = new LinkedList<>();
            for(NestedInteger ni : nestedList){
                flatten(ni);
            }
        }

        public void flatten(NestedInteger ni){

            if(ni.isInteger()){
                list.offer(ni.getInteger());
            }
            else {
                for(NestedInteger nestedList : ni.getList()){
                    flatten(nestedList);
                }
            }

            return;
        }

        @Override
        public Integer next() {
            return list.poll();
        }

        @Override
        public boolean hasNext() {
            return list.size()>0;
        }
    }

    //Leetcode 641
    class MyCircularDeque {

        private class ListNode {
            int val;
            ListNode next;
            ListNode prev;
            public ListNode(int val, ListNode next) {
                this.val = val;
                this.next = next;
            }
            public ListNode(int val) {
                this.val = val;
            }
        }

        int size=0;
        int K;
        ListNode head, tail;

        /** Initialize your data structure here. Set the size of the deque to be k. */
        public MyCircularDeque(int k) {
            this.K = k;
            head = tail = null;
        }

        /** Adds an item at the front of Deque. Return true if the operation is successful. */
        public boolean insertFront(int value) {
            if(isFull())
                return false;

            ListNode node = new ListNode(value);
            if(head == null)
                head = tail = node;
            else {
                node.next = head;
                node.prev = tail;
                head.prev = node;
                head = node;
                tail.next = head;
            }
            size++;
            return true;
        }

        /** Adds an item at the rear of Deque. Return true if the operation is successful. */
        public boolean insertLast(int value) {
            if(isFull())
                return false;

            ListNode node = new ListNode(value);
            if(head == null)
                head = tail = node;
            else {
                node.next = head;
                node.prev = tail;
                head.prev = node;
                tail.next = node;
                tail = node;
            }
            size++;
            return true;
        }

        /** Deletes an item from the front of Deque. Return true if the operation is successful. */
        public boolean deleteFront() {
            if(isEmpty())
                return false;

            if(head == tail)
                head = tail = null;
            else {
                head = head.next;
                head.prev = tail;
                tail.next = head;
            }
            size--;
            return true;
        }

        /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
        public boolean deleteLast() {
            if(isEmpty())
                return false;

            if(head == tail)
                head = tail = null;
            else {
                tail = tail.prev;
                tail.next = head;
                head.prev = tail;
            }
            size--;
            return true;
        }

        /** Get the front item from the deque. */
        public int getFront() {
            return isEmpty()? -1 : head.val;
        }

        /** Get the last item from the deque. */
        public int getRear() {
            return isEmpty()? -1 : tail.val;
        }

        /** Checks whether the circular deque is empty or not. */
        public boolean isEmpty() {
            return size==0;
        }

        /** Checks whether the circular deque is full or not. */
        public boolean isFull() {
            return size==K;
        }


    }

    //Leetcode 1670
    class FrontMiddleBackQueue {
        private int size;
        private Node head, tail, mid;

        public FrontMiddleBackQueue() {
            size = 0;
            head = tail = new Node(-1);
            head.next = tail;
            tail.prev = head;
            mid = head;     // default mid position
        }

        public void pushFront(int val) {
            Node newNode = new Node(val);
            add(head, newNode); // new node is added right to head
            size++;

            if (size == 1) {    // if its the first ever node
                mid = head.next;    // make it the mid
            }
            if (size % 2 == 0) {    // mid is the former of the two mids
                mid = mid.prev;
            }
        }

        public void pushMiddle(int val) {
            Node newNode = new Node(val);
            // if size is even, new mid will be at perfect centre
            // that is, right next to mid
            if (size % 2 == 0) {
                add(mid, newNode);  // goes right next to mid
                mid = mid.next;
            }
            // else new mid will go to the left of current mid
            // i.e. the right next position of mid's current prev
            else {
                add(mid.prev, newNode); // go next to mid's prev
                mid = mid.prev;
            }
            size++;
        }

        public void pushBack(int val) {
            Node newNode = new Node(val);
            // gets added right before tail
            // i.e. right next to tail's current prev
            add(tail.prev, newNode);
            size++;
            // if size becomes odd, mid will move to perfect centre
            if (size % 2 == 1) {
                mid = mid.next;
            }
        }

        public int popFront() {
            if (size == 0) {
                return -1;
            }
            if (size == 1) {  // if list wil become empty
                mid = head;   // default mid is head
            } else if (size % 2 == 0) {
                // list will become odd, mid will move to next node
                mid = mid.next;
            }

            int val = head.next.value;
            delete(head.next);  // delete the node next to head
            size--;
            return val;
        }

        public int popMiddle() {
            if (size == 0) {
                return -1;
            }

            Node node = mid;
            if (size == 1) {  // list wil become empty
                mid = head;   // default mid is head
            } else {
                // if list will become odd, mid will go to next node
                // if list will become even, mid will go to prev node
                mid = (size % 2 == 0)? mid.next : mid.prev;
            }

            delete(node);   // delete the node (old mid)
            size--;
            return node.value;
        }

        public int popBack() {
            if (size == 0) {
                return -1;
            }
            if (size == 1) {  // list wil become empty
                mid = head;   // default mid is head
            } else if (size % 2 == 1) { // if list will become even
                mid = mid.prev;         // mid will move to prev node
            }

            int val = tail.prev.value;
            delete(tail.prev);  // delete the preceding node of tail
            size--;
            return val;
        }

        // Adds a node `newNode` right next to a reference node `ref`
        private void add(Node ref, Node newNode) {
            newNode.next = ref.next;
            ref.next = newNode;
            newNode.next.prev = newNode;
            newNode.prev = ref;
        }

        // Deletes the passed in node 'node'
        private void delete(Node node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }

        // Node class
        private class Node {
            int value;
            Node next, prev;
            private Node(int value) {
                this.value = value;
            }
        }
    }

//    We can easily count the product in O(1) time if we keep the product prefix array - every next element of such array is the product of the previous product and next element added to the data structure.
//
//    Only problem with this approach is 0 element - it ruins this logic because every next element after it will be 0 if we just keep multiplying naively.
//
//            However if you think about it - we don't care about element before 0 because all such queries will return 0. The only sequence that can return some value > 0 will be the after the 0. This means that we essentially reset our structure after 0 element and start over.
//
//    For getProduct method we need to check if we have enough elements in our data structure. If not (as per problem statement we should always have enough) it means we have met 0 before and erased the data. In this case we can just return 0. Otherwise return normally as per prefix product logic - arr[N - 1]/arr[N - 1 - k].
//
//    Catches: it's easier if we initialize our list with 1 - in this case calculation became easier for cases when we added exactly k elements, otherwise we would need extra logic to check the size().
//    another cath - we can keep previous element and calculate next element based on it's value directly instead of goinmg every time to the list - will save some time
//
//    O(1) time for add (on average, if we skip details of dynamic array in java), O(1) getProduct method - few comparisions and one division.
//            O(n) space - potentially we can have products of all elements in a list

    //Leetcode 1352
    class ProductOfNumbers {

        List<Integer> list;
        int prev;

        ProductOfNumbers() {
            list = new ArrayList();
            list.add(1);
            prev = 1;
        }

        public void add(int num) {

            //if this is 0  we need to reinit the structure
            if (num==0) {
                list = new ArrayList();
                list.add(1);
                prev = 1;
                return;
            }

            //if element is > 0 create next element in a prefix product list. Update prev to be this
            //element
            prev*=num;
            list.add(prev);
        }

        public int getProduct(int k) {
            int N = list.size();
            //in case there are not enough elements by the problem definition it can only happen if
            //we have met 0 before. In this case return 0. In all other cases we get the product from
            //division due to prefix product property. Note that list always has n + 1 elements due to
            //initial 1, we need it to avoid outofbounds checks for edge cases
            return (k < N) ? prev / list.get(N - k -1) : 0;
        }

        //nums : 2 5 4 2
        //list : 1 2 10 40 80
        //prev = 80
    }

}
