package SlidingWindow;

import java.util.*;

public class SlidingWindow {

    //Count Occurences of Anagrams
    //https://www.geeksforgeeks.org/problems/count-occurences-of-anagrams5839/1
    /*
    Given a word pat and a text txt. Return the count of the occurrences of anagrams of the word in the text.

    Example 1:

    Input:
    txt = forxxorfxdofr
    pat = for
    Output: 3
    Explanation: for, orf and ofr appears
    in the txt, hence answer is 3.

    Problem Statement:
    Tumhe ek string text aur ek string pattern di gayi hai. Tumhe yeh find karna hai ki pattern ke kitne anagrams text mein present hain.

    Example:
    Input: text = "cbaebabacd", pattern = "abc"
    Output: 2
    Explanation:
    Anagrams of "abc" in "cbaebabacd" are: "cba", "bac"
    Brute Force Approach:
    Generate All Substrings:
    text ke saare substrings of length pattern.length() generate karo.
    Check Anagram:
    Har substring ko check karo agar wo pattern ka anagram hai.
    Count Matches:
    Anagrams ke matches count karo.
    */
     public int countAnagramsBruteForce(String text, String pattern) {
            int patternLength = pattern.length();
            int count = 0;

            // Loop through each substring of length patternLength
            for (int i = 0; i <= text.length() - patternLength; i++) {
                String substring = text.substring(i, i + patternLength);
                if (isAnagram(substring, pattern)) {
                    count++;
                }
            }

            return count;
        }

        // Helper function to check if two strings are anagrams
        private boolean isAnagram(String s1, String s2) {
            char[] arr1 = s1.toCharArray();
            char[] arr2 = s2.toCharArray();
            Arrays.sort(arr1);
            Arrays.sort(arr2);
            return Arrays.equals(arr1, arr2);
        }

    //    Optimal Approach (Sliding Window + Hashing):
    //    Brute force approach time-consuming hoti hai, kyunki hum har substring ko check karte hain. Ab hum optimal solution dekhte hain jo sliding window aur hashing ka use karta hai.
    //
    //    Create Frequency Maps:
    //    Ek frequency map pattern ke characters ke liye banao.
    //    Ek current window ke characters ke liye banao jo text mein hai.
    //    Sliding Window:
    //    Ek window of size pattern.length() ko text pe slide karo aur check karo ki window aur pattern ke frequency maps match karte hain ya nahi.
    //    Update Window:
    //    Window slide hone ke baad old character ko remove karo aur new character ko add karo.

     public static int countAnagramsOptimal(String text, String pattern) {
            int patternLength = pattern.length();
            int textLength = text.length();

            // Edge case
            if (patternLength > textLength) {
                return 0;
            }

            Map<Character, Integer> patternCount = new HashMap<>();
            Map<Character, Integer> windowCount = new HashMap<>();

            // Frequency map for the pattern
            for (char c : pattern.toCharArray()) {
                patternCount.put(c, patternCount.getOrDefault(c, 0) + 1);
            }

            int count = 0;

            // Initial window setup
            for (int i = 0; i < patternLength; i++) {
                char c = text.charAt(i);
                windowCount.put(c, windowCount.getOrDefault(c, 0) + 1);
            }

            // Check initial window
            if (patternCount.equals(windowCount)) {
                count++;
            }

            // Slide the window
            for (int i = patternLength; i < textLength; i++) {
                char newChar = text.charAt(i);
                char oldChar = text.charAt(i - patternLength);

                // Add new character to the window
                windowCount.put(newChar, windowCount.getOrDefault(newChar, 0) + 1);

                // Remove old character from the window
                if (windowCount.get(oldChar) == 1) {
                    windowCount.remove(oldChar);
                } else {
                    windowCount.put(oldChar, windowCount.get(oldChar) - 1);
                }

                // Compare window with pattern frequency map
                if (patternCount.equals(windowCount)) {
                    count++;
                }
            }

            return count;
        }

    // Yeh method check karta hai ki saare elements count array mein zero hain ya nahi
    // Agar sab zero hain, to iska matlab valid anagram hai
    private boolean allZero(int[] count) {
        for (int num : count) {
            if (num != 0) { // Agar koi count zero nahi hai, to anagram nahi hai
                return false;
            }
        }
        return true; // Saare counts zero hain, matlab valid anagram hai
    }

