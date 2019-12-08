package class_11_RecursionII;

import class_3_linked_list.ListNode;
import class_5_BST_BT.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

public class RecursionII {
    public List<List<Integer>> nqueens(int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> current = new ArrayList<>();
        nqueensHelper(n,current,result);
        return result;
    }

    private void nqueensHelper(int n, List<Integer> curr, List<List<Integer>> result){
        if(curr.size() == n){
            result.add(new ArrayList<Integer>(curr));
            System.out.println(new String(curr.toString()));
            return;
        }
        for(int i = 0; i < n; i++){
            if(checkValid(curr,i)){
                curr.add(i);
                nqueensHelper(n,curr,result);
                curr.remove(curr.size()-1);
            }
        }
    }

    private boolean checkValid(List<Integer> curr, int col){
        int row = curr.size();
        for(int i= 0; i < row; i ++){
            if(curr.get(i) == col || Math.abs(curr.get(i)-col) == row - i) return false;
        }
        return true;
    }

    public List<Integer> spiralSquare(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        spiralSHelper(matrix,0,matrix.length,result);
        return result;
    }

    private void spiralSHelper(int[][] matrix, int offset, int size, List<Integer> result){
        if(size <= 1){
            if(size == 1) result.add(matrix[offset][offset]);
            return; }
        for(int i = 0; i < size-1; i++) result.add(matrix[offset][i+offset]);
        for(int i = 0; i < size-1; i++ ) result.add(matrix[offset+i][offset+size-1]);
        for(int i = 0; i < size-1; i++) result.add(matrix[offset+size-1][offset+size-1-i]);
        for(int i = 0; i < size -1; i++) result.add(matrix[offset+size-1-i][offset]);
        spiralSHelper(matrix,offset+1,size-2,result);
    }

    public List<Integer> spiralRectangl(int[][] matrix){
        List<Integer> result = new ArrayList<>();
        spiralRHelper(matrix, 0, matrix[0].length, matrix.length,result);
        return result;
    }

    private void spiralRHelper(int[][] matrix, int offset, int x, int y, List<Integer> result){
        if(x == 0 || y == 0) {
            return;
        }
        if(x <= 1 && y <=1) {
            if(x==1 && y==1) result.add(matrix[offset][offset]);
            return;
        } else{
            if(x>1 && y == 1){
                for(int i=0;i < x;i++) result.add(matrix[offset][offset+i]);
                return;
            }
            else if(y > 1 && x==1) {
                for(int i = 0;i < y; i++) result.add(matrix[offset+i][offset]);
                return;
            }
        }
        for(int i = 0; i < x-1;i++) result.add(matrix[offset][offset+i]);
        for(int i = 0; i < y-1; i++) result.add(matrix[offset+i][offset+x-1]);
        for(int i =0; i < x-1; i++) result.add(matrix[offset + y -1][offset + x - 1- i]);
        for(int i = 0;i < y-1; i++) result.add(matrix[offset + y -1 -i][offset]);
        x -= 2;
        y -= 2;
        spiralRHelper(matrix,offset+1, x, y,result);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode one, TreeNode two) {
        if(root == null || root == one || root == two) return root;
        TreeNode left = lowestCommonAncestor(root.left, one,two);
        TreeNode right = lowestCommonAncestor(root.right, one , two);

        if(left != null && right != null){
            return root;
        }
        return left == null ? right : left;
    }


    public ListNode reverseInPairs(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = reverseInPairs(head.next.next);
        next.next = head;
        return next;
    }

    public boolean match(String input, String pattern) {
        if(input.length() == 0 && pattern.length() ==0){
            return true;
        }else if(input.length() == 0 || pattern.length() ==0){
            return false;
        }

        // if pattern char is letter then need to check same or not
        if(isLetter(pattern.charAt(0))){
            if(pattern.charAt(0) == input.charAt(0)){
                return match(input.substring(1),pattern.substring(1));
            }
            else{
                return false;
            }
        }
        // if pattern char is not letter then do number check
        else {
            int i = 0;
            int number = 0;
            // get the number in the pattern
            while (i < pattern.length() && isDigit(pattern.charAt(i))){
                number = number* 10 + (pattern.charAt(i)-'0');
                i++;
            }
            // if that number is even larger then the input, then just false
            if(number > input.length()) return false;
            else {
                // the sub problem is we want to jump to the number of char in
                // input from pattern. and also jump i char since this i is showing
                // the how many digit in pattern.
                return match(input.substring(number),pattern.substring(i));
            }
        }
    }

    public void numNodesLeft(TreeNodeLeft root) {
        if(root == null) {
            return;
        }
        helper(root);
    }

    private int helper(TreeNodeLeft root){
        if(root == null){
            return 0;
        }

        int leftNode = helper(root.left);
        int rightNode = helper(root.right);
        root.numNodesLeft = leftNode;
        return leftNode + rightNode + 1;
    }
}
