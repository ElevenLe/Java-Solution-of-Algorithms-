package class_16_CrossTraining1;

import class_5_BST_BT.TreeNode;

import java.io.PipedReader;
import java.util.*;

public class CT2 {

    // Deep Copy Linked List With Random Pointer
    // The problem of this question is that when doing
    // deep copy, we actually create a new node in the
    // heap, and such node may generate again.
    // inorder to avoid that happen, it is necessary
    // to create a map to avoid repeat creation
    public RandomListNode copy(RandomListNode head) {
        if(head == null)return head;
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode curr = dummy;

        while (head != null){
            if(!map.containsKey(head)){
                map.put(head,new RandomListNode(head.value));
            }
            curr.next = map.get(head);
            if(head.random != null){
                if(!map.containsKey(head.random)){
                    map.put(head.random,new RandomListNode(head.random.value));
                }
                curr.next.random = map.get(head.random);
            }
            head = head.next;
            curr = curr.next;
        }
        return dummy.next;
    }

    // same as to undirected graph. deep copy may cause
    // node regeneration.
    // input graph is contain all the graph node instead of
    // each graph head
    public List<GraphNode> copyByBFS(List<GraphNode> graph) {
        List<GraphNode> result = new ArrayList<>();
        HashMap<GraphNode, GraphNode> map = new HashMap<>();
        for(GraphNode curr : graph){
            GraphNode node = null;
            if(!map.containsKey(curr)){
                node = new GraphNode(curr.key);
                map.put(curr,node);}
            else {
                node = map.get(curr);
            }
            for(GraphNode neg: curr.neighbors){
                if(map.containsKey(neg)){
                    node.neighbors.add(map.get(neg));
                }
                else {
                    GraphNode negNode = new GraphNode(neg.key);
                    node.neighbors.add(negNode);
                    map.put(neg, negNode);
                }
            }
        }
        for(GraphNode node: map.keySet()){
            result.add(map.get(node));
        }
        return result;
    }

    // DFS
    public List<GraphNode> copyByDFS(List<GraphNode> graph) {
        HashMap<GraphNode, GraphNode> map = new HashMap<>();
        for(GraphNode node: graph){
            if(!map.containsKey(node)){
                map.put(node, new GraphNode(node.key));
                copyDFShleper(node, map);
            }
        }
        return new ArrayList<GraphNode>(map.values());
    }

    private void copyDFShleper(GraphNode seed, HashMap<GraphNode, GraphNode> map){
        GraphNode copy = map.get(seed);
        for(GraphNode each: seed.neighbors){
            if(!map.containsKey(each)){
                map.put(each,new GraphNode(each.key));
                copyDFShleper(each,map);
            }
            copy.neighbors.add(map.get(each));
        }
    }

    // Merge K Sorted Array
    // using min heap with k size
    // loop through all the array, and
    // poll the smallest number, and find
    // which array that this number belong to
    // then add a new value into it
    // inorder to find the which array it belong
    // we need to make a new Entry
    static class Entry{
        int x;
        int y;
        int value;