    // Method jo pattern `pat` ka text `txt` mein anagram occurrences count karega
    int search(String pat, String txt) {
        int k = pat.length(); // Pattern ka length
        int[] count = new int[26]; // Frequency array jo har character (a-z) ka count rakhega

        // Count array ko pattern ke characters ke frequency se initialize karna
        for (char ch : pat.toCharArray()) {
            count[ch - 'a']++; // Corresponding character ke count ko increment karo
        }

        int i = 0, j = 0; // Sliding window ke liye do pointers
        int n = txt.length(); // Text ka length
        int result = 0; // Result jo count store karega

        while (j < n) { // Jab tak j text ke end tak nahi pahuncha
            int idx = txt.charAt(j) - 'a'; // Current character ka index count array mein
            count[idx]--; // Current character ka count decrement karo

            if (j - i + 1 == k) { // Agar window ka size pattern ke length ke barabar hai
                if (allZero(count)) { // Agar saare counts zero hain, to valid anagram hai
                    result++; // Result increment karo
                }

                // Window ko slide karo
                count[txt.charAt(i) - 'a']++; // Leftmost character ka count increment karo
                i++; // Left pointer ko aage badhao
            }
            j++; // Right pointer ko aage badhao
        }
        return result; // Final result return karo
    }


    //Leetcode 2932
    public int maximumStrongPairXor(int[] nums) {

        //The brute force solution for this one is very easy
        //Let's think of sliding window
        Arrays.sort(nums);

        //window start and end initialised to 0
        int ws = 0;
        int we = 0;
        int res = 0;
        while(we < nums.length){

            //Agar |x-y|<=min(x,y) condition satisfy karni hai toh ye check krna padega
            int searchNumberUpto = nums[ws]*2;
            while(we < nums.length && nums[we] <= searchNumberUpto){
                res = Math.max(res,nums[ws]^nums[we]);
                we++;
            }

            //reset pointers once the calculation for 1 window is done
            ws++;
            we = ws;
        }

        return res;
    }

    //Leetcode 438
    public List<Integer> findAnagrams(String s, String p) {

        //Alphabet counter
        int[] counter = new int[26];

        //p stirng mein characters ki occurrences count krlo
        for(Character c : p.toCharArray()){
            counter[c-'a']++;
        }

        List<Integer> ans = new ArrayList<>();

        //Window start and end
        int ws = 0;
        int we = 0;
        int wl = p.length();

        while(we < s.length()){

            //Decrement the occurrences of s character occurrences from p characters count
            counter[s.charAt(we)-'a']--;

            //Reached window length
            if(we - ws + 1 == wl){

                if(allZero(counter)){
                    ans.add(ws);
                }

                // Window ko slide karo
                counter[s.charAt(ws) - 'a']++; // Leftmost character ka count increment karo
                ws++; // Left pointer ko aage badhao
            }

            we++;

        }

        return ans;

    }

