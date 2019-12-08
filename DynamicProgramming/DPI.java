package DynamicProgramming;

import java.util.HashMap;

public class DPI {

    // not the best solution
    public long fibonacci(int K) {
        HashMap<Integer, Long> apperance = new HashMap<>();
        return fibNHelper(K,apperance);
    }

    private long fibNHelper(int k, HashMap<Integer, Long> apperance){
        if(k == 1 || k == 0) return k;
        if(apperance.containsKey(k)) return apperance.get(k);
        long result = fibNHelper(k-1,apperance) + fibNHelper(k-2,apperance);
        apperance.put(k,result);
        return result;
    }

    // best dp with space(n)
    public long fibonacciII(int K) {
        // Write your solution here
        // using array
        // corner case : K<0
        if(K <= 0) return 0;
        // why k + 1 here?
        long[] dpTable = new long[K+1];
        dpTable[0] = 0;
        dpTable[1] = 1;
        for(int i = 2;i <= K; i++){
            dpTable[i] = dpTable[i-1] + dpTable[i-2];
        }

        return dpTable[K];
    }

    // best dp with space(1)
//    public long fibonacciIII(int K) {
//
//    }
//

    public int longestAscendingSubArrayI(int[] array) {
        if(array.length == 0) return 0;
        int[] DPtable = new int[array.length];
        DPtable[0] = 1;
        for(int i =1; i< array.length; i++){
            if(array[i] <= array[i-1]) DPtable[i] = 1;
            else DPtable[i] = DPtable[i-1] + 1;
        }
        int result = 0;
        for(int i =0 ; i< array.length; i ++ ){
            if(DPtable[i] > result) result = DPtable[i];
        }
        return result;
    }

    public int longestAscendingSubArrayII(int[] array){
        if(array.length == 0) return  0;
        int result = 1;
        int curr = 1;
        for(int i = 1;i < array.length; i++){
            if(array[i] <= array[i-1]) curr = 1;
            else curr++; result = Math.max(curr,result);
        }
        return result;
    }

    public int maxProduct(int length) {
        if(length == 2) return 1;
        int[] DPtable = new int[length+1];
        DPtable[0] = 1;
        DPtable[1] = 1;
        for(int i = 2; i <= length; i++){
            int curMax = 0;
            // j is looping cutting from 1 to i, so that just for i length rod, find the max
            for(int j = 1; j < i; j++){
                // find the possible large in j
                // now j is 1 * j, repersent keep not cut
                // dptable[j] is find that when cut made before j, what is largest
                // compare two
                int largeJ= Math.max(j,DPtable[j]);
                // at position j, it have to times how many left from j to i, so that
                // product would be at i, but cut at j, the value
                int value = largeJ * (i-j);
                // update the curMax for all j
                curMax = Math.max(curMax, value);
            }
            DPtable[i] = curMax;
        }
        return DPtable[length];
    }

    public int palindromePartitioning(String input){
        if(input.length() == 1) return 0;
        char[] array = input.toCharArray();
        if (checkPalindrome(array,0,array.length-1)) return 0;
        int[] DPtable = new int[input.length()+ 1];
        DPtable[0] = 0;
        DPtable[1] = 1;
        for(int i = 2; i <= input.length();i ++){
            int curmin = 0;
            for(int j = 1; j < i; j++){
                if(checkPalindrome(array,j,i)){
                    int mina = Math.min(j, DPtable[j]);
                    curmin = Math.min(curmin,mina);
                }

            }
            DPtable[i] = curmin;
        }
        return DPtable[input.length()];
    }

    public boolean checkPalindrome(char[] input, int left, int right){
        int i = left;
        int j = right;
        while (j-i > 1){
            if(input[j] == input[i]){
                i++;
                j++;
            }
            else return false;
        }
        return true;
    }


    // Array Hopper I
    public boolean canJump(int[] array) {
        if(array.length == 1) return true;
        boolean[] DPtable = new boolean[array.length];
        for(int i = array.length-2; i >= 0; i --){
            if(i + array[i] >= array.length -1){
                DPtable[i] = true;
            }
            else {
                for(int j = array[i]; j >= 1; j--){
                    if(DPtable[i+j]){
                        DPtable[i] = true;
                        break;
                    }
                }
            }
        }
        return DPtable[0];
    }

}
