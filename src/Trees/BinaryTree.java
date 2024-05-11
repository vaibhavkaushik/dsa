package Trees;

import Recursion.MazeQuestions;

public class BinaryTree {
    Node root;

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode (int val){
            this.val = val;
        }
    }

    //Leetcode 2331
    public boolean evaluateTree(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val == 1;
        }

        boolean leftAns = evaluateTree(root.left);
        boolean rightAns = evaluateTree(root.right);

        if (root.val == 2) {
            return leftAns || rightAns;
        }

        return leftAns && rightAns;
    }
}
