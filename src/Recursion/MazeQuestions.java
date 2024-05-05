package Recursion;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

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

    private static int travelInAMazeParameterPathCount(int[][] maze, int r, int c, int[][] dir, char[] dir_name){

        //Once we reach at the end, let's store the answer
        if(r==maze.length-1 && c== maze[0].length-1){
            return 1;
        }

        int count = 0;
        for(int i=0;i<dir_name.length;i++) {
            int row = r + dir[i][0];
            int col = c + dir[i][1];
            if (row < maze.length && col < maze[0].length) {
                count += travelInAMazeParameterPathCount(maze, row, col, dir, dir_name);
            }
        }

        return count;
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

    private static void travelInAMazeWithObstaclesParameter(int[][] maze, int r, int c, int[][] dir, char[] dir_name, String ans, ArrayList<String> allAns){

        //Once we reach at the end, let's store the answer
        if(r==maze.length-1 && c== maze[0].length-1){
            allAns.add(ans);
            return;
        }

        for(int i=0;i<dir_name.length;i++) {
            int row = r + dir[i][0];
            int col = c + dir[i][1];
            if (row < maze.length && col < maze[0].length && maze[row][col]!=-1) {
                travelInAMazeWithObstaclesParameter(maze, row, col, dir, dir_name, ans + dir_name[i], allAns);
            }
        }
    }

    private static void travelInAMazeParameterAllDirections(int[][] maze, int r, int c, int[][] dir, char[] dir_name, String ans, ArrayList<String> allAns){

        //Once we reach at the end, let's store the answer
        if(r==maze.length-1 && c== maze[0].length-1){
            allAns.add(ans);
            return;
        }

        for(int i=0;i<dir_name.length;i++) {
            int row = r + dir[i][0];
            int col = c + dir[i][1];
            if (row >= 0 && col >= 0 && row < maze.length && col < maze[0].length && maze[row][col]!=-1) {
                maze[r][c] = -1;
                travelInAMazeParameterAllDirections(maze, row, col, dir, dir_name, ans + dir_name[i], allAns);
                maze[r][c] = 0;
            }
        }
    }

    private static void travelInAMazeParameterAllDirectionsAndPrintMazePath(int[][] maze, int[][]path, int r, int c, int[][] dir, char[] dir_name, String ans, ArrayList<String> allAns, int level){

        //Once we reach at the end, let's store the answer
        if(r==maze.length-1 && c== maze[0].length-1){
            path[r][c] = level;
            for(int[] onePath : path){
                System.out.println(Arrays.toString(onePath));
            }
            System.out.println(ans);
            allAns.add(ans);
            return;
        }

        for(int i=0;i<dir_name.length;i++) {
            int row = r + dir[i][0];
            int col = c + dir[i][1];
            if (row >= 0 && col >= 0 && row < maze.length && col < maze[0].length && maze[row][col]!=-1) {
                maze[r][c] = -1;
                path[r][c] = level;
                travelInAMazeParameterAllDirectionsAndPrintMazePath(maze,path, row, col, dir, dir_name, ans + dir_name[i], allAns,level+1);
                maze[r][c] = 0;
                path[r][c] = 0;
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

    }

}
