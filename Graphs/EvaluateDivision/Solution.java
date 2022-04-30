class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // make graph
        Map<String, Map<String, Double>> graph = generateGraph(equations, values);
        
        // process each query
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            // if invalid query answer -1
            if (!graph.containsKey(query.get(0)) || !graph.containsKey(query.get(1)))
                results[i] = -1;
            // otherwise dfs
            else {
                Set<String> seen = new HashSet<>();
                results[i] = dfs(graph, seen, query.get(0), query.get(1), 1);
            }
        }
        
        return results;
    }
    
    
    // a/b = 3 <==> a = 3b <==> a -[3]-> b && a <-[1/3]- b.
    public Map<String, Map<String, Double>> generateGraph(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        
        // Maps numerator -> (denominator -> value) and denominator -> (numerator -> 1/value) 
        for (int i = 0; i < equations.size(); i++) {
            List<String> eq = equations.get(i);
            String numerator = eq.get(0);
            String denominator = eq.get(1);
            graph.putIfAbsent(numerator, new HashMap<>());
            graph.putIfAbsent(denominator, new HashMap<>());
            graph.get(numerator).put(denominator, values[i]);
            graph.get(denominator).put(numerator, 1/values[i]);
        }
        
        return graph;
    }
    
    // Based on our directed weighted graph, value(u --> v) is the product of all edges along u --> v. 
    public double dfs(Map<String, Map<String, Double>> graph, Set<String> seen, String curr, String target, double value) {
        seen.add(curr);
        
        if (graph.get(curr).containsKey(target))
            return (value *= graph.get(curr).get(target));
        
        for (String neighbour : graph.get(curr).keySet()) {
            double ret = -1;
            if (!seen.contains(neighbour) && 
                (ret = Math.max(dfs(graph, seen, neighbour, target, value*graph.get(curr).get(neighbour)), ret)) != -1) {
                return ret;
            }
        }
        
        return -1;
    }
}