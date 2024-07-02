package TopQuestions;

public class Leetcode995 {
    class Solution {
            public int minKBitFlips(int[] nums, int k) {
            int count = 0;
            int flipCount = 0;
            int[] isFlipped = new int[nums.length];
            
            for (int i = 0; i < nums.length; i++) {
                //i>=k ka mtlb hua ki window exceed karli, and agar window exceed krli hai toh iska mtlb ye hai ki flipCount   
                //change karna padega, kyoonki ab jo new window start hoyegi, uspe k index pehle ka fark nahi padna chahiye
                //Ab agar flipCount 1 hai to 0 banao, 0 hai toh 1 banao
                if (i >= k) {
                    flipCount ^= isFlipped[i - k];
                }
                if ((nums[i] == 0 && flipCount % 2 == 0) || (nums[i] == 1 && flipCount % 2 == 1)) {
                    if (i + k > nums.length) {
                        return -1;
                    }
                    flipCount += 1;
                    isFlipped[i] = 1;
                    count++;
                }
            }
            
            return count;
        }
    }
}
