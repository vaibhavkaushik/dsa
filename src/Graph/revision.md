### Detailed Explanation of Graph Algorithms in Bhai Lang (Revision Format)

#### 1. Graph Representation:

**Adjacency Matrix**:
- **Approach**:
    1. Ek 2D array banate hain jisme rows aur columns vertices ko represent karte hain.
    2. Agar vertex `i` aur `j` ke beech edge hai to `matrix[i][j]` ko 1 set karte hain.
    3. Graph ko initialize karte hain given number of vertices ke sath.
    4. Edges ko add karte hain corresponding elements ko 1 set karke.
    5. Matrix ko print karke sabhi connections ko visualize karte hain.

**Adjacency List**:
- **Approach**:
    1. Ek array of lists banate hain jisme har index ek vertex ko represent karta hai aur list uske neighbors ko represent karti hai.
    2. Har vertex ki list mein un sabhi vertices ko add karte hain jo us vertex se connected hain.
    3. Graph ko initialize karte hain given number of vertices ke sath.
    4. Edges ko add karte hain neighbors ko corresponding lists mein append karke.
    5. Adjacency lists ko print karke sabhi connections ko visualize karte hain.

#### 2. Graph Traversal:

**Depth-First Search (DFS)**:
- **Approach**:
    1. DFS ko ek stack ya recursive approach se implement karte hain.
    2. Har node ko visit karte hain aur uske sabhi unvisited neighbors ko explore karte hain.
    3. Start node se shuru karte hain aur jitna door tak possible ho sakta hai, traverse karte hain.
    4. Agar koi node ke sabhi neighbors visit ho chuke hain to backtrack karte hain.
    5. Har visit ko track karte hain taaki kisi node ko dobara visit na kare.

**Breadth-First Search (BFS)**:
- **Approach**:
    1. BFS ko ek queue ke through implement karte hain.
    2. Start node se shuru karte hain aur uske sabhi neighbors ko queue mein add karte hain.
    3. Queue se nodes ko dequeue karte hain aur unke sabhi unvisited neighbors ko visit karte hain.
    4. Yeh process tab tak repeat karte hain jab tak queue empty na ho jaye.
    5. Har visit ko track karte hain taaki kisi node ko dobara visit na kare.

#### 3. Shortest Path Algorithms:

**Dijkstra's Algorithm**:
- **Approach**:
    1. Initialize karte hain ek distance array sab vertices ke liye infinity se, aur source vertex ka distance 0 set karte hain.
    2. Ek priority queue ka use karte hain shortest distance ko track karne ke liye.
    3. Source vertex se shuru karte hain aur queue mein add karte hain.
    4. Queue se sabse chhoti distance wale vertex ko nikalte hain.
    5. Us vertex ke sabhi neighbors ke distances ko update karte hain agar naya distance chhota ho.
    6. Yeh process tab tak repeat karte hain jab tak queue empty na ho jaye.

**Bellman-Ford Algorithm**:
- **Approach**:
    1. Initialize karte hain ek distance array sab vertices ke liye infinity se, aur source vertex ka distance 0 set karte hain.
    2. Relaxation step ko sab edges ke liye |V|-1 times repeat karte hain (jaha |V| number of vertices hain).
    3. Har edge ke liye check karte hain agar us edge ko use karke shorter path milta hai to distance ko update karte hain.
    4. Negative-weight cycles ko detect karne ke liye sab edges ko ek aur baar relax karte hain agar koi distance change hota hai to cycle present hai.

**Floyd-Warshall Algorithm**:
- **Approach**:
    1. Ek 2D distance matrix banate hain jisme sab vertices ke beech ki initial distances ko set karte hain.
    2. Distance ko initialize karte hain infinity se except diagonal elements jo 0 honge.
    3. Har pair of vertices ke liye check karte hain agar koi intermediate vertex use karke shorter path milta hai.
    4. Har vertex ko intermediate vertex bana ke update karte hain sab pairs ke beech shortest distances ko.
    5. Sab pairs ke beech shortest distances ko matrix ke form mein print karte hain.

#### 4. Minimum Spanning Tree (MST) Algorithms:

**Kruskal's Algorithm**:
- **Approach**:
    1. Graph ke sabhi edges ko weight ke increasing order mein sort karte hain.
    2. Ek disjoint-set (union-find) structure banate hain cycle ko avoid karne ke liye.
    3. Sorted edges ko ek ek karke add karte hain agar wo cycle create nahi karta hai.
    4. Ye process tab tak continue karte hain jab tak sab vertices connected nahi ho jaate.
    5. Final MST (Minimum Spanning Tree) ko print ya store karte hain.

**Prim's Algorithm**:
- **Approach**:
    1. Ek arbitrary vertex ko start vertex select karte hain.
    2. Ek min-heap (priority queue) ka use karke minimum weight edge ko select karte hain jo already selected set of vertices ko new vertex se connect karta hai.
    3. Selected edge ko MST mein add karte hain.
    4. Ye process tab tak repeat karte hain jab tak sab vertices include nahi ho jaate.
    5. Final MST ko print ya store karte hain.

#### 5. Cycle Detection:

**Union-Find Algorithm**:
- **Approach**:
    1. Ek disjoint-set structure banate hain jisme har vertex apne hi set mein hota hai initially.
    2. Har edge ko process karte hain aur check karte hain ki kya dono vertices same set mein hain.
    3. Agar dono vertices same set mein hain to cycle detect hota hai, otherwise union operation karte hain.
    4. Sab edges ko process karke final result nikalte hain ki graph mein cycle hai ya nahi.

