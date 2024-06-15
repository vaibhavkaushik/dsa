package Graph.ShortestPaths;

import java.util.Arrays;

public class SimpleFloydWarshall {
    private int numVertices;
    private int[][] dist;

    public SimpleFloydWarshall(int numVertices) {
        this.numVertices = numVertices;
        dist = new int[numVertices][numVertices];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        for (int i = 0; i < numVertices; i++) {
            dist[i][i] = 0;
        }
    }

    public void addEdge(int u, int v, int weight) {
        dist[u][v] = weight;
    }

    public void floydWarshall() {
        for (int k = 0; k < numVertices; k++) {
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE
                            && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (dist[i][j] == Integer.MAX_VALUE) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SimpleFloydWarshall g = new SimpleFloydWarshall(4);

        g.addEdge(0, 1, 3);
        g.addEdge(0, 2, 5);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 3, 2);
        g.addEdge(3, 1, 1);

        g.floydWarshall();
    }
}

