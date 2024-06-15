package Graph;

import java.util.*;

public class DjikstraUsingPriorityQueue {

    public static void dijkstra(Map<Integer, List<int[]>> graph, int source) {
        int n = graph.size();  // Nodes ki sankhya
        int[] dist = new int[n];  // Shortest distances ko track karne ke liye array
        Arrays.fill(dist, Integer.MAX_VALUE);  // Initial mein sab distances infinity set karte hain
        dist[source] = 0;  // Source ka distance 0 hai

        boolean[] visited = new boolean[n];  // Visited nodes ko track karne ke liye

        // Priority Queue jo sabse chhota distance wale node ko pick karegi
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{source, 0});  // Start source node se

        while (!pq.isEmpty()) {
            int[] current = pq.poll();  // Sabse chhota distance wala node
            int u = current[0];  // Current node
            if (visited[u]) continue;  // Agar pehle hi visit kar chuke hain, to skip karo
            visited[u] = true;

            // Current node ke sab neighbors explore karo
            for (int[] neighbor : graph.getOrDefault(u, new ArrayList<>())) {
                int v = neighbor[0];  // Neighbor node
                int weight = neighbor[1];  // Edge ka weight (u, v)
                if (!visited[v] && dist[u] + weight < dist[v]) {  // Agar neighbor visited nahi aur shorter path mila
                    dist[v] = dist[u] + weight;  // Distance update karo
                    pq.offer(new int[]{v, dist[v]});  // Neighbor ko queue mein add karo
                }
            }
        }

        // Source se sab nodes ke shortest distances print karo
        System.out.println("Vertex \t Distance from Source");
        for (int i = 0; i < n; i++) {
            System.out.println(i + " \t\t " + dist[i]);
        }
    }

    public static void main(String[] args) {
        // Graph ko adjacency list ke roop mein represent karte hain
        Map<Integer, List<int[]>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(new int[]{1, 4}, new int[]{7, 8}));
        graph.put(1, Arrays.asList(new int[]{0, 4}, new int[]{2, 8}, new int[]{7, 11}));
        graph.put(2, Arrays.asList(new int[]{1, 8}, new int[]{3, 7}, new int[]{8, 2}, new int[]{5, 4}));
        graph.put(3, Arrays.asList(new int[]{2, 7}, new int[]{4, 9}, new int[]{5, 14}));
        graph.put(4, Arrays.asList(new int[]{3, 9}, new int[]{5, 10}));
        graph.put(5, Arrays.asList(new int[]{4, 10}, new int[]{3, 14}, new int[]{2, 4}, new int[]{6, 2}));
        graph.put(6, Arrays.asList(new int[]{5, 2}, new int[]{7, 1}, new int[]{8, 6}));
        graph.put(7, Arrays.asList(new int[]{0, 8}, new int[]{1, 11}, new int[]{6, 1}, new int[]{8, 7}));
        graph.put(8, Arrays.asList(new int[]{2, 2}, new int[]{6, 6}, new int[]{7, 7}));

        int source = 0;  // Starting point
        dijkstra(graph, source);  // Algorithm ko run karo
    }

}
