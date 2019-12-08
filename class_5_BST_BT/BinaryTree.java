package class_5_BST_BT;

import java.util.*;

public class BinaryTree {
    /*
    Balanced Binary Tree：
    Depth of Left and Right subtree of
    every node is differ by 1

    Complete Binary Tree:
    for all levels must be completely filled,
    except the last level. as left as possible

    Binary Search Tree:
    for every node, value in left subtree are
    smaller, and value in right subtree are bigger.
     */

    // Pre order traversed
    // Recursive
    public List<Integer> preOrderRe(TreeNode root){
        // create a array for output
        ArrayList<Integer> preOrder = new ArrayList<Integer>();
        // corner case: if root is null, return empty array
        if(root == null) return preOrder;
        preOrderHelper(root, preOrder);
        return preOrder;
    }
    private void preOrderHelper(TreeNode root,
                                ArrayList<Integer> list){
        if(root == null) return;
        // First add self to the result
        list.add(root.key);
        // Then go left
        preOrderHelper(root.left, list);
        // Then go right
        preOrderHelper(root.right, list);
    }

    // Iterative way
    public List<Integer> preOrder(TreeNode root){
        // create a array for output
        List<Integer> preOrder = new ArrayList<Integer>();
        // corner case: if root is null, return empty array
        if(root == null) return preOrder;
        // create stack for go over the tree
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.offerFirst(root);
        while(!stack.isEmpty()){
            // get root node, then push in right, then left
            TreeNode curr = stack.poll();
            if(curr.right != null)
                stack.offerFirst(curr.right);
            if(curr.left != null)
                stack.offerFirst((curr.left));
            preOrder.add(curr.key);
        }
        return preOrder;
    }

    // In Order traversed
    // Recursive
    public List<Integer> inOrderRe(TreeNode root){
        // for output
        ArrayList<Integer> inOrder = new ArrayList<>();
        // corner case: if root is null, return empty array
        if(root == null) return inOrder;
        inOrderHelper(root, inOrder);
        return inOrder;
    }

    private void inOrderHelper(TreeNode root,
                               ArrayList<Integer> list){
        if(root == null) return;
        // left first
        inOrderHelper(root.left, list);
        // self second
        list.add(root.key);
        // right last
        inOrderHelper(root.right,list);
    }

    // Iterative way
    public List<Integer> inOrder(TreeNode root){
        List<Integer> inOrder = new ArrayList<>();
        if(root == null) return inOrder;
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;
        while(curr != null || !stack.isEmpty()){
            // if current node is root, and have
            // left, offer until reach the bottom
            if(curr!=null){
                stack.offerFirst(curr);
                curr = curr.left;
            }else {
                // when curr is null, mean all left
                // have reached
                curr = stack.poll();
                inOrder.add(curr.key);
                curr = curr.right;
            }
        }
        return inOrder;
    }

    // Post Order Traversed
    // Recursive
    public List<Integer> postOrderRe(TreeNode root){
        // output list
        ArrayList<Integer> postOrder = new ArrayList<>();
        // corner case
        if (root == null) return postOrder;
        postOrderHelper(root,postOrder);
        return postOrder;
    }

    private void postOrderHelper(TreeNode root,
                                 ArrayList<Integer> list){
        // base case
        if(root==null) return;
        postOrderHelper(root.left,list);
        postOrderHelper(root.right,list);
        // add self at last
        list.add(root.key);
    }

    // Iterative way I
    public List<Integer> postOrder(TreeNode root){
        List<Integer> postOrder = new ArrayList<Integer>();
        if(root == null) return postOrder;
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.offerFirst(root);
        while(!stack.isEmpty()){
            // sequence of command matters here
            TreeNode curr = stack.poll();
            postOrder.add(curr.key);
            if(curr.left != null) stack.offerFirst((curr.left));
            if(curr.right != null) stack.offerFirst(curr.right);
        }
        Collections.reverse(postOrder);
        return postOrder;
    }


    // not optimal and why?
    public boolean isBalanced(TreeNode root){
        // corner case and base case
        if(root == null) return true;
        // determine the height difference
        if(Math.abs(getHeight(root.left) - getHeight(root.right)) > 1)
            return false;
        // recursively check the left and right is balanced
        return isBalanced(root.left) && isBalanced(root.right);
    }

    private int getHeight(TreeNode root){
        if(root == null) return 0;
        // recursively find the high, and return it from bottom to up
        return Math.max(getHeight(root.left),getHeight(root.right))+1;
    }

    public boolean isSymmetric(TreeNode root){
        // corner case: is null, then is symmetric
        if(root == null) return true;
        return isSymmetricHelper(root.left,root.right);
    }
    private boolean isSymmetricHelper(TreeNode left, TreeNode right){
        // base case 1
        if (left == null && right == null) return true;
        // base case 2
        else if(left == null || right == null) return false;
        // determine case
        else if(left.key != right.key) return false;
        // recursively determine the symmetric
        return isSymmetricHelper(left.left, right.right)
                && isSymmetricHelper(left.right,right.left);
    }

    public boolean isTweakedIdentical(TreeNode one, TreeNode two){
        // base case 1
        if(one == null && two == null) return true;
        // base case 2
        if(one == null || two == null) return false;
        // determine case
        if(one.key != two.key) return false;
        // recursively determine the symmetric and same
        return (isTweakedIdentical(one.right, two.right)
                && isTweakedIdentical(one.left, two.left))
                || (isTweakedIdentical(one.left, two.right)
                && isTweakedIdentical(one.right,two.left));
    }

    public boolean isBST(TreeNode root){
        // corner case
        if(root == null) return true;
        return isBSThelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private boolean isBSThelper(TreeNode root, int min, int max){
        // base case
        if(root == null) return true;
        // determine case
        if(root.key < min || root.key > max) return false;
        // change left bound and right bound to next level
        return isBSThelper(root.left, min,root.key-1)
                && isBSThelper(root.right, root.key+1,max);

    }

    // BST q
    public List<Integer> getRange(TreeNode root, int min, int max){
        // output
        List<Integer> list = new ArrayList<>();
        // corner case: null return empty list
        if(root == null) return list;
        getRangeHelper(root, min, max,list);
        return list;
    }

    private void getRangeHelper(TreeNode root, int min, int max,
                               List<Integer> list){
        // base case 1
        if(root == null) return;
        // 剪枝，if less than min bound, not need to go over
        if(root.key > min)
            getRangeHelper(root.left, min, max,list);
        // if in range, get the value
        if(root.key <= max && root.key >= min)
            list.add(root.key);
        // if larger than max bound, no need to go over
        if(root.key < max)
            getRangeHelper(root.right,min,max,list);
    }




}