    //Leetcode 209
    public int minSubArrayLen(int target, int[] nums) {
        // Window ke start aur end pointers
        int ws = 0;
        int we = 0;

        // Current window ka sum aur minimum length jo mil rahi hai
        int currSum = 0;
        int minLen = Integer.MAX_VALUE;

        // Window end pointer se array ko traverse karte hain
        while (we < nums.length) {
            // Window ke sum mein current element add karte hain
            currSum += nums[we];

            // Jab tak current sum target se bada ya barabar hai
            while (currSum >= target) {
                // Minimum length ko update karte hain
                minLen = Math.min(minLen, we - ws + 1);

                // Window start pointer ko move karke current sum se element remove karte hain
                currSum -= nums[ws];
                ws++;
            }

            // Window end pointer ko increment karte hain
            we++;
        }

        // Agar minimum length update nahi hui, iska matlab koi subarray nahi mila
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    //https://geeksforgeeks.org/problems/first-negative-integer-in-every-window-of-size-k3345/1
    //First negative integer in every window of size k
    public static List<Long> printFirstNegativeInteger(long[] A, int N, int k) {
        // Negative integers ko store karne ke liye LinkedList use kar rahe hain
        LinkedList<Long> dll = new LinkedList<>();
        List<Long> result = new ArrayList<>();

        int i = 0, j = 0;

        // Sliding window approach use karte hue array traverse kar rahe hain
        while (j < N) {
            // Agar current element negative hai to usse list mein add karte hain
            if (A[j] < 0) {
                dll.add(A[j]);
            }

            // Agar window ka size k ke barabar ho jata hai
            if (j - i + 1 == k) {
                // List ka first element (front) current window ka first negative element hai
                long neg = dll.isEmpty() ? 0 : dll.peekFirst();
                result.add(neg);

                // Window ke start element ko check karte hain agar wo negative hai aur list mein hai to remove karte hain
                if (A[i] < 0 && !dll.isEmpty()) {
                    dll.pollFirst();
                }

                // Window ko slide karte hain
                i++;
            }

            // Window ke end pointer ko increment karte hain
            j++;
        }

        return result;
    }

    //Leetcode 1652
    public int[] decrypt(int[] code, int k) {

        int ws = 0;
        int we = 0;
        int totalLength = code.length;
        int[] ans = new int[totalLength];

        while(totalLength!=0){

            int upto = k > 0 ? k : -k;
            int sum = 0;

            we = k > 0 ? ws : ws + k + code.length - 1;

            while(upto!=0){
                we++;
                sum+= code[we % code.length];
                upto--;
            }

            ans[ws] = k==0 ? 0 : sum;
            ws++;
            totalLength--;
        }

        return ans;
    }

    //Leetcode 1652
    public int[] decryptGPT(int[] code, int k) {
        int n = code.length;
        int[] answer = new int[n];

        if (k == 0) {
            // Agar k 0 hai to sabhi elements ke liye answer 0 hoga
            Arrays.fill(answer, 0);
            return answer;
        }

        for (int i = 0; i < n; i++) {
            int sum = 0;
            if (k > 0) {
                // k positive hai to aage ke k elements ka sum calculate karte hain
                for (int j = 1; j <= k; j++) {
                    sum += code[(i + j) % n];
                }
            } else {
                // k negative hai to pichle ke k elements ka sum calculate karte hain
                for (int j = 1; j <= -k; j++) {
                    sum += code[(i - j + n) % n];
                }
            }
            answer[i] = sum;
        }

        return answer;
    }

    //Leetcode 3090
    public int maximumLengthSubstring(String s) {

        int[] count = new int[26];
        char ch = '-';

        for(int i=0; i<s.length(); i++){
            count[s.charAt(i)-'a']++;
        }

        count = new int[26];

        int ws = 0;
        int we = 0;
        int maxLen = Integer.MIN_VALUE;
        while(we < s.length()){

            count[s.charAt(we)-'a']++;
            boolean atMostTwo = allOccurrences(count);

            while(!atMostTwo){
                count[s.charAt(ws)-'a']--;
                atMostTwo = allOccurrences(count);
                ws++;
            }

            maxLen = Math.max(maxLen,we-ws+1);

            we++;
        }

        return maxLen;
    }


    boolean allOccurrences(int[] count){
        int counter = 0;
        for(int i=0;i<26;i++){
            if(count[i] > 2){
                return false;
            }
        }
        return true;
    }

    //Leetcode 2269
    public int divisorSubstrings(int num, int k) {
        // convert the num to String
        String numStr = String.valueOf(num);
        int k_beauty = 0;
        // we need to have a window of size k which moves by 1
        int w_start = 0;
        int w_end = w_start + k - 1;
        // we need to slide the window till end of the window reaches str length
        while(w_end < numStr.length()) {
            // get the substring
            int subnum = Integer.parseInt(numStr.substring(w_start,w_end+1));
            // check for k beauty
            if(subnum != 0 && num%subnum==0) {
                k_beauty++;
            }
            // move the window
            w_start += 1;
            w_end = w_start + k - 1;
        }
        return k_beauty;
    }

    //Leetcode 1343
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int count = 0;  // Valid subarrays ka count rakhega
        int currentSum = 0;  // Current window ka sum rakhega

        // Initial window sum calculate kar rahe hain
        for (int i = 0; i < k; i++) {
            currentSum += arr[i];
        }

        // Agar initial window ka average threshold se zyada ya equal hai toh count badha do
        if (currentSum / k >= threshold) {
            count++;
        }

        // Window slide karna shuru karo
        for (int i = k; i < arr.length; i++) {
            currentSum += arr[i];  // New element ko window mein add kar rahe hain
            currentSum -= arr[i - k];  // Old element ko window se remove kar rahe hain

            // Check karo agar current window ka average threshold se zyada ya equal hai
            if (currentSum / k >= threshold) {
                count++;
            }
        }

        return count;  // Valid subarrays ka count return kar rahe hain
    }

