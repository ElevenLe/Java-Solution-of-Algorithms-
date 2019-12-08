package class_9_StringII;

import java.util.*;

public class StringII {

    // reverse a string: "abc" -> "cba"
    // iter way
    public String reverse1(String input) {
        char[] array = input.toCharArray();
        if (input.length() <= 1) return input;
        int i = 0, j = input.length() - 1;
        while (i < j) {
            swap(array, i, j);
            i++;
            j--;
        }
        return new String(array);
    }

    // recursive way
    public String reverse2(String input) {
        if (input.length() == 0) return input;
        char[] array = input.toCharArray();
        reverseHelper(array, 0, array.length - 1);
        return new String(array);
    }

    private void reverseHelper(char[] input, int left, int right) {
        if (left > right) return;
        swap(input, left, right);
        reverseHelper(input, left + 1, right + 1);
    }

    private void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // reverse words in the sentence: "i love google" -> "google love me"
    // swap the whole sentence, then swap each words
    public String reverseWords(String input) {
        if (input.length() <= 1) return input;
        String reversed = reverse1(input);
        char[] array = reversed.toCharArray();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int j = 0;
        // reverse each word using two pointers to find the each words
        while (j < input.length()) {
            if (array[j] == ' ') {
                String curr = new String(array, i, j - i);
                sb.append(reverse1(curr));
                sb.append(" ");
                i = j + 1;
                j++;
            } else j++;
        }
        // post processing. since j is comparing with " ". if the
        // last words is not end with " ", it will ignore, so we
        // have to process it.
        if (array[array.length - 1] != ' ') {
            String left = new String(array, i, array.length - i);
            sb.append(reverse1(left));
        }
        return sb.toString();
    }

    // right shift 2 "abcde" -> "deabc"
    // not a in place operation
    public String rightShift(String input, int n) {
        if (input.length() == 0 || n == 0) return input;
        char[] array = input.toCharArray();
        n %= input.length();
        StringBuilder sb = new StringBuilder();
        sb.append(new String(array, input.length() - n, n));
        sb.append(new String(array, 0, input.length() - n));
        return sb.toString();
    }

    // in-place operation
    // take example "abcdef" n = 4;
    public String rightShitf2(String input, int n) {
        if (input.length() == 0 || n == 0) return input;
        char[] array = input.toCharArray();
        n %= input.length();
        // reverse second part "cdef" -> "fedc", "abcdef" -> "abfedc"
        reverseSubstring(array, input.length() - n, input.length() - 1);
        // reverse first part "ab" -> "ba", "abfedc" -> "bafedc"
        reverseSubstring(array, 0, input.length() - n - 1);
        // now reverse the entire string, so that move the second part
        // to the first. "bafedc" -> "cdefab"
        reverseSubstring(array, 0, input.length() - 1);
        return new String(array);
    }

    private void reverseSubstring(char[] input, int left, int right) {
        while (left < right) {
            swap(input, left, right);
            left++;
            right--;
        }
    }

    public String replace(String input, String source, String target) {
        StringBuilder sb = new StringBuilder();
        int fromIndex = 0;
        int matchIndex = input.indexOf(source, fromIndex);
        while (matchIndex != -1) {
            sb.append(input, fromIndex, matchIndex);
            sb.append(target);
            fromIndex = matchIndex + source.length();
            matchIndex = input.indexOf(source, fromIndex);
        }
        sb.append(input, fromIndex, input.length());
        return sb.toString();
    }

    public int strstr1(String large, String small) {
        if (large.length() < small.length()) return -1;
        if (small.length() == 0) return 0;
        for (int i = 0; i <= large.length() - small.length(); i++) {
            if (equals(large, small, i)) {
                return i;
            }
        }
        return -1;
    }

    private boolean equals(String large, String small, int start) {
        for (int i = 0; i < small.length(); i++) {
            if (large.charAt(i + start) != small.charAt(i)) {
                return false;
            }
        }
        return true;
    }


    public int[] reorder(int[] array) {
        if (array.length == 0) return array;
        if (array.length % 2 == 0) {
            reorderHelper(array, 0, array.length - 1);
        } else {
            reorderHelper(array, 0, array.length - 2);
        }
        return array;
    }

    private void reorderHelper(int[] array, int left, int right) {
        // how many elements in the section passed in
        int length = right - left + 1;
        // base case, if already two elements, then return
        if (length <= 2) {
            return;
        }
        // calculate mid point
        // 0 1 2 3 4 5 6 7
        // lm: 2, m: 4, rm: 6
        // 0 1 2 3 4 56 7 8 9
        // lm: 2, m: 5, rm: 7
        int m = left + length / 2;
        int lm = left + length / 4;
        int rm = left + length * 3 / 4;
        // i love yahoo trick here. eg : "1 2 3 4 5 6"
        // first reverse left word: "3 2"
        reverse(array, lm, m - 1);
        // second reverse right word: "4"
        reverse(array, m, rm - 1);
        // reverse these two words together "4 2 3"
        // now "1 4 2 3 5 6"
        reverse(array, lm, rm - 1);
        // passing into next level with left and how many
        // elements should be paired with number of non reorder
        reorderHelper(array, left, left + 2 * (lm - left) - 1);
        // the remaining numbers
        reorderHelper(array, left + 2 * (lm - left), right);
    }

