package TopQuestions;

public class Leetcode1038 {
    class Solution {
        public TreeNode bstToGst(TreeNode root) {
            helper(root,new int[]{0});
            return root;
        }

        public void helper(TreeNode root, int[] currSum){

            if(root == null){
                return;
            }

            //Traverse to rightmost and then keep summing the values
            helper(root.right,currSum);
            currSum[0] = root.val+currSum[0];
            root.val = currSum[0];
            helper(root.left,currSum);

        }
    }
}
