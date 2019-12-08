package class_16_CrossTraining1;

import class_5_BST_BT.TreeNode;

import java.lang.reflect.Array;
import java.util.*;

public class CT1 {
    // Given a sorted integer array, remove duplicate elements.
    // For each group of elements with the same value keep only one of them.
    public int[] dedup(int[] array) {
        if(array.length <= 1) return array;
        int i =1;
        for(int j = 1; j < array.length; j ++){
            if(array[i-1] == array[j]) continue;
            array[i] = array[j];
            i++;
        }
        return Arrays.copyOf(array, i);
    }

    // Given a sorted integer array, remove duplicate elements.
    // For each group of elements with the same value keep at most two of them.
    public int[] dedupII(int[] array) {
        if(array.length <= 2) return array;
        int i = 2;
        for(int j = 2; j < array.length; j ++){
            if(array[i-2] == array[j]) continue;
            array[i] = array[j];
            i++;
        }
        return Arrays.copyOf(array,i);
    }

    // Given a sorted integer array, remove duplicate elements.
    // For each group of elements with the same value do not keep any of them
    public int[] dedupIII(int[] array){
        if(array.length <= 1) return array;
        int slow = 0;
        int fast = 0;
        while ( fast < array.length){
            int begin = fast;
            while (fast < array.length && array[fast] == array[begin]){
                fast++;
            }
            if(fast - begin == 1){
                array[slow] = array[begin];
                slow++;
            }
        }
        return Arrays.copyOf(array,slow);
    }

    // using same kind of method as before
    // but instead using stack. it change
    // to using first part of array as stack
    public int[] dedupIIII(int[] array) {
        if (array.length <= 1) return array;
        int slow = -1;
        // here we use left part as the original stack
        // if the top of stack is the same as fast pointer
        // then it should be poll. else then add
        for (int fast = 0; fast < array.length; fast++) {
            if (slow == -1 || array[fast] != array[slow]) {
                slow++;
                array[slow] = array[fast];
            } else {
                while (fast + 1 < array.length && array[fast + 1] == array[slow]) {
                    fast++;
                }
                slow--;
            }
        }
        return Arrays.copyOf(array, slow);
    }

    public int[] moveZero(int[] array) {
        if (array.length <= 1) return array;
        int slow = 0;
        for(int fast = 0; fast< array.length; fast++){
            if(array[slow] == 0){
                while (fast < array.length-1 && array[fast] == 0){
                    fast++;
                }
                swap(array,slow,fast);
                slow++;
            }
            else slow++;
        }
        return array;
    }

    // other way of doing this question
    public int[] moveZeroII(int[] array){
        if(array.length <= 1) return array;
        int slow = 0;
        // only consider the fast's position of situation
        for(int fast = 0; fast < array.length; fast++){
            if(array[fast] != 0) {
                array[slow] = array[fast];
                slow++;
            }
        }
        // let the rest of array become all zero
        for(int i = slow; i < array.length; i++){
            array[i] = 0;
        }
        return array;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public int[] largestAndSmallest(int[] array) {
        int[] result = new int[2];
        if (array.length == 1){
            result[0] = array[0];
            result[1] = array[0];
            return result;
        }
        if(array.length == 2){
            result[0] = array[0] > array[1] ? array[0] : array[1];
            result[1] = array[0] <= array[1] ? array[0] : array[1];
            return result;
        }
        int n = array.length;
        for(int i =0; i <= n/2; i ++){
            if(array[i] > array[n/2+i]){
                swap(array, i, n/2+i);
            }
        }
        result[0] = largest(array,n/2+1,array.length);
        result[1] = smallest(array,0,n/2+1);
        return result;
    }

    public int[] largestAndSmallestII(int[] array){
        int n = array.length;
        for(int i = 0; i < n/2 ; i ++){
            if(array[i] < array[n-1-i]){
                swap(array,i,n-1-i);
            }
        }

        return new int[]{largest(array,0,(n-1)/2+1),smallest(array,(n-1)/2+1,array.length)};
    }

    private int largest(int[] array, int left, int right){
        int largest = array[left];
        for(int i = left + 1; i < right; i++){
            largest = Math.max(largest,array[i]);
        }
        return largest;
    }

    private int smallest(int[] array, int left, int right){
        int smallest = array[left];
        for(int i = left + 1; i < right; i++){
            smallest = Math.min(smallest,array[i]);
        }
        return smallest;
    }

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if(n <= 1) return;
        rotateHelper(matrix,0,matrix.length);
    }

    private void rotateHelper(int[][] matrix, int offset, int size){
        if(size<=1) return;
        rotate90(matrix,offset, size);
        mirror(matrix,offset,size);
        rotateHelper(matrix,offset+1,size-2);
    }

    private void rotate90(int[][] matrix, int offset, int size){
        for(int i = 0; i < size-1; i++){
            swapMatrix(matrix,offset,i+offset,size-1-i+offset,offset);
            swapMatrix(matrix, i+offset,offset+size-1, offset-1+size,size-1-i+offset);
        }
    }

    private void mirror(int[][] matrix, int offset, int size){
        int leftIndex = 1+offset;
        int rightIndex = size-2+offset;
        while (leftIndex < size+offset && rightIndex >= offset){
            swapMatrix(matrix,leftIndex,offset,rightIndex,offset+size-1);
            leftIndex ++;
            rightIndex--;
        }
    }

    private void swapMatrix(int[][] matrix, int leftRow, int leftCol, int rightRow, int rightCol){
        int temp = matrix[leftRow][leftCol];
        matrix[leftRow][leftCol] = matrix[rightRow][rightCol];
        matrix[rightRow][rightCol] = temp;
    }

    public List<Integer> zigZag(TreeNode root) {
        if(root == null) return new LinkedList<Integer>();
        Deque<TreeNode> deque = new LinkedList<>();
        List<Integer> result = new LinkedList<Integer>();
        deque.offerFirst(root);
        int layer = 1;
        while (!deque.isEmpty()){
            int size = deque.size();
            for(int i =0; i < size; i++){
                if (layer == 1){
                    // first layer, odd layer, from right to left
                    // [first ... last] -->
                    TreeNode temp = deque.pollLast();
                    result.add(temp.key);
                    // --> [first ... last]
                    if(temp.right != null) deque.offerFirst(temp.right);
                    if(temp.left != null) deque.offerFirst(temp.left);

                }else {
                    // second layer, even layer, from left to right
                    // <-- [first ... last]
                    TreeNode temp = deque.pollFirst();
                    result.add(temp.key);
                    // [first ... last] <--
                    if(temp.left != null) deque.offerLast(temp.left);
                    if(temp.right != null) deque.offerLast(temp.right);
                }
            }
            layer = 1 - layer;
        }return result;
    }

    }
