package Graph.CycleDetection;

import java.util.*;

public class UnionFindCycleDetection {
    private int numVertices;
    private List<int[]> edges = new ArrayList<>();

    // Constructor to initialize the graph
    public UnionFindCycleDetection(int numVertices) {
        this.numVertices = numVertices;
    }

    // Add an edge to the graph
    public void addEdge(int u, int v) {
        edges.add(new int[]{u, v});
    }

    // Find method for Union-Find
    private int find(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = find(parent, parent[i]);
        }
        return parent[i];
    }

    // Union method for Union-Find
    private void union(int[] parent, int[] rank, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);

        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    // Method to detect cycle using Union-Find
    public boolean isCyclic() {
        int[] parent = new int[numVertices];
        int[] rank = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            int rootU = find(parent, u);
            int rootV = find(parent, v);

            if (rootU == rootV) {
                return true; // Cycle detected
            }

            union(parent, rank, rootU, rootV);
        }

        return false; // No cycle detected
    }

    public static void main(String[] args) {
        UnionFindCycleDetection g = new UnionFindCycleDetection(4);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 0);

        if (g.isCyclic()) {
            System.out.println("Graph contains cycle");
        } else {
            System.out.println("Graph doesn't contain cycle");
        }
    }

    //Leetcode 997
    // Method to find the town judge
    public int findJudge(int n, int[][] trust) {
        if (n == 1) return 1; // Agar sirf ek hi insaan hai, wohi judge hoga

        // Trusts aur trustedBy arrays initialize karte hain
        int[] trusts = new int[n + 1];
        int[] trustedBy = new int[n + 1];

        // Trust array ko process karte hain
        for (int[] t : trust) {
            trusts[t[0]]++; // t[0] kisi ko trust karta hai
            trustedBy[t[1]]++; // t[1] ko koi trust karta hai
        }

        // Town judge ko dhoondte hain
        for (int i = 1; i <= n; i++) {
            // Agar i kisi ko trust nahi karta aur sab log use trust karte hain to woh judge hai
            if (trusts[i] == 0 && trustedBy[i] == n - 1) {
                return i;
            }
        }

        // Agar judge nahi milta, return -1
        return -1;
    }

    //Leetcode 1557
    // Method to find the minimum number of vertices to reach all nodes
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {

        // Indegree array initialize karte hain
        int[] indegree = new int[n];

        // Edges ko process karte hain
        for (List<Integer> edge : edges) {
            indegree[edge.get(1)]++; // Destination node ka indegree increment karte hain
        }

        // Result list initialize karte hain
        List<Integer> result = new ArrayList<>();

        // Jitne bhi nodes ka indegree 0 hai unhe result mein add karte hain
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                result.add(i);
            }
        }

        return result;
    }

    //Leetcode 841
    // Method to check if we can visit all rooms using DFS
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        // Visited array initialize karte hain
        boolean[] visited = new boolean[rooms.size()];
        // DFS call karte hain room 0 se
        dfs(0, rooms, visited);

        // Sabhi rooms ko check karte hain agar sab visit hue hain ya nahi
        for (boolean roomVisited : visited) {
            if (!roomVisited) {
                return false;
            }
        }

        return true;
    }

    // DFS method jo rooms ko visit karta hai
    private void dfs(int room, List<List<Integer>> rooms, boolean[] visited) {
        visited[room] = true; // Current room ko visit mark karte hain

        // Sabhi keys (rooms) ko check karte hain jo current room mein hain
        for (int key : rooms.get(room)) {
            if (!visited[key]) {
                dfs(key, rooms, visited); // DFS call karte hain next room ke liye
            }
        }
    }
}
