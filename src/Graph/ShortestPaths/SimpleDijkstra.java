package Graph.ShortestPaths;

import java.util.*;

public class SimpleDijkstra {
    private int numVertices;
    private List<List<int[]>> adjList; // Each int[] is {neighbor, weight}

    public SimpleDijkstra(int numVertices) {
        this.numVertices = numVertices;
        adjList = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v, int weight) {
        adjList.get(u).add(new int[]{v, weight});
        adjList.get(v).add(new int[]{u, weight}); // Undirected graph
    }

    public void dijkstra(int src) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));
        int[] dist = new int[numVertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        pq.add(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int u = node[0];
            int currentDist = node[1];

            if (currentDist > dist[u]) continue; // Skip if a better path has already been found

            for (int[] neighbor : adjList.get(u)) {
                int v = neighbor[0];
                int weight = neighbor[1];

                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }

        for (int i = 0; i < numVertices; i++) {
            System.out.println("Vertex " + i + " Distance from Source " + src + " = " + dist[i]);
        }
    }

    public static void main(String[] args) {
        SimpleDijkstra g = new SimpleDijkstra(5);

        g.addEdge(0, 1, 4);
        g.addEdge(0, 2, 1);
        g.addEdge(2, 1, 2);
        g.addEdge(1, 3, 1);
        g.addEdge(2, 3, 5);
        g.addEdge(3, 4, 3);

        g.dijkstra(0);
    }
}

