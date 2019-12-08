package class_8_HashTable_StingI;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class StringI {
    public String removeChar(String input, String t){
        char[] list= input.toCharArray();
        Set<Character> targetSet = new HashSet<>();
        char[] targetList = t.toCharArray();
        for(char target : targetList) targetSet.add(target);
        int i = 0; // slow pointer. all element on this
        // left is want we want
        for(int j = 0; j<input.length();j++){
            // fast pointer, all element on this left is
            // processed
            if(!targetSet.contains(list[j])){
                list[i] = list[j];
                i++;
            }
        }
        return new String(list,0,i);
    }

    public String removeSpaces(String input) {
        char[] list = input.toCharArray();
        // slow pointer. all element on this
        // left is want we want
        int i = 0;
        // fast pointer, all element on this left is
        // processed
        for(int j = 0; j< list.length; j ++){
            // two condition with fast is empty
            // 1. at position 0;
            // 2. perv's also empty
            if(list[j] == ' ' && (j == 0 || list[j-1]== ' '))
                continue;
            list[i] = list[j];
            i++;
        }
        if(i > 0 && list[i- 1] == ' ')
            return new String(list,0,i-1);
        return new String(list,0,i);
    }

    public String deDupIngroup(String input) {
        char[] list = input.toCharArray();
        if (list.length == 0) return "";
        // fast pointer, all element on this left is
        // processed
        int i = 1;
        // fast pointer, all element on this left is
        // processed
        for(int j = 1; j < list.length; j ++ ){
            // ignore condition. if j == i-1,
            // means there two same char
            if(list[j] == list[i-1]) continue;
            list[i] = list[j];
            i ++;
        }
        return new String(list,0,i);
    }

    public String deDupRepeatedly(String input){
        char[] list = input.toCharArray();
        LinkedList<Character> stack = new LinkedList<>();
        int index = 0;
        while (index < input.length()){
            char curr = list[index];
            if(!stack.isEmpty() && curr == stack.peek()){
                while(index < list.length && list[index] == curr){
                    index ++;
                }
                stack.poll();
            }
            else {
                stack.push(list[index]);
                index++;
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.append(stack.pollLast());
        }
        return sb.toString();
    }

    // naive result
    public int strstr1(String large, String small){
        if(large.length() < small.length()) return -1;
        if(small.length() == 0) return 0;
        for (int i = 0; i <= large.length() - small.length(); i++){
            if(equals(large,small,i)){
                return i;
            }
        }
        return -1;
    }

    // this method is using very basic hash algo. limit in long small string
    public int strstr(String large, String small) {
        if(large.length() < small.length()) return -1;
        if(small.length() == 0)return 0;
        char[] smallStr = small.toCharArray();
        char[] largeStr = large.toCharArray();
        double smallHashCode = hashStr(smallStr,0,smallStr.length);
        double largeHashCode = hashStr(largeStr, 0, smallStr.length);
        if(smallHashCode == largeHashCode) return 0;
        double power = Math.pow(3,smallStr.length-1);
        for(int i = 0; i < large.length() - small.length(); i++){
            double delete =  (largeStr[i]) * power;
            int add = (int)largeStr[i+smallStr.length];
            largeHashCode = (largeHashCode - delete)*3 + add;
            double largeCompare = largeHashCode%(Math.pow(large.length(),3));
            double smallCompare = smallHashCode%(Math.pow(large.length(),3));
            if(largeCompare ==  smallCompare && equals(large, small, i+1)) return i+1;
        }
        return -1;
    }
    private boolean equals(String large, String small, int start){
        for(int i = 0; i < small.length();i++){
            if(large.charAt(i+start) != small.charAt(i)){
                return false;
            }
        }
        return true;
    }

    private double hashStr(char[] input, int start, int length){
        double hasdResult = 0;
        for(int i = 0; i < length; i ++){
            int each =  (int)(input[i+start]);
            double power = Math.pow(3,(length-i-1));
            double curr = each * power;
            hasdResult += curr;
        }
        return hasdResult;
    }


    // using rabinKarp hash function
    public int strstr2(String large, String small){
        if(large.length() < small.length()) return -1;
        if(small.length() == 0)return 0;
        // large prime as module end;
        int largePrime = 101;
        // small prime to calculate the hash value
        int prime = 31;
        int seed = 1;
        // hash value is calculated using the smallPrime and taken the module
        // operation on largePrime
        // hash = (s0* smallP^ k + s1 * smallP(k-1) + .. sk*smallP^0) % largeP
        int targetHash = small.charAt(0) % largePrime;
        for(int i =1; i < small.length(); i++){
            seed = moduleHash(seed, 0, prime, largePrime);
            targetHash = moduleHash(targetHash,small.charAt(i),prime,largePrime);
        }
        int hash = 0;
        for(int i = 0;i < small.length(); i++){
            hash = moduleHash(hash, large.charAt(i), prime, largePrime);
        }
        if (hash == targetHash && equals(large, small, 0)) {
            return 0;
        }
        for (int i = 0; i <= large.length() - small.length();i ++ ){
            // making sure the module number is non-negative
            hash = nonNegative(hash - seed* large.charAt(i-1) % largePrime, largePrime);
            hash = moduleHash(hash,large.charAt(i+small.length()-1),prime,largePrime);
            // hash number match dose not mean find. still need to check the
            // really match or not
            if(hash == targetHash && equals(large, small,i))return i;
        }
        return -1;
    }

    private int moduleHash(int hash, int addition, int prime, int largePrime){
        return (hash * prime % largePrime + addition) % largePrime;
    }

    public int nonNegative(int hash, int largePrime){
        if(hash < 0){
            hash += largePrime;
        }
        return hash;
    }




}
