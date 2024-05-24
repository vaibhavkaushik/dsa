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
            int yStart = 1, yEnd = 1000;
            //binary search loop
            while (yStart < yEnd) {
                //mid-point for this iteration
                int yMid = (yStart + yEnd) /2;
                int ansFromYStartToYMid = customfunction.f(x, yMid);
                //we found the solution  for this x - move to x + 1
                if (ansFromYStartToYMid == z) {
                    List<Integer> sol = new ArrayList();
                    sol.add(x);
                    sol.add(yMid);
                    res.add(sol);
                    break;
                }
                //change search window for next binary search iteration
                if (z < ansFromYStartToYMid)
                    yEnd = yMid;
                else
                    yStart = yMid + 1;
            }
        }
        return res;
    }


    //https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/solutions/769698/python-clear-explanation-powerful-ultimate-binary-search-template-solved-many-problems/
    //Leetcode 1011
    public int shipWithinDays(int[] weights, int days) {
        // Calculate maxWeight (max value in weights) and totalWeight (sum of all weights)
        int maxWeight = -1, totalWeight = 0;
        for (int weight : weights) {
            maxWeight = Math.max(maxWeight, weight);
            totalWeight += weight;
        }

        // Initialize binary search bounds
        int left = maxWeight, right = totalWeight;

        // Perform binary search
        while (left < right) {
            int mid = (left + right) / 2;
            int daysNeeded = 1, currWeight = 0;

            // Calculate daysNeeded for current mid capacity
            for (int weight : weights) {
                if (currWeight + weight > mid) {
                    // If adding this weight exceeds capacity, increment daysNeeded and reset currWeight
                    daysNeeded++;
                    currWeight = 0;
                }
                currWeight += weight;
            }

            // Adjust binary search bounds based on daysNeeded
            if (daysNeeded > days) {
                left = mid + 1; // Increase capacity
            } else {
                right = mid; // Decrease capacity or keep it same
            }
        }

        // left will be the minimum capacity needed
        return left;
    }


    /*
find the number of invalid elements
elements in arr1 is considered valid if the distance between any elements in arr2 is greater than d
and if there exists a distance of an element in arr1 and an element in arr2 that's less than or equal to d, it's an invalid element
the rest of elements in arr1 are considered valid
to find the invalid elements
sort arr2 so we can perform binary search
if the distance between a and current element in arr2 is less than or equal to d, then it's an invalid
else ( means distance is greater than d)
check if distance is negative
means we're at the left side and the ones smaller than current element must have greater distance, so we just want to look at the right side
check if distance is positive
means we're at the right side and the ones greater than current element must have greater distance, so we just want to look at the left side
*/
    //Leetcode 1385
        public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
            int invalid = 0;
            Arrays.sort(arr2);
            for (int a: arr1) {
                if (isInvalid(arr2, a, d))
                    invalid++;
            }
            return arr1.length-invalid;
        }


        private boolean isInvalid(int[] arr, int num, int d) {
            int left = 0, right = arr.length-1;
            while (left <= right) {
                int mid = left + (right-left)/2;
                int distance = arr[mid] - num;
                if (Math.abs(distance) <= d)
                    return true;
                else if (distance < 0)
                    left = mid+1;
                else
                    right = mid-1;
            }
            return false;
        }

        //Leetcode 786 (Unoptimized)
        public int[] kthSmallestPrimeFraction(int[] arr, int k) {
            PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));

            for (int i = 0; i < arr.length; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    double div = (double) arr[i] / arr[j];
                    pq.offer(new double[]{div, (double) arr[i], (double) arr[j]});
                    if (pq.size() > k)
                        pq.poll();
                }
            }

            double[] vec = pq.peek();
            int[] result = new int[2];
            result[0] = (int) vec[1];
            result[1] = (int) vec[2];
            return result;
        }

        //Leetcode 786
    public int[] kthSmallestPrimeFractionOptimized(int[] arr, int k) {
        int n = arr.length;
        PriorityQueue<double[]> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> a[0]));

        for (int i = 0; i < n; i++)
            pq.offer(new double[]{1.0 * arr[i] / arr[n - 1], (double) i, (double) (n - 1)});

        int smallest = 1;

        while (smallest < k) {
            double[] vec = pq.poll();

            int i = (int) vec[1];
            int j = (int) vec[2] - 1;

            pq.offer(new double[]{1.0 * arr[i] / arr[j], (double) i, (double) j});
            smallest++;
        }

        double[] vec = pq.peek();
        int i = (int) vec[1];
        int j = (int) vec[2];
        return new int[]{arr[i], arr[j]};
    }

    //Leetcode 786
    public int[] kthSmallestPrimeFractionBest(int[] arr, int k) {
        int n = arr.length;
        double left = 0, right = 1, mid;
        int[] res = new int[2];

        while (left <= right) {
            mid = left + (right - left) / 2;
            int j = 1, total = 0, numerator = 0, denominator = 0;
            double maxFrac = 0;
            for (int i = 0; i < n; ++i) {
                while (j < n && arr[i] >= arr[j] * mid) {
                    ++j;
                }

                total += n - j;

                if (j < n && maxFrac < arr[i] * 1.0 / arr[j]) {
                    maxFrac = arr[i] * 1.0 / arr[j];
                    numerator = i;
                    denominator = j;
                }
            }

            if (total == k) {
                res[0] = arr[numerator];
                res[1] = arr[denominator];
                break;
            }

            if (total > k) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return res;
    }

    //Leetcode 1539
    //https://www.youtube.com/watch?v=iWT1cH6LXLc
    public int findKthPositive(int[] arr, int k) {
        int n = arr.length;
        int left = 0, right = n - 1;

        // Binary search to find the position where k-th missing number would be
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check how many numbers are missing until arr[mid]
            if (arr[mid] - (mid + 1) < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // Return the k-th missing number
        return left + k;
    }



    public int getCommon(int[] nums1, int[] nums2) {
        for(int i=0;i<nums1.length;i++){
            if(isPresent(nums2,0,nums2.length-1,nums1[i])){
                return nums1[i];
            }
        }

        return -1;
    }

    //Leetcode 2540
    public boolean isPresent(int arr[], int start, int end, int target){

        while(start<=end){

            int mid = start + (end-start)/2;

            if(arr[mid]==target){
                return true;
            }

            if(target < arr[mid]){
                end = mid-1;
            }

            if(target > arr[mid]){
                start = mid+1;
            }
        }

        return false;
    }

    //Leetcode 2358
    public int maximumGroups(int[] grades) {
        int size = grades.length;
        int probableSize = (int)Math.sqrt(2*size);

        int requiredValue = probableSize*(probableSize+1);
        if(2*size < requiredValue){
            return probableSize-1;
        }else{
            return probableSize;
        }
    }

    //Leetcode 2024
    //TLE Approach
    //But intuition based
    public class Solution2024 {
        private int ans = 0;
        private int n;

        // Helper function to find the maximum length of consecutive 'T' or 'F'
        private void findMax(String answerKey) {
            int length = 0;
            int i = 0;

            while (i < n) {
                if (answerKey.charAt(i) == 'T') {
                    length = 1;
                    i++;
                    while (i < n && answerKey.charAt(i) == 'T') {
                        length++;
                        i++;
                    }
                    ans = Math.max(ans, length);
                } else {
                    length = 1;
                    i++;
                    while (i < n && answerKey.charAt(i) == 'F') {
                        length++;
                        i++;
                    }
                    ans = Math.max(ans, length);
                }
            }
        }

        // Recursive function to explore flipping options
        private void solve(int idx, StringBuilder answerKey, int k) {
            findMax(answerKey.toString());

            if (idx >= n || k <= 0) {
                return;
            }

            // Flip the current character
            answerKey.setCharAt(idx, answerKey.charAt(idx) == 'T' ? 'F' : 'T');
            solve(idx + 1, answerKey, k - 1);

            // Flip the character back
            answerKey.setCharAt(idx, answerKey.charAt(idx) == 'T' ? 'F' : 'T');
            solve(idx + 1, answerKey, k);
        }

        // Main function to find the maximum length of consecutive 'T' or 'F' after k flips
        public int maxConsecutiveAnswers(String answerKey, int k) {
            n = answerKey.length();
            StringBuilder answerKeyBuilder = new StringBuilder(answerKey);

            solve(0, answerKeyBuilder, k);

            return ans;
        }
    }

    //Leetcode 2024
    public class SolutionTwoSlidingWindow {
        public int maxConsecutiveAnswers(String answerKey, int k) {
            int n = answerKey.length();
            int result = 0;

            // First pass for flipping 'F' -> 'T'
            int i = 0, j = 0;
            int countF = 0;

            while (j < n) {
                if (answerKey.charAt(j) == 'F')
                    countF++;

                while (countF > k) {
                    if (answerKey.charAt(i) == 'F')
                        countF--;
                    i++;
                }

                result = Math.max(result, j - i + 1);
                j++;
            }

            // Second pass for flipping 'T' -> 'F'
            i = 0;
            j = 0;
            int countT = 0;

            while (j < n) {
                if (answerKey.charAt(j) == 'T')
                    countT++;

                while (countT > k) {
                    if (answerKey.charAt(i) == 'T')
                        countT--;
                    i++;
                }

                result = Math.max(result, j - i + 1);
                j++;
            }

            return result;
        }
    }

    //Leetcode 2024 (One pass sliding window)
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int result = k;

        Map<Character, Integer> mp = new HashMap<>();

        int i = 0;
        for (int j = 0; j < answerKey.length(); j++) {
            mp.put(answerKey.charAt(j), mp.getOrDefault(answerKey.charAt(j), 0) + 1);

            while (Math.min(mp.getOrDefault('T', 0), mp.getOrDefault('F', 0)) > k) {
                mp.put(answerKey.charAt(i), mp.get(answerKey.charAt(i)) - 1);
                i++;
            }

            result = Math.max(result, j - i + 1);
        }

        return result;
    }

    //Leetcode 744
    //Dry run is required to understand and solve
    public char nextGreatestLetter(char[] letters, char target) {

        int start =0;
        int end = letters.length-1;

        while(start <= end){

            int mid = start + (end-start)/2;

            if(target < letters[mid]){
                end = mid-1;
            }

            if(target >= letters[mid]){
                start = mid+1;
            }

        }
        //Finally after search is performed, we will have situation like this
        // end number start
        int idx = start == letters.length ? 0 : start;
        return letters[idx];
    }


    //Leetcode 34
    public int[] searchRange(int[] nums, int target) {

        int[] ans = {-1, -1};
        // check for first occurrence if target first
        ans[0] = search(nums, target, true);
        if (ans[0] != -1) {
            ans[1] = search(nums, target, false);
        }
        return ans;
    }

    // this function just returns the index value of target
    int search(int[] nums, int target, boolean findStartIndex) {
        int ans = -1;
        int start = 0;
        int end = nums.length - 1;
        while(start <= end) {
            // find the middle element
//            int mid = (start + end) / 2; // might be possible that (start + end) exceeds the range of int in java
            int mid = start + (end - start) / 2;

            if (target < nums[mid]) {
                end = mid - 1;
            } else if (target > nums[mid]) {
                start = mid + 1;
            } else {
                // potential ans found
                ans = mid;
                if (findStartIndex) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return ans;
    }

    //If we have infinite array, and we need to apply binary search
    //we need to think of expanding our search window, start from 1 length, keep increasing by 2 times
    //until target element lies in that range

    //Leetcode 852
    //Leetcode 162
    public int peakIndexInMountainArray(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;
            // 1 2 3 4 5 6 5 4 3 2 1
            //           -----------
            if (arr[mid] > arr[mid+1]) {
                // you are in dec part of array
                // this may be the ans, but look at left
                // this is why end != mid - 1
                end = mid;
            } else {
                // 1 2 3 4 5 6 5 4 3 2 1
                // ---------
                // you are in asc part of array
                start = mid + 1; // because we know that mid+1 element > mid element
            }
        }
        // in the end, start == end and pointing to the largest number because of the 2 checks above
        // start and end are always trying to find max element in the above 2 checks
        // hence, when they are pointing to just one element, that is the max one because that is what the checks say
        // more elaboration: at every point of time for start and end, they have the best possible answer till that time
        // and if we are saying that only one item is remaining, hence cuz of above line that is the best possible ans
        return start; // or return end as both are =
    }

    //Leetcode 33
    int search(int[] nums, int target) {
        int pivot = findPivot(nums);

        // if you did not find a pivot, it means the array is not rotated
        if (pivot == -1) {
            // just do normal binary search
            return binarySearch(nums, target, 0 , nums.length - 1);
        }

        // if pivot is found, you have found 2 asc sorted arrays
        if (nums[pivot] == target) {
            return pivot;
        }

        if (target >= nums[0]) {
            return binarySearch(nums, target, 0, pivot - 1);
        }

        return binarySearch(nums, target, pivot + 1, nums.length - 1);
    }

    int binarySearch(int[] arr, int target, int start, int end) {
        while(start <= end) {
            // find the middle element
//            int mid = (start + end) / 2; // might be possible that (start + end) exceeds the range of int in java
            int mid = start + (end - start) / 2;

            if (target < arr[mid]) {
                end = mid - 1;
            } else if (target > arr[mid]) {
                start = mid + 1;
            } else {
                // ans found
                return mid;
            }
        }
        return -1;
    }

    int findPivot(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            // 4 cases over here
            if (mid < end && arr[mid] > arr[mid + 1]) {
                return mid;
            }
            if (mid > start && arr[mid] < arr[mid - 1]) {
                return mid-1;
            }
            if (arr[mid] <= arr[start]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }


    //Leetcode 1095
    public int findInMountainArray(int target, MountainArray mountainArr) {

        int peak = peakIndexInMountainArray(mountainArr);
        int firstTry = orderAgnosticBS(mountainArr, target, 0, peak);
        if (firstTry != -1) {
            return firstTry;
        }
        // try to search in second half
        return orderAgnosticBS(mountainArr, target, peak+1, mountainArr.length() - 1);
    }

    public int peakIndexInMountainArray(MountainArray mountainArr) {
        int start = 0;
        int end = mountainArr.length()  - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (mountainArr.get(mid) > mountainArr.get(mid+1)) {
                // you are in dec part of array
                // this may be the ans, but look at left
                // this is why end != mid - 1
                end = mid;
            } else {
                // you are in asc part of array
                start = mid + 1; // because we know that mid+1 element > mid element
            }
        }
        // in the end, start == end and pointing to the largest number because of the 2 checks above
        // start and end are always trying to find max element in the above 2 checks
        // hence, when they are pointing to just one element, that is the max one because that is what the checks say
        // more elaboration: at every point of time for start and end, they have the best possible answer till that time
        // and if we are saying that only one item is remaining, hence cuz of above line that is the best possible ans
        return start; // or return end as both are =
    }

    static int orderAgnosticBS(MountainArray mountainArr, int target, int start, int end) {
        // find whether the array is sorted in ascending or descending
        boolean isAsc = mountainArr.get(start) < mountainArr.get(end);

        while(start <= end) {
            // find the middle element
//            int mid = (start + end) / 2; // might be possible that (start + end) exceeds the range of int in java
            int mid = start + (end - start) / 2;

            if (mountainArr.get(mid) == target) {
                return mid;
            }

            if (isAsc) {
                if (target < mountainArr.get(mid)) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target > mountainArr.get(mid)) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return -1;
    }

    //Leetcode 33
    //https://www.youtube.com/watch?v=U1VsdRgVevY
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        /* . */
        int left = 0, right = nums.length - 1;
        // when we use the condition "left <= right", we do not need to determine if
        // nums[left] == target
        // in outside of loop, because the jumping condition is left > right, we will
        // have the determination
        // condition if(target == nums[mid]) inside of loop
        while (left <= right) {
            // left bias
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                return mid;
            }
            // if left part is monotonically increasing, or the pivot point is on the right
            // part
            if (nums[left] <= nums[mid]) {
                // must use "<=" at here since we need to make sure target is in the left part,
                // then safely drop the right part
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    // right bias
                    left = mid + 1;
                }
            }

            // if right part is monotonically increasing, or the pivot point is on the left
            // part
            else {
                // must use "<=" at here since we need to make sure target is in the right part,
                // then safely drop the left part
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    //Leetcode 81
    public boolean searchII(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return true;
            }

            while (start < mid && nums[start] == nums[mid] && nums[end] == nums[mid]) {
                start++;
                end--;
            }

            if (nums[start] <= nums[mid]) {
                // Left side is sorted. Right side is unsorted.
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                // Left side is unsorted. Right side is sorted.
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return false;
    }

    //Leetcode 278
    public int firstBadVersion(int n) {
        int start = 1;
        int end = n;
        int firstBad = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            //This can be my potential bad, but since i need first bad, i will still go left and check
            if (isBadVersion(mid)) {
                firstBad = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return firstBad;
    }

    //Leetcode 1268
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> res = new ArrayList();
        for (int i=0; i<searchWord.length();i++) {
            String prefix= searchWord.substring(0,i+1);
            int index= binarySearch(products, prefix);
            List<String> list = new ArrayList();
            for (int j=index; j<products.length && list.size()<3; j+=1) {
                // check if prefix
                if (isPrefix(products[j], prefix)) {
                    list.add(products[j]);
                    continue;
                }
                break;
            }
            res.add(list);
        }
        return res;
    }

    private int binarySearch(String[] products, String prefix) {
        int left=0, right=products.length-1,mid=0;
        while(left<=right) {
            mid = left + (right-left)/2;
            if (products[mid].compareTo(prefix)>=0) {
                // higher or same.
                right =mid-1;
            } else {
                left=mid+1;
            }
        }
        return right+1;
    }

    private boolean isPrefix(String s, String prefix) {
        return s.startsWith(prefix);
    }

    //Leetcode 2517
    // Function to find the maximum tastiness of the candy basket
    public int maximumTastiness(int[] price, int k) {
        // Sort the array of candy prices
        Arrays.sort(price);

        // Initialize variables for binary search
        int left = 0;
        int right = price[price.length - 1] - price[0];
        int answer = 0;

        // Perform binary search to find the maximum minimum difference
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // Check if it's possible to select k candies with at least mid difference
            if (isFeasible(mid, price, k)) {
                answer = mid; // If possible, update the answer
                left = mid + 1; // Try for a larger minimum difference
            } else {
                right = mid - 1; // Otherwise, try for a smaller minimum difference
            }
        }

        return answer; // Return the maximum minimum difference found
    }

    // Helper function to check if it's possible to select k candies with at least gap difference
    public boolean isFeasible(int gap, int[] price, int k) {
        int count = 1; // Start by selecting the first candy
        int lastSelectedPrice = price[0]; // Price of the last selected candy

        // Iterate through the prices to count how many candies can be selected
        for (int i = 1; i < price.length; i++) {
            // If the difference between the current candy price and the last selected candy price is at least gap
            if (price[i] - lastSelectedPrice >= gap) {
                count++; // Increment the count of selected candies
                lastSelectedPrice = price[i]; // Update the last selected candy price
                if (count >= k) { // If we've selected k candies, return true
                    return true;
                }
            }
        }

        return count >= k; // Return true if at least k candies were selected, otherwise false
    }
    //Leetcode 378
    // Function to find the k-th smallest element in a sorted matrix
    public int kthSmallest(int[][] matrix, int k) {
        // Create a max heap (priority queue with descending order)
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        int n = matrix.length; // Get the size of the matrix

        // Iterate through the matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Add the current element to the priority queue
                pq.add(matrix[i][j]);

                // If the size of the priority queue exceeds k, remove the top element
                // This keeps the k smallest elements in the priority queue
                if (pq.size() > k) {
                    pq.poll();
                }
            }
        }

        // The top element of the priority queue is the k-th smallest element
        return pq.peek();
    }

    //Leetcode 378
    public int kthSmallestOptimised(int[][] matrix, int k) {
        //Step 1
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n-1][n-1];
        while(left <= right)
        {
            //Step 2
            int mid = left + (right - left)/2;
            int count = 0;
            int j = n-1;

            //Step 3
            for(int i = 0; i < n; i++)
            {
                while(j >= 0 && matrix[i][j] > mid)
                {
                    j--;
                }
                count += j + 1;
            }
            //Step 4
            if(count < k) left = mid + 1;
            else right = mid - 1;
        }
        //Step 5
        return left;
    }

    //Leetcode 1170
    public int[] numSmallerByFrequency(String[] queries, String[] words) {

        int[] queriesSmallestCounter = new int[queries.length];
        int[] wordsSmallestCounter = new int[words.length];

        for (int i = 0; i < queriesSmallestCounter.length; i++) {
            queriesSmallestCounter[i] = getFunc(queries[i]);
        }
        for (int i = 0; i < wordsSmallestCounter.length; i++) {
            wordsSmallestCounter[i] = getFunc(words[i]);
        }

        Arrays.sort(wordsSmallestCounter);

        int[] ans = new int[queriesSmallestCounter.length];
        for (int i = 0; i < queriesSmallestCounter.length; i++) {
            int l = 0, r = wordsSmallestCounter.length - 1;
            while (l <= r) {
                int mid = (r + l) / 2;
                if (queriesSmallestCounter[i] >= wordsSmallestCounter[mid]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            ans[i] = wordsSmallestCounter.length - l;
        }
        return ans;
    }

    int getFunc(String s) {
        char chr = 'z';
        int count = 0;
        //iterate over every character, keep current running smallest character. If we found the same one
        //increment count, if lesser - start counter from 1. If this character is greater - ignore it
        for (char ch : s.toCharArray()) {
            if (ch < chr) {
                count = 1;
                chr = ch;
            } else if (chr == ch) {
                count++;
            }
        }
        return count;
    }

    /*
Problem Statement Simplified:
Imagine Alice aur Bob ke paas candies hain. Har candy ka ek specific size hai. Alice aur Bob chahte hain ki kuch candies swap karein taaki dono ke paas candies ka total size barabar ho jaaye.

Problem ko Simple Example ke Saath Samjho:
Suppose Alice ke paas candies ka array A = [1, 1] aur Bob ke paas candies ka array B = [2, 2] hai.

Step 1: Calculate the Total Sum of Candies:

Alice ke candies ka total size sumA = 1 + 1 = 2.
Bob ke candies ka total size sumB = 2 + 2 = 4.
Step 2: Find the Difference:

Hum chahte hain ki swap ke baad Alice aur Bob ke paas barabar size ki candies ho. Toh yeh tabhi possible hai jab dono ke total sum ka difference adjust ho jaye.
Difference delta = (sumB - sumA) / 2 = (4 - 2) / 2 = 1.
Matlab agar Alice Bob ko ek candy de jo 1 unit choti hai Bob ki kisi candy se, to dono ka total sum barabar ho jayega.

Step 3: Swap Logic:

Ab humein dekhna hai ki kya Alice ke paas aisi koi candy hai jise swap karke yeh condition fulfill ho sake. Matlab:
Alice ka candy x ho.
Bob ka candy y ho.
Condition yeh hogi: y = x + delta.
Is case mein, delta = 1. Matlab Bob ko Alice se aisa candy lena chahiye jo uske candy se 1 unit choti ho.
Practical Steps:
Set Creation:

Pehle Bob ke candies ko ek set mein daal do. Yeh fast lookup ke liye helpful hoga.
Find Suitable Swap Pair:

Ab Alice ke har candy ke liye check karo ki kya Bob ke set mein aisa candy hai jo y = x + delta condition ko fulfill kare.
Agar aisa candy milta hai, toh wahi swap pair hoga jo solution hoga.
Example ko Aur Samjho:
Example 1: A = [1, 2], B = [2, 3]
Step 1: Calculate Sums:

sumA = 1 + 2 = 3
sumB = 2 + 3 = 5
Step 2: Calculate Delta:

delta = (sumB - sumA) / 2 = (5 - 3) / 2 = 1
Step 3: Find Swap Pair:

Alice ke paas candies [1, 2] hain.
Bob ke set mein candies [2, 3] hain.
Alice ka pehla candy 1:
Bob ke set mein dekhte hain kya 1 + 1 (delta) = 2 hai? Haan, hai.
Swap pair found: [1, 2].
Alice ka dusra candy 2:
Bob ke set mein dekhte hain kya 2 + 1 (delta) = 3 hai? Haan, hai.
Swap pair found: [2, 3].
Conclusion:
Is tarah se, problem ka solution dhoondhne ka idea yeh hai ki hum total sums ka difference calculate karein, phir check karein ki Alice aur Bob ke candies mein aisi koi pair hai jo swap hone ke baad difference ko equalize kar sake.

Deep Understanding:
Sum Calculation: Sum nikalne se humein total candies ka size pata chalta hai.
Difference (delta): Yeh difference humein guide karta hai ki kis tarah ka swap humein karna chahiye.
Set Usage: Set ka use karke, fast lookup ensure hota hai, jo ki humein quickly swap pairs dhoondhne mein help karta hai.
Finding Swap Pairs: Har candy ke liye potential swap pair check karna, condition ke saath ki swap ke baad difference adjust ho jaaye.
I hope yeh detailed explanation aapko samajh mein aaya hoga. Agar aur koi doubts ya questions hain, toh zaroor batao!
*/
    //Leetcode 888
    class Solution {
        private int binarySearch(int[] arr,int target){

            int s = 0, e = arr.length-1;

            while(s <= e)
            {
                int mid = s + (e-s)/2;
                if(arr[mid] == target) return mid;
                else if(arr[mid] < target) s=mid+1;
                else e = mid - 1;
            }
            return -1;
        }

        public int[] fairCandySwap(int[] a, int[] b) { //a = Alice , b=Bob

            int suma=0,sumb=0;
            for(int i = 0; i < a.length; i++)
                suma += a[i];

            for(int i = 0; i < b.length; i++)
                sumb +=b [i];

            int diff = (suma - sumb)/2;

            Arrays.sort(a);
            for(int num : b)
            {
                if (binarySearch(a, num + diff) !=-1)
                    return new int[] { num + diff, num };
            }

            return null;
        }
    }

    //Leetcode 2187
    public long minimumTime(int[] time, int totalTrips) {
        // Sort the array to get the minimum time element easily
        // Arrays.sort(time);

        // Initialize the minimum and maximum possible time
        long minTime = 1;
        long maxTime = (long) Arrays.stream(time).min().getAsInt() * totalTrips;

        // Binary search for the minimum time
        while (minTime < maxTime) {
            // Calculate the middle time
            long midTime = minTime + (maxTime - minTime) / 2;

            // Calculate the trips that can be completed in midTime
            long tripsThatCanBeCompletedInThisTime = tripsCompleted(time, midTime);

            // If we can complete at least totalTrips in midTime, reduce the maxTime
            if (tripsThatCanBeCompletedInThisTime >= totalTrips) {
                maxTime = midTime;
            } else { // Otherwise, increase the minTime
                minTime = midTime + 1;
            }
        }

        // Return the minimum time found
        return minTime;
    }

    // Helper method to calculate how many trips can be completed in the given time
    public long tripsCompleted(int[] trips, long time) {
        long ans = 0;
        for (int trip : trips) {
            ans += time / trip;
        }
        return ans;
    }

}
