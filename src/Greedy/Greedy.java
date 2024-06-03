package Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    //Leetcode 2656
    public int maximizeSum(int[] nums, int k) {

        int max = -1; // Array ka maximum value store karne ke liye variable

        // Array mein maximum value find karte hain
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]); // Maximum value ko update karte hain
        }

        // Pehle k-1 tak ka sum calculate karte hain (Arithmetic series formula)
        int sum_upto_k_minus_one = (k * (k - 1)) / 2; // (k * (k - 1)) / 2 formula se sum calculate karte hain

        // Final result return karte hain (max * k) + sum_upto_k_minus_one
        return max * k + sum_upto_k_minus_one; // Maximum value ko k times aur k-1 tak ke sum ko add karte hain
    }

    //Leetcode 1382
    public TreeNode balanceBST(TreeNode root) {
        // Pehle existing BST ko sorted array mein convert karte hain, phir us array se naya balanced BST banate hain

        List<Integer> sortedAns = new ArrayList<>(); // Sorted values ko store karne ke liye list
        inorder(root, sortedAns); // Inorder traversal se sorted array banate hain

        // Sorted array se balanced BST create karte hain
        TreeNode rootAns = createBST(sortedAns, 0, sortedAns.size() - 1);

        return rootAns; // Naya balanced BST return karte hain
    }

    public TreeNode createBST(List<Integer> sortedAns, int left, int right) {
        if (left > right) {
            return null; // Base case: agar left index right se bada hai to null return karte hain
        }

        // Current array ka mid element find karte hain
        int mid = left + (right - left) / 2;

        TreeNode curr = new TreeNode(sortedAns.get(mid)); // Mid element ko new TreeNode mein convert karte hain

        // Left aur right subtree recursively create karte hain
        curr.left = createBST(sortedAns, left, mid - 1);
        curr.right = createBST(sortedAns, mid + 1, right);

        return curr; // Current node return karte hain
    }

    public void inorder(TreeNode root, List<Integer> sortedAns) {
        if (root == null) {
            return; // Base case: agar root null hai to return karte hain
        }

        inorder(root.left, sortedAns); // Left subtree ko traverse karte hain
        sortedAns.add(root.val); // Root node ke value ko list mein add karte hain
        inorder(root.right, sortedAns); // Right subtree ko traverse karte hain

        return; // Function ko return karte hain
    }

    //Leetcode 2697
    public String makeSmallestPalindrome(String s) {

        int start = 0; // Starting index
        int end = s.length() - 1; // Ending index
        char[] arr = s.toCharArray(); // String ko character array mein convert karte hain

        // Start aur end se characters ko check karte hain
        while (start <= end) {
            char c = (char) (Math.min((int) arr[start], (int) arr[end])); // Dono characters mein se chota character lete hain
            arr[start++] = c; // Start index pe chota character daalte hain aur start ko increment karte hain
            arr[end--] = c; // End index pe bhi chota character daalte hain aur end ko decrement karte hain
        }

        return new String(arr); // Character array ko wapas string mein convert karke return karte hain
    }

    //Leetcode 861
    public int matrixScore(int[][] grid) {
        int m = grid.length; // Rows ka count
        int n = grid[0].length; // Columns ka count

        boolean[] row_requires_change = new boolean[m]; // Rows ko flip karna hai ya nahi
        boolean[] col_requires_change = new boolean[n]; // Columns ko flip karna hai ya nahi

        // Pehle column mein sab 1 hone chahiye
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 0) {
                row_requires_change[i] = true; // Agar pehle column mein 0 hai to row ko flip karna padega
            }
        }

        // Rows ko flip karte hain agar required hai
        int row_num = 0;
        for (int row[] : grid) {
            if (row_requires_change[row_num]) {
                for (int i = 0; i < row.length; i++) {
                    row[i] = row[i] == 0 ? 1 : 0; // Row ko flip karte hain
                }
            }
            row_num += 1;
        }

        // Columns ko check karte hain agar flip karna hai
        for (int j = 1; j < n; j++) {
            int one_count = 0;
            for (int i = 0; i < m; i++) {
                if (grid[i][j] == 1) {
                    one_count++;
                }
            }
            if (one_count < m - one_count) {
                col_requires_change[j] = true; // Agar 1 ka count kam hai to column ko flip karna hai
            }
        }

        // Columns ko flip karte hain agar required hai
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (col_requires_change[j]) {
                    grid[i][j] = grid[i][j] == 0 ? 1 : 0; // Column ko flip karte hain
                }
            }
        }

        int ans = 0; // Final answer store karne ke liye variable
        for (int i = 0; i < m; i++) {
            int pow = n - 1;

            for (int j = 0; j < n; j++) {
                ans += (int) (grid[i][j] * Math.pow(2, pow)); // Binary se decimal convert karte hain
                pow--;
            }
        }

        return ans; // Final score return karte hain
    }


    //Leetcode 561
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums); // Array ko sort karte hain ascending order mein
        int pairs = 0; // Pair ka count track karne ke liye variable
        int sum = 0; // Final sum store karne ke liye variable
        int min = Integer.MAX_VALUE; // Current pair ka minimum value store karne ke liye variable

        // Array ko traverse karte hain
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(min, nums[i]); // Current element aur min mein se chota value choose karte hain
            pairs++; // Pair ka count increment karte hain

            if (pairs == 2) { // Jab pair complete ho jaye
                pairs = 0; // Pair count reset karte hain
                sum += min; // Current pair ka minimum value sum mein add karte hain
                min = Integer.MAX_VALUE; // Min value ko reset karte hain
            }
        }

        return sum; // Final sum return karte hain
    }

    //Leetcode 942
    public int[] diStringMatch(String s) {
        int i = 0; // Increasing value start se
        int d = s.length(); // Decreasing value end se
        char[] arr = s.toCharArray(); // String ko character array mein convert karte hain
        int[] ans = new int[d + 1]; // Result array jo final permutation store karega
        int k = 0; // Result array ka index

        // Character array ko traverse karte hain
        for (char c : arr) {
            if (c == 'I') {
                ans[k++] = i++; // Agar 'I' hai to increasing value add karte hain
            } else {
                ans[k++] = d--; // Agar 'D' hai to decreasing value add karte hain
            }
        }

        // Last element ko set karte hain
        ans[k] = s.charAt(s.length() - 1) == 'I' ? i : d; // Last character check karke appropriate value set karte hain

        return ans; // Final permutation array return karte hain
    }


}