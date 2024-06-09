package BitManipulation;

import java.util.Arrays;

public class BitManipulation {
    /*
    Is problem ka main idea yeh hai ki humein a OR b ka result c ke equal banana hai by flipping minimum number of bits.
     Bitwise OR operation mein, agar c ki bit 1 hai, to a ya b mein se kam se kam ek bit 1 hona chahiye.
      Agar dono 0 hain, to humein ek flip karna padega (0 ko 1 banana hoga). Agar c ki bit 0 hai, to a aur b dono bits 0 hone chahiye.
      Agar koi bhi bit 1 hai, to usse 0 banana padega, jiske liye har 1 bit pe ek flip lagega.
      Har bit position ko check karke, hum yeh dekhte hain ki kitne flips required hain, aur un flips ko count karte hain. Bitwise operations aur right shift operations use karke hum har bit ko check karte hain, aur total flips ka sum return karte hain.
     */
    //Leetcode 1318
    public int minFlips(int a, int b, int c) {
        int flips = 0; // Total flips count karne ke liye variable

        // Jab tak a, b, aur c non-zero hain
        while (a != 0 || b != 0 || c != 0) {
            // a, b, aur c ke least significant bit nikaalo
            int aBit = a & 1;
            int bBit = b & 1;
            int cBit = c & 1;

            // Condition check karo
            if (cBit == 1) {
                // Agar c ka bit 1 hai aur dono a aur b ke bits 0 hain
                if (aBit == 0 && bBit == 0) {
                    flips++; // Ek flip ki zaroorat hai
                }
            } else {
                // Agar c ka bit 0 hai aur a ya b ka bit 1 hai
                flips += aBit + bBit; // Jitne 1 hain utne flips ki zaroorat hai
            }

            // Next bit position ke liye right shift karo
            a >>= 1;
            b >>= 1;
            c >>= 1;
        }

        return flips; // Total flips return karo
    }

    /*Is problem ka solution bitwise manipulation par based hai. Har element ka har bit position (0 se 31) tak
    check karte hain aur 1s ka count karte hain. Agar kisi bit position par 1 ka count 3 se divisible nahi hai,
    to iska matlab wo bit unique element ka part hai. Aise karne se har bit position ko individually analyze
    karke hum unique element ko reconstruct kar sakte hain. Yeh approach efficient hai kyunki yeh linear
    runtime complexity (O(n)) mein kaam karti hai aur constant space use karti hai. Har bit position ke
    liye 3 se modulo operation use karke, hum easily identify kar sakte hain ki kaunsa bit unique element
    mein set hona chahiye.
    */
    //Leetcode 137
    public int singleNumber(int[] nums) {
        int result = 0;

        // Har bit position (0 to 31) ke liye process karo
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            int mask = 1 << i; // i-th bit position ke liye mask

            // Array ke har element ke bits ko check karo
            for (int num : nums) {
                // Agar current bit set hai to sum ko increment karo
                if ((num & mask) != 0) {
                    sum++;
                }
            }

            // Agar sum 3 se divisible nahi hai, to result mein i-th bit set karo
            if (sum % 3 != 0) {
                result |= mask;
            }
        }

