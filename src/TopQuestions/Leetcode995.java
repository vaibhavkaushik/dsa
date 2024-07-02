package TopQuestions;

public class Leetcode995 {
class Solution {
    public int minKBitFlips(int[] nums, int k) {
        int count = 0;
        int flip = 0;
        int[] isFlipped = new int[nums.length];
        
        for (int i = 0; i < nums.length; i++) {
            if (i >= k) {
                flip ^= isFlipped[i - k];
            }
            if ((nums[i] == 0 && flip % 2 == 0) || (nums[i] == 1 && flip % 2 == 1)) {
                if (i + k > nums.length) {
                    return -1;
                }
                flip += 1;
                isFlipped[i] = 1;
                count++;
            }
        }
        
        return count;
    }
}
}
