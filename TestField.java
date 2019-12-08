import DynamicProgramming.DPIIII;
import LeetCode.LeetCode;
import OA_pre.Goldman;
import OA_pre.Google;
import OA_pre.Point72;
import OA_pre.Wayfair;
import Programming_Team.FallW5;
import Train.AllTrain;
import class_16_CrossTraining1.CT2;
import class_16_CrossTraining1.CT3;
import class_16_CrossTraining1.CT4;
import class_16_CrossTraining1.GraphNode;
import class_3_linked_list.ListNode;
import class_5_BST_BT.TreeNode;
import class_7_DFS.DFS;
// import com.sun.org.apache.xpath.internal.operations.String;

import java.util.*;

public class LaiCodeTestField {
    public static void main(String[] args) {
        //        int[][] test_input = new int[3][4];
//        test_input[0][0] = 1;
//        test_input[0][1] = 2;
//        test_input[0][2] = 3;
//        test_input[0][3] = 4;
//
//        test_input[1][0] = 5;
//        test_input[1][1] = 6;
//        test_input[1][2] = 7;
//        test_input[1][3] = 8;
//
//        test_input[2][0] = 9;
//        test_input[2][1] = 10;
//        test_input[2][2] = 11;
//        test_input[2][3] = 12;
//
//        BinarySearchInTwoD test = new BinarySearchInTwoD();
//        int[] out = test.BinarySearchIn2D(test_input, 90);
//        System.out.println(out[0]);
//        System.out.println(out[1]);
//        int[] out2 = test.BinarySearchIn2D2(test_input, 90);
//        System.out.println(out2[0]);
//        System.out.println(out2[1]);


        // test closest k elements
//        int[] a = new int[]{1,4,7,9,12,13,14};
//        int[] b = new int[]{3,5,10,11,17,23,32};
//        int k = 9;
//        ClosestElementToTarget test = new ClosestElementToTarget();
//        int out = test.KthSmallestInTwoSortedArrays(a, b, k);
//        System.out.println(out);
//
//        int[] input = new int[]{1,1,0,-1,0,1,-1};
//        SortingAlgo test = new SortingAlgo();
//        int[] out = test.rainBowSort(input);
//        for(int i =0;i<out.length; i++){
//            System.out.print(out[i] + " ");
//        }

//        Link_list test2 = new Link_list();
//        ListNode one = new ListNode(1);
//        ListNode one1 = new ListNode(9);
//        ListNode one2 = new ListNode(9);
//
//        one.next = one1;
//        one1.next = one2;
//
////        ListNode sp = new ListNode(3);
////        one2.next = sp;
//
//        ListNode two = new ListNode(9);
//        ListNode two1 = new ListNode(9);
//        ListNode two2 = new ListNode(8);
//        ListNode two3 = new ListNode(9);
//
//        two.next = two1;
//        two1.next = two2;
//        two2.next = two3;


//        System.out.println(test2.checkLength(one));
//        test2.extendLinkedList(one,2);
//        System.out.println(test2.checkLength(one));
//        ListNode result = test2.addTwo(one, two);

//        while(result != null){
//            System.out.println(result.value);
//            result = result.next;
//        }
//
//        ListNode result = test2.addTwo(one,two);
//
//                while(result != null){
//            System.out.println(result.value);
//            result = result.next;
//        }
// ----------------------------------------------------------------

        /*
        Stack and Queue implementation
        */

        /*

        // Queue: First in First out FIFO
        // create Queue
        Deque<Integer> queue = new ArrayDeque<>();
        Deque<Integer> queue2 = new LinkedList<>();
        LinkedList<Integer> queue3 = new LinkedList<>();

        queue.offerLast(1);
        queue.offerLast(2);
        queue.offerLast(3);

        while(!queue.isEmpty()){
            System.out.println(queue.pollFirst());
        }
        // 1
        // 2
        // 3

        queue3.offer(1);
        queue2.offer(1);
        queue2.offer(2);
        queue2.offer(3);

        while(!queue2.isEmpty()){
            System.out.println(queue2.poll());
        }

        // Stack: Last in First out LIFO
        // create Stack
        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> stack2 = new LinkedList<>();
        LinkedList<Integer> stack3 = new LinkedList<>();

        stack.offerFirst(1);
        stack.offerFirst(2);
        stack.offerFirst(3);

        while(!stack.isEmpty()){
            System.out.println(stack.poll());
        }
        // 3
        // 2
        // 1

        stack2.offerFirst(1);
        stack2.offerFirst(2);
        stack2.offerFirst(3);
        while(!stack2.isEmpty()){
            System.out.println(stack2.poll());
        }
        */

// ----------------------------------------------------------------


        /*
        LinkedList<Integer> input = new LinkedList<>();
        input.offerFirst(8);
        input.offerFirst(7);
        input.offerFirst(5);
        input.offerFirst(9);
        input.offerFirst(3);

        StackImpl stackImple = new StackImpl();
        stackImple.sort2Stack(input);
        while (!input.isEmpty()){
            System.out.println(input.poll());
        }

        Integer x = 128;
        Integer y = 128;
        System.out.println(x==y);
        */

        // ==================================================================

        /*
        PriorityQueue<Integer> test = new PriorityQueue<Integer>(7, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                if(o1.equals(o2)) return 0;
                return o1 > o2? -1: 1;
            }
        });
        test.add(2);
        test.add(1);
        System.out.println(test.poll());
        test.peek();

        int[] input = new int[]{3,1,5,2,4};
        Heap heap = new Heap();
        int[] output = heap.kSmallest(input,1);
        System.out.println(output[0]);

        BFS bfs = new BFS();
        GraphNode zero = new GraphNode(0);
        GraphNode one = new GraphNode(1);
        zero.neighbors.add(one);
        one.neighbors.add(zero);
        List<GraphNode> input2 = new ArrayList<GraphNode>();
        input2.add(zero);
        input2.add(one);
        System.out.println(bfs.isBipartite(input2));

        */

        /*
        DFS test = new DFS();
        List<String> output = test.subSet("abc");
        for(String a : output){
            System.out.println(a);
        }

         */

        /*
        int i = -1000;
        char c = (char)i;
        System.out.println(c);
         */

        /*
        Heap test = new Heap();
        int[] input = new int[]{2,5,1,7,4,6};
        test.buildHeap(input);
        for(int i : input){
            System.out.println(i);
        }

         */

//        HashTableImpl test = new HashTableImpl();
//        String[] input = new String[]{"d","a","c","b","d","a","b","b","a","d","d","a","d"};
//        String[] output = test.topKFrequent(input,4);
//        for(String s : output){
//            System.out.println(s);
//        }
//
//        List<Integer> A = Arrays.asList(1,2,3,4,5);
//        List<Integer> B = Arrays.asList(2,3,5,9);
//        List<Integer> out = test.common(A,B);
//
//        for(Integer o : out) System.out.println(o);
//
//        StringI test= new StringI();
//        String input = "   abc  de  ";
//        String out = test.removeSpaces(input);
//        System.out.println(out);

//        short a = -1;
//        char b = (char)a;
//        System.out.println((int)b);
//        BitOperation test = new BitOperation();
//        String out = test.hex2(255);
//        System.out.println(out);

//        RecursionII test = new RecursionII();
//        test.nqueens(4);

//        StringI test = new StringI();
//        String large = "aabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaa";
//        String small = "  baaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
//        int result = test.strstr(large,small);
//        System.out.println(result);

//        StringII test = new StringII();
//        String input = "It is a good day";
//        System.out.println( test.reverseWords(input));

//        int[] input = {1,2,3,4,5,6};
//        int[] result = test.reorder(input);
//        for(int i : result) {
//            System.out.println(i);
//        }
//        char p1 = 'a';
//        String p2 = "a";
//        p2.equals(p2);

//        String input = "abbbbcc";
//        String output = test.compress(input);
//        System.out.println(output);

//        String l = "a", s = "aaa";
//        List<Integer> out = test.allAnagrams(l,s);
//        for(Integer one : out){
//            System.out.println(one);
//        }

//        BitOperation test = new BitOperation();
//        long output = test.reverse(255L);
//        System.out.println(output);
//        int[][] input = {{1,2,3,4,5},{6,7,8,9,10}};
//        RecursionII test = new RecursionII();
//        List<Integer> output = test.spiralRectangl(input);
//        for(Integer one : output) {
//            System.out.print(one + " ");
//        }

//        DPII test = new DPII();
////        int out = test.minJump(new int[]{5,6,0,0,0,10,0,0,0});
////        System.out.println(out);
//        int out = test.largestSum(new int[]{1,-2,3});
//        System.out.println(out);

//        boolean out = test.canBreak("bcdabc", new String[]{"abc","bcd","def"});
//        System.out.println(out);

//        int out = test.editDistance2("abcdef", "bbccf");
//        System.out.println(out);
//        DPIII test = new DPIII();
//        int[][] input = new int[][]{{3}};
//        int out = test.largestSquareOfMatches(input);
//        System.out.println(out);

//        Randomization test = new Randomization();
//        test.read(1);
//        test.read(2);
//
//        System.out.println(test.getMedian());
//        CT1 test = new CT1();
//        int[][] input = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
//        test.rotate(input);
//        for(int[] each: input){
//            for (int eac: each){
//                System.out.print(eac + " ");
//            }
//            System.out.println();
//        }

//        CT4 test = new CT4();
//        List<Integer> result = test.maxWindowsIII(new int[]{1,2,3,4,5,6,7,8,9,1,1}, 2);
//        for(Integer each: result){
//            System.out.println(each);
//        }
//        test.test2();
//        System.out.println(8/3);
//        CT3 test = new CT3();
//        // List<List<Integer>> input = new ArrayList<List<Integer>>()
//        // List<Integer> output = test.commonElementsInKSortedArrays(new int[]{3,2,1,5,4}, new int[]{2,9,5,3});
//        int out = test.largestProduct(new String[]{"aa","bb","ccc","abcfgh","ehi"});
//        System.out.println(out);

//        DPIIII test = new DPIIII();
//        int out = test.minCost(new int[]{2, 4, 7},10);
//        System.out.println(out);
//
//        int[] input = new int[]{1,1,1,3,5,2};
//        int out2 = test.LAS(input);
//        System.out.println(out2);

     //  CT2 test = new CT2();
//        GraphNode one = new GraphNode(1);
//        GraphNode two = new GraphNode(2);
//        GraphNode three = new GraphNode(3);
//        one.neighbors.add(two);
//        two.neighbors.add(one);
//
//        one.neighbors.add(three);
//        three.neighbors.add(one);
//
//        two.neighbors.add(three);
//        three.neighbors.add(two);
//
//        List<GraphNode> input = new ArrayList<>();
//        input.add(one);
//        input.add(two);
//        input.add(three);

//        List<GraphNode> out = test.copyByBFS(input);
//        for(GraphNode each : out){
//            System.out.println(each.key);
//        }

//        int[] out = test.merge(new int[][]{{0,2},{1,3}});
//        for (int i = 0; i < out.length; i ++){
//            System.out.println(out[i]);
//        }

//        List<List<Integer>> out = test.allTriples(new int[]{1,1,1,1,1,1,1,1,1,1,1}, 3);
//        for(List<Integer> one : out){
//            for(Integer each : one){
//                System.out.print(each + " ");
//            }
//            System.out.println();
//        }

//        LeetCode leetCode = new LeetCode();
//        int out = leetCode.coinChange(new int[]{186,419,83,408}, 6249);
//        System.out.println(out);
        // AllTrain test2 = new AllTrain();
       // test2.existSum(new int[]{-1,0,1}, 0);

//        test2.combinations(10,new int[]{34,31,29,16,2});

//        System.out.println(test2.longestPalindrome("abcbcbd"));
//        String[] result = test2.topKFrequent2(new String[]{"d","a","c","b","d","a","b","b","a","d","d","a","d"},2);
//        for(String each : result){
//            System.out.println(each);
//        }

//        List<Integer> reuslt = test2.maxWindows(new int[]{1,3,2,5,8,9,4,7,3},3);
//        for(Integer each : reuslt){
//            System.out.println(each);
//        }

        Goldman test = new Goldman();
        // System.out.println(test.winner("HHEE" ,"HMME"));

//        List<List<String>> input1 = new ArrayList<>();
//        /**
//         input1.add(Arrays.asList("06-22","Chuck","540","540"));
//         input1.add(Arrays.asList("06-23","Debby","540","555"));
//         input1.add(Arrays.asList("06-23","Chuck","540","540"));
//         input1.add(Arrays.asList("06-23","Doug","600","630"));
//         input1.add(Arrays.asList("06-24","Chuck","600","600"));
//         input1.add(Arrays.asList("06-24","Doug","600","600"));**/
//        input1.add(Arrays.asList("09-01","Arlene","540","570"));
//        input1.add(Arrays.asList("09-01","Bobby","540","543"));
//        input1.add(Arrays.asList("09-01","Caroline","540","530"));
//        input1.add(Arrays.asList("09-02","Arlene","540","580"));
//        input1.add(Arrays.asList("09-02","Bobby","540","580"));
//        input1.add(Arrays.asList("09-02","Caroline","540","595"));
//        System.out.println(test.latest(input1));

//        String[] input = new String[]{"a/*comment", "line", "more_comment*/b"};
//        List<String> result = test.removeComments(input);
//        System.out.println(result.size());
//        for(String each : result){
//            System.out.println(each);
//        }
//
//        String[] ex = new String[]{"int main()","{ ","  ","int a, b, c;","a = b + c;","}"};
//        System.out.println(ex.length);

//        int[][] input = new int[][]{{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
//        test.gameOfLife(input);
//        for(int[] each : input){
//            for(int ele: each){
//                System.out.print(ele+" ");
//            }
//            System.out.println();
//        }

//        int[] input = new int[]{2, 3, 2, 1, 4, 5, 2, 11};
//        // System.out.println(test.maxProfit4(input,3));
//
//        System.out.println(test.rotateString("lkeyaaskhusmdqxrceycclheezetkuadmzshxeijzrwwvwijqghzfanifimwzenrxrgrbwlpcgvebqgglolngtdyvvygugxmobdd",
//                                             "qgglolngtdyvvygugxmobddlkeyaaskhusmdqxrceycclheezetkuadmzshxeijzrwwvwijqghzfanifimwzenrxrgrbwlpcgveb"));
//
//        System.out.println(test.sharePurchase("ABC"));
        Google googleTest = new Google();
//        int[] out = googleTest.intersect(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13}, new int[]{1,4,7,9});
//        for(int each : out) {
//            System.out.println(each);
//        }

//        ListNode one = new ListNode(1);
//        ListNode two = new ListNode(1);
//        one.next = two;
//        ListNode head = googleTest.removeDup(one);
//        while(head != null){
//            System.out.println(head.value);
//            head = head.next;
//        }
        // System.out.println(googleTest.sqrt2(2147395600));
        // String ans = "[0, 2, 1], [2, 4, 3], [4, 9, 4], [9, 16, 5], [16, 18, 6], [18, 22, 7], [22, 23, 8], [23, 25, 10], [25, 26, 11], [26, 33, 12], [33, 36, 13], [36, 37, 14], [37, 38, 15], [38, 39, 16], [39, 40, 17], [40, 48, 16], [48, 49, 17], [49, 56, 18], [56, 58, 19], [58, 70, 18], [70, 77, 17], [77, 78, 18], [78, 81, 17], [81, 87, 18], [87, 88, 19], [88, 89, 21], [89, 90, 23], [90, 92, 24], [92, 96, 23], [96, 98, 24], [98, 102, 25], [102, 104, 24], [104, 105, 23], [105, 109, 24], [109, 120, 25], [120, 124, 26], [124, 130, 25], [130, 131, 24], [131, 132, 23], [132, 141, 22], [141, 145, 23], [145, 147, 22], [147, 148, 21], [148, 150, 20], [150, 158, 21], [158, 159, 20], [159, 160, 19], [160, 162, 20], [162, 165, 21], [165, 167, 20], [167, 170, 21], [170, 173, 20], [173, 194, 22], [194, 195, 21], [195, 196, 22], [196, 199, 23], [199, 207, 22], [207, 209, 23], [209, 212, 22], [212, 213, 23], [213, 214, 22], [214, 215, 21], [215, 218, 20], [218, 220, 19], [220, 221, 18], [221, 222, 17], [222, 232, 16], [232, 233, 14], [233, 243, 15], [243, 255, 14], [255, 256, 13], [256, 258, 12], [258, 259, 11], [259, 260, 9], [260, 262, 8], [262, 270, 7], [270, 275, 6], [275, 276, 5], [276, 279, 4], [279, 285, 3], [285, 292, 4], [292, 294, 3], [294, 297, 1]";
        //int[][] result = googleTest.sessionNum(new int[][]{{0,100},{1,2},{2,3},{3,4}});
       // String res_str = "";
//        for(int[] line : result){
//          //  res_str += "["+line[0] + ", "+line[1]+", "+line[2]+"], ";
//        }

       // System.out.println(ans);
//        System.out.println(res_str);

       //System.out.println(googleTest.longestConsecutive(new int[]{100,4,200,1,3,2}));
//       int[] result = googleTest.largestSubarrayLengthKD(new int[]{5,5,5,5,5,7,2,2},4);
//       System.out.println(googleTest.maxTime("?8:4?"));
//
//       AllTrain allTraintest = new AllTrain();
//       System.out.println(allTraintest.kth(2));

//      // System.out.println(googleTest.large(new int[]{3,5,5,6}));
//        Wayfair wayfairtest = new Wayfair();
//        //System.out.println(wayfairtest.maxNumberOfBalloons("krhizmmgmcrecekgyljqkldocicziihtgpqwbticmvuyznragqoyrukzopfmjhjjxemsxmrsxuqmnkrzhgvtgdgtykhcglurvppvcwhrhrjoislonvvglhdciilduvuiebmffaagxerjeewmtcwmhmtwlxtvlbocczlrppmpjbpnifqtlninyzjtmazxdbzwxthpvrfulvrspycqcghuopjirzoeuqhetnbrcdakilzmklxwudxxhwilasbjjhhfgghogqoofsufysmcqeilaivtmfziumjloewbkjvaahsaaggteppqyuoylgpbdwqubaalfwcqrjeycjbbpifjbpigjdnnswocusuprydgrtxuaojeriigwumlovafxnpibjopjfqzrwemoinmptxddgcszmfprdrichjeqcvikynzigleaajcysusqasqadjemgnyvmzmbcfrttrzonwafrnedglhpudovigwvpimttiketopkvqw"));
////        System.out.println(wayfairtest.solution(2,3,2));
////        System.out.println(wayfairtest.delete("aaaaabbcccccddddddiiaeeeee"));
//        int[][] testinput = new int[][]{{4, 3, 4, 5, 3}, {2, 7, 3, 8, 4}, {1, 7, 6, 5, 2}, {8, 4, 9, 5, 5}};
//        System.out.println(wayfairtest.magicSquare(testinput));
////        System.out.println(wayfairtest.gamble(10,10));
////        System.out.println(wayfairtest.solution2("ba"));


        Point72 test3 = new Point72();
        int[] out = test3.frequencyOfMaxValue(new int[]{1,2,3,4,5,6,7,5,3,4,5,6,6,2,6,3,4,6,2,5}, new int[]{9});
        for(int i : out){
            System.out.println(i);
        }
    }
}



//class Coordinate{
//    int x;
//    int y;
//    public Coordinate(int a, int b){
//        x = a;
//        y=b;
//    }
//    @Override
//    public String toString(){
//        return "(" + x + ", " + y+")";
//    }
//
//    @Override
//    public boolean equals(Object other){
//        if(!(other instanceof Coordinate)) return false;
//        Coordinate o = (Coordinate) other;
//        return x==o.x && y == o.y;
//    }
//    @Override
//    public int hashCode(){
//        return 31*x + y;
//    }
//}
