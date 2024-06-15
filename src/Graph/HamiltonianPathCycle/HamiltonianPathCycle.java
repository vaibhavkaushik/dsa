package Graph.HamiltonianPathCycle;

import java.util.*;

public class HamiltonianPathCycle {
    private int numVertices;
    private List<List<Integer>> adjList;

    // Constructor to initialize the graph
    public HamiltonianPathCycle(int numVertices) {
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

    // Utility method to check if the graph contains Hamiltonian Path
    private boolean isHamiltonianPathUtil(int v, boolean[] visited, List<Integer> path) {
        if (path.size() == numVertices) {
            return true;
        }

        for (int neighbor : adjList.get(v)) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                path.add(neighbor);
                if (isHamiltonianPathUtil(neighbor, visited, path)) {
                    return true;
                }
                visited[neighbor] = false;
                path.remove(path.size() - 1);
            }
        }
        return false;
    }

    // Method to check if the graph contains Hamiltonian Path
    public boolean isHamiltonianPath() {
        boolean[] visited = new boolean[numVertices];
        List<Integer> path = new ArrayList<>();

        for (int i = 0; i < numVertices; i++) {
            visited[i] = true;
            path.add(i);
            if (isHamiltonianPathUtil(i, visited, path)) {
                System.out.println("Hamiltonian Path: " + path);
                return true;
            }
            visited[i] = false;
            path.remove(path.size() - 1);
        }
        return false;
    }

    // Utility method to check if the graph contains Hamiltonian Cycle
    private boolean isHamiltonianCycleUtil(int v, boolean[] visited, List<Integer> path) {
        if (path.size() == numVertices) {
            return adjList.get(v).contains(path.get(0));
        }

        for (int neighbor : adjList.get(v)) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                path.add(neighbor);
                if (isHamiltonianCycleUtil(neighbor, visited, path)) {
                    return true;
                }
                visited[neighbor] = false;
                path.remove(path.size() - 1);
            }
        }
        return false;
    }

    // Method to check if the graph contains Hamiltonian Cycle
    public boolean isHamiltonianCycle() {
        boolean[] visited = new boolean[numVertices];
        List<Integer> path = new ArrayList<>();

        for (int i = 0; i < numVertices; i++) {
            visited[i] = true;
            path.add(i);
            if (isHamiltonianCycleUtil(i, visited, path)) {
                System.out.println("Hamiltonian Cycle: " + path);
                return true;
            }
            visited[i] = false;
            path.remove(path.size() - 1);
        }
        return false;
    }

    public static void main(String[] args) {
        HamiltonianPathCycle g = new HamiltonianPathCycle(5);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 0);

        if (!g.isHamiltonianPath()) {
            System.out.println("Graph does not contain a Hamiltonian Path");
        }

        if (!g.isHamiltonianCycle()) {
            System.out.println("Graph does not contain a Hamiltonian Cycle");
        }
    }
}

