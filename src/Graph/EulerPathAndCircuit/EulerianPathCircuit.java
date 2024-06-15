package Graph.EulerPathAndCircuit;

import java.util.*;

public class EulerianPathCircuit {
    private int numVertices;
    private List<List<Integer>> adjList;

    // Constructor to initialize the graph
    public EulerianPathCircuit(int numVertices) {
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

    // Method to check if the graph is Eulerian
    public String isEulerian() {
        if (!isConnected()) {
            return "Graph is not Eulerian";
        }

        int oddCount = 0;
        for (List<Integer> neighbors : adjList) {
            if (neighbors.size() % 2 != 0) {
                oddCount++;
            }
        }

        if (oddCount == 0) {
            return "Graph has an Eulerian Circuit";
        } else if (oddCount == 2) {
            return "Graph has an Eulerian Path";
        } else {
            return "Graph is not Eulerian";
        }
    }

    // Method to check if the graph is connected
    private boolean isConnected() {
        boolean[] visited = new boolean[numVertices];
        int startVertex = -1;

        // Find a vertex with non-zero degree to start the DFS
        for (int i = 0; i < numVertices; i++) {
            if (!adjList.get(i).isEmpty()) {
                startVertex = i;
                break;
            }
        }

        // Perform DFS
        dfs(startVertex, visited);

        // Check if all non-zero degree vertices are visited
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i] && !adjList.get(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    // DFS utility to check connectivity
    private void dfs(int v, boolean[] visited) {
        visited[v] = true;
        for (int neighbor : adjList.get(v)) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited);
            }
        }
    }

    // Hierholzer's algorithm to find Eulerian Path or Circuit
    public void printEulerianPathOrCircuit() {
        Stack<Integer> stack = new Stack<>();
        List<Integer> path = new ArrayList<>();
        int startVertex = 0;

        // Start from a vertex with an odd degree if it exists
        for (int i = 0; i < numVertices; i++) {
            if (adjList.get(i).size() % 2 != 0) {
                startVertex = i;
                break;
            }
        }

        stack.push(startVertex);
        while (!stack.isEmpty()) {
            int u = stack.peek();
            if (!adjList.get(u).isEmpty()) {
                int v = adjList.get(u).remove(0);
                adjList.get(v).remove(Integer.valueOf(u));
                stack.push(v);
            } else {
                path.add(stack.pop());
            }
        }

        System.out.println("Eulerian Path or Circuit: " + path);
    }

    public static void main(String[] args) {
        EulerianPathCircuit g = new EulerianPathCircuit(5);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 0);

        System.out.println(g.isEulerian());
        g.printEulerianPathOrCircuit();
    }
}

