package Graph;
import java.util.*;

//### Kahn's Algorithm: Logic and Intuition in Bhai Lang
//
//        **Kahn's Algorithm** ek algorithm hai jo directed acyclic graph (DAG) ka topological sort nikalne ke
//        liye use hota hai. Iska main idea yeh hai ki vertices ko unke in-degree ke basis pe process kiya jaye.
//        In-degree un vertices ka count hai jo kisi vertex par point kar rahe hain. Hum initially un vertices
//        ko identify karte hain jinka in-degree 0 hai (jo kisi aur vertex pe depend nahi karte).
//        Phir in vertices ko queue mein daal kar, ek ek karke process karte hain aur unke neighbors
//        ka in-degree decrement karte hain. Jab kisi neighbor ka in-degree 0 ho jata hai,
//        to usko bhi queue mein daal dete hain. Yeh process tab tak chalta hai jab tak queue empty
//        nahi ho jati. Agar process ke baad humne saare vertices ko process kar liya, to graph
//        acyclic hai aur humne successfully topological sort nikaal liya. Agar kuch vertices bache hain,
//        to graph mein cycle hai.
//
//        ### Code with Bhai Lang Comments:
//
//        ```java
//import java.util.*;

public class TopologicalSortKahn {
    private Map<Integer, List<Integer>> adjList;

    // Constructor to initialize the graph
    public TopologicalSortKahn() {
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

    // Kahn's Algorithm for Topological Sort
    public List<Integer> topologicalSortKahn() {
        // In-degree calculate karne ke liye map
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (int vertex : adjList.keySet()) {
            inDegree.put(vertex, 0);
        }
        for (int vertex : adjList.keySet()) {
            for (int neighbor : adjList.get(vertex)) {
                inDegree.put(neighbor, inDegree.get(neighbor) + 1);
            }
        }

        // Queue to process vertices with in-degree 0
        Queue<Integer> queue = new LinkedList<>();
        for (int vertex : inDegree.keySet()) {
            if (inDegree.get(vertex) == 0) {
                queue.add(vertex);
            }
        }

        // Topological sort result list
        List<Integer> topologicalOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            topologicalOrder.add(vertex);

            for (int neighbor : adjList.get(vertex)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // Check if topological sort was possible (acyclic graph)
        if (topologicalOrder.size() != adjList.size()) {
            throw new RuntimeException("Graph has a cycle, topological sort not possible.");
        }

        return topologicalOrder;
    }

    public static void main(String[] args) {
        TopologicalSortKahn graph = new TopologicalSortKahn();
        graph.addVertex(5);
        graph.addVertex(7);
        graph.addVertex(3);
        graph.addVertex(11);
        graph.addVertex(8);
        graph.addVertex(2);
        graph.addVertex(9);
        graph.addVertex(10);

        graph.addEdge(5, 11);
        graph.addEdge(7, 11);
        graph.addEdge(7, 8);
        graph.addEdge(3, 8);
        graph.addEdge(3, 10);
        graph.addEdge(11, 2);
        graph.addEdge(11, 9);
        graph.addEdge(11, 10);
        graph.addEdge(8, 9);

        List<Integer> sortedOrder = graph.topologicalSortKahn();
        System.out.println("Topological Sort (Kahn's Algorithm): " + sortedOrder);
    }
}
//```
//
//        ### Explanation:
//
//        1. **Graph Initialization**:
//        - `TopologicalSortKahn` class mein adjacency list `adjList` use hoti hai jo graph ko represent karti hai.
//
//2. **Vertex and Edge Addition**:
//        - `addVertex(int vertex)`: Naya vertex add karta hai graph mein agar wo pehle se nahi hai.
//   - `addEdge(int src, int dest)`: Directed edge `src` se `dest` tak add karta hai.
//
//        3. **Topological Sort Using Kahn's Algorithm**:
//        - `topologicalSortKahn()`: Topological sort perform karta hai Kahn's algorithm use karke.
//        - **In-degree Calculation**:
//        - `inDegree` map initialize karte hain har vertex ka in-degree calculate karne ke liye.
//       - Har vertex ke neighbors ka in-degree increment karte hain.
//        - **Queue Initialization**:
//        - Queue initialize karte hain un vertices ke saath jinka in-degree 0 hai.
//     - **Process Vertices**:
//        - Queue se vertex ko poll karte hain aur topological order mein add karte hain.
//        - Uske neighbors ka in-degree decrement karte hain. Agar koi neighbor ka in-degree 0 ho jata hai, to usko queue mein add karte hain.
//     - **Cycle Detection**:
//        - Agar topological sort ka size graph ke vertices ke size ke barabar nahi hai, to graph mein cycle hai.
//
//        4. **Main Method**:
//        - Graph ko initialize karte hain, vertices aur edges ko add karte hain.
//   - Topological sort ko call karte hain aur result ko print karte hain.
//
//        ### Summary:
//
//Kahn's Algorithm directed acyclic graph (DAG) ka topological sort nikalne ka ek efficient method hai jo vertices ko unke in-degree ke basis pe process karta hai. Yeh algorithm real-life applications mein task scheduling, build systems, aur project management jaisi situations mein useful hota hai.
