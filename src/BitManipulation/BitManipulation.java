package BitManipulation;

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
}
