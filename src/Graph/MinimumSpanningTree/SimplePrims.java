package Graph.MinimumSpanningTree;

import java.util.*;

public class SimplePrims {
    private int numVertices;
    private List<List<int[]>> adjList;

    // Constructor to initialize the graph
    public SimplePrims(int numVertices) {
        this.numVertices = numVertices;
        adjList = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    // Add an edge to the graph
    public void addEdge(int u, int v, int weight) {
        adjList.get(u).add(new int[]{v, weight});
        adjList.get(v).add(new int[]{u, weight}); // Undirected graph
    }

    // Prim's algorithm to find the MST
    public void primMST() {
        boolean[] inMST = new boolean[numVertices];
        int[] key = new int[numVertices];
        int[] parent = new int[numVertices];
        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        parent[0] = -1;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.add(new int[]{0, key[0]});

        while (!pq.isEmpty()) {
            int u = pq.poll()[0];
            inMST[u] = true;

            for (int[] neighbor : adjList.get(u)) {
                int v = neighbor[0];
                int weight = neighbor[1];

                if (!inMST[v] && weight < key[v]) {
                    key[v] = weight;
                    pq.add(new int[]{v, key[v]});
                    parent[v] = u;
                }
            }
        }

        // Print the MST
        for (int i = 1; i < numVertices; i++) {
            System.out.println("Edge: " + parent[i] + " - " + i + " weight: " + key[i]);
        }
    }

    public static void main(String[] args) {
        SimplePrims g = new SimplePrims(5);
        g.addEdge(0, 1, 2);
        g.addEdge(0, 3, 6);
        g.addEdge(1, 2, 3);
        g.addEdge(1, 3, 8);
        g.addEdge(1, 4, 5);
        g.addEdge(2, 4, 7);
        g.addEdge(3, 4, 9);

        g.primMST();
    }
}

