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

    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        return isValidBSTHelper(root,null,null);
    }

    //Leetcode 98
    public boolean isValidBSTHelper(TreeNode root, Integer min, Integer max){

        if(root==null){
            return true;
        }

        //Concept here
        //min and max define boundary in which the nodes can lie
        //If any node goes below min boundary, or goes above max boundary
        //then we will not have a BST

        if(min!=null && root.val <=min){
            return false;
        }

        if(max!=null && root.val >=max){
            return false;
        }

        boolean isLeftValid = isValidBSTHelper(root.left,min,root.val);
        boolean isRightValid = isValidBSTHelper(root.right,root.val,max);

        return isLeftValid && isRightValid;
    }

    //Leetcode 230
    public int kthSmallest(TreeNode root, int k) {
        int[] level = new int[]{1};
        int[] ans = new int[1];
        helper(root,k,level,ans);
        return ans[0];
    }

    public void helper(TreeNode root, int k, int[] level, int[] ans) {
        if (root == null) {
            return;
        }

        helper(root.left, k, level, ans);
        if(k == level[0]){
            ans[0] = root.val;
        }
        level[0]+=1;
        helper(root.right, k, level, ans);
    }

    //Leetcode 105
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int start = 0;
        int end = preorder.length - 1;
        int[] idx = new int[]{0};
        return buildTreeHelper(preorder, inorder, start, end, idx);
    }

    public TreeNode buildTreeHelper(int[] preorder, int[] inorder, int start, int end, int[] idx) {

        if (end < start) {
            return null;
        }

        if(idx[0] == preorder.length){
            return null;
        }

        // Root of this tree
        TreeNode root = new TreeNode(preorder[idx[0]]);
        int root_idx = findIdxOfRoot(inorder, root.val, start, end);
        idx[0]++;
        root.left = buildTreeHelper(preorder, inorder, start, root_idx - 1, idx);
        root.right = buildTreeHelper(preorder, inorder, root_idx + 1, end, idx);
        return root;
    }

    public int findIdxOfRoot(int[] inorder, int root, int start, int end) {
        int idx = -1;
        for (int i = start; i <= end; i++) {
            if (inorder[i] == root) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    //Leetcode 297
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return serializeHelper(root).toString();
    }

    public StringBuilder serializeHelper(TreeNode root){
        if(root == null){
            return new StringBuilder("#");
        }

        StringBuilder leftSerialized = serializeHelper(root.left);
        StringBuilder rightSerialized = serializeHelper(root.right);

        return new StringBuilder().append(root.val).append(",").append(leftSerialized).append(",").append(rightSerialized);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodeValues = data.split(",");
        //System.out.println(Arrays.toString(nodeValues));
        return deserialize(nodeValues,new int[]{0});
    }

    public TreeNode deserialize(String[] nodeValues, int[] idx){
        if(idx[0] == nodeValues.length){
            return null;
        }

        TreeNode root = null;
        String nodeValue = nodeValues[idx[0]];
        idx[0]++;
        if(!nodeValue.equals("#")){
            root = new TreeNode(Integer.valueOf(nodeValue));
            root.left = deserialize(nodeValues,idx);
            root.right = deserialize(nodeValues,idx);
        }
        List<Integer> ans = new ArrayList<>();
        return root;
    }

    //Leetcode 129
    public int sumNumbers(TreeNode root) {
        int[] sum = new int[]{0};
        sumNumbersHelper(root,0,sum);
        return sum[0];
    }

    public void sumNumbersHelper(TreeNode root, int currAns, int[] sum){

        if(root == null){
            return ;
        }

        if(root.left == null && root.right == null){
            int leafAns = currAns*10 + root.val;
            sum[0]+=leafAns;
            return;
        }

        int ans = currAns*10 + root.val;
        sumNumbersHelper(root.left,ans,sum);
        sumNumbersHelper(root.right,ans,sum);
    }

    //Leetcode 124
    public int maxPathSum(TreeNode root) {
        int[] maxSum = new int[]{Integer.MIN_VALUE};
        helper(root,maxSum); // return value is not important in this function
        return maxSum[0];
    }

    private int helper(TreeNode root, int[] maxSum) {
        if (root == null) {
            return 0; // null node contributes nothing to the sum
        }

        // Postorder traversal
        int leftSum = helper(root.left,maxSum);
        int rightSum = helper(root.right,maxSum);

        //Left Node ya Right Node
        int maxChildSum = Math.max(leftSum, rightSum);

        // We now need to compute three aspects:
        // Case 1: root is maximum
        int rootVal = root.val;
        // Case 2: the path includes node and left childAns
        int rootAndLeftVal = root.val + leftSum;
        // Case 3: the path includes node and right childAns
        int rootAndRightVal = root.val + rightSum;
        // Case 3: Path including Node, left and right childAns
        int rootAndLeftAndRightVal = root.val + leftSum + rightSum;

        maxSum[0] = Math.max(maxSum[0],Math.max(rootAndLeftVal,Math.max(rootAndRightVal,Math.max(rootAndLeftAndRightVal,rootVal))));

        // The question here is to find max path sum, and not max subtree sum
        // hence we return only a viable path sum to the parent node
        // Depending on the value of the nodes, this can either be the node itself,
        // or sum of the node and max of its children

        //Dono mein se koi ek child (jissey max result mile) ya fir bss root, kyoonki path toh tbhi bn paayega
        //Warna subtree reh jaayega
        return Math.max(root.val, root.val + maxChildSum);
    }

    //Leetcode 257
    class Solution {
        public List<String> binaryTreePaths(TreeNode root) {

            List<String> complete = new ArrayList<>();
            helper(root,complete,"");

            return complete;

        }

        void helper(TreeNode root,  List<String> complete, String ans) {

            if(root==null) return;

            if(root.left==null && root.right==null) {
                ans+=root.val;
                complete.add(ans);
            }

            ans = ans+root.val+"->";
            helper(root.left, complete, ans);
            helper(root.right, complete, ans);

        }
    }
}
