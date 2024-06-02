package TwoPointers;

public class TwoPointers {

    //Leetcode 345
    public static String reverseVowels(String s) {
        // Vowels ko identify karne ke liye ek string banai
        String vowels = "aeiouAEIOU";

        // String ko character array mein convert kar rahe hain
        char[] chars = s.toCharArray();

        // Do pointers rakhenge, ek left se aur ek right se
        int left = 0, right = s.length() - 1;

        while (left < right) {
            // Jab tak left pointer vowel na mile, aage badhao
            while (left < right && vowels.indexOf(chars[left]) == -1) {
                left++;
            }
            // Jab tak right pointer vowel na mile, peeche le jao
            while (left < right && vowels.indexOf(chars[right]) == -1) {
                right--;
            }
            // Dono pointers pe vowel mil gaye to swap kar do
            if (left < right) {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                // Pointers ko move kar do
                left++;
                right--;
            }
        }

        // Character array ko wapas string mein convert kar do
        return new String(chars);
    }

        //Leetcode 28
        public static int strStr(String haystack, String needle) {
            // Agar needle empty hai to return 0
            if (needle.isEmpty()) {
                return 0;
            }

            // Haystack aur needle ka length nikal lo
            int haystackLen = haystack.length();
            int needleLen = needle.length();

            // Loop chalate hain haystack pe
            for (int i = 0; i <= haystackLen - needleLen; i++) {
                int j = 0;
                // Needle ko match karte hain haystack ke current position se
                while (j < needleLen && haystack.charAt(i + j) == needle.charAt(j)) {
                    j++;
                }
                // Agar poora needle match ho gaya to return current index
                if (j == needleLen) {
                    return i;
                }
            }

            // Agar needle nahi mili to -1 return karo
            return -1;
        }

    private static final int MOD = 1_000_000_007;
    //Leetcode 1498
    public static int numSubseq(int[] nums, int target) {
        // Array ko sort karte hain taaki minimum aur maximum elements ko easily access kar sakein
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0, right = n - 1;
        int count = 0;

        // Precompute karte hain powers of 2 upto n-1 tak, taaki subsequence count efficiently calculate ho sake
        int[] power = new int[n];
        power[0] = 1;
        for (int i = 1; i < n; i++) {
            power[i] = (power[i - 1] * 2) % MOD;
        }

        // Two-pointer technique use karte hain
        while (left <= right) {
            // Agar min element (nums[left]) aur max element (nums[right]) ka sum target se kam ya barabar hai
            if (nums[left] + nums[right] <= target) {
                // Count mein add karte hain, jitne bhi subsequences ban sakte hain between left aur right
                count = (count + power[right - left]) % MOD;
                // Left pointer ko aage badhate hain
                left++;
            } else {
                // Agar condition satisfy nahi hoti, toh right pointer ko piche le jate hain
                right--;
            }
        }

        return count;
    }

    //Leetcoe 1750
    public int minimumLength(String s) {
        int left = 0; // Left pointer start se initialize karte hain
        int right = s.length() - 1; // Right pointer end se initialize karte hain

        // Jab tak left aur right pointer same character pe hain
        while (left < right && s.charAt(left) == s.charAt(right)) {
            char currentChar = s.charAt(left); // Current character ko store karte hain

            // Left pointer ko aage badhane ka kaam
            while (left <= right && s.charAt(left) == currentChar) {
                left++;
            }

            // Right pointer ko peeche le aane ka kaam
            while (left <= right && s.charAt(right) == currentChar) {
                right--;
            }
        }

        // Remaining string ki length return karte hain
        return right - left + 1;
    }

    //Leetcode 2367
    public int arithmeticTriplets(int[] nums, int diff) {
        // Maan lo triplet ke beech mein wala number nums hai
        // Toh humhe dekhna hai ki nums - diff aur nums + diff array mein hain ya nahi
        int count = 0; // Valid triplets ka count

        // Har number ke liye check karte hain
        for (int i = 0; i < nums.length; i++) {
            // Agar nums - diff aur nums + diff array mein hain, toh count badhao
            if (binarySearch(nums, 0, nums.length - 1, nums[i] - diff) && binarySearch(nums, 0, nums.length - 1, nums[i] + diff)) {
                count++;
            }
        }

        return count; // Total number of valid triplets return karte hain
    }

    public boolean binarySearch(int[] arr, int start, int end, int find) {
        // Binary search function
        while (start <= end) {
            int mid = start + (end - start) / 2; // Mid point nikalte hain

            // Agar mid point pe number mil gaya toh true return karte hain
            if (arr[mid] == find) {
                return true;
            }

            // Agar search number chota hai mid se toh left half mein search karte hain
            if (find < arr[mid]) {
                end = mid - 1;
            } else { // Agar search number bada hai mid se toh right half mein search karte hain
                start = mid + 1;
            }
        }

        return false; // Agar number nahi mila toh false return karte hain
    }
}
