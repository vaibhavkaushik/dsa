package Trees;

import Recursion.MazeQuestions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    //Leetcode 701 (Without height balance)
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null){
            return new TreeNode(val);
        }

        if(val < root.val){
            root.left = insertIntoBST(root.left,val);
        }else{
            root.right = insertIntoBST(root.right,val);
        }

        return root;
    }

    //Leetcode 701 (Height Balanced)
    public TreeNode insertIntoBSTBalanced(TreeNode root, int val) {
        if(root == null){
            return new TreeNode(val);
        }

        if(val < root.val){
            root.left = insertIntoBSTBalanced(root.left,val);
        }else{
            root.right = insertIntoBSTBalanced(root.right,val);
        }

        return convertToHeightBalancedTree(root);
    }

    public TreeNode convertToHeightBalancedTree(TreeNode root){

        //Find height to check if tree is unbalanced
        int heightLeft = heightOfTree(root.left);
        int heightRight = heightOfTree(root.right);
        //Left Side Unabalanced
        if(heightLeft-heightRight > 1){
            int heightLeftLeftChild = heightOfTree(root.left.left);
            int heightLeftRightChild = heightOfTree(root.left.right);
            //Left-Left
            if(heightLeftLeftChild - heightLeftRightChild > 0){
                //Right Rotation
                return rightRotate(root);
            }
            //Left-Right
            else if(heightLeftLeftChild - heightLeftRightChild < 0){
                //Left Rotation
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }

        }
        //Right Side Unbalanced
        else if(heightLeft - heightRight < -1){
            int heightRightRightChild = heightOfTree(root.right.right);
            int heightRightLeftChild = heightOfTree(root.right.left);
            //Right-Right
            if(heightRightRightChild - heightRightLeftChild > 0){
                //Left Rotation
                return leftRotate(root);
            }else if(heightRightRightChild - heightRightLeftChild < 0){
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }
        }

        return root;
    }

    //      p
    //    c   pr
    //  cl cr

    //      c
    //    cl  p
    //      cr pr
    //p - parent, c - child, cl,cl child left right, pr parent right
    public TreeNode rightRotate(TreeNode root){
        TreeNode parent = root;
        TreeNode child = root.left;
        TreeNode childRight = child.right;
        child.right = parent;
        parent.left = childRight;

        return child;
    }

    public TreeNode leftRotate(TreeNode root){
        TreeNode parent = root;
        TreeNode child = root.right;
        TreeNode childLeft = child.left;
        child.left = parent;
        parent.right = childLeft;

        return child;
    }

    public int heightOfTree(TreeNode root){
        if(root == null){
            return -1;
        }

        int leftHeight = heightOfTree(root.left);
        int rightHeight = heightOfTree(root.right);

        return 1 + Math.max(leftHeight,rightHeight);
    }

    //Leetcode 102
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }

        List<List<Integer>> completeAns = new ArrayList<>();
        levelOrderHelper(root,completeAns,0);

        return completeAns;

    }

    public void levelOrderHelper(TreeNode root,List<List<Integer>> allLevelAns, int level){

        if(root==null){
            return;
        }

        //Check for levels
        //This means we have not explored this level yet
        if(allLevelAns.size() <= level){
            List<Integer> newTraversal = new ArrayList<>();
            newTraversal.add(root.val);
            allLevelAns.add(newTraversal);

        }else{
            //Level already explored, just add the node
            allLevelAns.get(level).add(root.val);
        }
        levelOrderHelper(root.left,allLevelAns,level+1);
        levelOrderHelper(root.right,allLevelAns,level+1);

        Queue<Integer> queue = new LinkedList<>();
        queue.poll();

    }

    //Leetcode 102
    public List<List<Integer>> levelOrderIterative(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }

        List<List<Integer>> completeAns = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        while(!queue.isEmpty()){
            ArrayList<Integer> currentLevel = new ArrayList<>();
            int nodesOnLevel = queue.size();
            for(int i=0;i<nodesOnLevel;i++){
                TreeNode node = queue.poll();
                currentLevel.add(node.val);

                if(node.left!=null)
                    queue.offer(node.left);
                if(node.right!=null)
                    queue.offer(node.right);
            }
            completeAns.add(currentLevel);
        }
        return completeAns;
    }
}