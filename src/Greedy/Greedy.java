package Greedy;

import java.util.Arrays;

public class Greedy {

    //Leetcode 807
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        // Agar grid null hai to return 0
        if (grid == null)
            return 0;

        int n = grid.length; // Rows ka count
        int m = grid[0].length; // Columns ka count

        int maxrow[] = new int[n]; // Har row ka maximum value store karne ke liye array
        int maxcol[] = new int[m]; // Har column ka maximum value store karne ke liye array

        // Har row aur column ke maximum values find karte hain
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                maxrow[i] = Math.max(maxrow[i], grid[i][j]); // Row ka max update karte hain
                maxcol[j] = Math.max(maxcol[j], grid[i][j]); // Column ka max update karte hain
            }

        int count = 0; // Total increase ka count

        // Har cell ko update karte hain
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                // Minimum of row max aur column max se cell value ko subtract karte hain aur count mein add karte hain
                count += (Math.min(maxrow[i], maxcol[j]) - grid[i][j]);

        return count; // Total increase return karte hain
    }

    //Leetcode 2160
    public int minimumSum(int num) {

        int[] arr = new int[4]; // 4 digits store karne ke liye array
        int k = 0; // Index ko track karne ke liye variable

        // Number ke digits ko array mein store karte hain
        while (num != 0) {
            arr[k++] = num % 10; // Last digit ko array mein daalte hain
            num /= 10; // Last digit ko remove karte hain
        }

        Arrays.sort(arr); // Array ko sort karte hain taaki digits ascending order mein ho jaayein

        // Do smallest possible numbers banate hain aur unka sum calculate karte hain
        return arr[0] * 10 + arr[2] + arr[1] * 10 + arr[3]; // Smallest numbers ko combine karke sum return karte hain
    }

    //Leetcode 1561
    public int maxCoins(int[] piles) {
        Arrays.sort(piles); // Piles ko sort karte hain ascending order mein
        int ans = 0; // Total coins ka sum store karne ke liye variable
        int loop_times = piles.length / 3; // Loop kitni baar chalana hai, wo calculate karte hain

        // Piles ko reverse order mein traverse karte hain, second largest elements ko
        // select karte hain
        for (int i = piles.length - 2; i > 0 && loop_times > 0; i = i - 2, loop_times--) {
            ans += piles[i]; // Answer mein second largest element add karte hain
        }

        return ans; // Total coins ka sum return karte hain
    }

    //Leetcode 2864
    public String maximumOddBinaryNumber(String s) {

        char[] arr = s.toCharArray(); // String ko character array mein convert karte hain
        int one_count = 0; // '1' ka count karne ke liye variable
        int zero_count = 0; // '0' ka count karne ke liye variable

        // Har character ko traverse karke '1' aur '0' count karte hain
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '1') {
                one_count++; // '1' ka count badhate hain
            } else {
                zero_count++; // '0' ka count badhate hain
            }
        }

        StringBuilder ans = new StringBuilder(); // Final answer store karne ke liye StringBuilder

        // Pehle sab '1' ko add karte hain except ek '1' jo end mein rahega
        while (one_count > 1) {
            ans.append("1"); // '1' ko StringBuilder mein add karte hain
            one_count--;
        }

        // Phir sab '0' ko add karte hain
        while (zero_count != 0) {
            ans.append("0"); // '0' ko StringBuilder mein add karte hain
            zero_count--;
        }

        // Ek last '1' ko add karte hain taaki number odd ho
        if (one_count == 1) {
            ans.append("1");
        }

        return ans.toString(); // Final answer return karte hain
    }
}