    //Leetcode 2379
    public int minimumRecolors(String blocks, int k) {

        int blacksRequired = 0;  // Yeh track karega ki kitne 'W' ko 'B' mein convert karna padega current window mein

        int windowStart = 0;  // Sliding window ka start pointer
        int windowEnd = 0;  // Sliding window ka end pointer
        int minOperations = Integer.MAX_VALUE;  // Minimum operations ko track karega

        // Sliding window ko blocks ke end tak move karenge
        while (windowEnd < blocks.length()) {

            // Agar current character 'W' hai to blacksRequired increment kar do
            blacksRequired = blocks.charAt(windowEnd) == 'W' ? blacksRequired + 1 : blacksRequired;
            // System.out.println("blacksRequired : " + blacksRequired + " window size : " + (windowEnd - windowStart + 1));

            // Jab window ka size k ho jaye
            if (windowEnd - windowStart + 1 == k) {
                // Minimum operations update karo agar current window mein kam 'W' hain
                minOperations = Math.min(minOperations, blacksRequired);
                // System.out.println("minOperations : " + minOperations);

                // Window ko slide karte hain aur start pointer ko aage badhate hain
                // Agar start pointer ka character 'W' hai to blacksRequired decrement karo
                blacksRequired = blocks.charAt(windowStart) == 'W' ? blacksRequired - 1 : blacksRequired;
                windowStart++;  // Start pointer ko aage badhao
            }

            windowEnd++;  // End pointer ko aage badhao
        }

        return minOperations;  // Minimum operations ko return karo
    }

