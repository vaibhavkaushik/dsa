package Graph.Bipartite;

import java.util.*;

public class BipartiteCheckBFS {
    private int numVertices;
    private List<List<Integer>> adjList;

    // Constructor to initialize the graph
    public BipartiteCheckBFS(int numVertices) {
        this.numVertices = numVertices;
        adjList = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    // Add an edge to the graph
    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u); // Undirected graph
    }

    // Method to check if graph is bipartite using BFS
    public boolean isBipartite() {
        int[] color = new int[numVertices];
        Arrays.fill(color, -1);

        for (int i = 0; i < numVertices; i++) {
            if (color[i] == -1) {
                if (!bfsCheck(i, color)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Utility method for BFS to check bipartite
    private boolean bfsCheck(int start, int[] color) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        color[start] = 0;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v : adjList.get(u)) {
                if (color[v] == -1) {
                    color[v] = 1 - color[u];
                    queue.add(v);
                } else if (color[v] == color[u]) {
                    return false; // If two adjacent nodes have the same color, it's not bipartite
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        BipartiteCheckBFS g = new BipartiteCheckBFS(5);
        g.addEdge(0, 1);
        g.addEdge(0, 3);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(2, 4);

        if (g.isBipartite()) {
            System.out.println("Graph is Bipartite");
        } else {
            System.out.println("Graph is not Bipartite");
        }
    }
}

