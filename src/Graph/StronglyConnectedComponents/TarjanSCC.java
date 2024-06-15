package Graph.StronglyConnectedComponents;

import java.util.*;

public class TarjanSCC {
    private int numVertices;
    private List<List<Integer>> adjList;
    private boolean[] visited;
    private int[] ids, low;
    private int id;
    private boolean[] onStack;
    private Stack<Integer> stack;
    private List<List<Integer>> sccs;

    // Constructor to initialize the graph
    public TarjanSCC(int numVertices) {
        this.numVertices = numVertices;
        adjList = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }
        visited = new boolean[numVertices];
        ids = new int[numVertices];
        low = new int[numVertices];
        onStack = new boolean[numVertices];
        stack = new Stack<>();
        sccs = new ArrayList<>();
        id = 0;
    }

    // Add an edge to the graph
    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
    }

    // DFS utility for Tarjan's algorithm
    private void dfs(int at) {
        stack.push(at);
        onStack[at] = true;
        visited[at] = true;
        ids[at] = low[at] = id++;

        for (int neighbor : adjList.get(at)) {
            if (!visited[neighbor]) {
                dfs(neighbor);
                low[at] = Math.min(low[at], low[neighbor]);
            } else if (onStack[neighbor]) {
                low[at] = Math.min(low[at], ids[neighbor]);
            }
        }

        // On recursive callback, if we're at the root node (start of SCC)
        if (ids[at] == low[at]) {
            List<Integer> scc = new ArrayList<>();
            while (true) {
                int node = stack.pop();
                onStack[node] = false;
                low[node] = ids[at]; // Assign root's low-link value to all nodes in the SCC
                scc.add(node);
                if (node == at) break;
            }
            sccs.add(scc);
        }
    }

    // Method to find and print SCCs
    public List<List<Integer>> findSCCs() {
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }
        return sccs;
    }

    public static void main(String[] args) {
        TarjanSCC g = new TarjanSCC(5);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(1, 0);
        g.addEdge(0, 3);
        g.addEdge(3, 4);

        List<List<Integer>> sccs = g.findSCCs();
        for (List<Integer> scc : sccs) {
            System.out.println("SCC: " + scc);
        }
    }
}

