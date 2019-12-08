package DynamicProgramming;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.HashSet;
import java.util.Set;

public class DPII {
    public int minJump(int[] array) {
        int[] DPmin = new int[array.length];
        DPmin[array.length - 1] = 0;
        for (int i = array.length - 2; i >= 0; i--) {
            DPmin[i] = -1;
            for (int j = i + array[i]; j > i; j--) {
                if (j < array.length && DPmin[j] != -1) {
                    if (DPmin[i] == -1 || DPmin[j] + 1 < DPmin[i]) {
                        DPmin[i] = DPmin[j] + 1;
                    }
                }
            }
        }
        return DPmin[0];
    }


    // finding the largest sum of subarray.
    // subarray so that have to be consistent
    // assumption: sub array must contain one element;
    public int largestSum(int[] array) {
        int globalMax = array[0];
        int[] DPtable = new int[array.length];
        DPtable[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            if (DPtable[i - 1] < 0) DPtable[i] = array[i];
            else DPtable[i] = DPtable[i - 1] + array[i];
            globalMax = DPtable[i] > globalMax ? DPtable[i] : globalMax;
        }
        return globalMax;
    }

    // short version of above
    public int largestSum2(int[] array) {
        int globalMax = array[0];
        int[] DPtable = new int[array.length];
        DPtable[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            // here just compare if previous value is very negative,
            // then replace it with i th value. which is start from
            // beginning.
            DPtable[i] = Math.max(DPtable[i - 1] + array[i], array[i]);
            // update global max with compare current value
            globalMax = Math.max(DPtable[i], globalMax);
        }
        return globalMax;
    }

    // save space action: instead using array, just use a int;
    public int largestSum3(int[] array) {
        // dptable[i] means the max sum ending at current index;
        // but only record the largest sum should be work;
        int curr = array[0];
        int result = array[0];
        for (int i = 1; i < array.length; i++) {
            curr = Math.max(curr + array[i], array[i]);
            result = Math.max(curr, result);
        }
        return result;
    }


    public boolean canBreak(String input, String[] dict) {
        boolean[] DPtable = new boolean[input.length() + 1];
        Set<String> strDict = toSet(dict);
        for (int i = 1; i < DPtable.length; i++) {
            if (strDict.contains(input.substring(0, i))) {
                DPtable[i] = true;
            }
            for (int j = 0; j < i; j++) {
                if (DPtable[j] && strDict.contains(input.substring(j, i))) {
                    DPtable[i] = true;
                    break;
                }
            }
        }
        return DPtable[input.length()];
    }

    // shorter version
    public boolean canBreak2(String input, String[] dict) {
        boolean[] DPtable = new boolean[input.length() + 1];
        Set<String> dic = toSet(dict);
        DPtable[0] = true;
        for (int i = 0; i < DPtable.length; i++) {
            for (int j = 0; j < i; j++) {
                if (DPtable[j] && dic.contains(input.substring(j, i))) {
                    DPtable[i] = true;
                    break;
                }
            }
        }
        return DPtable[input.length()];
    }

    private Set<String> toSet(String[] dict) {
        Set<String> hash = new HashSet<>();
        for (String one : dict) {
            hash.add(one);
        }
        return hash;
    }

    // there is two way of solving this problem
    // one is the recursion, and other is reduce
    // the runtime of such recursion by DP
    // Recursion
    public int editDistance(String one, String two) {
        if (one.isEmpty()) return two.length();
        if (two.isEmpty()) return one.length();
        // consider four cases
        // if current char are the same
        if (one.charAt(0) == two.charAt(0)) {
            return editDistance(one.substring(1), two.substring(1));
        }

        // replace one's char with two's char. then they will be same
        // at first char, so both increase 1. But since the doing one
        // operation, so add 1
        int replace = 1 + editDistance(one.substring(1), two.substring(1));
        // delete one's char. so that one increase by 1, and two do nothing;
        // but still one operation. so add one;
        int delete = 1 + editDistance(one.substring(1), two);
        // insert one's char, so that the first would be the same;
        // after one add one, it's first char would be the same as two's first
        // so then we need compare one's remind, which is keep the same, but
        // two increased by one
        // one do not change, two increase one
        // one operation so add one
        int insert = 1 + editDistance(one, two.substring(1));

        return min(replace, delete, insert);
    }

    private int min(int one, int two, int three) {
        return one <= two ? one <= three ? one : three : two <= three ? two : three;
    }

    // DP solution

    public int editDistance2(String one, String two) {
        int[][] DPtable = new int[one.length() + 1][two.length() + 1];
        for (int i = 0; i <= one.length(); i++) {
            for (int j = 0; j <= two.length(); j++) {
                if (i == 0) DPtable[i][j] = j;
                else if (j == 0) DPtable[i][j] = i;
                else if (one.charAt(i - 1) == two.charAt(j - 1))
                    DPtable[i][j] = DPtable[i - 1][j - 1];
                else {
                    DPtable[i][j] = Math.min(DPtable[i - 1][j] + 1, DPtable[i][j - 1] + 1);
                    DPtable[i][j] = Math.min(DPtable[i - 1][j - 1]+1, DPtable[i][j]);
                }
            }
        }
        return DPtable[one.length()][two.length()];
    }

    // each cell in DPtable represents the max size of a square with (i,j) as bottom right corner
    public int largest(int[][] matrix) {
        int length = matrix.length;
        if(length == 0) return 0;
        int[][] DPtable = new int[length][length];
        int result = 0;
        for(int i=0; i < length; i++){
            for(int j = 0; j < length; j ++){
                if(i == 0|| j ==0) DPtable[i][j] = matrix[i][j]==1?1:0;
                else if(matrix[i][j] == 1){
                    DPtable[i][j] = Math.min(DPtable[i][j-1]+1,DPtable[i-1][j]+1);
                    DPtable[i][j] = Math.min(DPtable[i-1][j-1] + 1, DPtable[i][j]);
                }
                result = Math.max(result, DPtable[i][j]);
            }
        }
        return result;
    }

}
