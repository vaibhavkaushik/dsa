package Graph;
import java.util.*;

public class TopologicalSort {

    /*
    Stack ka use topological sort mein isliye kiya jaata hai kyunki DFS traversal ke baad vertices ko reverse order mein retrieve karna zaroori hai. Jab hum DFS traversal perform karte hain aur kisi vertex ke saare neighbors visit kar lete hain, tab hum us vertex ko stack mein push karte hain. Finally, stack ko pop karte waqt vertices correct topological order mein milti hain.

Significance of Stack in Topological Sort:
Reverse Order Retrieval:

DFS traversal mein jab hum ek vertex ke saare neighbors ko visit kar lete hain, tab hum us vertex ko stack mein push karte hain. Yeh ensure karta hai ki jab hum stack ko pop karenge, tab hume vertices reverse order mein milengi jismein unhone complete traversal kiya tha.
Correct Topological Order:

Topological sort mein har vertex ko uske dependencies se pehle aana chahiye. Stack use karne se hum ensure karte hain ki har vertex apne dependencies se baad mein retrieve ho. Jab hum stack se pop karte hain, tab vertices correct topological order mein hoti hain.
Handling Cycles:

Stack help karta hai detect karne mein agar graph cyclic hai. DFS traversal ke dauran, agar koi vertex stack mein already present hai jab hum usko visit karne ki koshish karte hain, to cycle detect hoti hai.



     */
    private Map<Integer, List<Integer>> adjList;

    // Constructor to initialize the graph
    public TopologicalSort() {
        adjList = new HashMap<>();
    }

    // Vertex add karne ka method
    public void addVertex(int vertex) {
        adjList.putIfAbsent(vertex, new ArrayList<>());
    }

    // Edge add karne ka method for directed graph
    public void addEdge(int src, int dest) {
        adjList.putIfAbsent(src, new ArrayList<>());
        adjList.putIfAbsent(dest, new ArrayList<>());
        adjList.get(src).add(dest);
    }

    // Topological Sort using DFS
    public List<Integer> topologicalSortDFS() {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        // Loop to ensure all vertices are covered
        for (int vertex : adjList.keySet()) {
            if (!visited.contains(vertex)) {
                dfsHelper(vertex, visited, stack);
            }
        }

        List<Integer> sortedOrder = new ArrayList<>();
        while (!stack.isEmpty()) {
            sortedOrder.add(stack.pop());
        }

        return sortedOrder;
    }

    private void dfsHelper(int vertex, Set<Integer> visited, Stack<Integer> stack) {
        visited.add(vertex);

        for (int neighbor : adjList.get(vertex)) {
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited, stack);
            }
        }

        stack.push(vertex);
    }

    public static void main(String[] args) {
        TopologicalSort graph = new TopologicalSort();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(4, 5);

        List<Integer> sortedOrder = graph.topologicalSortDFS();
        System.out.println("Topological Sort (DFS): " + sortedOrder); // Output will depend on graph structure
    }
}