    //Leetcode 1984
    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);  // Pehle array ko sort kar lo

        int start = 0;  // Sliding window ka start pointer
        int end = 0;  // Sliding window ka end pointer

        int min = Integer.MAX_VALUE;  // Minimum value ko track karega
        int max = Integer.MIN_VALUE;  // Maximum value ko track karega

        int minDiff = Integer.MAX_VALUE;  // Minimum difference ko track karega

        // Sliding window ko nums ke end tak move karenge
        while (end < nums.length) {

            // Current window mein minimum aur maximum values ko update karo
            min = Math.min(min, nums[end]);
            max = Math.max(max, nums[end]);

            // Jab window ka size k ho jaye
            if (end - start + 1 == k) {
                // Minimum difference ko update karo
                minDiff = Math.min(minDiff, max - min);
                start++;  // Window ko slide karte hain aur start pointer ko aage badhate hain

                // Start pointer ko aage badhane ke baad minimum aur maximum values ko update karo
                if (start < nums.length) {
                    min = nums[start];
                    max = Math.max(max, nums[start]);
                }
            }

            end++;  // End pointer ko aage badhao
        }

        return minDiff;  // Minimum difference ko return karo
    }

    //Leetcode 594
    public int findLHS(int[] nums) {
        // Agar array ka length 0 hai to return 0 kyunki koi harmonious subsequence nahi ban sakta
        if (nums.length == 0) {
            return 0;
        }

        // Array ko sort kar rahe hain taaki hum consecutive elements ko easily compare kar sakein
        Arrays.sort(nums);

        // Left pointer ko initialize kar rahe hain 0 pe aur right pointer ko 1 pe
        int left = 0, right = 1;
        // Maximum length ko store karne ke liye variable
        int maxLen = 0;

        // Right pointer ko array ke end tak le ja rahe hain
        while (right < nums.length) {
            // Current window ka difference calculate kar rahe hain
            int diff = nums[right] - nums[left];

            // Agar difference 1 hai to harmonious subsequence mil gaya
            if (diff == 1) {
                // Maximum length ko update kar rahe hain
                maxLen = Math.max(maxLen, right - left + 1);
            }

            // Agar difference 1 ya 1 se kam hai to window ko expand karte hain
            if (diff <= 1) {
                right++;
            } else {
                // Agar difference 1 se zyada hai to window ko shrink karte hain
                left++;
            }
        }

        // Maximum length return karte hain
        return maxLen;
    }

    //Leetcode 3095
    public int minimumSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        int minLength = Integer.MAX_VALUE; // Minimum length ko track karne ke liye
        int left = 0; // Window ka start index
        int currentOR = 0; // Current window ka OR value

        // Right pointer ko 0 se array ke end tak le ja rahe hain
        for (int right = 0; right < n; right++) {
            currentOR |= nums[right]; // Right element ko include karke OR operation karte hain
            //System.out.println("current OR : "+currentOR+" window size : "+(right - left + 1));
            // Jab tak currentOR k se kam se kam equal nahi hota, window ko shrink karte hain
            while (left < nums.length && currentOR >= k && (right - left + 1) >=1) {
                minLength = Math.min(minLength, right - left + 1); // Minimum length update karte hain
                currentOR &= nums[left]; // Left element ko exclude karke OR operation update karte hain
                left++; // Left pointer ko aage badhate hain

                // Ensure the OR value is correctly updated for the new window
                if (left <= right) {
                    currentOR = 0;
                    for (int i = left; i <= right; i++) {
                        currentOR |= nums[i];
                    }
                }
                //System.out.println("current OR : "+currentOR+" window size : "+(right - left + 1));
            }
        }

        // Agar minLength update nahi hua to return -1, warna minLength return karte hain
        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

    //Leetcode 2760
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int answer = 0, i = 1, r = Integer.MAX_VALUE;
        if(nums[0] <= threshold && nums[0] % 2 == 0) {
            r = 0;
        }
        while(i < nums.length){
            if( nums[i] > threshold || ((nums[i] % 2) == (nums[i-1] % 2)) ){
                // Conditions not satisfied
                answer = Math.max(answer, i - r );
                r = Integer.MAX_VALUE;
            }
            if(r == Integer.MAX_VALUE && nums[i] <= threshold && nums[i] % 2 == 0){
                // Conditions satisfied - increase the length of subarray
                r = i;
            }
            i++;
        }
        answer = Math.max(answer, i - r );
        return answer;
    }

    //Leetcode 1493
    // Function to find the longest subarray of 1s after deleting one element
    public int longestSubarray(int[] nums) {
        int left = 0;         // Initialize the left pointer of the sliding window
        int zeroCount = 0;    // Count the number of zeros in the current window
        int maxLength = 0;    // Store the maximum length of the subarray of 1s

        // Traverse the array using the right pointer
        for (int right = 0; right < nums.length; right++) {
            // If the current element is 0, increment zeroCount
            if (nums[right] == 0) {
                zeroCount++;
            }

            // If zeroCount is more than 1, adjust the left pointer
            while (zeroCount > 1) {
                // If the element at the left pointer is 0, decrement zeroCount
                if (nums[left] == 0) {
                    zeroCount--;
                }
                // Move the left pointer to the right
                left++;
            }

            // Update maxLength with the length of the current window
            // (right - left) gives the length of the window
            maxLength = Math.max(maxLength, right - left);
        }

        return maxLength; // Return the maximum length of the subarray of 1s
    }

    //Leetcode 2799
    public int countCompleteSubarrays(int[] nums) {

        // Step-1: Identify all distinct elements in the array
        // Sab distinct elements ko HashSet mein daalo
        HashSet<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }
        int distinct = set.size(); // Distinct elements ki count

        int ans = 0; // Total complete subarrays ka count

        // Sliding window ke elements ko track karne ke liye HashMap
        HashMap<Integer, Integer> map = new HashMap<>();

        int left = 0; // Sliding window ka left pointer
        int right = 0; // Sliding window ka right pointer

        // Jab tak right pointer array ke end tak nahi pahuncha
        while (right < nums.length) {
            // Right pointer ko move karke window mein element add karo
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

            // Jab window mein saare distinct elements hain, left pointer ko move karke valid subarrays count karo
            while (map.size() == distinct) {
                //System.out.println(map); // Debugging ke liye print statement
                ans += nums.length - right; // Yeh subarray aur iske baad ke sab subarrays valid hain

                // Left pointer ko move karke window size ko chhota karo
                map.put(nums[left], map.get(nums[left]) - 1);

                // Agar element ka count zero ho gaya to usko remove kar do
                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }

                left++; // Left pointer ko move karo taaki window chhoti ho jaye
            }
            right++; // Right pointer ko move karke window ko expand karo
        }

        return ans; // Total complete subarrays ka count return karo
    }

    //Leetcode 2962
    // Function to count subarrays where the max element appears at least K times
    public static long countSubarrays(int[] arr, int K) {
        int n = arr.length; // Array length
        long count = 0; // To keep track of valid subarrays
        int left = 0; // Left pointer for the sliding window
        int max_element = arr[0]; // Assume karo pehla element max hai

        // Loop through the array to find the max element
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max_element) {
                max_element = arr[i]; // Update max element agar current element bada hai
            }
        }
        int max_count = 0; // To track the frequency of max_element

        // Right pointer for the sliding window
        for (int right = 0; right < n; right++) {
            if (arr[right] == max_element) {
                max_count++;
            }

            // Adjust the left pointer to maintain the condition of the sliding window
            while (max_count >= K) {
                count += (n - right); // All subarrays ending at 'right' and starting from 'left' to 'right' are valid
                if (arr[left] == max_element) {
                    max_count--; // Decrease the frequency of max_element as we move the left pointer
                }
                left++; // Move the left pointer to shrink the window
            }
        }

        return count; // Return the total count of valid subarrays
    }

    //Leetcode 1031
    // Function to calculate the maximum sum of two non-overlapping subarrays
    public static int maxSumTwoNoOverlap(int[] arr, int L, int M) {
        int n = arr.length;
        int[] prefixSum = new int[n + 1];

        // Calculate prefix sums
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + arr[i];
        }

        int maxL = 0, maxM = 0;
        int result = 0;

        // Find maximum sum of L-length subarray and M-length subarray
        for (int i = L + M; i <= n; i++) {
            // Calculate maximum sum of L-length subarray ending before the M-length subarray starts
            maxL = Math.max(maxL, prefixSum[i - M] - prefixSum[i - M - L]);
            // Calculate maximum sum of M-length subarray ending before the L-length subarray starts
            maxM = Math.max(maxM, prefixSum[i - L] - prefixSum[i - L - M]);

            // Update result with the maximum sum of the two non-overlapping subarrays
            result = Math.max(result, Math.max(maxL + (prefixSum[i] - prefixSum[i - M]), maxM + (prefixSum[i] - prefixSum[i - L])));
        }

        return result;
    }

    //Leetcode 1208
    public static int equalSubstring(String s, String t, int maxCost) {
        int maxLength = 0;  // Maximum length of substring ko store karne ke liye
        int currentCost = 0;  // Current total cost ko store karne ke liye
        int start = 0;  // Window ke start ko initialize karte hain

        // Slide the window using the end pointer
        for (int end = 0; end < s.length(); end++) {
            // s[end] ko t[end] se change karne ka cost calculate karte hain
            currentCost += Math.abs(s.charAt(end) - t.charAt(end));

            // Agar currentCost maxCost se zyada ho jaye, to start pointer ko move karke cost reduce karte hain
            while (currentCost > maxCost) {
                currentCost -= Math.abs(s.charAt(start) - t.charAt(start));
                start++;
            }

            // Maximum length of the substring ko update karte hain
            maxLength = Math.max(maxLength, end - start + 1);
        }
        new StringBuilder().replace()
        return maxLength;  // Maximum length of the substring within the budget return karte hain
    }

    //Leetcode 1695
    public static int maximumUniqueSubarray(int[] nums) {
        int maxSum = 0;  // Maximum sum of unique subarray
        int currentSum = 0;  // Current sum of the window
        int start = 0;  // Start pointer for the window
        Set<Integer> uniqueElements = new HashSet<>();  // Set to track unique elements in the window

        // Slide the window using the end pointer
        for (int end = 0; end < nums.length; end++) {
            // While loop to handle duplicates
            while (uniqueElements.contains(nums[end])) {
                uniqueElements.remove(nums[start]);
                currentSum -= nums[start];
                start++;
            }

            // Add the new element to the set and update the current sum
            uniqueElements.add(nums[end]);
            currentSum += nums[end];

            // Update the maximum sum if the current sum is greater
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;  // Return the maximum sum of unique subarray
    }
    ///Leetcode 2000
    class Solution {
        public String reversePrefix(String word, char ch) {
            // Word ke har character ko loop karte hain
            // Strings immutable hote hain java mein, isliye hum character array ka use karte hain
            // Swapping operations ke liye
            char[] answer = word.toCharArray();
            for (int i = 0; i < word.length(); i++) {
                // Agar current character target character ke barabar ho
                if (word.charAt(i) == ch) {
                    int j = 0; // Pointer `j` ko string ke shuruaat mein initialize karte hain
                    // Prefix ko reverse karte hain string ka index `i` tak
                    while (j < i) {
                        // Characters ko swap karte hain positions `j` aur `i` par
                        swapChars(answer, j++, i--);
                    }
                    return new String(answer);
                }
            }
            // Agar target character na mile, to original word return karte hain
            return word;
        }

        // Helper method jo characters ko swap karta hai positions `i` aur `j` par
        private void swapChars(char[] answer, int index1, int index2) {
            char temp = answer[index2];
            answer[index2] = answer[index1];
            answer[index1] = temp;
        }
    }


    public static void main(String[] args) {
        long[] A = { -8, 2, 3, -6, 10 };
        int N = A.length;
        int k = 2;
        List<Long> result = printFirstNegativeInteger(A, N, k);
        System.out.println(result); // Output: [-8, 0, -6, -6]
    }
}
