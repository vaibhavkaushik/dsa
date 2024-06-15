package Graph.ShortestPaths;

import java.util.Arrays;

public class SimpleBellmanFord {
    private int numVertices;
    private int[][] edges; // Each edge is {u, v, weight}
    private int edgeCount;

    public SimpleBellmanFord(int numVertices, int maxEdges) {
        this.numVertices = numVertices;
        edges = new int[maxEdges][3];
        edgeCount = 0;
    }

    public void addEdge(int u, int v, int weight) {
        edges[edgeCount++] = new int[]{u, v, weight};
    }

    public void bellmanFord(int src) {
        int[] dist = new int[numVertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int i = 0; i < numVertices - 1; i++) {
            for (int j = 0; j < edgeCount; j++) {
                int u = edges[j][0];
                int v = edges[j][1];
                int weight = edges[j][2];
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }

        // Check for negative-weight cycles
        for (int j = 0; j < edgeCount; j++) {
            int u = edges[j][0];
            int v = edges[j][1];
            int weight = edges[j][2];
            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                System.out.println("Graph contains a negative-weight cycle");
                return;
            }
        }

        for (int i = 0; i < numVertices; i++) {
            System.out.println("Vertex " + i + " Distance from Source " + src + " = " + dist[i]);
        }
    }

    public static void main(String[] args) {
        SimpleBellmanFord g = new SimpleBellmanFord(5, 8);

        g.addEdge(0, 1, -1);
        g.addEdge(0, 2, 4);
        g.addEdge(1, 2, 3);
        g.addEdge(1, 3, 2);
        g.addEdge(1, 4, 2);
        g.addEdge(3, 2, 5);
        g.addEdge(3, 1, 1);
        g.addEdge(4, 3, -3);

        g.bellmanFord(0);
    }
}

