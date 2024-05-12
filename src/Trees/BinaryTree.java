package Trees;

import Recursion.MazeQuestions;

import java.util.*;

public class BinaryTree {
    Node root;

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode next;

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


    //Leetcode 637
    public List<Double> averageOfLevels(TreeNode root) {

        ArrayList<Double> averageOfLevels = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){

            double levelNodes = queue.size();
            double sumOfThisLevel = 0;
            for(int i=0;i<levelNodes;i++){
                TreeNode currNode = queue.poll();
                sumOfThisLevel+=currNode.val;

                if(currNode.left!=null){
                    queue.offer(currNode.left);
                }
                if(currNode.right!=null){
                    queue.offer(currNode.right);
                }
            }

            averageOfLevels.add(sumOfThisLevel/levelNodes);
        }

        return averageOfLevels;

    }

    //Find level order successor of a given node
    public TreeNode findSuccessor(TreeNode root, int key){
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            TreeNode currentNode = queue.poll();
            if (currentNode.left != null) {
                queue.offer(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.offer(currentNode.right);
            }
            if (currentNode.val == key) {
                break;
            }
        }
        return queue.peek();
    }

    //Leetcode 116
    public TreeNode connect(TreeNode root) {
        if (root == null) return root;
        Deque<TreeNode> dq = new LinkedList<>();
        dq.offerLast()
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                if (curr.left != null){
                    q.add(curr.left);
                }
                if (curr.right != null) {
                    q.add(curr.right);
                }
                if (i == size - 1){
                    curr.next = null;
                } else {
                    curr.next = q.peek();
                }
            }
        }
        return root;
    }

    //Leetcode 103
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root==null){
            return new ArrayList<>();
        }
        List<List<Integer>> completeAns = new ArrayList<>();
        Deque<TreeNode> dq = new LinkedList<>();
        dq.offerFirst(root);
        int level = 0;
        while (!dq.isEmpty()) {
            int dqSize = dq.size();
            List<Integer> thisLevelNodes = new ArrayList<>();

            for (int i = 0; i < dqSize; i++) {
                if(level%2==0){
                    // Insert right to left case
                    TreeNode curr = dq.pollFirst();

                    if (curr != null) {
                        thisLevelNodes.add(curr.val);
                        if (curr.left != null) {
                            dq.offerLast(curr.left);
                        }
                        if (curr.right != null) {
                            dq.offerLast(curr.right);
                        }
                    }
                }
                else
                // Insert left to right case
                {
                    TreeNode curr = dq.pollLast();

                    if (curr != null) {
                        thisLevelNodes.add(curr.val);
                        if (curr.right != null) {
                            dq.offerFirst(curr.right);
                        }
                        if (curr.left != null) {
                            dq.offerFirst(curr.left);
                        }
                    }
                }
            }
            completeAns.add(thisLevelNodes);
            level += 1;
        }

        return completeAns;
    }

    //Leetcode 107
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null)
            return new ArrayList<>();

        List<List<Integer>> result = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                list.add(node.val);
                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);
            }
            result.add(0, list);
        }
        return result;
    }

    //Leetcode 199
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        while (!queue.isEmpty()) {

            int nodesOnLevel = queue.size();
            for (int i = 0; i < nodesOnLevel; i++) {
                TreeNode node = queue.poll();
                if(i==nodesOnLevel-1){
                    ans.add(node.val);
                }
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
        }
        return ans;
    }

    //Leetcode 993
    public boolean isCousins(TreeNode root, int x, int y) {

        boolean matchFound = false;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            boolean foundX = false;
            boolean foundY = false;
            List<Integer> list = new ArrayList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if(node.val == x){
                    foundX = true;
                }
                if(node.val == y){
                    foundY = true;
                }
                if (node.left != null && node.right != null) {
                    if (node.left.val == x && node.right.val == y) {
                        return false;
                    }
                    if (node.left.val == y && node.right.val == x) {
                        return false;
                    }
                }
                if (node.left != null){
                    q.add(node.left);
                }
                if (node.right != null){
                    q.add(node.right);
                }
            }

            matchFound = foundX && foundY;
            if(matchFound){
                return true;
            }
        }
        return false;
    }

    //Leetcode 101
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);

        while(!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();

            if(left == null && right == null) {
                continue;
            }

            if(left == null || right == null) {
                return false;
            }

            if (left.val != right.val) {
                return false;
            }

            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);

        }
        return true;
    }

    //Leetcode 101 (Recursive)
    public boolean isSymmetricRecursive(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }


    private boolean isMirror(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        return node1.val == node2.val && isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left);
    }

    //Leetcode 543
    public int diameterOfBinaryTree(TreeNode root) {
        int max[] = new int[1];
        maxDepth(root,max);
        return max[0];
    }

    //Leetcode 226
    private int maxDepth(TreeNode root, int max[]) {
        if (root == null) return 0;

        int left = maxDepth(root.left,max);
        int right = maxDepth(root.right,max);

        max[0] = Math.max(max[0], left + right);

        return Math.max(left, right) + 1;
    }

    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }

        TreeNode leftNode = invertTree(root.left);
        TreeNode rightNode = invertTree(root.right);

        root.right = leftNode;
        root.left = rightNode;

        return root;
    }
}
