package SearchingSorting;

public class BinarySearchVariations {

    /**
     * 1. Normal binary search to find the matching index for the target.
     * This function searches for the exact target value in a sorted array.
     * It adjusts the search range based on whether the middle element is
     * less than or greater than the target.
     *
     * @param nums   the sorted array of integers
     * @param target the target value to find
     * @return the index of the target value if found, otherwise -1
     */
    public int binarySearch(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            // Calculate mid index to avoid overflow
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid; // Target found
            } else if (target > nums[mid]) {
                low = mid + 1; // Search in the right half
            } else {
                high = mid - 1; // Search in the left half
            }
        }
        return -1; // Target not found
    }

    /**
     * Lower Bound Example:
     * Suppose tumhare paas ek sorted array hai: [1, 3, 3, 5, 8, 8, 10] aur target value 3 hai.
     *
     * Lower bound ka matlab hoga pehla index jahan element 3 ya usse bara ho.
     * Is case mein, 3 pehli baar index 1 pe aata hai. To lower bound 1 hoga.
     * Agar target 6 hota:
     *
     * Array mein 6 se bara pehla element 8 hai jo index 4 pe aata hai.
     * To lower bound 4 hoga.
     * 2. Find the lower bound.
     * This function finds the smallest index where the element is greater than or equal to the target.
     * The idea is to keep updating the lower bound when an element greater than or equal to the target is found,
     * and continue searching in the left half of the array to find the smallest index.
     *
     * @param nums   the sorted array of integers
     * @param target the target value to find the lower bound for
     * @return the index of the lower bound
     */
    public int findLowerBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int lowerBound = -1;

        while (left <= right) {
            // Calculate mid index to avoid overflow
            int mid = left + (right - left) / 2;
            //We need to find element just greater than equal to target
            if (nums[mid] >=target) {
                lowerBound = mid; // Potential lower bound found
                right = mid - 1; // Search in the left half
            } else {
                left = mid + 1; // Search in the right half
            }
        }
        return lowerBound; // Return the lower bound index
    }

    /**
     * Upper Bound Example:
     * Suppose tumhare paas ek sorted array hai: [1, 3, 3, 5, 8, 8, 10] aur target value 3 hai.
     *
     * Upper bound ka matlab hoga pehla index jahan element 3 se bara ho.
     * Is case mein, 3 se bara pehla element 5 hai jo index 3 pe aata hai. To upper bound 3 hoga.
     * Agar target 8 hota:
     *
     * Array mein 8 se bara pehla element 10 hai jo index 6 pe aata hai.
     * To upper bound 6 hoga.
     *
     *
     * @param nums   the sorted array of integers
     * @param target the target value to find the upper bound for
     * @return the index of the upper bound
     */
    public int findUpperBound(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;
        int upperBound = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            // maybe an answer
            //We need to find element just greater than target, so in this if condition we are doing that only
            if (arr[mid] > x) {
                upperBound = mid;
                //look for smaller index on the left
                high = mid - 1;
            } else {
                low = mid + 1; // look on the right
            }
        }
        return upperBound; // Return the upper bound index
    }

    /**
     * 4. Find the least greater than target.
     * This function finds the smallest element in the array that is greater than the target.
     * The idea is to keep updating the result when an element greater than the target is found,
     * and continue searching in the left half of the array to find the smallest such element.
     *
     * @param nums the sorted array of integers
     * @param key  the target value to find the least greater element for
     * @return the index of the least greater element
     */
    public int findLeastGreater(int[] nums, int key) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;
        int result = -1;

        while (low <= high) {
            // Calculate mid index to avoid overflow
            int mid = low + (high - low) / 2;
            if (nums[mid] > key) {
                result = mid; // Potential least greater element found
                high = mid - 1; // Search in the left half
            } else {
                low = mid + 1; // Search in the right half
            }
        }
        return result; // Return the index of the least greater element
    }

    /**
     * 5. Find the greatest less than target.
     * This function finds the largest element in the array that is less than the target.
     * The idea is to keep updating the result when an element less than the target is found,
     * and continue searching in the right half of the array to find the largest such element.
     *
     * @param nums the sorted array of integers
     * @param key  the target value to find the greatest less element for
     * @return the index of the greatest less element
     */
    public int findGreatestLess(int[] nums, int key) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;
        int result = -1;

        while (low <= high) {
            // Calculate mid index to avoid overflow
            int mid = low + (high - low) / 2;
            if (nums[mid] < key) {
                result = mid; // Potential greatest less element found
                low = mid + 1; // Search in the right half
            } else {
                high = mid - 1; // Search in the left half
            }
        }
        return result; // Return the index of the greatest less element
    }


//    Least Greater than Target
//    Least greater than target ka matlab hai array mein sabse chhota index jahan element target se bara ho.
//
//    Aayiye, example se samjhte hain:
//
//    Example of Least Greater than Target:
//    Suppose tumhare paas ek sorted array hai: [1, 3, 3, 5, 8, 8, 10] aur target value 4 hai.
//
//    Least greater ka matlab hoga pehla index jahan element 4 se bara ho.
//            Is case mein, 4 se bara pehla element 5 hai jo index 3 pe aata hai. To least greater 3 hoga.
//    Agar target 8 hota:
//
//    Array mein 8 se bara pehla element 10 hai jo index 6 pe aata hai.
//    To least greater 6 hoga.
//    Greatest Less than Target
//    Greatest less than target ka matlab hai array mein sabse chhota index jahan element target se chhota ho.
//
//    Example of Greatest Less than Target:
//    Suppose tumhare paas ek sorted array hai: [1, 3, 3, 5, 8, 8, 10] aur target value 4 hai.
//
//    Greatest less ka matlab hoga pehla index jahan element 4 se chhota ho.
//            Is case mein, 4 se chhota pehla element 3 hai jo index 2 pe aata hai. To greatest less 2 hoga.
//    Agar target 6 hota:
//
//    Array mein 6 se chhota pehla element 5 hai jo index 3 pe aata hai.
//    To greatest less 3 hoga.

    public static void main(String[] args) {
        BinarySearchVariations solution = new BinarySearchVariations();

        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int target = 5;

        System.out.println("Normal Binary Search: " + solution.binarySearch(nums, target));
        System.out.println("Lower Bound: " + solution.findLowerBound(nums, target));
        System.out.println("Upper Bound: " + solution.findUpperBound(nums, target));
        System.out.println("Least Greater: " + solution.findLeastGreater(nums, target));
        System.out.println("Greatest Less: " + solution.findGreatestLess(nums, target));
    }
}
