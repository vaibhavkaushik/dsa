package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphQuestions {

    //Leetcode 1791
    public int findCenter(int[][] edges) {
        // vertices calculate karenge
        int vertices = edges.length + 1;

        // adjacency list banate hain
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<>());
        }

        // edges ko adjacency list mein add karenge
        for (int[] edge : edges) {
            adj.get(edge[0] - 1).add(edge[1] - 1);
            adj.get(edge[1] - 1).add(edge[0] - 1);
        }

        // node ko 1 se start karenge
        int node = 1;
        for (List<Integer> neighbours : adj) {
            // agar neighbours ki size edges ke barabar hai, to ye center node hai
            if (neighbours.size() == edges.length) {
                return node;
            }
            node++;
        }

        // agar center node nahi milta, to -1 return karenge
        return -1;
    }


    //Leetcode 1971
    // Graph path ko valid check karne ka method
    public boolean validPath(int n, int[][] edges, int source, int destination) {

        // Total vertices ki sankhya
        int vertices = n;

        // Adjacency list banate hain
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<>());
        }

        // Graph mein edges ko adjacency list mein add karte hain
        for(int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        // Visited array initialize karte hain
        boolean[] visited = new boolean[n];

        // BFS call karke path check karte hain
        return BFS(source, destination, visited, adj);
    }

    // BFS method jo source se destination tak path check karta hai
    public boolean BFS(int source, int destination, boolean[] visited, List<List<Integer>> adj) {

        // Agar source hi destination hai to return true
        if(source == destination) {
            return true;
        }

        // BFS ke liye queue banate hain
        Queue<Integer> q = new LinkedList<>();
        q.offer(source);
        visited[source] = true;

        // Jab tak queue empty na ho, BFS perform karte hain
        while(!q.isEmpty()) {

            // Queue se node nikalte hain
            int node = q.poll();

            // Har neighbor ko check karte hain
            for(int neighbour : adj.get(node)) {

                // Agar neighbor visit nahi hua to process karte hain
                if(!visited[neighbour]) {

                    visited[neighbour] = true;

                    // Agar neighbor destination hai to return true
                    if(neighbour == destination) {
                        return true;
                    }

                    // Neighbor ko queue mein add karte hain
                    q.offer(neighbour);
                }
            }
        }

        // Agar path nahi milta to return false
        return false;
    }


}
