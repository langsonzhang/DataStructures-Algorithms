
// Solution using DJS
class Solution {
    int[] parent;
    int[] rank;
    
    
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        parent = new int[s.length()];
        Arrays.fill(parent, -1);
        rank = new int[s.length()];
        
        
        // create groups
        for (List<Integer> p : pairs) {
            for (int n : p) {
                if (findSet(n) == -1)
                    makeSet(n);
            }
            union(p.get(0), p.get(1));
        }
        
        // populate indices in their respective sets
        List<Integer>[] groups = new ArrayList[s.length()];
        for (int i = 0; i < s.length(); i++) {
            int rep = findSet(i);
            if (rep == -1)
                continue;
            if (groups[rep] == null) {
                groups[rep] = new ArrayList<>();
            }
            groups[rep].add(i);
        }
        
        // Generate sorted characters and indices for each group
        // Then construct the string to return
        char[] ret = s.toCharArray();
        for (List<Integer> group : groups) {
            if (group == null)
                continue;
            System.out.println(group);
            List<Character> chars = new ArrayList<>();
            for (int index : group) {
                chars.add(s.charAt(index));
            }
            Collections.sort(chars);
            Collections.sort(group);
            for (int i = 0; i < group.size(); i++) {
                ret[group.get(i)] = chars.get(i);
            }
        }
        
        return new String(ret);
    }
    
    public void makeSet(int n) {
        parent[n] = n;
        rank[n] = 0;
    }
    
    public int findSet(int n) {
        if (parent[n] == -1) {
            return -1;
        }
        if (parent[n] == n) {
            return n;
        }
        else {
            return parent[n] = findSet(parent[n]);
        }
    }
    
    public void union(int a, int b) {
        a = findSet(a);
        b = findSet(b);
        
        if (a != b) {
            if (rank[a] > rank[b]) {
                parent[b] = a;
            }
            else if (rank[b] > rank[a]) {
                parent[a] = b;
            }
            else {
                parent[b] = a;
                rank[a]++;
            }
        }
    }
    
}

// Alternate Solution using DFS to generate groups
class SolutionDFS {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        List<List> trees = new ArrayList<>();
        
        // create adjacency list
        List<Integer>[] graph = new ArrayList[s.length()];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (List<Integer> p : pairs) {
            int start = p.get(0);
            int end = p.get(1);
            graph[start].add(end);
            graph[end].add(start);
        }
        
        List<List<Integer>> forest = new ArrayList<>();
        dfs(graph, forest);
        
        
        char[] ret = s.toCharArray();
        for (List<Integer> tree : forest) {
            List<Character> chars = new ArrayList<>();
            for (int i : tree) {
                chars.add(s.charAt(i));
            }
            Collections.sort(chars);
            Collections.sort(tree);
            for (int i = 0; i < tree.size(); i++) {
                ret[tree.get(i)] = chars.get(i);
            }
        }
        
        return new String(ret);
        
    }
    
    int[] discovered;
    public void dfs(List<Integer>[] graph, List<List<Integer>> forest) {
        discovered = new int[graph.length];
        
        for (int node = 0; node < graph.length; node++) {
            if (discovered[node] == 0) {
                List<Integer> tree = new ArrayList<>();
                forest.add(tree);
                dfsVisit(graph, tree, node);
            }
        }
    }
    
    public void dfsVisit(List<Integer>[] graph, List<Integer> tree, int node) {
        discovered[node] = 1;
        tree.add(node);
        for (int neighbour : graph[node]) {
            if (discovered[neighbour] == 0)
                dfsVisit(graph, tree, neighbour);
        }
    }
}