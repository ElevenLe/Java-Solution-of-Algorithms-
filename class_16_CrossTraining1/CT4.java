package class_16_CrossTraining1;

import class_6_Heap_BFS.Heap;
import com.sun.glass.events.mac.NpapiEvent;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

public class CT4 {
    // xxxxxxxxxxxxx
    // yyyyyyyyyyyyy find the kth using binary search
    public int kth(int[] a, int[] b, int k) {
        // Write your solution here
        return kTwoArraysHelper(a,b,0,0,k);

    }

    public int kTwoArraysHelper(int[] a,int[] b, int aleft, int bleft, int k){
        if(aleft >= a.length) return b[bleft + k - 1];
        if(bleft >= b.length)return a[aleft + k - 1];
        if(k == 1) return Math.min(a[aleft], b[bleft]);

        int amid = aleft + k/2-1;
        int bmid = bleft + k/2-1;

        int aval = amid >= a.length ? Integer.MAX_VALUE : a[amid] ;
        int bval = bmid >= b.length ? Integer.MAX_VALUE : b[bmid] ;

        return aval < bval ?
                kTwoArraysHelper(a,b, amid+1, bleft, k - k/2):
                kTwoArraysHelper(a,b, aleft, bmid+1, k - k/2);
    }

    // Maximum Values Of Size K Sliding Windows
    // [1,2,3,4,5,6,7,8,9] k = 3
    //  _____ -> find the largest one inside of this sliding windows

    // first approach: using max heap without lazy deletion
    public List<Integer> maxWindowsI(int[] array, int k) {
        List<Integer> result = new ArrayList<>();
        if(array.length < 1) return result;
        if(array.length == 1)  {
            result.add(array[0]);
            return result;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(14, Collections.reverseOrder());
        int leftBorder = 0;
        int rightBorder = k-1;
        for(int i = 0; i < k; i++) maxHeap.offer(array[i]);
        result.add(maxHeap.peek());

        while (rightBorder < array.length-1){
            // right border go first
            rightBorder++;
            maxHeap.offer(array[rightBorder]);
            // we still need to delete the old left position
            // instead of the new position;
            maxHeap.remove(array[leftBorder]);
            result.add(maxHeap.peek());
            leftBorder++;

        }
        return result;
    }

    // second approach: using max heap with lazy deletion
    // inorder to do lazy deletion, we need to have the connection
    // between max heap and element's position in the original array
    public List<Integer> maxWindowsII(int[] array, int k){
        List<Integer> result = new ArrayList<>();
        // corner cases
        if(array.length < 1) return result;
        if(array.length == 1)  {
            result.add(array[0]);
            return result;
        }

        Element[] temp = new Element[array.length];
        int index =0;
        for (int each: array){
            temp[index] = new Element(each,index);
            index ++;
        }

        // set up max heap
        PriorityQueue<Element> maxHeap = new PriorityQueue<Element>(new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
                if(o1.value == o2.value) return 0;
                return o1.value < o2.value? 1 : -1;
            }
        });
        int leftBorder = 0;
        int rightBorder = k-1;
        for(int i = 0; i < k; i++) maxHeap.offer(temp[i]);
        result.add(maxHeap.peek().value);


        while (rightBorder < temp.length-1){
            // here we must increase the border first
            // otherwise it would not consider the
            // new left border below
            rightBorder++;
            leftBorder ++;
            maxHeap.offer(temp[rightBorder]);
            // lazy deletion here;
            // only consider that in the heap that
            // the largest one is out of the border
            // so we do not need them.
            while (maxHeap.peek().index < leftBorder){
                maxHeap.poll();
            }

            result.add(maxHeap.peek().value);
        }

