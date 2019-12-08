package Train;

import class_16_CrossTraining1.GraphNode;
import class_3_linked_list.ListNode;
import class_5_BST_BT.TreeNode;

import java.io.PipedReader;
import java.util.*;

public class AllTrain {

    // 2 sum n speed, n space
    public boolean existSum(int[] array, int target) {
        // Write your solution here
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int each: array){
            if(!map.containsKey(each)){
                map.put(each, 1);
            }
            else{
                map.put(each,map.get(each)+1);
            }
        }

        for(int each : array){
            int left = target - each;
            if(map.containsKey(left) && map.get(left) != 0) {
                if(left == each && map.get(left) > 1) return true;
                else if(left != each) return true;
            }
        }

        return false;
    }
    // 3 sum
    public List<List<Integer>> allTriples(int[] array, int target) {
        // Write your solution here
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(array);
        for(int i =0; i < array.length - 2; i++){
            // did not check i duplicate
            if(i > 0 && array[i] == array[i-1]) continue;
            int left = i+1;
            int right = array.length-1;
            // no inner loop
            while(left < right){
                // forget give the type to new variable
                int temp = array[left] + array[right];
                if(temp + array[i] == target){
                    result.add(Arrays.asList(array[i], array[left], array[right]));
                    // do not forget increase the left
                    left ++;
                    // ignore the duplicate
                    while(left < right && array[left] == array[left-1])left ++;

                }else if(temp + array[i] > target) right --;
                else left ++;
            }
        }
        // missing return
        return result;
    }

    // combination of coins
    public List<List<Integer>> combinations(int target, int[] coins) {
        // Write your solution here
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(target == 0) return result;
        List<Integer> curr = new ArrayList<Integer>();
        helper(target, coins, curr, result, 0);
        return result;
    }

    // did not think of the current level paramter
    private void helper(int left, int[] coins, List<Integer> curr, List<List<Integer>> result, int index){
        // consider the base case
        if(index == coins.length-1){
            // did not know how to do the base case
            if(left % coins[coins.length-1] == 0){
                curr.add(left/coins[coins.length-1]);
                // did not make new array list for such layer
                // new ArrayList<Integer> (curr)
                result.add(new ArrayList<Integer> (curr));
                // did not understand the base case why remove
                curr.remove(curr.size()-1);
            }
            // forget base case termination
            return;
        }
        int number = left / coins[index];
        for(int i = 0; i <= number; i ++){
            // make sure you know each layer is repersent what
            curr.add(i);
            int newLeft = left - i*coins[index];
            helper(newLeft, coins,curr, result, index + 1);
            // back to the orignal state after the recursion
            curr.remove(curr.size()-1);
        }
    }

    // longest Ascending subarray
    public int longest2(int[] array) {
        // Write your solution here
        if(array.length == 0) return 0;
        int curr = 1;
        int result = 1;
        for(int i = 1; i < array.length ; i++){
            if(array[i] > array[i-1]){
                curr++;
                result = Math.max(curr, result);
            }else curr = 1;
        }
        return result;
    }


    // Longest Ascending Subsequence
    public int longest(int[] array) {
        if(array.length == 0) return 0;
        int[] dpTable = new int[array.length];
        dpTable[0] = 0;
        for(int i = 1; i < array.length;i++){
            for(int j = 0; j < i;j++){
                if(array[i] > array[j]){
                    dpTable[i] = Math.max(dpTable[j] + 1, dpTable[i]);
                }
            }
        }
        int result = 1;
        for(int each: dpTable){
            if(each >= result){
                result = each;
            }
        }
        // special case
        return result == 1 ? result : result + 1;
    }

    // Merge K Sorted LinkedLists
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

    static class ListNodeComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            if(o1.value == o2.value) return 0;
            else return o1.value < o2.value? -1 : 1;
        }
    }

    // level order Traversal
    public List<List<Integer>> layerByLayer(TreeNode root) {
        // Write your solution here
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root == null) return result;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            List<Integer> currLayer = new ArrayList<>();
            int size = queue.size();
            for(int i =0; i<size;i++){
                TreeNode curr = queue.poll();
                currLayer.add(curr.key);
                if(curr.left!=null) queue.offer(curr.left);
                if(curr.right!=null) queue.offer(curr.right);
            }
            result.add(currLayer);
        }
        return result;
    }

    // coin less
    public int coinChange(int[] coins, int amount){
        int[] dpTable = new int[amount+1];
        // how to fill the array with same number quick
        Arrays.fill(dpTable, Integer.MAX_VALUE -1);
        dpTable[0] = 0;
        for(int cur = 1; cur <= amount; cur++){
            for(int coin : coins){
                if(cur - coin >= 0){
                    dpTable[cur] = Math.min(dpTable[cur-coin]+1, dpTable[cur]);
                }
            }
        }
        return dpTable[amount] != Integer.MAX_VALUE-1 ? dpTable[amount] : -1;
    }

    // valid anagram
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        int[] table = new int[26];
        for(int i =0; i < s.length(); i++){
            table[s.charAt(i)-'a'] ++;
        }

        for(int i = 0; i < t.length(); i++){
            table[t.charAt(i)-'a']--;
            if(table[t.charAt(i)-'a'] < 0){
                return false;
            }
        }
        return true;
    }

    // max profit
    public int maxProfit(int[] array) {
        // Write your solution here
        int min = array[0];
        int maxprofit = 0;
        for(int i = 1; i < array.length; i++){
            if(array[i] < min){
                min = array[i];
            }
            if(array[i] - min > maxprofit){
                maxprofit = array[i]-min;
            }
        }

        return maxprofit;
    }

    // how many 1 bit in the bits
    public int hammingWeight(int n){
        int result = 0;
        int mask = 1;
        for(int i =0; i< 32; i++){
            if((mask & n) !=0 ) result ++ ;
            mask <<= 1;
        }
        return result;
    }


    // revers linked list
    // iterative way
    public ListNode reverseLinkedList(ListNode head){
        ListNode prev = null;
        while(head != null){
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    // recursive way
    public ListNode reverseLinkedList2(ListNode head){
        // head == null is corner case
        // head.next == null is base case
        if(head == null || head.next == null){
            return head;
        }
        ListNode newHead = reverseLinkedList2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    // Bipartite
    public boolean isBipartite(List<GraphNode> graph) {
        // write your solution here

        HashMap<GraphNode, Integer> visited = new HashMap<GraphNode, Integer>();
        for(GraphNode node : graph){
            if(!BFS(node,visited)) return false;
        }
        return true;
    }

    // neet to be understand more
    public boolean BFS(GraphNode node, HashMap<GraphNode, Integer> visited){
        if(visited.containsKey(node)) return true;
        LinkedList<GraphNode> queue = new LinkedList<>();
        queue.offer(node);
        visited.put(node, 0);
        while (!queue.isEmpty()){
            GraphNode currVertex = queue.poll();
            int curGroup = visited.get(currVertex);
            int neiGroup = curGroup == 0? 1 : 0;
            for(GraphNode vertex : currVertex.neighbors){
                if(!visited.containsKey(vertex)){
                    visited.put(vertex,neiGroup);
                    queue.offer(vertex);
                }
                else if(visited.get(vertex) != neiGroup) {
                    return false;
                }
            }
        }
        return true;
    }


    // Find Minimum in Rotated Sorted Array
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length -1;
        if(nums[left] < nums[right]){
            return nums[left];
        }

        while(right - left > 1){
            int mid = left + (right - left)/2;
            if(nums[mid] > nums[right]) left = mid;
            else if(nums[mid] <= nums[right]) right = mid;

        }

        return nums[right];
    }

    // Reconstruct Binary Tree With Preorder And Inorder
    public TreeNode reconstruct(int[] inOrder, int[] preOrder) {
        // Write your solution here
        HashMap<Integer, Integer> index = formIndex(inOrder);
        return heaper(preOrder,index, 0, preOrder.length-1, 0, inOrder.length-1);
    }
    private HashMap<Integer,Integer> formIndex (int[] inOrder){
        HashMap<Integer, Integer> index = new HashMap<Integer, Integer>();
        for(int i = 0; i< inOrder.length;i ++){
            index.put(inOrder[i], i);
        }
        return index;
    }
    private TreeNode heaper(int[] preOrder, HashMap<Integer, Integer> index, int preLeft, int preRight, int inLeft, int inRight){
        if(inLeft > inRight) return null;
        TreeNode root = new TreeNode(preOrder[preLeft]);
        int mid = index.get(root.key);
        // why need to -inleft??
        root.left = heaper(preOrder, index, preLeft+1, preLeft+mid-inLeft, inLeft, mid-1);
        root.right = heaper(preOrder, index, preRight+mid-inRight+1, preRight, mid+1, inRight);
        return root;
    }

    // LCA
    public TreeNode lowestCommonAncestor(TreeNode root,
                                         TreeNode one, TreeNode two) {
        // Write your solution here.

        if(root == null || root == one || root == two) return root;
        TreeNode left = lowestCommonAncestor(root.left, one,two);
        TreeNode right = lowestCommonAncestor(root.right, one , two);

        if(left != null && right != null){
            return root;
        }
        return left == null ? right : left;

    }

    // largest subarray product
    public double largestProduct(double[] array) {
        // Write your solution here
        double[] dp_min = new double[array.length];
        double[] dp_max = new double[array.length];
        dp_min[0] = array[0];
        dp_max[0] = array[0];
        double result = array[0];
        for(int i = 1; i < array.length; i++){
            dp_min[i] = Math.min(Math.min(dp_max[i-1]*array[i], dp_min[i-1]*array[i]), array[i]);
            dp_max[i] = Math.max(Math.max(dp_max[i-1]*array[i], dp_min[i-1]*array[i]), array[i]);
            result = Math.max(result, dp_max[i]);
        }
        return result;
    }

    // Array Deduplication I
    public int[] dedup(int[] array) {
        // Write your solution here
        if(array.length <= 1) return array;
        int i =1;
        for(int j = 1; j < array.length; j ++){
            if(array[i-1] == array[j]) continue;
            array[i] = array[j];
            i++;
        }
        return Arrays.copyOf(array, i);
    }

    //217. Contains Duplicate
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> hashTable = new HashSet<Integer>();
        for(int each: nums){
            if(hashTable.contains(each)) return true;
            else hashTable.add(each);
        }
        return false;
    }

    // Dictionary Word I
    public boolean canBreak(String input, String[] dict) {
        // Write your solution here
        boolean[] DPtable  = new boolean[input.length() + 1];
        Set<String> dic  = toSet(dict);
        DPtable[0] = true;
        for (int i =0; i < DPtable.length; i++){
            for(int j = 0; j < i ;j ++){
                String now = input.substring(j,i);
                if(DPtable[j] && dic.contains(now)){
                    DPtable[i] = true;
                    break;
                }
            }
        }
        return DPtable[input.length()];
    }
    private Set<String> toSet(String[] dict) {
        Set<String> hash = new HashSet<>();
        for(String one : dict){
            hash.add(one);
        }
        return hash;
    }

    // Max Water Trapped I
    public int maxTrapped(int[] array) {
        // Write your solution here
        if(array.length == 0) return 0;
        int left = 0;
        int right = array.length-1;
        int lmax = array[left];
        int rmax = array[right];
        int result = 0;
        while(left < right){
            if(array[left] <= array[right]){
                result += Math.max(0,lmax- array[left]);
                lmax = Math.max(lmax, array[left]);
                left++;
            }else{
                result += Math.max(0, rmax - array[right]);
                rmax = Math.max(rmax, array[right]);
                right--;
            }
        }
        return result;
    }

    //133 Deep Copy Undirected Graph
    public List<GraphNode> copy(List<GraphNode> graph) {
        // Write your solution here.
        List<GraphNode> result = new ArrayList<>();
        HashMap<GraphNode, GraphNode> map = new HashMap<>();
        for(GraphNode each: graph){
            // step 1, get copy
            GraphNode copy = null;
            if(!map.containsKey(each)) {
                copy = new GraphNode(each.key);
                map.put(each, copy);
            }
            else copy = map.get(each);

            // step 2, using org neg
            for(GraphNode neg: each.neighbors){
                GraphNode neg_copy = map.get(neg);
                if(neg_copy == null){
                    neg_copy = new GraphNode(neg.key);
                    map.put(neg,neg_copy);
                }
                copy.neighbors.add(neg_copy);
            }

            // step 3, finish current node, put it into result
            result.add(copy);
        }
        return result;
    }

    // Merge Two Sorted Linked Lists
    public ListNode merge(ListNode one, ListNode two) {
        // Write your solution here
        if(one == null || two == null)
            return one == null ? two : one;
        ListNode dummy = new ListNode(0);
        dummy.value = 0;
        ListNode curr = dummy;
        while(one != null && two != null){
            if(one.value <= two.value){
                curr.next = one;
                one = one.next;
            }else {
                curr.next = two;
                two = two.next;
            }
            curr = curr.next;
        }

        if(one != null) curr.next = one;
        else curr.next = two;
        return dummy.next;
    }

    // Longest Palindromic Substring
    public String longestPalindrome(String input) {
        boolean[][] dp = new boolean[input.length()][input.length()];
        int left = 0;
        int right = 0;

        for(int i = 0; i < input.length();i++){
            for(int j = i; j >=0 ;j--){
                boolean same = input.charAt(i) == input.charAt(j);
                if(i == j){
                    dp[i][j] = true;
                }
                else if( i -1 == j){
                    dp[i][j] = same;
                }else if(same && dp[i-1][j+1]){
                    dp[i][j] = true;
                }

                if(dp[i][j] && i-j > right - left){
                    right = i;
                    left = j;
                }
            }
        }

        return input.substring(left, right + 1);
    }

    //median tracker
    private PriorityQueue<Integer> smallHalf;
    private PriorityQueue<Integer> largeHalf;

    public void Solution(){
        largeHalf = new PriorityQueue<Integer>();
        smallHalf = new PriorityQueue<Integer>(11, Collections.reverseOrder());
    }

    public void read(int value) {
        if(smallHalf.isEmpty() || value < smallHalf.peek()){
            smallHalf.offer(value);
        }else {
            largeHalf.offer(value);
        }

        if(smallHalf.size() - largeHalf.size() >= 2){
            largeHalf.offer(smallHalf.poll());
        }else if(largeHalf.size() > smallHalf.size()){
            smallHalf.offer(largeHalf.poll());
        }
    }
    public Double median(){
        int size = size();
        if(size ==0 ) return null;
        else if(size % 2 != 0){
            return (double)(smallHalf.peek());
        }else {
            return (smallHalf.peek()+largeHalf.peek()) / 2.0;
        }
    }

    private int size(){
        return smallHalf.size() + largeHalf.size();
    }

    // First Occurrence
    public int firstOccur(int[] array, int target) {
        // Write your solution here
        if(array.length == 0 || array == null) return -1;
        int L = 0;
        int R = array.length-1;

        while(L < R -1){
            int M = (L+R)/2 ;
            if(array[M] == target){
                R = M;
            }else if(array[M] < target){
                L = M;
            }else{
                R = M;
            }
        }

        if(array[L] == target){
            return L;
        }else if(array[R] == target){
            return R;
        }else{
            return -1;
        }
    }

    // Reverse bit
    public int reversbit(int n){
        for(int offset = 0; offset < 16 ; offset++){
            long left = (n >> offset) & 1L;
            long right = (n >> (31 - offset)) & 1L;
            if(left != right){
                n ^= (1L << offset);
                n ^= (1L << (31 - offset));
            }
        }
        return n;
    }

    // Check If Linked List Has A Cycle
    public boolean hasCycle(ListNode head) {
        // write your solution here

        if(head == null || head.next == null) return false;
        // slow pointer
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null &&
                fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) return true;
        }

        return false;
    }

    // the high of a binary tree
    public int findHeight(TreeNode root) {
        // Write your solution here
        if(root == null) return 0;
        return Math.max(findHeight(root.left), findHeight(root.right)) +1;
    }


    // top k frequent words
    public String[] topKFrequent(String[] combo, int k) {
        // Write your solution here
        if(combo.length == 0) return new String[0];
        Map<String,Integer> frequentTable= new HashMap<>();
        for(String s: combo){
            if (frequentTable.containsKey(s))
                frequentTable.put(s,frequentTable.get(s)+1);
            else frequentTable.put(s,1);
        }

        PriorityQueue<Map.Entry<String, Integer>> freqArray = new PriorityQueue<>(k, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        for(Map.Entry<String, Integer> entry : frequentTable.entrySet()){
            if(freqArray.size() < k) {
                freqArray.offer(entry);
            }
            else if(freqArray.peek().getValue() < entry.getValue()){
                freqArray.poll();
                freqArray.offer(entry);
            }
        }

        int index = freqArray.size();
        String[] result = new String[index];
        while(!freqArray.isEmpty()){
            result[index-1] = freqArray.poll().getKey();
            index--;
        }

        return result;
    }

    // selection sort
    public int[] solve(int[] array) {
        // Write your solution here
        int temp, smallest;
        for(int i = 0; i < array.length; i++){

            smallest = i;
            for(int j = i; j < array.length; j++){
                if(array[j] < array[smallest]){smallest = j;}
            }
            temp = array[i];
            array[i] = array[smallest];
            array[smallest] = temp;
        }

        return array;
    }


    // Longest Substring Without Repeating Characters
    public int longest(String input) {
        // Write your solution here
        Set<Character> distinct = new HashSet<>();
        int s = 0;
        int f = 0;
        int longest = 0;
        while (f < input.length()){
            if(distinct.contains(input.charAt(f))){
                distinct.remove(input.charAt(s));
                s++;
            }
            else {
                distinct.add(input.charAt(f));
                f++;
                longest = Math.max(longest, f-s);
            }
        }
        return longest;
    }

    // Maximum Values Of Size K Sliding Windows
    public List<Integer> maxWindows(int[] array, int k) {
        // Write your solution here
        List<Integer> result = new ArrayList<>();
        // the deque store the index.
        Deque<Integer> deque = new LinkedList<>();
        for(int i =0; i<array.length; i++){
            while (!deque.isEmpty() && array[deque.peekLast()] < array[i]){
                deque.pollLast();
            }
            // here is avoiding the sliding window out of bound
            if(!deque.isEmpty() && deque.peekFirst() <= i - k){
                deque.pollFirst();
            }
            deque.offerLast(i);
            if(i >= k-1){
                result.add(array[deque.peekFirst()]);
            }
        }
        return result;
    }

    // Last Occurrence
    public int lastOccur(int[] array, int target) {
        // Write your solution here
        if (array.length == 0 || array == null) return -1;
        int L = 0;
        int R = array.length -1 ;

        while(L < R -1){
            int M = L+ (R-L)/2;
            if(array[M] <= target) L = M;
            else R = M;
        }

        if(array[R] == target) return R;
        else if(array[L] == target) return L;
        else return -1;
    }

    // 25. K Smallest In Unsorted Array
    public int[] kSmallest(int[] array, int k) {
        // corner case
        if(array == null || array.length == 0) return new int[0];
        // create minHeap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1.equals(o2)) return 0;
                return o1 < o2 ? -1 : 1;
            }
        });
        int[] result = new int[k];
        for(int each: array){
            minHeap.offer(each);
        }
        int index = k-1;
        while (index >= 0){
            result[index] = minHeap.poll();
            index--;
        }
        return result;
    }

    public int[] kSmallest2(int[] array, int k){
        if(array == null || array.length == 0) return new int[0];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1.equals(o2)) return 0;
                return o1 > o2? -1 : 1;
            }
        });

        for(int i =0;i < array.length;i++){
            if(i < k) maxHeap.offer(array[i]);
            else if(array[i] < maxHeap.peek()){
                maxHeap.poll();
                maxHeap.offer(array[i]);
            }
        }

        int[] result = new int[k];
        while(k != 0){
            result[k-1] = maxHeap.poll();
            k--;
        }

        return result;
    }

    // laioffer
    // 192. Kth Smallest With Only 2, 3 As Factors
    public double kth(int k) {
        PriorityQueue<Double> maxHeap = new PriorityQueue<>(k, new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                if(o1.equals(o2))return 0;
                return o1 < o2 ? -1 : 1;
            }
        });
        LinkedList<Position> queue = new LinkedList<>();
        queue.push(new Position(0,0));

        while(maxHeap.size() <= k){
            Position curr = queue.poll();
            int a = curr.x;
            int b = curr.y;
            if(maxHeap.size() <= k){
                maxHeap.offer(curr.getValue());
            }

            maxHeap.offer(curr.getValue());
            queue.push(new Position(a+1,b));
            queue.push(new Position(a,b+1));
        }

        return maxHeap.poll();
    }

    class Position{
        int x = 0;
        int y = 0;
        Position(int x, int y){
            this.x = x;
            this.y = y;
        }
        public double getValue(){
            return  Math.pow(2,x) * Math.pow(3,y);
        }
    }
}