**DFS-based Cycle Detection**:
- **Approach**:
    1. Har vertex ke liye DFS traversal start karte hain agar wo ab tak visit nahi hua.
    2. Har vertex ko visited aur recursion stack mein mark karte hain.
    3. Har neighbor vertex ke liye:
        - Agar neighbor visit nahi hua to uske liye recursive DFS call karte hain.
        - Agar neighbor current recursion stack mein hai to cycle detect hoti hai.
    4. Current vertex ko recursion stack se remove karte hain.
    5. Har vertex ko visit karke cycle detection ka result nikalte hain.

#### 6. Topological Sorting:

**Kahn’s Algorithm**:
- **Approach**:
    1. Har vertex ka in-degree calculate karte hain.
    2. Zero in-degree wale vertices ko queue mein add karte hain.
    3. Queue se vertices ko dequeue karte hain aur unke neighbors ka in-degree reduce karte hain.
    4. In-degree zero hone par unhe queue mein add karte hain.
    5. Ye process tab tak repeat karte hain jab tak queue empty nahi ho jaati.
    6. Final topological order ko print ya store karte hain.

**DFS-based Topological Sort**:
- **Approach**:
    1. Har vertex ke liye DFS traversal start karte hain.
    2. DFS complete hone ke baad vertex ko stack mein push karte hain.
    3. Har vertex ko visit karke stack ko fill karte hain.
    4. Stack ko reverse order mein print karte hain jo topological order hoga.

#### 7. Strongly Connected Components (SCC):

**Kosaraju’s Algorithm**:
- **Approach**:
    1. First pass DFS traversal karke finishing times store karte hain.
    2. Graph ko transpose (reverse) karte hain.
    3. Second pass DFS traversal reverse finishing times ke order mein karte hain aur SCCs ko identify karte hain.
    4. Final SCCs ko print ya store karte hain.

**Tarjan’s Algorithm**:
- **Approach**:
    1. Single-pass DFS traversal start karte hain.
    2. Har vertex ke liye discovery time aur low-link values calculate karte hain.
    3. Stack ka use karke SCCs ko identify karte hain jab low-link value discovery time ke barabar hoti hai.
    4. Final SCCs ko print ya store karte hain.

#### 8. Bipartite Graph Check:

**Bipartite Check Using BFS/DFS**:
- **Approach**:
    1. Graph ko 2 colors se color karne ki koshish karte hain using BFS ya DFS.
    2. Har vertex ko ek color assign karte hain aur uske neighbors ko alternate color.
    3. Agar kisi stage pe adjacent vertices same color ke milte hain to graph bipartite nahi hai.
    4. Complete traversal ke baad result nikalte hain ki graph bipartite hai ya nahi.

#### 9. Network Flow:

**Ford-Fulkerson Algorithm**:
- **Approach**:
    1. Augmenting

paths ko find karte hain jab tak possible ho using DFS ya BFS.
2. Har augmenting path ka residual capacity update karte hain.
3. Maximum flow ko augmenting paths ke flows ko add karke calculate karte hain.
4. Final maximum flow ko print ya store karte hain.

**Edmonds-Karp Algorithm**:
- **Approach**:
    1. Ford-Fulkerson algorithm ka implementation using BFS for finding augmenting paths.
    2. BFS ka use karke shortest augmenting paths ko find karte hain.
    3. Har augmenting path ka residual capacity update karte hain.
    4. Maximum flow ko augmenting paths ke flows ko add karke calculate karte hain.
    5. Final maximum flow ko print ya store karte hain.

#### 10. Eulerian Path and Circuit:

**Hierholzer’s Algorithm**:
- **Approach**:
    1. Graph mein Eulerian path ya circuit check karte hain (degree conditions).
    2. Start vertex se traversal start karte hain aur stack ka use karke edges ko visit karte hain.
    3. Visit karte hue path ko record karte hain.
    4. Final Eulerian path ya circuit ko print ya store karte hain.

#### 11. Hamiltonian Path and Circuit:

**Backtracking Approach**:
- **Approach**:
    1. Ek path ko start karte hain aur backtracking ka use karke sab possible paths ko explore karte hain.
    2. Har vertex ko visit karte hain aur check karte hain agar path mein sab vertices include ho chuke hain.
    3. Agar valid Hamiltonian path ya circuit milta hai to result store karte hain.
    4. Sab possible paths ko explore karke final result nikalte hain.

#### 12. Graph Coloring:

**Greedy Coloring**:
- **Approach**:
    1. Har vertex ko color assign karte hain ensuring no two adjacent vertices have the same color.
    2. Ek greedy approach ka use karke har vertex ko minimum available color assign karte hain.
    3. Har vertex ke liye available colors ko track karte hain.
    4. Final coloring ko print ya store karte hain ensuring valid coloring of the graph.

### Quick Revision Points:

1. **Minimum Spanning Tree (MST) Algorithms**: Kruskal's (Greedy, edge-based), Prim's (Greedy, vertex-based).
2. **Cycle Detection**: Union-Find (Disjoint sets), DFS-based (Back edges).
3. **Topological Sorting**: Kahn’s (BFS-based), DFS-based.
4. **Strongly Connected Components (SCC)**: Kosaraju’s (Two-pass DFS), Tarjan’s (Single-pass DFS).
5. **Bipartite Graph Check**: BFS/DFS-based coloring technique.
6. **Network Flow**: Ford-Fulkerson (DFS/BFS-based augmenting paths), Edmonds-Karp (BFS-based implementation).
7. **Eulerian Path and Circuit**: Hierholzer’s algorithm.
8. **Hamiltonian Path and Circuit**: Backtracking approach.
9. **Graph Coloring**: Greedy Coloring approach.

In detailed explanations aur approaches se, tum in graph algorithms ko achche se samajh sakte ho aur effectively implement kar sakte ho.