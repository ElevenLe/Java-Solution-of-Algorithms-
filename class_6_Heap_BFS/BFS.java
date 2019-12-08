package class_6_Heap_BFS;

import class_5_BST_BT.TreeNode;

import java.util.*;

public class BFS {
    // base BFS
    public void BFSprint(GraphNode root){
        // base case
        if(root == null) return;
        // set up the queue for storing all the elements
        LinkedList<GraphNode> queue = new LinkedList<>();
        // put the first node
        queue.offer(root);
        while(!queue.isEmpty()){
            // get element from queue
            GraphNode curr = queue.poll();
            System.out.println(curr.key);
            // put all the neighbors into queue
            for(GraphNode neg: curr.neighbors){
                if(!curr.neighbors.contains(neg))
                    queue.offer(neg);
            } } }


    // print all the value layer by layer
    public List<List<Integer>> layerByLayer (TreeNode root){
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // create queue for BFS
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            // create layer's list
            List<Integer> currLayer = new ArrayList<>();
            int size = queue.size();
            // current layer's size is queue size
            // loop current layer's element and add them
            // to layer list.
            for(int i =0; i<size;i++){
                TreeNode curr = queue.poll();
                currLayer.add(curr.key);
                // meanwhile, put their children into queue
                // for next round.
                if(curr.left!=null) queue.offer(curr.left);
                if(curr.right!=null) queue.offer(curr.right);
            }
            result.add(currLayer); }
        return result;
    }

    public boolean isBipartite(List<GraphNode> graph){
        // the hash map keep track the showed node and it's groups
        HashMap<GraphNode, Integer> visited = new HashMap<GraphNode, Integer>();
        // this for loop is in case of forest.
        for(GraphNode node : graph){
            if(!isBipartiteHelper(node,visited)) return false;
        }
        return true;
    }

    // using BFS as search base, but also keep track of
    // the shown node and their group
    private boolean isBipartiteHelper(GraphNode node,
                                      HashMap<GraphNode, Integer> visited){
        // base case if this node have been BFS, no need to do it again
        if(visited.containsKey(node)) return true;
        // queue for BFS
        LinkedList<GraphNode> queue = new LinkedList<>();
        queue.offer(node);
        // once it put into queue, it should be marked as visited.
        // record it with it's group
        visited.put(node, 0);
        // BFS loop
        while (!queue.isEmpty()){
            // Get the first node.
            GraphNode currVertex = queue.poll();
            // determine the group for this node
            int curGroup = visited.get(currVertex);
            // the neighbor of current node should be all in different group
            int neiGroup = curGroup == 0? 1 : 0;
            // loop through the neighbors of the current node. they all
            // should be in different group.
            for(GraphNode vertex : currVertex.neighbors){
                // if never seen this neighbor, put it into queue, and record
                // with group number as opposed of current node
                if(!visited.containsKey(vertex)){
                    visited.put(vertex,neiGroup);
                    queue.offer(vertex);
                }
                // if this neighbor have visited, check it's group. It should
                // be in different group. otherwise it is false.
                else if(visited.get(vertex) != neiGroup) {
                    return false;
                } } }
        return true;
    }

    public boolean isCompleted(TreeNode root){
        // corner case
        if(root == null) return true;
        // flag for null appear.
        // if no null appear, flag is false
        // if null appear, flag is true
        boolean flag = false;
        // BFS queue
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        // BFS loop
        while (!queue.isEmpty()){
            // get the first item
            TreeNode currRoot = queue.poll();
            // if current node left have no value
            // then set up the flag show that there
            // is null appear
            if(currRoot.left == null) flag = true;
            // if current node left have value
            // but priors nodes somewhere have null,
            // means that there is a value after
            // null, return false
            else if(flag) return false;
            // if current node left have value
            // and no null before, then BFS
            else queue.offer(currRoot.left);

            // same check to right node
            if(currRoot.right == null) flag = true;
            else if(flag) return false;
            else queue.offer(currRoot.right);
        }
        return true;
    }

    // Dijkstra Algorithm


    // k th smallest in Martix
    public int kthSmallest(int[][] matrix, int k) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        // setup a Min heap for getting the first k items in the heap
        PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(k, new Comparator<Cell>() {
            @Override
            public int compare(Cell o1, Cell o2) {
                if(o1.value == o2.value) return 0;
                return o1.value < o2.value ? -1 : 1;
            }
        });
        boolean[][] visited = new boolean[rows][columns];
        minHeap.offer(new Cell(0,0,matrix[0][0]));
        visited[0][0] = true;
        // BFS. Imaging the matrix is a graph, and neighbor is left, and down 1.
        // using BFS searching, but keep the data in min heap structure.
        for(int i = 0; i < k-1; i++){
            Cell curr = minHeap.poll();
            // two condition for putting the element into min heep
            // 1.if it is in the range of matrix
            // 2.if it is not visited.
            if(curr.row + 1 < rows && !visited[curr.row+1][curr.column]){
                minHeap.offer(new Cell(curr.row+1,curr.column,matrix[curr.row+1][curr.column]));
                visited[curr.row+1][curr.column] = true;
            }
            if(curr.column + 1 < columns && ! visited[curr.row][curr.column+1]){
                minHeap.offer(new Cell(curr.row,curr.column+1, matrix[curr.row][curr.column+1]));
                visited[curr.row][curr.column+1] = true;
            }
        }
        // after put first k item into min heap, we only need to get the top item
        return minHeap.peek().value;
    }


    // static
    static class Cell{
        int value;
        int row;
        int column;

        public Cell(int row, int column,int value){
            this.row = row;
            this.column = column;
            this.value = value;
        }

    }
}

