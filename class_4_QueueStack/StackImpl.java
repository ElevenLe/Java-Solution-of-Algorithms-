package class_4_QueueStack;

import java.util.LinkedList;

public class StackImpl {
    public void sort3Stack(LinkedList<Integer> s1){
        // corner case: if s1 is empty or only one
        // element, no need to sort
        if(s1.isEmpty() || s1.size() <= 1) return;
        LinkedList<Integer> s2 = new LinkedList<>();
        LinkedList<Integer> s3 = new LinkedList<>();
        // set first element in s1 as first pivot
        int curr = s1.poll();
        s3.offerFirst(curr);
        while(!s1.isEmpty()){
            // step1
            curr = s1.poll();
            // case 1: if curr smaller than s3 top
            while(!s3.isEmpty() && s3.peek() > curr){
                s2.offerFirst(s3.poll());
            }
            // case 2: put curr on s3
            s3.offerFirst(curr);
            // step 2: put back from s2 to s3
            while(!s2.isEmpty()){
                s3.offerFirst(s2.poll());
            }
        }
        // step 3: put back from s3 to s1
        while (!s3.isEmpty()){
            s1.offerFirst(s3.poll());
        }
    }

    public void sort2Stack(LinkedList<Integer> s1){
        if(s1.isEmpty() || s1.size() <= 1) return;
        LinkedList<Integer> s2 = new LinkedList<>();
        s2.offerFirst(s1.poll());
        sort2StackHelper(s1, s2);
    }

    private void sort2StackHelper(LinkedList<Integer> s1,
                                  LinkedList<Integer> buffer){
        while(!s1.isEmpty()){
            // step 1: store the s1 value
            int curr = s1.poll();
            int index = 0;
            // case 1: if value smaller than buffer top
            while(!buffer.isEmpty() && buffer.peek() > curr){
                s1.offerFirst(buffer.poll());
                index ++;
            }
            // case 2: value larger than buffer top
            // directly put on top
            buffer.offerFirst(curr);
            // step 2: put back from s1 to s3
            while (index != 0){
                buffer.offerFirst(s1.poll());
                index --;
            }
        }
        // step 3: put back from s3 to s1
        while(!buffer.isEmpty()){
            s1.offerFirst(buffer.poll());
        }
    }
}

class StackWithMin{
    private LinkedList<Integer> input;
    private LinkedList<Integer> minStack;
    public StackWithMin(){
        input = new LinkedList<>();
        minStack = new LinkedList<>();
    }
    public int pop() {
        // pop the min stack with input pop
        if(!minStack.isEmpty())
            minStack.poll();
        return input.isEmpty() ? -1 : input.poll();
    }
    public void push(int element) {
        input.offerFirst(element);
        // case 1: nothing, directly put
        if(minStack.isEmpty())
            minStack.offerFirst(element);
        // case 2: smaller than in min stack, put new value
        else if(minStack.peek() >= element)
            minStack.offerFirst(element);
        // case 3: bigger than in min stack, put odd min
        else minStack.offerFirst(minStack.peek());
    }
    public int top() {
        return input.isEmpty() ? -1 : input.peek();
    }
    public int min() {
        return minStack.isEmpty() ? -1 : minStack.peek();
    }
}