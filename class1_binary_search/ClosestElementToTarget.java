/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package class1_binary_search;

/**
 *
 * @author elevenli
 */
public class ClosestElementToTarget {
    public int closestElementToTarget(int[] array, int target){
        // corner case 
        if(array == null || array.length == 0)return -1;
        int left = 0;
        int right = array.length-1;
        // 这里是< 而不是 <= 因为有可能找不到，
        // 但是依然需要target 左右两边的值
        // 为了让left 在 right 左边的时候就跳出循环，需要设置
        // left<right-1. 否则假设当left=1 && right=2 的时候并
        // 没有结束循环而是要再进行一轮循环，但是违反了terminate 的条件
        while(left < right-1){
            int mid = (left+right)/2;
            // 如果target 找到了那么它本身就是最近的值
            if(array[mid] == target) return mid;
            // left || right = mid 原因是我们依然可能需要返回这两个值
            else if(array[mid] < target) left = mid;
            else right = mid;
        }  
        // post-processing
        // 如果我们没有找到这个target，那么我们需要判断最后落在target的
        // left 和 right 的值跟target 哪个更小，返回哪个
        return Math.abs(array[left]-target)
                <=Math.abs(array[right]-target)
                ?left : right;  
    }
    
    public int FirstTarget(int[] array ,int target){
        // corner case
        if(array == null || array.length == 0) return -1;
        int left =0;
        int right = array.length - 1;
        // loop 的terminate 条件是当left 在right 左边的时候
        while(left < right-1){
            int mid = (left+right)/2;
            if(array[mid] == target) {
                // case1
                right = mid;
            }
            // case2
            else if(array[mid] < target) left = mid+1;
            // case3
            else right = mid -1;
        }
        // post-processing. 来判断left 先是target
        // 还是right先是target。
        if(array[left] == target) return left;
        if(array[right] == target) return right;
        return -1;
    }
    
    public int LastTarget(int[] array ,int target){
        // corner case
        if(array == null || array.length == 0) return -1;
        int left =0;
        int right = array.length - 1;
        // loop 的terminate 条件是当left 在right 左边的时候
        while(left < right-1){
            int mid = (left+right)/2;
            if(array[mid] == target) {
                // case1 // 对比first_target，
                // 这里需要让left移动以保证往后面找
                left = mid;
            }
            // case2
            else if(array[mid] < target) left = mid+1;
            // case3
            else right = mid -1;
        }
        // post-processing.
        // 对比first_target，这里需要先判断right
        if(array[right] == target) return right;
        if(array[left] == target) return left;
        return -1;
    }
    
    // this need to check the answer
    public int SmallestElementLargerThanTarget(int[] array, int target){
        // corner case
        if(array == null || array.length == 0) return -1;
        int left =0;
        int right = array.length -1;
        while(left <right-1){
            int mid = (left + right)/2;
            if(array[mid] <= target){
                left=mid;
            }
            else right = mid;
        }
        // 这里有问题，array[left] 永远不可能超过 target
        // if(array[left] > target) return left;
        if(array[right] > target) return right;
        return -1;
    }
    
    public int[] KthClosetElement(int[] array, int target, int k){
        int[] k_list = new int[k];
        // corner case
        //if(array == null || array.length == 0) return -1;
        int left = 0;
        int right = array.length -1;
        // 寻找元素的左右两边或者元素本身
        while(left < right -1){
            int mid = (left+ right)/2;
            if(array[mid] == target) left=mid;
            else if(array[mid] < target) left = mid;
            else right = mid;
        }
        //post-processing
        for (int i = 0; i < k; i++) {
            // case3: corner case 
            if (left < 0){
              k_list[i] = array[right];
              right++;}
            else if(right > array.length -1){
              k_list[i] = array[left];
              left--;}
            // case1 and case2 determine.
            else if(Math.abs(array[left] - target) 
                    <= Math.abs(array[right] - target)){
                k_list[i] = array[left];
                left--;} 
            else{
                k_list[i] = array[right];
                right++;}
        }
        return k_list;
    }
    
    public int KthSmallestInTwoSortedArrays(int[] a, int[]b, int k){
        return kTwoArraysHelper(a,b,0,0,k);   
    }
    public int kTwoArraysHelper(int[] a,int[] b, int aleft, int bleft, int k){
        // base case: when used entire array, we just need to use other array
        // to find its k-th elements
        if(aleft >= a.length) return b[bleft + k - 1];
        if(bleft >= b.length)return a[aleft + k - 1];
        // base case: if k = 1 then compare two left, and the smaller 
        // one is the result
        if(k == 1) return Math.min(a[aleft], b[bleft]);
        
        // find mid of each array base on the left bound, 
        // in order to comparing them.
        int amid = aleft + k/2-1;
        int bmid = bleft + k/2-1;


        // if the mid with left bound over the range, means that we do not
        // have any element avaliable for current array, so we have to assign 
        // huge value in order to just get other array's elements
        int aval = amid >= a.length ? Integer.MAX_VALUE : a[amid] ;
        int bval = bmid >= b.length ? Integer.MAX_VALUE : b[bmid] ;
        
        // key method
        // doing the comparision. if one value smaller than other one, mean that
        // we can eliminate smaller array with position before, because those
        // element for sure will not include the kth element.
        return aval < bval ? 
                kTwoArraysHelper(a,b, amid+1, bleft, k - k/2): 
                kTwoArraysHelper(a,b, aleft, bmid+1, k - k/2);
    }
}
