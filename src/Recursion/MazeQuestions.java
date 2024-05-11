package Recursion;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MazeQuestions {

    public static class ListNode{
        ListNode next;
    }

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
    }

    //go from 0,0 to 4,4
    // with directions like U,D,R,L
    private static void travelInAMazeParameter(int[][] maze, int r, int c, int[][] dir, char[] dir_name, String ans, ArrayList<String> allAns) {

        //Once we reach at the end, let's store the answer
        if (r == maze.length - 1 && c == maze[0].length - 1) {
            allAns.add(ans);
            return;
        }

        for (int i = 0; i < dir_name.length; i++) {
            int row = r + dir[i][0];
            int col = c + dir[i][1];
            if (row < maze.length && col < maze[0].length) {
                travelInAMazeParameter(maze, row, col, dir, dir_name, ans + dir_name[i], allAns);
            }
        }
    }

    private static int travelInAMazeParameterPathCount(int[][] maze, int r, int c, int[][] dir, char[] dir_name) {

        //Once we reach at the end, let's store the answer
        if (r == maze.length - 1 && c == maze[0].length - 1) {
            return 1;
        }

        int count = 0;
        for (int i = 0; i < dir_name.length; i++) {
            int row = r + dir[i][0];
            int col = c + dir[i][1];
            if (row < maze.length && col < maze[0].length) {
                count += travelInAMazeParameterPathCount(maze, row, col, dir, dir_name);
            }
        }

        return count;
    }

    private static ArrayList<String> travelInMazeReturnType(int[][] arr, int r, int c) {

        if (r == arr.length - 1 && c == arr[0].length - 1) {
            ArrayList<String> baseCase = new ArrayList<>();
            baseCase.add("");
            return baseCase;
        }

        ArrayList<String> finalAnswer = new ArrayList<>();

        if (c + 1 < arr[0].length) {
            ArrayList<String> bottomAnswersForRightPath = travelInMazeReturnType(arr, r, c + 1);

            for (String rightPath : bottomAnswersForRightPath) {
                finalAnswer.add('R' + rightPath);
            }

        }
        if (r + 1 < arr.length) {
            ArrayList<String> bottomAnswersForDownPath = travelInMazeReturnType(arr, r + 1, c);

            for (String downPath : bottomAnswersForDownPath) {
                finalAnswer.add('D' + downPath);
            }
        }

        return finalAnswer;
    }

    private static void travelInAMazeWithObstaclesParameter(int[][] maze, int r, int c, int[][] dir, char[] dir_name, String ans, ArrayList<String> allAns) {

        //Once we reach at the end, let's store the answer
        if (r == maze.length - 1 && c == maze[0].length - 1) {
            allAns.add(ans);
            return;
        }

        for (int i = 0; i < dir_name.length; i++) {
            int row = r + dir[i][0];
            int col = c + dir[i][1];
            if (row < maze.length && col < maze[0].length && maze[row][col] != -1) {
                travelInAMazeWithObstaclesParameter(maze, row, col, dir, dir_name, ans + dir_name[i], allAns);
            }
        }
    }

    private static void travelInAMazeParameterAllDirections(int[][] maze, int r, int c, int[][] dir, char[] dir_name, String ans, ArrayList<String> allAns) {

        //Once we reach at the end, let's store the answer
        if (r == maze.length - 1 && c == maze[0].length - 1) {
            allAns.add(ans);
            return;
        }

        for (int i = 0; i < dir_name.length; i++) {
            int row = r + dir[i][0];
            int col = c + dir[i][1];
            if (row >= 0 && col >= 0 && row < maze.length && col < maze[0].length && maze[row][col] != -1) {
                maze[r][c] = -1;
                travelInAMazeParameterAllDirections(maze, row, col, dir, dir_name, ans + dir_name[i], allAns);
                maze[r][c] = 0;
            }
        }
    }

    private static void travelInAMazeParameterAllDirectionsAndPrintMazePath(int[][] maze, int[][] path, int r, int c, int[][] dir, char[] dir_name, String ans, ArrayList<String> allAns, int level) {

        //Once we reach at the end, let's store the answer
        if (r == maze.length - 1 && c == maze[0].length - 1) {
            path[r][c] = level;
            for (int[] onePath : path) {
                System.out.println(Arrays.toString(onePath));
            }
            System.out.println(ans);
            allAns.add(ans);
            return;
        }

        for (int i = 0; i < dir_name.length; i++) {
            int row = r + dir[i][0];
            int col = c + dir[i][1];
            if (row >= 0 && col >= 0 && row < maze.length && col < maze[0].length && maze[row][col] != -1) {
                maze[r][c] = -1;
                path[r][c] = level;
                travelInAMazeParameterAllDirectionsAndPrintMazePath(maze, path, row, col, dir, dir_name, ans + dir_name[i], allAns, level + 1);
                maze[r][c] = 0;
                path[r][c] = 0;
            }
        }
    }

    //At each row, we will try to place a queen, check if it is possible to place it
    //If possible pace it and move ahead to place others, if at a row none can be placed
    //go and backtrack
    private static void N_Queen(int[][] board, int row, int queens_left, char[][] solution_board,
                                boolean[] col_check, boolean[] up_diagonal_check, boolean[] down_diagonal_check) {

        if (queens_left == 0) {
            for (char[] rows : solution_board) {
                System.out.println(Arrays.toString(rows));
            }
            System.out.println();
        }

        for (int col = 0; col < board[0].length; col++) {
            if (row < board.length && board[row][col] != -1 && can_place(board, row, col, col_check,
                    up_diagonal_check, down_diagonal_check)) {
                board[row][col] = -1;
                solution_board[row][col] = 'Q';
                col_check[col] = true;
                up_diagonal_check[row + col] = true;
                down_diagonal_check[row - col + board[0].length - 1] = true;
                N_Queen(board, row + 1, queens_left - 1, solution_board, col_check,
                        up_diagonal_check, down_diagonal_check);
                board[row][col] = 0;
                solution_board[row][col] = '-';
                col_check[col] = false;
                up_diagonal_check[row + col] = false;
                down_diagonal_check[row - col + board[0].length - 1] = false;
            }
        }
    }

    //check notebook for concept
    private static boolean can_place(int[][] board, int row, int col, boolean[] col_check,
                                     boolean[] up_diagonal_check, boolean[] down_diagonal_check) {
        return !col_check[col] && !up_diagonal_check[row + col] && !down_diagonal_check[row - col + board[0].length - 1];
    }

    //TODO: Knight Tour

    private static void sudokuSolver(int[][] board, int row, int col) {

        if (row == board.length) {
            for (int[] board_row : board) {
                System.out.println(Arrays.toString(board_row));
            }
            System.out.println();
            System.out.println();
            return;
        }

        int r;
        int c;

        if (col == board[0].length-1) {
            r = row+1;
            c = 0;
        }else{
            r = row;
            c = col+1;
        }
        if(board[2][4]==5&&board[2][3]==6)
        {
            System.out.println();
        }

        if(board[row][col]!=0){
            sudokuSolver(board,r,c);
        }else{
            for(int i=1;i<=9;i++){
                if(can_place_sudoku(board,row,col,i)){
                    board[row][col] = i;
                    sudokuSolver(board,r,c);
                    board[row][col] = 0;
                }
            }
        }
    }

    private static boolean can_place_sudoku(int[][] board, int row, int col, int val){

        //can place in row
        for(int i=0; i<board[row].length;i++){
            if(board[row][i]==val){
                return false;
            }
        }

        //can place in col
        for(int i=0; i<board.length;i++){
            if(board[i][col]==val){
                return false;
            }
        }

        int r = (row/3)*3;
        int c = (col/3)*3;
        for(int i=r;i<r+3;i++){
            for(int j=c;j<c+3;j++){
                if(board[i][j]==val){
                    return false;
                }
            }
        }

        return true;
    }

    //Leetcode 39
    public static void combinationSumHelper(int[] candidates, int target, List<Integer> currentAns,
                                            List<List<Integer>> totalAnswer){

        if(target == 0){
            ArrayList<Integer> baseAnswer = new ArrayList<>(currentAns);
            totalAnswer.add(currentAns);
            return;
        }

        for (int candidate : candidates) {
            if (target - candidate >= 0) {
                currentAns.add(candidate);
                combinationSumHelper(candidates, target - candidate, currentAns, totalAnswer);
                currentAns.remove(currentAns.size() - 1);
            }
        }

        return;

    }

    //Leetcode 77
    public void combineHelper(int n, int k, int idx, List<Integer> ans, List<List<Integer>> totalAns){

        if(k==0){
            List<Integer> localAns = new ArrayList<>(ans);
            totalAns.add(localAns);
            return;
        }

        for(int i=idx;i<=n;i++){
            ans.add(i);
            combineHelper(n,k-1,i+1,ans,totalAns);
            ans.remove(ans.size()-1);
        }

    }

    //Leetcode 22
    private static void balancedParanthesisGenerator(int leftOpen, int rightOpen, String ans, ArrayList<String> allAns){

        if(leftOpen==0 && rightOpen==0){
            allAns.add(ans);
            return;
        }

        //if equal number of left_open and right_open brackets, then only option we have is to use left_open
        //left_open
        if(leftOpen==rightOpen){
            balancedParanthesisGenerator(leftOpen-1,rightOpen,ans+'(',allAns);
        }
        else{
            //if left open_is less than right_open then we have both the choices of left and right brackets
            //left_open
            if(leftOpen > 0) {
                balancedParanthesisGenerator(leftOpen - 1, rightOpen, ans+'(', allAns);
            }
            //right_open
            if(rightOpen > 0) {
                balancedParanthesisGenerator(leftOpen, rightOpen - 1, ans + ')', allAns);
            }
        }
    }

   //Leetcode 17
    public List<String> letterCombinations(String digits) {

            if(digits.isEmpty()){
                return new ArrayList<String>();
            }

            HashMap<Character,String> digitToLetterMap = new HashMap<>();
            digitToLetterMap.put('2',"abc");
            digitToLetterMap.put('3',"def");
            digitToLetterMap.put('4',"ghi");
            digitToLetterMap.put('5',"jkl");
            digitToLetterMap.put('6',"mno");
            digitToLetterMap.put('7',"pqrs");
            digitToLetterMap.put('8',"tuv");
            digitToLetterMap.put('9',"wxyz");
            return letterCombinationHelper(digits,0,digitToLetterMap);
        }

        public ArrayList<String> letterCombinationHelper(String digits,int idx,HashMap<Character,String> digitToLetterMap){
            if(idx==digits.length()){
                ArrayList<String> ans = new ArrayList<>();
                ans.add("");
                return ans;
            }

            ArrayList<String> ansFromBottom = letterCombinationHelper(digits,idx+1,digitToLetterMap);
            ArrayList<String> ansOfThisLevel = new ArrayList<>();
            Character c = digits.charAt(idx);
            String mapping = digitToLetterMap.get(c);
            for(Character key : mapping.toCharArray()){
                for(String stringAns : ansFromBottom){
                    ansOfThisLevel.add(key+stringAns);
                }
            }

            return ansOfThisLevel;
        }

        //leetcode 40
    public static void combinationSum2Helper(int[] candidates,int target, int idx,List<Integer> ansSoFar, List<List<Integer>> completeAns){

        if(target == 0){
            List<Integer> ans = new ArrayList<>(ansSoFar);
            completeAns.add(ans);
            return;
        }

        //Try all numbers
        for(int i=idx;i<candidates.length;i++){
            if(target-candidates[i] >= 0){
                ansSoFar.add(candidates[i]);
                combinationSum2Helper(candidates,target-candidates[i],i+1,ansSoFar,completeAns);
                ansSoFar.remove(ansSoFar.size()-1);
            }
            while(i>=0 && i<candidates.length-1 && candidates[i]==candidates[i+1]){
                i++;
            }
        }
    }

    //leetcode 216
    public void combinationSum3Helper(int k, int n, int idx, List<Integer> answerSoFar, List<List<Integer>> completeAnswer){

        if(k==0){
            if(n==0){
                List<Integer> ans = new ArrayList<>(answerSoFar);
                completeAnswer.add(ans);
            }
            return;
        }

        for(int i=idx;i<=9;i++){
            if(n-i>=0){
                answerSoFar.add(i);
                combinationSum3Helper(k-1,n-i,i+1,answerSoFar,completeAnswer);
                answerSoFar.remove(answerSoFar.size()-1);
            }
        }
    }

    //Leetcode 46
    public static void permuteHelper(int[] nums, int idx, List<Integer> ansSoFar, List<List<Integer>> completeAns) {
        if (idx == nums.length) {
            List<Integer> ans = new ArrayList<>(ansSoFar);
            completeAns.add(ans);
            return;
        }
        for (int i=idx;i < nums.length; i++) {
            int temp = nums[idx];
            nums[idx] = nums[i];
            nums[i] = temp;
            ansSoFar.add(nums[idx]);
            permuteHelper(nums, idx + 1, ansSoFar, completeAns);
            temp = nums[idx];
            nums[idx] = nums[i];
            nums[i] = temp;
            ansSoFar.remove(ansSoFar.size()-1);
        }
    }

    //Leetcode 47
    public static void permuteUniqueHelper(int[] nums, int idx, List<Integer> currentAns, List<List<Integer>> totalAnswer){
        if(idx==nums.length){
            List<Integer> ans = new ArrayList<>(currentAns);
            totalAnswer.add(ans);
            return;
        }

        for(int i=idx;i<nums.length;i++){
            if(idx==0){
                System.out.println("Base Level");
            }
            if(i!=idx && shouldSkip(idx,i,nums)) continue;
            int temp = nums[idx];
            nums[idx] = nums[i];
            nums[i] = temp;
            currentAns.add(nums[idx]);
            permuteUniqueHelper(nums,idx+1,currentAns,totalAnswer);
            currentAns.remove(currentAns.size()-1);
            temp = nums[idx];
            nums[idx] = nums[i];
            nums[i] = temp;
        }

    }

    private static boolean shouldSkip(int idx, int curr, int[] nums){
        for(int i=idx; i<curr;i++){
            if(nums[i]==nums[curr]){
                return true;
            }
        }
        return false;
    }

    //Leetcode 78
    public void subsetsHelper(int[] nums, int idx, List<Integer> answerSoFar, List<List<Integer>> completeAns){

        if(idx==nums.length){
            List<Integer> ans = new ArrayList<>(answerSoFar);
            completeAns.add(ans);
            return;
        }

        //Take
        answerSoFar.add(nums[idx]);
        subsetsHelper(nums,idx+1,answerSoFar,completeAns);
        answerSoFar.remove(answerSoFar.size()-1);
        //Don't Take
        subsetsHelper(nums,idx+1,answerSoFar,completeAns);

    }

    //leetcode 131
    public static void partitionHelper(String s, List<String> step, List<List<String>> result) {
        // Base case
        if (s == null || s.isEmpty()) {
            result.add(new ArrayList<>(step));
            return;
        }
        for (int i = 1; i <= s.length(); i++) {
            String temp = s.substring(0, i);
            if (!isPalindrome(temp))
                continue; // only do backtracking when current string is palindrome

            step.add(temp); // choose
            partitionHelper(s.substring(i, s.length()), step, result); // explore
            step.remove(step.size() - 1); // unchoose
        }
        return;
    }

    public static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }

    //Leetcode 79
    public boolean existsHelper(char[][] board, boolean[][] visited, int r, int c, String word, int idx){

        if(idx == word.length()-1 && word.charAt(idx)==board[r][c]){
            return true;
        }

        boolean up = false;
        boolean right= false;
        boolean down= false;
        boolean left= false;
        visited[r][c] = true;
        if(word.charAt(idx)==board[r][c]){
            //Up
            if(r-1 >= 0 && !visited[r-1][c]){
                up = existsHelper(board,visited,r-1,c,word,idx+1);
            }
            //Right
            if(c+1 < board[0].length  && !visited[r][c+1]){
                right = existsHelper(board,visited,r,c+1,word,idx+1);
            }
            //Down
            if(r+1 < board.length  && !visited[r+1][c]){
                down = existsHelper(board,visited,r+1,c,word,idx+1);
            }
            //Left
            if(c-1 >= 0  && !visited[r][c-1]){
                left = existsHelper(board,visited,r,c-1,word,idx+1);
            }
        }
        visited[r][c]=false;

        return up||right||down||left;
    }

    //Leetcode 344
    public void reverseStringHelper(char[] s, int start, int end){
        if(end < start){
            return;
        }

        char temp = s[end];
        s[end] = s[start];
        s[start] = temp;
        reverseStringHelper(s,start+1,end-1);
    }

    //Leetcode 24
    public ListNode swapPairsHelper(ListNode curr){
        if(curr==null || curr.next==null){
            return curr;
        }

        //Swap
        ListNode remainingList = curr.next.next;
        ListNode first = curr;
        ListNode second = curr.next;
        second.next = first;
        first.next = swapPairsHelper(remainingList);

        return second;
    }

    //Leetcode  206
    private ListNode reverseListHelper(ListNode curr, ListNode newHead) {
        if (curr == null)
            return newHead;

        ListNode leftOverList = curr.next;
        curr.next = newHead;
        return reverseListHelper(leftOverList, curr);
    }

    //Leetcode 700
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null || root.val == val){
            return root;
        }

        TreeNode left = searchBST(root.left,val);
        TreeNode right = searchBST(root.right,val);

        return left == null ? right : left;

    }

    //Leetcode 119 : Pascal's Triangle II
    public List<Integer> getRow(int rowIndex) {
        if(rowIndex == 0){
            List<Integer> ans = new ArrayList<>();
            ans.add(1);
            return ans;
        }

        List<Integer> ansFromBelowCalls = getRow(rowIndex-1);
        List<Integer> currentCallAnswer = new ArrayList<>();
        currentCallAnswer.add(1);
        for(int i=0;i<ansFromBelowCalls.size()-1;i++){
            currentCallAnswer.add(ansFromBelowCalls.get(i)+ansFromBelowCalls.get(i+1));
        }
        currentCallAnswer.add(1);

        return currentCallAnswer;
    }

    //Leetcode 70 (Requires DP)
    public int climbStairs(int n) {
        if(n==0){
            return 1;
        }

        int oneStep=0;
        int twoStep=0;

        if(n-1 >=0)
            oneStep = climbStairs(n-1);
        if(n-2 >=0)
            twoStep = climbStairs(n-2);

        return oneStep+twoStep;
    }

    //Leetcode 104
    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        int currentMaxDepth = Math.max(leftDepth,rightDepth)+1;

        return currentMaxDepth;
    }

    //Leetcode 50
    public double myPowHelper(double x, int n){

        if(n==0){
            return 1;
        }

        double bottomLevelAnswer = myPowHelper(x,n/2);
        double thisLevelAnswer = bottomLevelAnswer*bottomLevelAnswer;
        if(n%2!=0){
            thisLevelAnswer = x*thisLevelAnswer;
        }

        return thisLevelAnswer;

    }

    //Leetcode 779
    public int kthGrammar(int N, int K) {
        if(N==1) return 0;
        if(K%2==0){
            if (kthGrammar(N-1,K/2)==0) return 1;
            else return 0;
        }
        else{
            if(kthGrammar(N-1,(K+1)/2)==0) return 0;
            else return 1;
        }
    }

    //Leetcode 799
    public int kthGrammarMine(int n, int k) {
        if(n==1 && k==1){
            return 0;
        }

        if(k > (int)Math.pow(2,n-2)){
            int newK = k - (int)Math.pow(2,n-2);
            return kthGrammarMine(n-1,newK)^1;
        }else{
            return kthGrammarMine(n-1,k);
        }
    }

    //TODO: Leetcode 95


    //Leetcode 98
    public boolean isValidBSTHelper(TreeNode root){

        if(root==null){
            return true;
        }

        if(root.left == null && root.right==null){
            return true;
        }

        boolean isLeftValid = isValidBSTHelper(root.left);
        boolean isRightValid = isValidBSTHelper(root.right);

        long leftMax = treeMaxValHelper(root.left);
        long rightMin = treeMinValHelper(root.right);
        //System.out.println("["+root.val+","+leftMax+","+rightMin+"]");

        if(leftMax >= root.val || rightMin <= root.val){
            return false;
        }

        return isLeftValid && isRightValid;
    }

    public long treeMaxValHelper(TreeNode root){

        if(root==null){
            return Long.MIN_VALUE;
        }

        if(root.left == null && root.right == null){
            return root.val;
        }

        long leftMax = treeMaxValHelper(root.left);
        long rightMax = treeMaxValHelper(root.right);

        long rootVal = root.val;

        long completeMaxValOfThisTree = Math.max(rootVal,Math.max(leftMax,rightMax));
        return completeMaxValOfThisTree;
    }

    public long treeMinValHelper(TreeNode root){

        if(root==null){
            return Long.MAX_VALUE;
        }

        if(root.left == null && root.right == null){
            return root.val;
        }

        long leftMax = treeMinValHelper(root.left);
        long rightMax = treeMinValHelper(root.right);

        long rootVal = root.val;

        long completeMinValOfThisTree = Math.min(rootVal,Math.min(leftMax,rightMax));
        return completeMinValOfThisTree;
    }

    //Leetcode 111
    public int minDepth(TreeNode root) {
        // Base case...
        // If the subtree is empty i.e. root is NULL, return depth as 0...
        if(root == null)  return 0;
        // Initialize the depth of two subtrees...
        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);
        // If the both subtrees are empty...
        if(root.left == null && root.right == null)
            return 1;
        // If the left subtree is empty, return the depth of right subtree after adding 1 to it...
        if(root.left == null)
            return 1 + rightDepth;
        // If the right subtree is empty, return the depth of left subtree after adding 1 to it...
        if(root.right == null)
            return 1 + leftDepth;
        // When the two child function return its depth...
        // Pick the minimum out of these two subtrees and return this value after adding 1 to it...
        return Math.min(leftDepth, rightDepth) + 1;    // Adding 1 is the current node which is the parent of the two subtrees...
    }

    //Leetcode 2265
    public int averageOfSubtree(TreeNode root) {

        if(root==null){
            return 0;
        }

        if(root.left == null && root.right == null){
            return 1;
        }

        int leftAverageEqual = averageOfSubtree(root.left);
        int rightAverageEqual = averageOfSubtree(root.right);

        int thisNodeSum = sumOfTreeNodes(root);
        int thisNodeCount = countOfTreeNodes(root);
        int thisNodeAverage = thisNodeSum/thisNodeCount;

        // System.out.println(root.val+" "+thisNodeSum+" "+thisNodeCount+" "+thisNodeAverage);

        int thisNodeAverageCount = (thisNodeAverage == root.val) ? (leftAverageEqual+rightAverageEqual)+1 : leftAverageEqual+rightAverageEqual;

        return thisNodeAverageCount;
    }

    public int sumOfTreeNodes(TreeNode root){
        if(root == null){
            return 0;
        }

        int leftSum = sumOfTreeNodes(root.left);
        int rightSum = sumOfTreeNodes(root.right);

        return leftSum + root.val + rightSum;
    }

    public int countOfTreeNodes(TreeNode root){
        if(root == null){
            return 0;
        }

        int leftCount = countOfTreeNodes(root.left);
        int rightCount = countOfTreeNodes(root.right);

        return leftCount + rightCount + 1;
    }

    //Leetcode 654
    public TreeNode constructionHelper(int[] nums, int start, int end){
        if(end<start){
            return null;
        }

        int maxIdx = findMaxIdx(nums,start,end);
        //System.out.println(maxIdx);
        TreeNode currentRoot = new TreeNode(nums[maxIdx]);
        currentRoot.left = constructionHelper(nums,start,maxIdx-1);
        currentRoot.right = constructionHelper(nums,maxIdx+1,end);

        return currentRoot;
    }

    public int findMaxIdx(int[] arr, int start, int end){
        int max = -1;
        int maxIdx = -1;
        for(int i=start;i<=end;i++){
            if(arr[i] > max){
                //System.out.println(arr[i]);
                maxIdx = i;
                max = arr[i];
            }
        }

        return maxIdx;
    }

    public static void main(String[] args) {
        int[][] maze = new int[4][4];
        int[][] dir = new int[][]{{0,1},{1,0},{1,1}}; //R, V and D
        char[] dir_name = new char[]{'R','V','D'};  //Right Vertical Diagonal

        ArrayList<String> allPaths =  new ArrayList<>();
        travelInAMazeParameter(maze,0,0,dir,dir_name,"",allPaths);
        System.out.println(allPaths);
        System.out.println(travelInMazeReturnType(maze,0,0));
        System.out.println(travelInAMazeParameterPathCount(maze,0,0,dir,dir_name));
        maze[1][0] = -1;
        maze[2][1] = -1;
        ArrayList<String> allPathsInBlockedMaze =  new ArrayList<>();
        travelInAMazeWithObstaclesParameter(maze,0,0,dir,dir_name,"",allPathsInBlockedMaze);
        System.out.println(allPathsInBlockedMaze);
        maze[1][0] = 0;
        maze[2][1] = 0;
        int[][] dir_four = new int[][]{{0,-1},{0,1},{-1,0},{1,0}}; //Left, Right, Up, Down
        char[] dir_four_name = new char[]{'L','R','U','D'};
        ArrayList<String> allPathsInFourDirectionalMazeTravel =  new ArrayList<>();
        travelInAMazeParameterAllDirections(maze,0,0,dir_four,dir_four_name,"",allPathsInFourDirectionalMazeTravel);
        System.out.println(allPathsInFourDirectionalMazeTravel.size());
        int[][] path = new int[3][3];
        int[][] newMaze = new int[3][3];
        ArrayList<String> allPathsInFourDirectionalMazeTravelWithPathPrint =  new ArrayList<>();
        travelInAMazeParameterAllDirectionsAndPrintMazePath(newMaze,path,0,0,dir_four,dir_four_name,"",allPathsInFourDirectionalMazeTravelWithPathPrint,1);
        int[][] board = new int[5][5];
        char[][] solution_board = new char[5][5];
        boolean[] col_check = new boolean[5];
        boolean[] up_diagonal_check = new boolean[9];
        boolean[] down_diagonal_check = new boolean[9];
        for(char[] row : solution_board){
            Arrays.fill(row,'-');
        }
        N_Queen(board,0,5,solution_board,col_check,up_diagonal_check,down_diagonal_check);
        int[][] sudoku_board = new int[9][9];
        sudoku_board[0] = new int[]{3, 0, 6, 5, 0, 8, 4, 0, 0};
        sudoku_board[1] = new int[]{5, 2, 0, 0, 0, 0, 0, 0, 0};
        sudoku_board[2] = new int[]{0, 8, 7, 0, 0, 0, 0, 3, 1};
        sudoku_board[3] = new int[]{0, 0, 3, 0, 1, 0, 0, 8, 0};
        sudoku_board[4] = new int[]{9, 0, 0, 8, 6, 3, 0, 0, 5};
        sudoku_board[5] = new int[]{0, 5, 0, 0, 9, 0, 6, 0, 0};
        sudoku_board[6] = new int[]{1, 3, 0, 0, 0, 0, 2, 5, 0};
        sudoku_board[7] = new int[]{0, 0, 0, 0, 0, 0, 0, 7, 4};
        sudoku_board[8] = new int[]{0, 0, 5, 2, 0, 6, 3, 0, 0};
        sudokuSolver(sudoku_board,0,0);
        List<List<Integer>> totalAnswer = new ArrayList<>();
        List<Integer> currentAns = new ArrayList<>();
        combinationSumHelper(new int[]{2,3,6,7},7,currentAns,totalAnswer);
        System.out.println(totalAnswer);
        List<List<Integer>> totalAnswerSum2Helper = new ArrayList<>();
        List<Integer> currentAnsSum2Helper = new ArrayList<>();
        int[] combinationSum2HelperArray = new int[]{10,1,2,7,6,1,5};
        Arrays.sort(combinationSum2HelperArray);
        combinationSum2Helper(combinationSum2HelperArray,8,0,currentAnsSum2Helper,totalAnswerSum2Helper);
        System.out.println(totalAnswerSum2Helper);
        List<List<Integer>> totalAnswerpermuteHelper = new ArrayList<>();
        List<Integer> currentAnspermuteHelper = new ArrayList<>();
        permuteHelper(new int[]{1,2,3}, 0, currentAnspermuteHelper, totalAnswerpermuteHelper);
        System.out.println(totalAnswerpermuteHelper);
        List<List<Integer>> totalAnswerpermuteUniqueHelper = new ArrayList<>();
        List<Integer> currentAnspermuteUniqueHelper = new ArrayList<>();
        permuteUniqueHelper(new int[]{1,1,2,2},0,currentAnspermuteUniqueHelper,totalAnswerpermuteUniqueHelper);
        System.out.println(totalAnswerpermuteUniqueHelper);
        List<List<String>> totalAnswerPartitionHelper = new ArrayList<>();
        List<String> currentAnsPartitionHelper = new ArrayList<>();
        partitionHelper("aabccd",currentAnsPartitionHelper,totalAnswerPartitionHelper);
        System.out.println(totalAnswerPartitionHelper);
    }

}
