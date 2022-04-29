//Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

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
    // Symmetric iff preorder = postorder
    public boolean isSymmetric(TreeNode root) {
        List<Integer> pre = new ArrayList();
        List<Integer> post = new ArrayList();
        preorder(pre, root);
        postorder(post, root);
        for (int i = 0; i < pre.size(); i++) {
            if (pre.get(i) != post.get(i))
                return false;
        }
        return true;
    }
    
    public void preorder(List<Integer> seq, TreeNode root) {
        if (root == null)
            seq.add(null);
        else {
            seq.add(root.val);
            preorder(seq, root.left);
            preorder(seq, root.right);
        }
    }
    
    public void postorder(List<Integer> seq, TreeNode root) {
        if (root == null)
            seq.add(null);
        else {
            seq.add(root.val);
            postorder(seq, root.right);
            postorder(seq, root.left);
        }
    }
}