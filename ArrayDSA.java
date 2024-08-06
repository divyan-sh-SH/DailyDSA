import java.util.*;

class ArrayDSA {

    // Remove Duplicate From Sorted Array.
    // [1,2,3,3,3,4,5] -> 
    static int removeDuplicateFromSorted(int[] arr, int size) {
        if(arr.length == 0) {
            return 0;
        }
        int res = 1;
        for(int i=1;i<arr.length;i++) {
            if(arr[i] != arr[res-1]) {
                arr[res] = arr[i];
                res++;
            }
        }
        return res;
    }

    // Move zeros to the end 
    // [1,2,0,3,0,0,4] -> [1,2,3,2,3,0,0]
    static int[] moveZerosToEnd(int[] arr) {
        int lastNonZero = 0;
        for(int i=0;i<arr.length;i++) {
            if(arr[i] != 0) {
                CommonUtils.swap(arr, lastNonZero, i);
                lastNonZero++;
            }
        }

        return arr;
    }

    // Left Rotation of array [1,2,3,4,5], 3 -> [4,5,1,2,3]
    static int[] leftRotateArrayByK(int[] arr, int k) {
        int[] k_arr = new int[k];
        for(int i=0;i<arr.length;i++){
            if(i<k) {
                k_arr[i] = arr[i];
            } else {
                arr[i-k] = arr[i];
            }
        }
        for(int i=0;i<k_arr.length;i++) {
            arr[i+k-1] = k_arr[i];
        }

        return arr;
    }

    // Left rotation using partition reverse
    // [1,2,3,4,5] -> [3,2,1,5,4] -> [4,5,1,2,3]
    static int[] leftRotateArrayByKReverse(int[] arr, int k) {
    
        int l = 0;
        int r = k-1;
        while( l<r) {
            CommonUtils.swap(arr, l, r);
            l++; r--;
        }


        l = k;
        r = arr.length-1;
        while(l<r) {
            CommonUtils.swap(arr, l, r);
            l++; r--;
        }

        CommonUtils.reverseArray(arr);
        return arr;
    }

    // Leaders of array 
    // Element is leader when there is no element greater than it on right side of it
    // [2,3,1,1,2]
    static List<Integer> leadersOfArray(int[] arr) {
        int size = arr.length;
        List<Integer> res = new ArrayList<>();
        int largestTill = arr[size-1];
        res.add(0,largestTill);
        for(int i=size-2;i>=0;i--) {
            if(largestTill < arr[i]) {
                largestTill = arr[i];
                res.add(0,largestTill);
            }
        }
        return res;
    }

    // [2,4,3,5,1] arr[j] - arr[i] where j > i
    // 
    static int maxDiffInArray(int[] arr) {
        int min_val = arr[0];
        int max_diff = arr[1] - arr[0];

        for(int i=1;i<arr.length;i++) {
            if(arr[i] - min_val > max_diff) {
                max_diff = arr[i] - min_val;
            }

            if(arr[i] < min_val) {
                min_val = arr[i];
            }
        }
        return max_diff;
    }

    // [10,10,10,20,20,30] -> (10, 3) (20, 2) (30, 1)
    static void printFreqInSortedArray(int[] arr) {
        int size = arr.length;
        int currentCount = 1;
        int prevEle = arr[0];
        for(int i=1;i<size;i++) {
            
            if (arr[i] == prevEle) {
                currentCount++;
                continue;
            }
            System.out.println("("+prevEle+", "+ currentCount+")");
            prevEle = arr[i];
            currentCount = 1;
        }

        System.out.println("("+prevEle+", "+ currentCount+")");
    }

    // Stock Buy and Sell Problem 
    static int stockBuyAndSell(int[] arr) {

        int maxProfit = 0;
        for(int i=1;i<arr.length;i++) {
            if (arr[i] > arr[i-1]) {
                maxProfit = maxProfit + (arr[i] - arr[i-1]);
            }
        }

        return maxProfit;
    }

    // { 3, 0, 5, 1, 2}
    // left iteration -> store the max from left { 3, 3, 3, 5, 5}
    // right iteration -> store the max from right { 5, 5, 5, 5, 5} 
    // min ( left[i], right[i]) - { 3, 3, 3, 3, 3 } -> SUM() -> 
    static int trappingRainWater(int[] arr) {
        int n = arr.length;
        int[] leftArr = new int[n];
        int[] rightArr = new int[n];
        int maxInLeft = arr[0];
        int maxInRight = arr[n-1];
        leftArr[0] = maxInLeft;
        rightArr[n-1] = maxInRight;

        for(int i=1;i<n;i++) {
            maxInLeft = Math.max(maxInLeft, arr[i-1]);
            leftArr[i] = maxInLeft;
        }

        for(int i=n-2;i>=0;i--) {
            maxInRight = Math.max(maxInRight, arr[i+1]);
            rightArr[i] = maxInRight;
        }

        int res = 0;
        for(int i=0;i<n;i++) {
            res += (Math.min(leftArr[i], rightArr[i]) - arr[i]) > 0 ? (Math.min(leftArr[i], rightArr[i]) -arr[i]) : 0 ;
        }
        return res;
    }

    static int maxConecutiveOnes(int[] arr) {
        int maxCount = 0;
        int currentCount = 0;
        for(int i=0;i<arr.length;i++) {
            if(arr[i] == 0) {
                maxCount = Math.max(maxCount, currentCount);
                currentCount = 0;
                continue;
            }
            currentCount++;
        }

        return maxCount;
    }

    // Finding Max Sum of Contigous SubArray 
    // Max_Ending is the current max_sum till element i;
    // It works only for contigous subarray 
    // bottom up approach. {i} <- starts here then computes till {i, i+1, ... i+n}
    static int maxSumSubArray(int[] arr) {
        int max_sum = Integer.MIN_VALUE;
        int max_ending = arr[0];

        for(int i=1;i<arr.length;i++) {
            max_ending = Math.max(max_ending+arr[i], arr[i]);
            max_sum = Math.max(max_ending, max_sum);
        }
        return max_sum;
    }

    // Max Even Odd length SubArray 
    // [1,2,3,4,5] => 
    static int maxEvenOddSubArray(int[] arr) {
        int max_len = 1;
        int current_len = 1;
        boolean IsPrevEven = arr[0] % 2 == 0;

        for(int i=1; i<arr.length; i++) {
            boolean IsCurrentEven = arr[0] % 2 == 0;

            if ((IsPrevEven && IsCurrentEven) || (!IsPrevEven && !IsCurrentEven)) {
                max_len = Math.max(max_len, current_len);
                current_len = 1;
                continue;
            }
            IsPrevEven = arr[i] % 2 == 0;
            current_len++;
        }
        return max_len;
    }

}

