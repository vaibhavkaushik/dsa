package TopQuestions;

public class Leetcode3191 {
    class Solution {
        public int minOperations(int[] nums) {
            int count = 0;
            int ones = 0;
            for(int i=0; i<=nums.length-3; i++){
                if(nums[i] == 1){
                    continue;
                }
                for(int j=i; j<i+3; j++){
                    nums[j] = 1-nums[j];
                }
                count++;
            }

            for(int i=0; i<nums.length; i++){
                if(nums[i]==1){
                    ones++;
                }
            }
            return ones == nums.length ? count : -1;
        }
    }
}
