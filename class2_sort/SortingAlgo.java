/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package class2_sort;

/**
 *
 * @author elevenli
 */
public class SortingAlgo {
    
    // insertion sort an array in place
    public void insertionSort(int[] a){
        for(int j = 1; j <= a.length-1; j ++){
            // store the value for comparsion
            int key = a[j];
            // start the comparision from j left 1
            int i = j -1;
            // loop back and place the value 
            // in the smallest possible position
            // and also move all the element that is
            // larger back
            while(i >=0 && a[i] > key){
                a[i+1] = a[i];
                i--;}
            a[i+1] = key;
        }
    }
    
    // selection sort an array in place
    public void selectionSort(int[] a){
        // set the search range, reduce each time
        for(int i = 0; i < a.length; i++){
            // set first element in search range as minimum；
            int minValue = i;
            // here is find the minimum in the range and 
            // save the position
            for(int j=i; j < a.length; j++){
                if(a[j] < a[minValue]) minValue = j;
            }
            // swap the value for the first 
            // with minimum value
            int key = a[i];
            a[i] = a[minValue];
            a[minValue] = key;   
        }
    }
    
    public int[] mergeSort(int[] a){
       if(a == null || a.length == 0) return a;
       mergeSortHelper(a,0,a.length-1);
       return a;
    }
    
    private void mergeSortHelper(int[] a, int left, int right){
        // base caes, if the left is right position,\
        // means it reach the bottom
        if(left >= right) return ;
        // spearate in the middle
        int mid = (left + right)/2;
        // merge sort left part
        mergeSortHelper(a,left, mid);
        // merge sort right part
        mergeSortHelper(a,mid+1,right);
        // merge two sorted part
        merge(a,left,right,mid);
    }
    
   private void merge(int[] a, int left, int right,int mid){
        // create a new helper array in order to copy the value
        // the size of the helper have to be the sum of two length
        int[] helper = new int[right - left + 1];
        int leftIndex = left;
        int rightIndex = mid+1;
        for (int i = 0; i < helper.length; i++) {
            // two condition, if leftIndex is smaller and inside the range,
            // add the left into array. While, if rightIndex run out, then
            // using the left all the time.
            if (rightIndex > right || 
                    (a[leftIndex] < a[rightIndex] && leftIndex <= mid)) {
                helper[i] = a[leftIndex];
                leftIndex++;} 
            else{
                helper[i] = a[rightIndex];
                rightIndex++;}
        }
        // copy back from helper array to inital array
        for (int i = 0; i < helper.length; i++) {
            a[left] = helper[i];
            left++;
        }
    }

    public int[] quickSort(int[] array) {
        if (array.length == 0 || array.length == 1) return array;
        quickSortHelper(array, 0, array.length - 1);
        return array;
    }

    public void quickSortHelper(int[] a, int left, int right) {
        // recursion base case
        if (left > right) return;
        // partition on the pivot, and get where
        // the pivot positioin
        int pivot = partition(a, left, right);
        // recursion on the left side of the pivot
        quickSortHelper(a, left, pivot - 1);
        // recursion on the right side of the pivot
        quickSortHelper(a, pivot + 1, right);
    }

    public int partition(int[] a, int left, int right) {
        // set most left element as pivot so that
        // we only need to partition through right part
        int key = a[left];
        int leftIndex = left + 1;
        int rightIndex = right;
        // if only one side meet move requirement, then other
        // side will wait until the side do not meet requirment
        while (leftIndex <= rightIndex) {
            // the move of the pointer
            // if on the left side, value is less than key,
            // then increase pointer because meet the requirement
            if (a[leftIndex] < key) leftIndex++;
            // same as above
            else if (a[rightIndex] > key) rightIndex--;
            // if any above requirement do not meet
            // then must say that both side need to
            // swap.
            else {
                int temp = a[leftIndex];
                a[leftIndex] = a[rightIndex];
                a[rightIndex] = temp;
                leftIndex++;
                rightIndex--;
        }}
        // swap back to the pivot to the middle
        // where left is all smaller and right
        // is all larger
        a[left] = a[rightIndex];
        a[rightIndex] = key;
        return rightIndex;

    }
    
    // three element rainbow sort
    public int[] rainBowSort(int[] array){
        int i = 0; // -1, first element
        int j = 0; // 0 , second element
        int k = array.length - 1; // 1, third element
        
        while(j <= k){
            switch (array[j]) {
                case -1: swap(array, j++, i++);
                // case 1, -1, should be placed on the left.
                // so only need to swap this element to the
                // left
                    break;
                case 0: j++;
                // if current element is second item. place it as
                // it is
                    break;
                case 1: swap(array, j,  k--);
                // if current element is third item, place it on
                // the back
                    break;
            }
        }
        return array;
    }
    
    private void swap(int[] a, int left, int right){
        int temp = a[left];
        a[left] = a[right];
        a[right] = temp;
    }
    
    // stupid way of doing mergesort 
//    public int[] mergeSort(int[] array) {
//    // Write your solution here
//    if (array.length==0 || array.length == 1){return array;}
//    
//    int leftLen = array.length/2;
//    int[] left = new int[leftLen];
//    int[] right = new int[array.length-leftLen];
//    for(int i=0; i<array.length; i ++){
//      if(i <= leftLen-1){
//        left[i] = array[i];
//      }else{
//        right[i-leftLen] = array[i];
//      }
//    }
//    int[]out = merge(mergeSort(left),mergeSort(right));
//    return out;
//    
//  }
//  
//  public int[] merge(int[] one, int[] two) {
//    int[] out = new int[one.length+two.length];
//    int temp1 = 0;
//    int temp2 = 0;
//    int inde = 0;
//    if (out.length==0){return out;}
//    
//    if (out.length == 1){
//      if(one.length == 1){return one;}
//      else{return two;}
//    }
//    
//    for(int i=0;i<out.length;i++){
//      if(one[temp1] < two[temp2]){out[i]=one[temp1];temp1 +=1;}
//      else{out[i]=two[temp2];temp2+=1;}
//      
//      if(temp1 == one.length || temp2 == two.length){inde = i+1; break;}
//    }
//    
//    if(inde != out.length){
//      if(temp1 < one.length){
//        for(int i= temp1; i<one.length;i++){out[inde] = one[i];inde +=1;
//        }
//    }
//     if(temp2 < two.length){
//      for(int i= temp2 ; i<two.length;i++){out[inde] = two[i]; inde += 1;}
//      }
//    }
//    
//    return out;
//  }
    
}
