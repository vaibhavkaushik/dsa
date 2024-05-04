package Recursion;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {78, 100, 4, 3, 2, 1, 6, 78, 100};
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    static void sort(int[] nums, int low, int hi) {
        if (low >= hi) {
            return;
        }

        //low,hi is for array coverage
        //start,end is for swap purpose
        int s = low;
        int e = hi;
        int m = s + (e - s) / 2;
        int pivot = nums[m];

        while (s <= e) {

            // also a reason why if its already sorted it will not swap
            while (nums[s] < pivot) {
                s++;
            }
            while (nums[e] > pivot) {
                e--;
            }

            if (s <= e) {
                int temp = nums[s];
                nums[s] = nums[e];
                nums[e] = temp;
                s++;
                e--;
            }
        }

        // now my pivot is at correct index, please sort two halves now
        //Once the loop ends, the end will come before pivot element and start will shoot over the pivot element
        //that's why left array is from low to end and right array is from start to high
        sort(nums, low, e);
        sort(nums, s, hi);
    }
}
