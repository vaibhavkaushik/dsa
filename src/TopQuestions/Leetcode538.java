package TopQuestions;

//Same as 1038
public class Leetcode538 {
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

    class SolutionMorris {
        public TreeNode convertBST(TreeNode root) {
            TreeNode cur= root;
            int sum = 0;
            while (cur != null) {
                if (cur.right == null) {
                    int tmp = cur.val;
                    cur.val += sum;
                    sum += tmp;
                    cur = cur.left;
                } else {
                    TreeNode prev = cur.right;
                    while (prev.left != null && prev.left != cur)
                        prev = prev.left;
                    if (prev.left == null) {
                        prev.left = cur;
                        cur = cur.right;
                    } else {
                        prev.left = null;
                        int tmp = cur.val;
                        cur.val += sum;
                        sum += tmp;
                        cur = cur.left;
                    }
                }
            }
            return root;
        }
    }
}
