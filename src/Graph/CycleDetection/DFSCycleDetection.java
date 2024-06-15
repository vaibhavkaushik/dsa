package Graph.CycleDetection;

import java.util.*;

public class DFSCycleDetection {
    private int numVertices;
    private List<List<Integer>> adjList;
    private boolean[] visited;
    private boolean[] recStack;

    // Constructor to initialize the graph
    public DFSCycleDetection(int numVertices) {
        this.numVertices = numVertices;
        adjList = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }
        visited = new boolean[numVertices];
        recStack = new boolean[numVertices];
    }

    // Add an edge to the graph
    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
    }

    // Utility method for DFS-based cycle detection
    private boolean isCyclicUtil(int v) {
        if (recStack[v]) {
            return true; // Back edge found, cycle detected
        }
        if (visited[v]) {
            return false; // Already visited, no cycle
        }

        visited[v] = true;
        recStack[v] = true;

        for (Integer neighbor : adjList.get(v)) {
            if (isCyclicUtil(neighbor)) {
                return true;
            }
        }

        recStack[v] = false;
        return false;
    }

    // Method to detect cycle using DFS
    public boolean isCyclic() {
        for (int i = 0; i < numVertices; i++) {
            if (isCyclicUtil(i)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        DFSCycleDetection g = new DFSCycleDetection(4);
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
