package Graph;

import java.util.*;

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

    // Method to find the minimum cost to connect all points
    //Leetcode 1584
    public int minCostConnectPoints(int[][] points) {

        // List to store all edges {u, v, weight}
        List<int[]> edges = new ArrayList<>();

        // Calculate the distance between all pairs of points and store as edges
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int dist = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges.add(new int[]{i, j, dist});
            }
        }

        // Sort the edges based on their weight (distance)
        Collections.sort(edges, Comparator.comparingInt(val -> val[2]));

        // Initialize Union-Find structure
        int[] parent = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            parent[i] = i; // Initially, every node is its own parent
        }

        int totalCost = 0; // Variable to store the total cost of connecting all points

        // Iterate through the sorted edges
        for (int[] edge : edges) {

            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];

            int xParent = findParent(u, parent);
            int yParent = findParent(v, parent);

            // If u and v are in different components, connect them
            if (xParent != yParent) {
                parent[xParent] = yParent; // Union operation
                totalCost += weight; // Add the weight to the total cost
            }
        }

        return totalCost; // Return the total cost
    }

    // Find the root parent of x with path compression
    int findParent(int x, int[] parent) {
        if (parent[x] != x) {
            parent[x] = findParent(parent[x], parent);
        }
        return parent[x];
    }


}