    private void reverse(int[] input, int left, int right) {
        while (left < right) {
            int temp = input[left];
            input[left] = input[right];
            input[right] = temp;
            left++;
            right--;
        }
    }

    public List<String> permutationsDU(String set) {
        List<String> result = new ArrayList<>();
        if (set == null) {
            return result;
        }
        if (set.length() == 0) {
            result.add(set);
            return result;
        }
        char[] array = set.toCharArray();
        permutationsDUhelper(array, 0, result);
        return result;
    }

    private void permutationsDUhelper(char[] array, int index, List<String> result) {
        if (index == array.length) {
            result.add(new String(array));
            return;
        }

        Set<Character> used = new HashSet<>();
        for (int i = index; i < array.length; i++) {
            if (used.add(array[i])) {
                swap(array, index, i);
                permutationsDUhelper(array, index + 1, result);
                swap(array, index, i);
            }
        }
    }

    // "aaabbbccc" -> "a3b3c3" with inplace operation
    public String compress(String input) {
        if (input == null || input.isEmpty()) return input;
        char[] array = input.toCharArray();
        return encode(array);
    }

    private String encode(char[] input) {
        // part one, normal case.
        int slow = 0;
        int fast = 0;
        int newLength = 0;
        while (fast < input.length) {
            int begin = fast;
            while (fast < input.length && input[fast] == input[begin]) {
                fast++;
            }
            input[slow] = input[begin];
            slow++;

            // skip the special case
            if (fast - begin == 1) {
                newLength += 2;
            } else {
                int len = copyDigits(input, slow, fast - begin);
                slow += len;
                newLength += len + 1;
            }
        }

        // part two special case: only one
        char[] result = new char[newLength];
        fast = slow - 1;
        slow = newLength - 1;
        while (fast >= 0) {
            if (Character.isDigit(input[fast])) {
                while (fast >= 0 && Character.isDigit(input[fast])) {
                    result[slow--] = input[fast--];
                }
            } else {
                result[slow--] = '1';
            }
            result[slow--] = input[fast--];
        }
        return new String(result);
    }

    private int copyDigits(char[] input, int index, int count) {
        int len = 0;
        for (int i = count; i > 0; i /= 10) {
            index++;
            len++;
        }
        for (int i = count; i > 0; i /= 10) {
            int digit = i % 10;
            input[--index] = (char) ('0' + digit);
        }
        return len;
    }

    // find the longest substring without any repeating characters
    public int longest(String input) {
        Set<Character> distinct = new HashSet<>();
        int s = 0;
        int f = 0;
        int longest = 0;
        while (f < input.length()) {
            if (distinct.contains(input.charAt(f))) {
                distinct.remove(input.charAt(s));
                s++;
            } else {
                distinct.add(input.charAt(f));
                f++;
                longest = Math.max(longest, f - s);
            }
        }
        return longest;
    }

    public List<Integer> allAnagrams(String lo, String sh) {
        List<Integer> result = new ArrayList<>();
        if(sh.isEmpty()) return result;
        HashMap<Character, Integer> shortString = countMap(lo);
        int slow = 0;
        int fast = lo.length()-1;
        int match = 0;
        for(int i = 0; i < lo.length(); i ++){
            Integer value = shortString.get(sh.charAt(i));
            if(value != null){
                match ++;
                shortString.put(sh.charAt(i),value-1);
            }
        }
        if(match == lo.length()){
            result.add(slow);
        }

        while (fast < sh.length()-1){

            // determine the left index first, which is slow
            // determine old slow
            Integer slowBefore = shortString.get(sh.charAt(slow));
            if(slowBefore != null){
                if(slowBefore >=0 ){
                    match--;
                }

                shortString.put(sh.charAt(slow),slowBefore+ 1);
            }
            slow ++;

            // determine the new add right index second
            fast++;
            Integer fastCount = shortString.get(sh.charAt(fast));
            if(fastCount != null){
                if(fastCount > 0){
                    match ++;
                }
                shortString.put(sh.charAt(fast),fastCount-1);
            }

            // condition of get match
            if(match == lo.length()){
                result.add(slow);
            }
        }

        return result;
    }

    private HashMap<Character, Integer> countMap(String s){
        HashMap<Character, Integer> result = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            if(result.get(s.charAt(i)) == null){
                result.put(s.charAt(i), 1);
            }else {
                result.put(s.charAt(i), result.get(s.charAt(i))+1);
            }
        }
        return result;
    }
}
