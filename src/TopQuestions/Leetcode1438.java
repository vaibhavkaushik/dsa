class Solution {
    public int longestSubarray(int[] nums, int limit) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int left = 0, right = 0;
        int maxLength = 0;
        
        while (right < nums.length) {
            maxHeap.add(nums[right]);
            minHeap.add(nums[right]);
            
            while (!maxHeap.isEmpty() && !minHeap.isEmpty() && (maxHeap.peek() - minHeap.peek() > limit)) {
                maxHeap.remove(nums[left]);
                minHeap.remove(nums[left]);
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
            right++;
        }
        
        return maxLength;
    }
}
