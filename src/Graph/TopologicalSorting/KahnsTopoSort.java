package Graph.TopologicalSorting;

import java.util.*;

public class KahnsTopoSort {
    private int numVertices;
    private List<List<Integer>> adjList;

    // Constructor to initialize the graph
    public KahnsTopoSort(int numVertices) {
        this.numVertices = numVertices;
        adjList = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    // Add an edge to the graph
    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
    }

    // Kahn's algorithm to perform topological sort
    public List<Integer> topologicalSort() {
        int[] inDegree = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            for (int neighbor : adjList.get(i)) {
                inDegree[neighbor]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numVertices; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> topoOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            topoOrder.add(u);

            for (int neighbor : adjList.get(u)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        if (topoOrder.size() != numVertices) {
            throw new RuntimeException("Graph has a cycle, topological sort not possible");
        }

        return topoOrder;
    }

    public static void main(String[] args) {
        KahnsTopoSort g = new KahnsTopoSort(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        List<Integer> topoOrder = g.topologicalSort();
        System.out.println("Topological Sort: " + topoOrder);
    }
}

