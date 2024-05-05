package Recursion;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class MazeQuestions {

    //go from 0,0 to 4,4
    // with directions like U,D,R,L
    private static void travelInAMazeParameter(int[][] maze, int r, int c, int[][] dir, char[] dir_name, String ans, ArrayList<String> allAns){

        //Once we reach at the end, let's store the answer
        if(r==maze.length-1 && c== maze[0].length-1){
            allAns.add(ans);
            return;
        }

        for(int i=0;i<dir_name.length;i++) {
            int row = r + dir[i][0];
            int col = c + dir[i][1];
            if (row < maze.length && col < maze[0].length) {
                travelInAMazeParameter(maze, row, col, dir, dir_name, ans + dir_name[i], allAns);
            }
        }
    }

    private static ArrayList<String> travelInMazeReturnType(int[][] arr, int r, int c){

        if(r == arr.length-1 && c == arr[0].length-1){
            ArrayList<String> baseCase = new ArrayList<>();
            baseCase.add("");
            return baseCase;
        }

        ArrayList<String> finalAnswer = new ArrayList<>();

        if(c+1 < arr[0].length) {
            ArrayList<String> bottomAnswersForRightPath = travelInMazeReturnType(arr, r, c + 1);

            for(String rightPath : bottomAnswersForRightPath){
                finalAnswer.add('R'+rightPath);
            }

        }
        if(r+1 < arr.length) {
            ArrayList<String> bottomAnswersForDownPath = travelInMazeReturnType(arr, r + 1, c);

            for(String downPath : bottomAnswersForDownPath){
                finalAnswer.add('D'+downPath);
            }
        }

        return finalAnswer;
    }

    public static void main(String[] args) {
        int[][] maze = new int[3][3];
        int[][] dir = new int[][]{{0,1},{1,0},{1,1}}; //R and D
        char[] dir_name = new char[]{'R','N','D'};  //Right Neeche Diagonal

        ArrayList<String> allPaths =  new ArrayList<>();
        travelInAMazeParameter(maze,0,0,dir,dir_name,"",allPaths);
        System.out.println(allPaths);
        System.out.println(travelInMazeReturnType(maze,0,0));
    }

}
