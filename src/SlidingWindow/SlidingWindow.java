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

    public static void main(String[] args) {
        long[] A = { -8, 2, 3, -6, 10 };
        int N = A.length;
        int k = 2;
        List<Long> result = printFirstNegativeInteger(A, N, k);
        System.out.println(result); // Output: [-8, 0, -6, -6]
    }
}
