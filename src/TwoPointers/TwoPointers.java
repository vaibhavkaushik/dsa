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
}
