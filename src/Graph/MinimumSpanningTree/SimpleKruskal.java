package Graph.MinimumSpanningTree;

import java.util.*;

public class SimpleKruskal {
    private int numVertices;
    private List<int[]> edges = new ArrayList<>();

    // Constructor to initialize the graph
    public SimpleKruskal(int numVertices) {
        this.numVertices = numVertices;
    }

    // Add an edge to the graph
    public void addEdge(int u, int v, int weight) {
        edges.add(new int[]{u, v, weight});
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

    // Kruskal's algorithm to find the MST
    public void kruskalMST() {
        Collections.sort(edges, Comparator.comparingInt(o -> o[2]));

        int[] parent = new int[numVertices];
        int[] rank = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        List<int[]> mst = new ArrayList<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];

            int rootU = find(parent, u);
            int rootV = find(parent, v);

            if (rootU != rootV) {
                mst.add(edge);
                union(parent, rank, rootU, rootV);
            }
        }

        // Print the MST
        for (int[] edge : mst) {
            System.out.println("Edge: " + edge[0] + " - " + edge[1] + " weight: " + edge[2]);
        }
    }

    public static void main(String[] args) {
        SimpleKruskal g = new SimpleKruskal(5);
        g.addEdge(0, 1, 2);
        g.addEdge(0, 3, 6);
        g.addEdge(1, 2, 3);
        g.addEdge(1, 3, 8);
        g.addEdge(1, 4, 5);
        g.addEdge(2, 4, 7);
        g.addEdge(3, 4, 9);

        g.kruskalMST();
    }
}
