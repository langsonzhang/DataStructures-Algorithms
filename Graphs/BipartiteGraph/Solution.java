// There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1. You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to. More formally, for each v in graph[u], there is an undirected edge between node u and node v. The graph has the following properties:

// There are no self-edges (graph[u] does not contain u).
// There are no parallel edges (graph[u] does not contain duplicate values).
// If v is in graph[u], then u is in graph[v] (the graph is undirected).
// The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
// A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node in set A and a node in set B.

// Return true if and only if it is bipartite.

class Solution {
    
    int[] state; // 0 - undiscovered, 1 - discovered, -1 - processed
    int[] bw; // 1 - black, -1 - white
    
    
    public boolean isBipartite(int[][] graph) {
        state = new int[graph.length];
        bw = new int[graph.length];
        
        return dfs(graph);
        
    }
    
    // Return true if dfs forest is 2-colourable iff bipartite
    public boolean dfs(int[][] graph) {
        for (int i = 0; i < graph.length; i++) {
            if (state[i] == 0) {
                if (!dfsVisit(graph, i, 1))
                    return false;
            }
        }
        return true;
    }
    
    // Return true if dfs tree is 2-colourable iff bipartite, otherwise false
    public boolean dfsVisit(int[][] graph, int node, int colour) {
        state[node] = 1;
        bw[node] = colour;
        
        for (int neighbour : graph[node]) {
            if (state[neighbour] == 0 && !dfsVisit(graph, neighbour, -colour)) {
                return false;
            }
            if (bw[neighbour] == colour) {
                return false;
            }
        }
        
        state[node] = -1;
        return true;
    }
}