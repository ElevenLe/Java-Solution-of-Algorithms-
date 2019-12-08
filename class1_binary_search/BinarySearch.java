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
public class BinarySearch {
    // ascending order for array
    public int binarySearch(int[] array, int target){
        if(array == null || array.length == 0) return -1;
        int left = 0;
        int right = array.length-1;
        while(left <= right){
            int mid = (left + right)/2; 
            // left + (right-left)/2 is same but eaz to explain
            if(array[mid] == target) return mid;
            // if descending order, then just change < to > 
            else if (array[mid] < target){left = mid + 1;}
            else right = mid - 1;     
        }
        return -1;
    }
    
    public int searchInsertPosition(int[] array, int target) {
        // inial conrner case
        if (array == null || array.length == 0) return 0;
        // cornor case of target larger than biggest
        // or smaller than smallest elements.
        if (target > array[array.length-1]) return array.length;
        else if (target <= array[0]) return 0;
        int left = 0;
        int right = array.length - 1;
        // search where most left position of such target
        // can put
        while (left < right - 1) {
            int mid = (left + right) / 2;
            if (array[mid] >= target) right = mid;
            else left = mid;}
        return right;
    }
}
