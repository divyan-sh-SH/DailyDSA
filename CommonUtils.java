 import java.util.*;
 
 class CommonUtils {
    static void swap(int[] arr, int firstIndex, int secondIndex) {
        int temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
    }

    // Using Two Pointer
    static void reverseArray(int[] arr) {
        int l=0;
        int r=arr.length-1;

        while(l<r) {
            swap(arr, l, r);
            l++; r--;
        }
    }

    static void printObjectList(List<Integer> list) {
        for(Object i: list) {
            System.out.println(i);
        }
    }

    static int factorial(int n) {
        int[] dp = new int[n+1];
        if(n < 2) {
            return 1;
        }

        dp[0] = 1;
        dp[1] = 1;
        for(int i=2;i<=n;i++) {
            dp[i] = i*dp[i-1];
        }

        return dp[n];
    }
}
