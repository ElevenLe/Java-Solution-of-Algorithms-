package class_4_QueueStack;

import java.util.LinkedList;

public class QueueImpl {

}

class QueueWithTwoStack{
    private LinkedList<Integer> inputStack;
    private LinkedList<Integer> outputStack;
    public QueueWithTwoStack(){
        inputStack = new LinkedList<>();
        outputStack = new LinkedList<>();
    }
    public Integer poll() {
        move(); // when need to poll, poll from
        // output stack
        return outputStack.isEmpty()?
                null : outputStack.poll();
    }
    public void offer(int element) {
        inputStack.offerFirst(element);
    }
    public Integer peek() {
        move();
        return outputStack.isEmpty() ?
                null : outputStack.peek();
    }
    public int size() {
        return inputStack.size() + outputStack.size();
    }
    public boolean isEmpty() {
        return inputStack.size() == 0
                && outputStack.size() == 0;
    }
    // move is for move all element in input stack
    // to output stack
    private void move(){
        if(outputStack.isEmpty()){
            while (!inputStack.isEmpty())
                outputStack.offerFirst(inputStack.poll());
        }
    }
}
