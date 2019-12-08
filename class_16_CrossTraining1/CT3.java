package class_16_CrossTraining1;

import com.sun.org.apache.regexp.internal.RE;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

public class CT3 {
    // no duplicate
    // increasing order
    // not sorted
    public List<Integer> common(int[] a, int[] b) {
        List<Integer> result = new ArrayList<>();
        if(a.length == 0 || b.length == 0)return result;
        Set<Integer> hashSet = new HashSet<>();
        if(a.length < b.length){
            for(int each: a) hashSet.add(each);
            for(int each: b){
                if(hashSet.contains(each)) result.add(each);
            }
        }else {
            for(int each: b) hashSet.add(each);
            for(int each: a){
                if(hashSet.contains(each)) result.add(each);
            }
        }
        Collections.sort(result);
        return result;
    }

    // duplicate
    // not sorted
    public List<Integer> common2(Integer[] a, Integer[] b){
        List<Integer> result = new ArrayList<>();
        if(a.length == 0 || b.length == 0) return result;
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int each: a){
            if(hashMap.containsKey(each)){
                hashMap.put(each,hashMap.get(each) + 1);
            }else {
                hashMap.put(each,1);
            }
        }
        for(int each: b){
            if (hashMap.containsKey(each) && hashMap.get(each) > 0){
                result.add(each);
                hashMap.put(each, hashMap.get(each)-1);
            }
        }

        return result;
    }

    // three sorted array
    // find the common
    public List<Integer> commonIII(int[] a, int[] b, int[] c) {
        List<Integer> result = new ArrayList<>();
        if(a.length == 0 || b.length == 0 || c.length == 0)return result;
        int ai = 0;
        int bi = 0;
        int ci = 0;
        while (ai < a.length && bi < b.length && ci < c.length){
            if(a[ai] == b[bi] && b[bi] == c[ci]){
                result.add(a[ai]);
                ai++;
                bi++;
                ci++;
            }
            else if(a[ai] <= b[bi] && a[ai] <= c[ci]) {
                ai++;
            }
            else if(b[bi] < a[ai] && b[bi] <= c[ci]){
                bi++;
            }else {
                ci++;
            }
        }
        return result;
    }

    //Each array support fast random access.
    //There could be duplicate elements in each of the lists.
    public List<Integer> commonElementsInKSortedArrays(List<List<Integer>> input) {
        List<Integer> result = input.get(0);
        for(int i = 1; i < input.size(); i++){
            Integer[] resultArr = new Integer[result.size()];
            resultArr = result.toArray(resultArr);
            Integer[] newArr = new Integer[input.get(i).size()];
            newArr = input.get(i).toArray(newArr);
            result = common2(resultArr,newArr);
        }
        return result;
    }

    public int largestProduct(String[] dict) {
        HashMap<String,Integer> map = new HashMap<>();
        map = bitMasks(dict);
        int largest = 0;
        Arrays.sort(dict, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length() == o2.length()) return 0;
                return o1.length() < o2.length() ? 1 : -1;
            }
        });
        for(int i = 1; i < dict.length; i++){
            for(int j = 0; j < i; j++){
                int por = dict[i].length() * dict[j].length();
                if(por <= largest) break;
                int iMask = map.get(dict[i]);
                int jMask = map.get(dict[j]);
                if((iMask & jMask) == 0){
                    largest = por;
                }
            }
        }
        return largest;
    }

    private HashMap<String,Integer> bitMasks(String[] dict){
        HashMap<String,Integer> map = new HashMap<>();
        for(String str: dict){
            int bitMask = 0;
            for(int i =0; i< str.length(); i++){
                bitMask |= 1 << (str.charAt(i)-'a');
            }
            map.put(str,bitMask);
        }
        return map;
    }
}