        return result; // Unique element return karo
    }

    //Leetcode 338
    /*
    Is problem ka solution bit manipulation par based hai. Har integer i ka binary representation mein 1's ka
    count efficiently find karne ke liye, hum i & (i - 1) operation use karte hain. Yeh operation i se ek 1 bit
     remove karta hai. Example ke liye, agar i = 5 (binary: 101), to 5 & (5 - 1) hoga 5 & 4 (binary: 100),
     jo 4 ke barabar hoga. 5 ke 1's count ko 4 ke 1's count mein 1 add karke find kar sakte hain. Is approach
     se har bit position ko individually check karne ki zaroorat nahi hoti, aur hum previous results ka reuse
     karte hain, isse time complexity O(n) hoti hai aur space constant use hota hai.
     */
    public int[] countBits(int n) {
        int[] ans = new int[n + 1]; // Result array initialize karo

        // Har integer i (0 se n tak) ke liye 1's count calculate karo
        for (int i = 1; i <= n; i++) {
            // ans[i] ko ans[i & (i - 1)] + 1 se derive karo
            ans[i] = ans[i & (i - 1)] + 1;
        }

        return ans; // Final result return karo
    }

    /*
     given array ke integers ko unke binary representation mein 1's bits ke count ke basis pe sort karna,
     bina inbuilt functions ke. Yeh achieve karne ke liye hum Streams ka use karte hain, jisme hum pehle
      array ko box karke Integer objects mein convert karte hain, phir custom comparator ka use karte
       hue sort karte hain aur finally wapas int array mein convert kar dete hain. Custom comparator
       pehle har integer ka binary representation mein 1's bits count karta hai, phir in counts ko compare
        karta hai. Agar 1's bits ka count same hota hai, to original values ko compare karta hai.
         Is process ke baad sorted array return hota hai.
     */
    //Leetcode 1356
    // Function to count 1's bits in the binary representation of a number
    private int countOnes(int n) {
        int count = 0;
        while (n != 0) {
            count += n & 1; // Increment count if the least significant bit is 1
            n >>= 1; // Right shift to process the next bit
        }
        return count;
    }

    public int[] sortByBits(int[] arr) {
        // Custom comparator to sort by number of 1's bits and then by value
        return Arrays.stream(arr)
                .boxed()
                .sorted((a, b) -> {
                    int countA = countOnes(a);
                    int countB = countOnes(b);
                    if (countA == countB) {
                        return Integer.compare(a, b);
                    } else {
                        return Integer.compare(countA, countB);
                    }
                })
                .mapToInt(Integer::intValue)
                .toArray();
    }

    /*
    given array pref se original array arr ko find karna jismein har element ka XOR operation pref
    array ke corresponding index value ke equal ho. pref[i] cumulative XOR hai first i+1 elements
    ka arr array mein. Yeh problem solve karne ke liye, hum yeh samajhte hain ki arr[i] ko pref[i]
     aur pref[i-1] ka XOR karke easily find kar sakte hain kyunki XOR operation similar elements
      ko cancel out kar deta hai. Pehla element arr[0] directly pref[0] ke barabar hoga. Uske baad
      har element arr[i] ko calculate karte hain pref[i] aur pref[i-1] ka XOR karke. Is
      tarike se hum original array arr ko efficiently reconstruct kar sakte hain.
     */
    //Leetcode 2433
    public int[] findArray(int[] pref) {
        int n = pref.length;
        int[] arr = new int[n];
        arr[0] = pref[0]; // arr[0] is directly pref[0]

        // Calculate subsequent elements
        for (int i = 1; i < n; i++) {
            arr[i] = pref[i] ^ pref[i - 1];
        }

        return arr;
    }

    //Leetcode 2939
    public int maximumXorProduct(long a, long b, int n) {
        int mod = 1000000007;
        for (int i = n - 1; i >= 0; --i) {
            long mask = (long) 1 << i;
            if ((a & mask) != 0 && (b & mask) != 0) {
                continue;
            } else if ((a & mask) != 0) {
                if (a > b) {
                    a ^= mask;
                    b |= mask;
                }
                continue;
            } else if ((b & mask) != 0) {
                if (a < b) {
                    a |= mask;
                    b ^= mask;
                }
            } else {
                a |= mask;
                b |= mask;
            }
        }
        a = a % mod;
        b = b % mod;
        return (int) ((1L * a * b) % mod);
    }

    //Leetcode 1863
    public int subsetXORSum(int[] nums) {
        return helper(nums, 0, 0);
    }

    private int helper(int[] nums, int index, int currentXor) {
        // return currentXor when all elements in nums are already considered
        if (index == nums.length) return currentXor;

        // calculate sum of xor with current element
        int withElement = helper(nums, index + 1, currentXor ^ nums[index]);

        // calculate sum of xor without current element
        int withoutElement = helper(nums, index + 1, currentXor);

        // return sum of xors from recursion
        return withElement + withoutElement;
    }
}
