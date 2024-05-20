package SearchingSorting;

import java.util.*;

public class SearchingSorting {

    //Leetcode 1295
    public int findNumbers(int[] nums) {
        int count = 0;
        for(int n : nums){
            int digits = (int)Math.floor(Math.log10(n) + 1);
            count = digits%2 == 0 ? count+1 : count;
        }
        return count;
    }

    //Leetcode 1672
        public int maximumWealth(int[][] accounts) {
            int max = 0;
            for (int[] customer : accounts) {
                int wealth = 0;
                for (int account : customer)
                    wealth += account;
                max = Math.max(max, wealth);
            }
            return max;
        }

    //Leetcode 35
    public int searchInsert(int[] nums, int target) {

        int left = 0;
        int found = 0;
        int right = nums.length-1;


        while(left <= right) {

            int mid = left + (right-left)/2;

            if(nums[mid]==target) {
                return mid;
            }

            if(target>nums[mid]) {
                left=mid+1;
            }

            if(target<nums[mid]) {
                right=mid-1;
            }


        }

        return left;

    }

    //Leetcode 2089
    public List<Integer> targetIndices(int[] nums, int target) {
        ArrayList<Integer> lst=new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++){
            if(nums[i]==target)
                lst.add(i);
        }
        return lst;
    }

    // This method finds the highest index 'mid' in the list where list.get(mid) <= x within the given range [beg, end].
    // If no such element is found, it returns 'end', which represents the index just before 'beg'.
    private int findIndexOfFloor(List<Integer> list, int x, int beg, int end) {
        int ans = -1; // Initialize ans to -1, indicating no valid index found initially.

        // Perform binary search within the range [beg, end].
        while (beg <= end) {
            int mid = (beg + end) / 2; // Calculate the middle index.

            // If the middle element is less than or equal to x, update ans and move beg to mid + 1.
            if (list.get(mid) <= x) {
                ans = mid;
                beg = mid + 1;
            } else {
                // Otherwise, move end to mid - 1.
                end = mid - 1;
            }
        }

        // Return the highest valid index found or end if no valid index is found.
        return end;
    }

    //Leetcode 2824
    // This method counts the number of pairs (i, j) in the list such that i < j and list.get(i) + list.get(j) < target.
    public int countPairs(List<Integer> list, int target) {
        Collections.sort(list); // Sort the list in ascending order.

        int ans = 0; // Initialize the count of valid pairs to 0.

        // Iterate through each element in the list except the last one.
        for (int i = 0; i < list.size() - 1; i++) {
            // Calculate the maximum value that list.get(j) can have to satisfy the condition.
            int maxVal = target - list.get(i) - 1;

            // Find the highest index j such that list.get(j) <= maxVal and j > i.
            int j = findIndexOfFloor(list, maxVal, i + 1, list.size() - 1);

            // If there are valid indices j such that j > i, increment ans by the number of such indices.
            if (j - i > 0) {
                ans += (j - i); // Add the count of valid pairs for this iteration.
            }
        }

        // Return the total count of valid pairs.
        return ans;
    }

    //Leetcode 1337
    /*
    Priority Queue Initialization:

Priority queue q banate hain jo pehle soldiers ke count (number of 1s) aur fir row ke index ke hisaab se sort karti hai.
Binary Search for Counting Soldiers:

Har row mein kitne soldiers hain, ye pata karne ke liye binary search ka use karte hain. Har row sorted hai isliye binary search fast hota hai.
Queue Population:

Har row ke soldiers count aur uska index ek array mein daal kar priority queue mein add karte hain. Isse weakest rows sabse pehle aayengi.
Extracting Weakest Rows:

Priority queue se k weakest rows nikaal kar unke indices output array mein store karte hain.

     */
    //Leetcode 1337
        public int[] kWeakestRows(int[][] mat, int k) {
            // Priority queue to store the rows with their soldier count and index.
            // The comparator sorts primarily by the number of soldiers and secondarily by the row index.
            PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

            int pos = 0; // Variable to track the row index.

            // Loop through each row in the matrix.
            for (int[] row : mat) {
                int lo = 0, hi = row.length;

                // Binary search to find the count of soldiers in the row.
                // Soldiers are represented by 1s, which are followed by 0s.
                while (lo < hi) {
                    int mid = (lo + hi) / 2;
                    if (row[mid] != 0) {
                        lo = mid + 1; // If mid element is 1, search in the right half.
                    } else {
                        hi = mid; // If mid element is 0, search in the left half.
                    }
                }
                // After the binary search, 'lo' contains the number of soldiers in the row.
                q.add(new int[]{lo, pos++});
            }

            // Array to store the indices of the k weakest rows.
            int[] output = new int[k];

            // Extract the indices of the k weakest rows from the priority queue.
            for (int i = 0; i < k; i++) {
                output[i] = q.remove()[1];
            }

            // Return the indices of the k weakest rows.
            return output;
        }

        //Leetcode 2389
    public int[] answerQueries(int[] nums, int[] queries) {

        //Since only count is required, we can sort the array
        Arrays.sort(nums);

        int ans[] = new int[queries.length];

        //Make nums array, a prefix sum array
        for(int i=1;i<nums.length;i++){
            nums[i] = nums[i] + nums[i-1];
        }

        for(int i=0;i<queries.length;i++){
            //Binary search on prefix array based on queries to find just exact or just smaller value index
            int start = 0;
            int end = nums.length - 1;
            int target = queries[i];

            while(start<=end){

                int mid = start + (end-start)/2;

                if(nums[mid] == target){
                    end = mid;
                    break;
                }

                if(target < nums[mid]){
                    end = mid-1;
                }

                if(target > nums[mid]){
                    start = mid+1;
                }

            }
            //index of end is either the index of exact value or just lesser value
            ans[i] = end+1;
        }

        return ans;

    }

    //Leetcode 2529
    public int maximumCount(int[] nums) {
        int start = 0, end = nums.length-1;
        int pos = 0, neg = 0;
        //Use binary search to find positive number
        while (start <= end) {
            int mid = (start + end)/2;
            if (nums[mid] > 0) {
                pos = nums.length - mid;
                end = mid - 1;
            }
            if (nums[mid] <= 0) {
                start = mid + 1;
            }
        }
        //Use binary search to find negative number
        int first = 0, last = nums.length-1;
        while (first <= last) {
            int mid = (first + last)/2;
            if (nums[mid] < 0) {
                neg = mid + 1;
                first = mid + 1;
            }
            if (nums[mid] >= 0) {
                last = mid - 1;
            }
        }
        return Math.max(pos,neg);
    }

    //Leetcode 1237
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> res = new ArrayList();
        //scan x values
        for (int x = 1; x <=1000; x++) {
            //check treshold values - if min > z or max < z
            if (customfunction.f(x, 1) > z || customfunction.f(x, 1000) < z)
                break;
            //init y values for binary search lookup
            int l = 1, r = 1000;
            //binary search loop
            while (l < r) {
                //mid point for this iteration
                int y = (r + l) /2;
                int z1 = customfunction.f(x, y);
                //we found the solution  for this x - move to x + 1
                if (z1 == z) {
                    List<Integer> sol = new ArrayList();
                    sol.add(x);
                    sol.add(y);
                    res.add(sol);
                    break;
                }
                //change search window for next binary search iteration
                if (z1 > z )
                    r = y;
                else
                    l = y + 1;
            }
        }
        return res;
    }
}
