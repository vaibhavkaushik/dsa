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

    //Leetcode 108
    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode ans = sortedArrayToBSTHelper(nums, null, 0, nums.length - 1);
        return ans;
    }

    public TreeNode sortedArrayToBSTHelper(int[] nums, TreeNode currRoot, int start, int end) {

        if (end < start) {
            return null;
        }

        int mid = start + (end - start) / 2;

        // Create current node
        TreeNode root = new TreeNode(nums[mid]);


        root.left = sortedArrayToBSTHelper(nums, root, start, mid - 1);
        root.right = sortedArrayToBSTHelper(nums, root, mid + 1, end);


        return root;
    }
}
