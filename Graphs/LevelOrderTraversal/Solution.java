// Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        
        List<List<Integer>> ret = new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque();
        Queue<Integer> depthq = new ArrayDeque();
        q.add(root);
        depthq.add(0);
        
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            int depth = depthq.poll();
            
            while (ret.size() <= depth)
                ret.add(new ArrayList<>());
            List<Integer> level = ret.get(depth);
            
            if (curr.left != null) {
                q.add(curr.left);
                depthq.add(depth+1);
            }
            if (curr.right != null) {
                q.add(curr.right);
                depthq.add(depth+1);
            }
            
            level.add(curr.val);
        }
        
        return ret;
    }
    
}