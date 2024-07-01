package TopQuestions;

public class Leetcode108 {

    class Solution {
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
}
