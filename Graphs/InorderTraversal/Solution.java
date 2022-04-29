// Given the root of a binary tree, return the inorder traversal of its nodes' values.

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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        traverse(ret, root);
        return ret;
    }
    
    public void traverse(List<Integer> sequence, TreeNode root) {
        if (root == null)
            return;
        if (root.left == null && root.right == null) {
            sequence.add(root.val);
        }
        else {
            traverse(sequence, root.left);
            sequence.add(root.val);
            traverse(sequence, root.right);
        }
    }
}