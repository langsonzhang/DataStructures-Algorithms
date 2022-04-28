// Implemented using a modified Prim's Algorithm

class Solution {
    int maxEffort;
    int x;
    int y;
    
    int xMax;
    int yMax;
    
    PriorityQueue<Node> safeNodes;
    int[][] heights;
    
    public int minimumEffortPath(int[][] heights) {
        safeNodes = new PriorityQueue<Node>(11, (Node n1, Node n2) -> n1.weight - n2.weight);
        this.heights = heights;
        x = 0;
        y = 0;
        xMax = heights[0].length-1;
        yMax = heights.length-1;
        maxEffort = 0;
        
        safeNodes.add(new Node(0, 0, -1));
        
        Node safeNode;
        while (!safeNodes.isEmpty()) {
            safeNode = safeNodes.poll();
            x = safeNode.x;
            y = safeNode.y;
            if (heights[y][x] == 0)
                continue;
            maxEffort = maxEffort > safeNode.weight ? maxEffort : safeNode.weight;
            if (x == xMax && y == yMax)
                return maxEffort;
            addAdjacentNode(1, 0);
            addAdjacentNode(0, 1);
            addAdjacentNode(-1, 0);
            addAdjacentNode(0, -1);
            heights[y][x] = 0;
        }
        return -1;
        
    }
    
    public void addAdjacentNode(int dx, int dy) {
        if (inRange(x+dx, 0, xMax) && inRange(y+dy, 0, yMax) && heights[y+dy][x+dx] != 0) {
            safeNodes.add(new Node(x+dx, y+dy, Math.abs(heights[y+dy][x+dx] - heights[y][x])));
        }
    }
    
    public boolean inRange(int n, int lo, int hi) {
        return n >= lo && n <= hi;
    }
    
    class Node {
        int x;
        int y;
        int weight;
        
        public Node(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }
}