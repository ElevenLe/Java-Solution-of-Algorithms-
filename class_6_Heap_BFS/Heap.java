package class_6_Heap_BFS;

import class_5_BST_BT.TreeNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Heap {

    // the operation of update in heap
    public void max_heapify(int[] array, int position){
        // get current value left child
        int left = position*2 + 1;
        // get current value right child
        int right = position*2 + 2;
        // record the biggest value's position
        int largest = 0;
        // find the largest elements among self, left, right
        // first comparision. compare self with left
        if(left <= array.length-1 && array[position] < array[left])
            // if left larger, update largest with left position.
            largest = left;
        // otherwise update as self
        else largest= position;
        // second comparision. compare the larger one among left and
        // self with right. and update the largest position
        if(right <= array.length-1 && array[right] > array[largest])
            largest= right;
        // operation. If find the largest is not the self, then need
        // to swap and recursion.
        if(largest != position){
            swap(array, largest,position);
            max_heapify(array,largest);
        }
    }

    private void swap(int[] a, int left, int right){
        int temp = a[left];
        a[left] = a[right];
        a[right] = temp;
    }

    // building a heap from the unsorted array
    public void buildHeap(int[] array){
        // 从一半的元素开始max_heapify 可以保证都能找到left and right
        for(int i = (array.length-1) /2; i >= 0; i--){
            max_heapify(array,i);
        }
    }
    public int[] kSmallest(int[] array, int k){
        // base case
        if(k == 0|| array.length == 0) return new int[]{array[0]};
        // get a max heap. which the original heap PriorityQueue
        // is min heap. Using a comparetor function.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k,
                new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1.equals(o2)) return 0;
                return o1 > o2? -1 : 1; }});
        // go over the array
        for(int i = 0;i < array.length-1 ;i++){
            // first, create k elements of max heap
            if(i<k) maxHeap.offer(array[i]);
            // if now is k+1 and more, do comparing
            else{
                if(array[i] < maxHeap.peek()){
                    maxHeap.poll();
                    maxHeap.offer(array[i]);
                } } }
        // transfer max heap to array
        int[] result = new int[k];
        while(k != 0){
            result[k-1] = maxHeap.poll();k--; }
        return result;
    }

    // public int[] kSmallestII(int[] array, int k){}

    // public int[] kSmallestIII(int[] array, int k){}
}