        Entry(int x, int y, int value){
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
    static class Mycomparator implements Comparator<Entry>{
        @Override
        public int compare(Entry o1, Entry o2) {
            if(o1 == o2) return 0;
            return o1.value < o2.value ? -1 : 1;
        }
    }
    public int[] mergeKsortedArray(int[][] arrayOfArrays) {
        PriorityQueue<Entry> minHeap = new PriorityQueue<>(11, new Mycomparator());
        // calculating how many elements in total
        int length = 0;
        for(int i = 0; i < arrayOfArrays.length ; i++){
            int[] line = arrayOfArrays[i];
            length += line.length;
            if(line.length != 0){
                minHeap.offer(new Entry(i,0,line[0]));
            }
        }

        int[] result = new int[length];
        int cur = 0;
        while (!minHeap.isEmpty()){
            Entry min = minHeap.poll();
            result[cur] = min.value;
            if(min.y + 1 < arrayOfArrays[min.x].length){
                //minHeap.offer(new Entry(min.x,min.y+1,arrayOfArrays[min.x][min.y+1]));
                // if we can just change the entry's position and value
                // we do not have to recreate other entry
                min.y++;
                min.value = arrayOfArrays[min.x][min.y];
                minHeap.offer(min);
            }
            cur ++;
        }
        return result;
    }



    static class ListNode{
        public int value;
        public ListNode next;
        public ListNode(int value) {
            this.value = value;
            next = null;
        }
    }

    static class ListNodeComparator implements Comparator<ListNode>{
        @Override
        public int compare(ListNode o1, ListNode o2) {
            if(o1.value == o2.value) return 0;
            else return o1.value < o2.value? -1 : 1;
        }
    }

    // Merge K Sorted Lists
    public ListNode merge(List<ListNode> listOfLists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(11, new ListNodeComparator());
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        for(ListNode each : listOfLists){
            if(each != null){
                minHeap.offer(each);
            }
        }
        while (!minHeap.isEmpty()){
            curr.next = minHeap.poll();
            if(curr.next.next != null){
                minHeap.offer(curr.next.next);
            }
            curr = curr.next;
        }
        return dummyHead.next;
    }

    // Closest Number In Binary Search Tree
    public int closest(TreeNode root, int target) {
        int result = root.key;
        while (root != null){
            if(result == target) return result;
            if(Math.abs(root.key - target) < Math.abs(result - target)) result = root.key;
            if(root.key < target) root = root.right;
            else root = root.left;
        }
        return result;
    }

    // Largest Number Smaller In Binary Search Tree
    public int largestSmaller(TreeNode root, int target) {
        int result = Integer.MIN_VALUE;
        while (root != null){
            // if root value larger than value, then
            // we need to check weather the left of such
            // root is the largest number smaller than
            // target
            if(root.key >= target){
                root = root.left;
            }
            // if root value smaller than value, then
            // it could be the result
            // however, we still need to go right to
            // see right side have the largest number
            // smaller than. But we do not have to go
            // left since all left is define smaller than
            // current.
            else {
                result = root.key;
                root = root.right;
            }
        }
        return result;
    }

    // my version:
    // find the largest number smaller than the delete target, and it
    // also have to be on the lowest level. So that replace them
    // would not cause any problem.
    // step 1: find the largest number smaller than target
    // step 2: delete such node
    // step 3: find target node and switch it with replace node
    public TreeNode deleteTreeI(TreeNode root, int key) {
        TreeNode replace = findLargestSmallerInRoot(root, key);

        if(replace != null){
            TreeNode find = root;
            while (find != null){
                if(find.left == replace){
                    find.left = null;
                }
                else if(find.right == replace){
                    find.right = null;
                }else {
                    if(replace.key < find.key) find = find.left;
                    else find = find.right;
                }
            }
            TreeNode curr = root;
            while (curr != null){
                TreeNode temp = null;
                if(curr.left.key == key){
                    temp = curr.left;
                    curr.left = replace;
                    replace.left = temp.left;
                    replace.right = temp.right;
                    break;
                }else if(curr.right.key == key) {
                    temp = curr.right;
                    curr.right = replace;
                    replace.left = temp.left;
                    replace.right = temp.right;
                    break;
                }
                else {
                    if (curr.key > key) curr = curr.left;
                    else curr = curr.right;
                }
            }
        }
        else {
            TreeNode find = root;
            while (find != null){
                if(find.left!=null && find.left.key == key){
                    find.left = null;
                }
                else if(find.right != null && find.right.key == key){
                    find.right = null;
                }else {
                    if(key < find.key) find = find.left;
                    else find = find.right;
                }
            }
        }
        return root;
    }

    private TreeNode findLargestSmallerInRoot(TreeNode root, int key){
        TreeNode result = null;
        TreeNode curr = root;
        while (curr != null){
            if(curr.key >= key){
                curr = curr.left;
            }
            else {
                if(curr.left == null && curr.right == null){
                    result = curr;
                }
                curr = curr.right;
            }
        }
        return result;
    }

    // for testing
    public TreeNode sortedArrayToBST(int arr[], int start, int end) {
        /* Base Case */
        if (start > end) {
            return null;
        }
        /* Get the middle element and make it root */
        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(arr[mid]);
        /* Recursively construct the left subtree and make it
         left child of root */
        node.left = sortedArrayToBST(arr, start, mid - 1);
        /* Recursively construct the right subtree and make it
         right child of root */
        node.right = sortedArrayToBST(arr, mid + 1, end);
        return node;
    }


    // lai offer way of doing such work
    public TreeNode deleteII(TreeNode root, int key){
        if(root == null){
            return null;
        }
        if(key == root.key){
            if(root.left == null){
                return root.right;
            }
            else if(root.right == null){
                return root.left;
            }else if(root.right.left == null){
                root.right.left = root.left;
                return root.right;
            }else {
                TreeNode newRoot = deleteSmallest(root.right);
                newRoot.left = root.left;
                newRoot.right = root.right;
                return newRoot;
            }
        }
        if(key < root.key){
            root.left = deleteII(root.left, key);
        }else if(key > root.key){
            root.right = deleteII(root.right,key);
        }
        return root;
    }

    private TreeNode deleteSmallest(TreeNode root){
        while (root.left.left != null){
            root = root.left;
        }
        TreeNode smallest = root.left;
        root.left = root.left.right;
        return smallest;
    }

    //2 Sum : exist two elements in a given array, the sum of
    // which is the given target number.
    public boolean existSum(int[] array, int target) {
        int left = 0;
        int right = array.length -1;
        while (left < right){
            int sum = array[left] + array[right];
            if(sum == target){
                return true;
            }
            else if(sum < target){
                left ++;
            }else {
                right--;
            }
        }
        return false;
    }


    public List<List<Integer>> allTriples(int[] array, int target) {
        // Write your solution here
        Arrays.sort(array);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Set<List<Integer>> set = new HashSet<>();
        if(array.length == 0) return result;
        int left = 0;
        int right = array.length - 1 ;
        while(left < right){
            int tmp = array[left] + array[right];
            if(tmp >= target) right--;
            else {
                int mid = left+1;
                while(mid < right){
                    if(tmp + array[mid] == target){
                        List<Integer> one = new ArrayList<>();
                        one.add(array[left]);
                        one.add(array[mid]);
                        one.add(array[right]);
                        set.add(one);
                    }
                    mid ++;
                }
                left ++;
            }
        }

        for(List<Integer> each: set){
            result.add(each);
        }
        return result;
    }

}
