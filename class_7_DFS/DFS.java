package class_7_DFS;

import java.util.ArrayList;
import java.util.List;

public class DFS {
    public List<String> subSet(String set){
        List<String> result = new ArrayList<>();
        if(set == null) return result;

        char[] arraySet = set.toCharArray();
        StringBuilder sb = new StringBuilder();
        subSetHelper(arraySet,sb, 0, result);
        return result;
    }

    private void subSetHelper(char[] set, StringBuilder sb, int index, List<String> result){
        // base case. if the index is growth to length, it reach the bottom
        if(index == set.length){
            result.add(sb.toString());
            return;
        }
        // select the character at index, add it to sb
        subSetHelper(set, sb.append(set[index]), index+1, result);
        // in order to return to the initial state for sb
        sb.deleteCharAt(sb.length()-1);
        // not select the character at index, add it to sb
        subSetHelper(set, sb, index +1, result);
    }

    // with DP
    private void subSetHelper2(char[] set, StringBuilder sb, int index, List<String> result){
        result.add(sb.toString());
        for(int i = index; i < set.length; i++){
            sb.append(set[i]);
            subSetHelper2(set, sb, i+1, result);
            sb.deleteCharAt(sb.length() -1);
        }
    }

    public List<String> validParentheses(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        // corner case, if n = 0, means no parentheses
        if(n == 0) return result;
        validParenthesesHelper(sb,n,0,0,result);
        return result;
    }

    // n stores total number of "pair ()" need to add
    // left store how many "(" we add so far
    // right store how many ")" we add so far
    private void validParenthesesHelper(StringBuilder sb,
                                        int n, int left, int right,
                                        List<String> result){
        // recursion base case
        // when left parentheses and right parentheses are same
        // as n, mean we use all the parentheses. need to save
        if(left == n && right == n){
            result.add(sb.toString());
            return; }
        // dfs to the left until very bottom
        // then find if use all the left parentheses, then we need to only use
        // right parentheses.
        if(left < n) {
            validParenthesesHelper(sb.append("("), n, left+ 1, right, result);
            sb.deleteCharAt(sb.length()-1);}
        // if dfs on right, find that some situation right parentheses is more
        // than left, it means this is not the right case. otherwise support
        // thee left parentheses numbers.
        if(right < left){
            validParenthesesHelper(sb.append(")"), n,left, right + 1, result);
            sb.deleteCharAt(sb.length()-1);}
    }

    public void printIFblocks(int n){
        List<List<String>> result = new ArrayList<>();
        List<String> currnt = new ArrayList<>();
        currnt.add("if {}");
        printHelper(n-1, currnt, result);
        for(List<String> one : result){
            for(String each : one){
                System.out.println(each);
            }
        }
    }

    private void printHelper(int n, List<String> current, List<List<String>> result){
        if(n == 0){
            result.add(current);
            return;
        }
        current.add("if {}");
        printHelper(n-1, current,result);
        current.remove(current.size() - 5);

        current.remove(current.size()-1);
        current.add("if {}}");
        printHelper(n-1,current,result);
        current.remove(current.size()-6);
    }



    public List<List<Integer>> coinCombins(int target, int[] coins) {
        // final result, only including the correct ones.
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // save for each result
        List<Integer> curr = new ArrayList<>();
        coinCombinsHelper(target, coins, 0, curr,result);
        return result;
    }

    // moneyLeft is how much money left each round
    // index indicates which price of coins current at
    // curr is for current branch saved coins before
    private void coinCombinsHelper(int moneyLeft, int[] coins, int index,
                                   List<Integer> curr, List<List<Integer>> result){
        // base case. if reach the coin before last coin
        if(index == coins.length-1){
            // for the remind money, if it can divided by the last value of coin
            // if it can, it mean there are just ok amount of value. which is
            // qualified answer
            if(moneyLeft % coins[index] == 0){
                // but we do not have the last layer of answer, so we have to add
                curr.add(moneyLeft / coins[index]);
                result.add(new ArrayList<Integer>(curr));
                // once finish put curr into result, we need to back to
                // upper layer state so that it can try other loop
                curr.remove(curr.size()-1); }
            return; }
        // a loop from 0 of index value of coins. for example
        // 0,1,2... of 25 cens. we have total amount of coins until
        // it reach total money left
        for(int i = 0 ; i <= moneyLeft/coins[index]; i++){
            // add curr number of coins into curr
            curr.add(i);
            // call stack with new money left and next value of coin
            coinCombinsHelper(moneyLeft-i*coins[index],coins,index+1,
                    curr, result);
            // once finished the lower call stack, it have to go back to
            // original state.
            curr.remove(curr.size()-1);
        }
    }

    public List<String> permutations(String set) {
        List<String> result = new ArrayList<>();
        // corner case
        if(set == null) return result;
        if(set.equals("")){
            result.add(new String(""));
            return result; }
        // transfer set to array so that we can
        // indicate the position.
        char[] array = set.toCharArray();
        permutationsHelper(array, 0, result);
        return result;
    }

    // index indicate which element is selected.
    private void permutationsHelper(char[] array, int index,
                                    List<String>result){
        // base case. if index is length, then means
        // all element have been selected
        if(index == array.length){
            result.add(new String(array));
            return; }
        // loop all the swaps after selection position
        for(int i = index; i < array.length; i ++){
            swap(array, index, i);
            // after each swap, go to next level with
            // one more select position
            permutationsHelper(array, index+1, result);
            // return to the original position
            swap(array, index, i);
        }
    }
    private void swap(char[] a, int left, int right){
        char temp = a[left];
        a[left] = a[right];
        a[right] = temp;
    }
}
