package Recursion;


import java.util.ArrayList;

public class MazeQuestions {

    //go from 0,0 to 4,4
    // with directions like U,D,R,L
    private static void travelInAMaze(int[][] maze, int r, int c, int[][] dir, char[] dir_name, String ans, ArrayList<String> allAns){

        //Once we reach at the end, let's store the answer
        if(r==maze.length-1 && c== maze[0].length-1){
            allAns.add(ans);
            return;
        }

        for(int i=0;i<dir_name.length;i++) {
            int row = r + dir[i][0];
            int col = c + dir[i][1];
            if (row < maze.length && col < maze[0].length) {
                travelInAMaze(maze, row, col, dir, dir_name, ans + dir_name[i], allAns);
            }
        }
    }

    public static void main(String[] args) {
        int[][] maze = new int[5][5];
        int[][] dir = new int[][]{{0,1},{1,0}}; //R and D
        char[] dir_name = new char[]{'R','D'};

        ArrayList<String> allPaths =  new ArrayList<>();
        travelInAMaze(maze,0,0,dir,dir_name,"",allPaths);
        System.out.println(allPaths);
    }

}
