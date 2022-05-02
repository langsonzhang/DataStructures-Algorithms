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
    
    int max;
    
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        max = 1;
        dfs(root, 1);
        return max;
    }
    
    public void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        else {
            dfs(root.left, depth+1);
            dfs(root.right, depth+1);
            max = Math.max(max, depth);
        }
    }
}