package Graph.ShortestPaths;

import java.util.Arrays;

public class SimpleFloydWarshall {
    private int numVertices;
    private int[][] dist;

    // Constructor to create a graph with n vertices
    public SimpleFloydWarshall(int numVertices) {
        this.numVertices = numVertices;
        dist = new int[numVertices][numVertices];

        // Initialize the distance table
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE); // Sab distances ko infinity se fill kar do
        }

        // Distance from a vertex to itself is always 0
        for (int i = 0; i < numVertices; i++) {
            dist[i][i] = 0;
        }
    }

    // Add an edge between two vertices with weight
    public void addEdge(int u, int v, int weight) {
        dist[u][v] = weight; // Direct road ki duri likh do
    }

    // Floyd-Warshall algorithm to find shortest paths between all pairs of vertices
    public void floydWarshall() {
        // Check har location ke beech short cut dhoondho
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                for (int k = 0; k < numVertices; k++) {
                    // Agar i se k aur k se j ka raasta chhota hai to usko update karo
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE
                            && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Sab shortest distances print kar do
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (dist[i][j] == Integer.MAX_VALUE) {
                    System.out.print("INF "); // Agar infinity hai to "INF" print karo
                } else {
                    System.out.print(dist[i][j] + " "); // Distance print karo
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SimpleFloydWarshall g = new SimpleFloydWarshall(4);

        // Roads (edges) ko add karo
        g.addEdge(0, 1, 3);
        g.addEdge(0, 2, 5);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 3, 2);
        g.addEdge(3, 1, 1);

        // Shortest paths find karo aur print karo
        g.floydWarshall();
    }
}


