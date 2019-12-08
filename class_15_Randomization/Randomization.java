package class_15_Randomization;

import class_6_Heap_BFS.Heap;
import com.sun.org.apache.bcel.internal.generic.LADD;

import java.util.*;

public class Randomization {
    public void shuffle(int[] array) {
        if(array.length < 1) return;
        for(int i = array.length-1; i >= 0; i --){
            int idx = (int)(Math.random()* i) ;
            swap(array, idx, i);
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    class ReservoirSampling{
        private int count;
        private Integer sample;
        public ReservoirSampling(){
            this.count = 0;
            this.sample = null;
        }

        public void read(int value){
            count ++;
            int idx = (int) (Math.random()* count);
            // the current read value has the probability
            // of 1/ count current sample
            if(idx == 0) sample = value;
        }

        public Integer getSample(){
            return sample;
        }
    }

    // Generalized Reservoir Sampling
    class GRS{
        private final int k;
        private List<Integer> result = new ArrayList<>();
        private int counter;
        public GRS(int k){
            this.k = k;
            counter = 0;
        }

        public void read(int value){
            counter ++;
            if(counter <= k) result.add(value);
            else {
                // 100 / counter
                int idx = (int) (Math.random() * counter);
                if(idx < k) result.set(idx,value);
            }
        }

        public List<Integer> sample(){
            return result;
        }
    }

    private int random5(){
        return (int) (Math.random()*5);
    }
    private int random2(){
        return  (int) (Math.random()*2);
    }

    public int random7() {
        while(true){
        // first to get random 25 by using random 5
        int random25 = random5()*5 + random5();
        if(random25 < 21) return random25 % 7;
        }
    }

    public int random1000(){
        while (true){
            int random = 0;
            for(int i=0;i < 5; i ++){
                random = random*5 + random5();
            }
            if(random < 1000){
                return random;
            }
        }
    }

    // using random2 to generate random 1,000,000
    // 2 ^ 20 = 1048576
    // so we need to call random 2 by 20 times
    // and make number that is [0, 1048575]
    // then we only need the number that is <1,000,000
    public int random1000000(){
        while (true){
            int random = 0;
            for (int i =0; i < 20; i ++){
                random = random * 2 + random2();
            }
            if(random < 1000000){
                return random;
            }
        }
    }

    class MedianTracker{
        private PriorityQueue<Integer> smallHalf;
        private PriorityQueue<Integer> largeHalf;

        public MedianTracker(){
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

        public Double median() {
            int small = 0;
            int large = 0;
            if(smallHalf.size() > 0 && largeHalf.size() > 0){
                if(smallHalf.size() == largeHalf.size() ){
                    small = smallHalf.peek();
                    large = largeHalf.peek();
                    return (double)(small+large)/2;
                }
                else {
                    return (double) (smallHalf.peek());
                }
            }
            else{
                return null;
            }
        }

        public Double median2(){
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
    }

    MedianTracker test = new MedianTracker();

    public void read(int i){
        test.read(i);
    }

    public Double getMedian(){
        return test.median2();
    }

    public int percentile95(List<Integer> lengths){
        int[] buckets = new int[4097];
        for(int each: lengths){
            buckets[each] ++;
        }
        int sum =0;
        int len = 4097;
        while (sum <= 0.05 * lengths.size()){
            sum += buckets[--len];
        }
        return len;
    }
}