        return result;
    }
    class Element{
        int value;
        int index;
        public Element(int value, int index){
            this.value = value;
            this.index = index;
        }
    }

    // third approach: using deque
    // keep the deque as a descending order for the value
    public List<Integer> maxWindowsIII(int[] array, int k){
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


    class LRUcache<K,V>{
        private final int limit;
        private Node<K,V> head;
        private Node<K,V> tail;
        private Map<K, Node<K,V>> map;
        public LRUcache(int limit){
            this.limit = limit;
            this.map = new HashMap<K, Node<K,V>>();
            head = new Node<K,V>(null, null);
            tail = new Node<K,V>(null, null);
        }

        public void set(K key, V value){
            Node<K,V> node = null;
            if(map.containsKey(key)){
                // get the old value
                node = map.get(key);
                // update it with new input value
                node.v = value;
                delete(node);
            }
            else{
                node = new Node<K,V>(key,value);
                map.put(key,node);
                if(map.size() > limit) {
                    map.remove(head.next.k);
                    delete(head.next);
                }
            }
            append(node);
        }

        public V get(K key, V value){
            Node<K,V> node = map.get(key);
            if(node == null){
                return null;
            }
            else {
                delete(node);
                append(node);
                return node.v;
            }
        }

        private void append(Node<K,V> node){
            if(head.next == null){
                head.next = node;
                node.next = tail;
                node.prev = head;
                tail.prev = node;
            }
            else {
                Node<K,V> temp = tail.prev;
                node.next = tail;
                node.prev = temp;
                temp.next = node;
            }
        }

        private void delete(Node<K,V> node){
            if(head.next == null){
                return;
            }
            else {
                if(node.prev != null && node.next != null){
                    Node before = node.prev;
                    Node next = node.next;
                    before.next = next;
                    next.prev = before;
                }
            }
        }
    }

    static class Node<K,V>{
        Node<K,V> next = null;
        Node<K,V> prev = null;
        K k;
        V v;
        public Node(K k, V v){
            this.k = k;
            this.v = v;
        }

        public void update(K key, V value){
            this.k = key;
            this.v = value;
        }
    }

    public void test(){
        LRUcache testc = new LRUcache(4);
        for(int i =0; i < 5; i ++){
            testc.append(new Node(i,i+1));
        }
    }

    class NonRepeting{
        private HashMap<Character, Nod> hashmap;
        private Nod head;
        private Nod tail;
        public NonRepeting(){
            hashmap = new HashMap<>();
            head = new Nod(null);
            tail = new Nod(null);
        }

        public void read(char ch) {
            Nod curr = null;
            if(!hashmap.containsKey(ch)){
                curr = new Nod(ch);
                hashmap.put(ch,curr);
                append(curr);
            }
            else {
                curr = hashmap.get(ch);
                delete(curr);
            }
        }

        public Character firstNonRepeating() {
            // Implement this method here.
            return head.next.value;
        }

        private void delete(Nod node){
            if(head.next == tail && tail.prev == head){
                return;
            }
            else {
                Nod before = node.prev;
                Nod next = node.next;
                before.next = next;
                next.prev = before;
            }
        }

        private void append(Nod add){
            if(head.next == null){
                head.next = add;
                add.next = tail;
                tail.prev = add;
                add.prev = head;
            }
            else {
                Nod temp = tail.prev;
                tail.prev = add;
                temp.next = add;
                add.prev = temp;
                add.next = tail;
            }
        }
    }

    static class Nod{
        Nod next = null;
        Nod prev = null;
        Character value;
        public Nod(Character value){
            this.value = value;
        }
    }

    public void test2(){
        NonRepeting test = new NonRepeting();
        char[] input = new char[]{'a', 'b', 'c', 'b', 'a', 'd', 'c', 'd', 'a', 'e'};
        for(char each: input){
            test.read(each);
            System.out.print(test.firstNonRepeating() + " ");
        }

    }

    public int majorityI(int[] array) {
        int count = 1;
        int candidate = array[0];
        for(int i= 1; i < array.length; i++){
            if(count == 0){
                candidate = array[i];
                count++;
            }else if(candidate != array[i]) count --;
            else count++;
        }
        return candidate;
    }

    public int majorityII(int[] array){
        if(array.length <=2 ) return array[0];
        int count1 = 1;
        int count2 = 1;
        int candidate1 = array[0];
        int candidate2 = array[1];
        for(int i = 2; i <array.length; i++){
            if(array[i] == candidate1) count1++;
            else if(array[i] == candidate2) count2++;
            else if(count1 == 0){
                count1++;
                candidate1 = array[i];
            }
            else if(count2 == 0){
                count2++;
                candidate2 = array[i];
            }
            else {
                count1--;
                count2--;
            }
        }
        return candidate1;
    }
}
