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
}
