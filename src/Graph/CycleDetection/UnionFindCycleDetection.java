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
}
