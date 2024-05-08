package Recursion;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MazeQuestions {

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
    }

}
