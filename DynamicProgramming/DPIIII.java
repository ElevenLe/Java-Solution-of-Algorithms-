package DynamicProgramming;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class DPIIII {
    public int minCost(int[] cuts, int length) {
        int[] helper = new int[cuts.length + 2];
        helper[0] = 0;
        for(int i = 0; i < cuts.length; i++){
            helper[i+1] = cuts[i];
        }
        // ?
        helper[helper.length - 1] = length;
        //
        int[][] minCost = new int[helper.length][helper.length];
        for(int i =1 ; i < helper.length ;i++){
            for(int j = i-1; j >= 0; j--){
                if(j + 1 == i){
                    minCost[j][i] = 0;
                } else {
                    minCost[j][i] = Integer.MAX_VALUE;
                    for(int k = j+1; k <= i-1; k++){
                        minCost[j][i] = Math.min(minCost[j][i], minCost[j][k] + minCost[k][i]);
                    }
                    minCost[j][i] += helper[i] - helper[j];
                }
            }
        }
        return minCost[0][helper.length-1];
    }


    // longest common substring
    // require continuity
    public String longestCommon(String source, String target) {
        // transfer the string to array
        // so that we can compare the char
        char[] strS = source.toCharArray();
        char[] strT = target.toCharArray();

        int start = 0;
        int longest = 0;
        int[][] dpTable = new int[source.length()][target.length()];
        for(int i = 0; i < source.length(); i++){
            for(int j = 0; j < target.length(); j++){
                // writing into the table
                // case 1, on the border
                // only have to record it as 1
                if(strS[i] == strT[j]){
                    if( i == 0 || j == 0) dpTable[i][j] = 1;
                    else dpTable[i][j] = 1 + dpTable[i-1][j-1];
                }
                //update with largest common substring
                if(dpTable[i][j] > longest){
                    longest = dpTable[i][j];
                    start = i - longest + 1;
                }
            }
        }
        return source.substring(start,start+ longest);
    }
    // longest common subsequence
    // can be continuity
    public int longest(String source, String target) {
        int[][] dpTable = new int[source.length()+1][target.length()+1];
        for(int s = 1; s <= source.length(); s++){
            for(int t = 1; t <= target.length(); t++){
                if(source.charAt(s-1) == target.charAt(t-1)){
                    dpTable[s][t] = dpTable[s-1][t-1] + 1;
                }
                else {
                    dpTable[s][t] = Math.max(dpTable[s-1][t],dpTable[s][t-1]);
                }
            }
        }
        return dpTable[source.length()][target.length()];
    }

    // Longest Ascending Subsequence
    // n^2
    public int LAS(int[] array) {
        if(array.length == 0) return 0;
        if(array.length == 1) return 1;
        int[] dpTable = new int[array.length];
        dpTable[0] = 1;
        int larget = 0;
        for(int i = 1; i < array.length; i++){
            dpTable[i] = 1+LASsubfind(array,dpTable,i);
            larget = Math.max(dpTable[i],larget);
        }
        return larget;
    }
    private int LASsubfind(int[] array,int[] dp, int right){
        int largest = 0;
        for(int i = 0; i < right; i ++){
            if(array[right] > array[i] ){
                largest = Math.max(largest, dp[i]);
            }
        }
        return largest;
    }

//     Longest Ascending Subsequence
//     nlogn
    public int LASII(int[] array){
        if(array.length == 0){
            return 0;
        }
        int[] tbl = new int[array.length + 1];
        int result = 1;
        tbl[1] = array[0];
        for(int i = 1; i < array.length; i++){
            int index = find(tbl,1,result,array[i]);
            if(index == result) {
                tbl[++result] = array[i];
            }else {
                tbl[index + 1] = array[i];
            }
        }
        return result;
    }

    private int find(int[] tbl, int left, int right, int target){
        while (left <= right){
            int mid = left + (right - left) /2;
            if(tbl[mid] >= target){
                right = mid-1;
            }else {
                left = mid + 1;
            }
        }
        return right;


    }

    class Point{
        public int x;
        public int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    // Most Points On A Line
    public int most(Point[] points) {
        int result = 0;
        for (int i =0; i<points.length; i++){
            // line can be represented by a point and a slope
            // we take the point as seed and try to find all
            // possible slopes
            Point seed = points[i];
            // record the points with same <x,y>
            int same = 1;
            // record the points with same x, for the special case
            // infinite slope
            int sameX= 0;
            // record the maximum number of points on the same line
            // crossing the seed point
            int most = 0;
            // a map with all possible slopes
            HashMap<Double, Integer> cnt = new HashMap<>();
            for(int j = 0; j < points.length; j++){
                if(i == j){
                    continue;
                }
                Point tmp = points[j];
                // same point as x
                if(tmp.x == seed.x && tmp.y == seed.y){
                    same++;
                }
                else {
                    // otherwise calculate the slope and increment the counter
                    // for the calculated slope
                    double slope = ((tmp.y - seed.y) + 0.0) / (tmp.x - seed.x);
                    if(!cnt.containsKey(slope)){
                        cnt.put(slope,1);
                    }else {
                        cnt.put(slope,cnt.get(slope)+1);
                    }
                    most = Math.max(most,cnt.get(slope));
                }
            }
            most = Math.max(most, sameX) + same;
            result = Math.max(result, most);
        }
        return result;
    }

    // largest set of points with positive slopes
    // a set such that any pair of points in the set
    // can form a line with positive slope.
    public int largest(Point[] points) {
        Arrays.sort(points, new MyComparator());
        int result = 0;
        int[] longest = new int[points.length];
        for (int i  =0 ; i < longest.length; i++){
            for(int j = 0; j < i; j ++){
                if(points[j].y < points[i].y){
                    longest[i] = Math.max(longest[i], longest[j]);
                }
            }
            longest[i]++;
            result = Math.max(result, longest[i]);
        }
        return result == 1?0 : result;
    }

    static class MyComparator implements Comparator<Point>{
        @Override
        public int compare(Point o1, Point o2) {
            return o1 != o2 ? o1.x - o2.x : o2.y - o1.y;
        }
    }
}
