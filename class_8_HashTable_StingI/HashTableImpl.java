package class_8_HashTable_StingI;

import com.sun.org.apache.regexp.internal.RE;

import java.util.*;

public class HashTableImpl {
    public String[] topKFrequent(String[] combo, int k) {
        // corner case
        if(combo.length == 0) return new String[0];
        Map<String,Integer> frequentTable= new HashMap<>();
        // put all string into dic so get their frequency
        for(String s: combo){
            if (frequentTable.containsKey(s))
                frequentTable.put(s,frequentTable.get(s)+1);
            else frequentTable.put(s,1);
        }
        PriorityQueue<Map.Entry<String, Integer>> freqArray = new PriorityQueue<>
                (k, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue()); }
        });
        // using min heap to keep find the top k frequency
        for(Map.Entry<String, Integer> entry : frequentTable.entrySet()){
            if(freqArray.size() < k) freqArray.offer(entry);
            else if(freqArray.peek().getValue() < entry.getValue()){
                freqArray.poll();
                freqArray.offer(entry); } }
        // in order to return array meet the min heap
        int index = freqArray.size();
        String[] result = new String[index];
        // put smallest in min heap into back of output array
        while(!freqArray.isEmpty()){
            result[index-1] = freqArray.poll().getKey();
            index--;
        }
        return result;
    }

    public int missing(int[] array) {
        HashSet<Integer> hash = new HashSet<Integer>();
        for(int i : array){
            hash.add(i);
        }
        for(int i = 1; i < array.length + 1; i ++){
            if(!hash.contains(i)) return i;
        }
        return array.length + 1;
    }

    public int missing2(int[] array){
        if (array.length == 0) return 1;
        int actualSum = 0;
        for(int i :array) actualSum += i;
        int n = array.length+1;
        int targetSum = n*(n+1) / 2;
        return targetSum - actualSum;
    }

    public int missing3(int[] array){
        if (array.length == 0) return 1;
        int n = array.length + 1 ;
        int xor = 0;
        for(int i= 1; i < n; i++) xor ^= i;
        for(int num : array) xor ^= num;
        return xor;
    }

    public List<Integer> common(List<Integer> A, List<Integer> B) {
        List<Integer> result = new ArrayList<>();
        HashMap<Integer, Integer> Atable = new HashMap<>();
        // put A in to dic, with frequency.
        for(Integer a : A){
            Integer cur = Atable.get(a);
            if(cur == null) Atable.put(a,1);
            else Atable.put(a, cur+1);
        }
        // loop through B to check common. if found then -1 in
        // frequency
        for(Integer b: B){
            Integer cur = Atable.get(b);
            if(cur != null && cur > 0){
                result.add(b);
                Atable.put(b,Atable.get(b)-1);
            }
        }
        return result;
    }

    public List<Integer> common2(List<Integer> A, List<Integer> B) {
        List<Integer> result = new ArrayList<>();
        int indexA = 0;
        int indexB = 0;
        while(indexA < A.size() && indexB < B.size()){
            if(A.get(indexA).equals(B.get(indexB))){
                result.add(A.get(indexA));
                indexA ++;
                indexB ++;
            }else if(A.get(indexA)<B.get(indexB))indexA++;
            else indexB++;
        }
        return result;
    }


}
