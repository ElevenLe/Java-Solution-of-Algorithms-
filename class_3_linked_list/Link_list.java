package class_3_linked_list;

import com.sun.org.apache.bcel.internal.generic.LADD;

import java.util.List;

public class Link_list {
    public void test(){
        ListNode one_get = new ListNode();
        one_get.value = 1;
        one_get.next = one_get;
    }


    // iterative way
    public ListNode reverseLinkedList(ListNode head){
        ListNode prev = null;
        while(head != null){
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    // recursive way
    public ListNode reverseLinkedList2(ListNode head){
        // head == null is corner case
        // head.next == null is base case
        if(head == null || head.next == null){
            return head;
        }
        ListNode newHead = reverseLinkedList2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public ListNode middleNode(ListNode head){
        if(head == null) return null;
        // slow pointer
        ListNode slow = head;
        // fast pointer
        ListNode fast = head;
        while(fast.next != null &&
                fast.next.next != null){
            // slow pointer jump one
            slow = slow.next;
            // fast pointer jump two
            // so that when fast pointer finish
            // the list, slow pointer is only
            // half way there, so we get middle one
            fast = fast.next.next;
        }
        return slow;
    }

    // given a sorted linked list head. insert the value
    public ListNode insertion(ListNode head, int value){
        ListNode insert = new ListNode(value);
        // case 1, if the value is smaller than the head
        if(head == null || head.value >= value){
            insert.next = head;
            return insert;
        }
        // case 2, if the value is samller inside of list
        ListNode prev = head;
        while(prev.next != null && prev.next.value < value){
            // find the last element that is smaller than
            // target
            prev = prev.next;
        }
        // here prev and prev.next did not change
        insert.next = prev.next;
        prev.next = insert;
        return head;
    }

    public boolean checkCycle(ListNode head){
        if(head == null || head.next == null)
            return false;
        // slow pointer
        ListNode slow = head;
        // fast pointer
        ListNode fast = head;
        while(fast != null &&
                fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            // if there is a cycle, then the
            // fast pointer must meet the
            // slow pointer
            if(fast == slow) return true;
        }
        return false;
    }

    public ListNode mergeLinkedList(ListNode one, ListNode two){
        if(one == null || two == null)
            // if one is null, then return two
            // if two is null, then return one;
            // if both is null, then return two, still null
            return one == null ? two : one;
        // dummy head for output
        ListNode dummy = new ListNode(1);
        // curr pointer for go though the dummy, and attach
        // the element after it
        ListNode curr = dummy;
        // remember, curr is only a pointer for us to indicate
        // which node.
        while(one != null && two != null){
            // comparing two value, passing the smaller one
            if(one.value <= two.value){
                curr.next = one;
                // after passing, step one more
                one = one.next;
            }else {
                curr.next = two;
                two = two.next;
            }
            // iter the curr that it is ready for curr.next
            // operation
            curr = curr.next;
        }
        // check left element, and attach it into curr.
        if(one != null) curr.next = one;
        else curr.next = two;
        return dummy.next;
    }


    public ListNode reorder(ListNode head){
        if(head == null || head.next == null) return head;
        // find the middle point of list
        ListNode middle = middleNode(head);
        // disconnect the second part to avoid cycle
        ListNode secondPart = middle.next;
        middle.next = null;
        // reverse the second part
        ListNode reversed = reverseLinkedList(secondPart);
        // merge then together
        return mergeLinkListNoOrder(head,reversed);
    }

    // just merge two linked list
    private ListNode mergeLinkListNoOrder(ListNode one,
                                          ListNode two){
        if(one == null || two == null)
            return one == null ? two : one;
        ListNode dummy = new ListNode(1);
        ListNode curr = dummy;
        while(one != null && two != null){
            curr.next = one;
            // once curr connect to the one, the
            // pointer one is useless for current one
            one = one.next;
            // connect curr to two
            curr.next.next = two;
            // same as one, once connect, should
            // move forward
            two = two.next;
            curr = curr.next.next;
        }
        if(one != null) curr.next = one;
        else curr.next = two;
        return dummy.next;
    }

    public ListNode LinkListPartition(ListNode head, int target){
        // corner case 1: if head is null return null
        // corner case 2: if only one node, no need partition
        if(head == null || head.next == null ) return head;
        // two dummy head bc two new linked list needed
        ListNode dummySmall = new ListNode(0);
        ListNode dummyLarge = new ListNode(0);
        // two helper pointers for iter dummy linked list
        ListNode smallTail = dummySmall;
        ListNode largeTail = dummyLarge;
        // third helper pointer for iter the head.
        ListNode curr = head;
        while(curr != null){
            // case 1: if node value is smaller than target
            // move this node into small dummy
            if(curr.value < target){
                smallTail.next = curr;
                smallTail = smallTail.next;
            }
            // case 2: if node value is larger than target
            // move it into bigger dummy
            else {
                largeTail.next = curr;
                largeTail = largeTail.next;
            }
            // iter head
            curr = curr.next;
        }
        // post processing: connecting two linked list
        smallTail.next = dummyLarge.next;
        largeTail.next = null;
        return dummySmall.next;
    }

    public ListNode MergeSortLinkedList(ListNode head){
        // corner case 1: if head is null return null
        // base case : if only one node, no need sort
        // but return
        if(head == null || head.next == null ) return head;
        // middle cut
        ListNode middleNode = middleNode(head);
        ListNode leftHead = head;
        ListNode rightHead = middleNode.next;
        middleNode.next = null;
        // merge sort one the left and right recursively,
        // and get the sorted linked list
        ListNode sortedLeft = MergeSortLinkedList(leftHead);
        ListNode sortedRight = MergeSortLinkedList(rightHead);
        // merge two sorted linked list in order to
        // get one long sorted linked list
        return mergeLinkedList(sortedLeft,sortedRight);
    }

    public ListNode addTwo(ListNode l1, ListNode l2){
        // corner case: if any of them is null, return other
        // one even if both is null, then return null
        if(l1 == null || l2 == null) return l1==null?l1:l2;
        // pre processing: if the length is different have to make
        // them same length. in order to do so have to extend the
        // shorter linked list
        if(checkLength(l1) < checkLength(l2))
            extendLinkedList(l1, checkLength(l2)-checkLength(l1));
        else
            extendLinkedList(l2, checkLength(l1)-checkLength(l2));
        // helper pointer for iter l1 and l2
        ListNode l1_curr = l1;
        ListNode l2_curr = l2;
        // a dummy head for out put
        ListNode dummySumHead = new ListNode(0);
        // helper pointer for iter the curr
        ListNode curr = dummySumHead;
        // if sum is over 10, carry over is true
        boolean carryOver = false;
        while(l1_curr != null && l2_curr != null){
            // sum the two current number
            int sum = l1_curr.value + l2_curr.value;
            // if carry over is 1, then sum have to add one too
            sum = carryOver ? sum+1 : sum;
            // if sum bigger than 10, then have to
            // set carry over as 1, and assign a node to curr.next
            if(sum >= 10){
                carryOver = true;
                curr.next = new ListNode(sum % 10);
            }
            else {
                carryOver = false;
                curr.next = new ListNode(sum);
            }
            // iter three pointer
            l1_curr = l1_curr.next;
            l2_curr = l2_curr.next;
            curr = curr.next;
        }
        // if the last l1 and last l2 used, but still
        // have a carry over, have to creat new node for it
        if(l1_curr == null && l2_curr == null && carryOver)
            curr.next = new ListNode(1);
        return dummySumHead.next;
    }

    private int checkLength(ListNode head){
        int index = 0;
        ListNode curr = head;
        while(curr != null){
            index ++;
            curr = curr.next;
        }
        return index;
    }

    private ListNode extendLinkedList(ListNode head,
                                      int target){
        ListNode curr = head;
        while(curr.next != null)  curr = curr.next;
        for(int i =0;i < target ; i++){
            ListNode newNode = new ListNode(0);
            curr.next = newNode;
            curr = curr.next;
        }
        return head;
    }

    public boolean LinkedListIsPalindrome(ListNode head){
        // corner case: if the head is null or only one
        // node, no need to palindrome, directly return
        if(head == null || head.next == null) return true;
        // length calculation for case 1 or case 2 but
        // length calculation have to be here because once
        // break the list, then the length of head changes
        int length = checkLength(head);
        // find the middle point
        ListNode middle = middleNode(head);
        // using helper pointers indicate two linked list
        ListNode firstPart = head;
        ListNode secondPart = middle.next;
        // cut middle
        middle.next = null;
        // case 2 only: if the linked list is odd number
        // the middle point have to be abandoned.
        // so find the node before the middle node, and
        // delete it by next = null;
        if(length % 2 == 1){
            ListNode middleBefore = head;
            while(middleBefore != null){
                if(middleBefore.next == middle){
                    middleBefore.next = null;
                    break;
                }
                middleBefore = middleBefore.next;
            }
        }
        // reverse second part
        secondPart = reverseLinkedList(secondPart);
        // check if the second part and first part is the
        // same or not, if it is then palindrome.
        return checkTwoLinkedListEqual(firstPart, secondPart);
    }

    public boolean checkTwoLinkedListEqual(ListNode one,
                                           ListNode two){
        if(checkLength(one) != checkLength(two))
            return false;
        ListNode onePointer = one;
        ListNode twoPointer = two;
        while(onePointer != null && twoPointer != null){
            if(onePointer.value != twoPointer.value)
                return false;
            onePointer = onePointer.next;
            twoPointer = twoPointer.next;
        }
        return true;
    }

    public ListNode removeElementInLinkedList(ListNode head,
                                              int target){
        // corner case: if list is null, then return null
        if(head == null) return head;
        // corner case: if only one element, and it is
        // target, return null
        if(head.value == target && head.next == null)
            return null;
        // case 1: if head is target, so move head
        // forward one.
        if(head.value == target) head = head.next;
        // dummy head for output
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        while(head != null){
            // if current head is not target, then
            // attach it to dummy
            if(head.value != target){
                curr.next = head;
                curr = curr.next; }
            head = head.next; }
        // if finish the iter but left last one as
        // target, then remove it
        if(curr.next !=null && curr.next.value == target)
            curr.next = null;
        return dummyHead.next;
    }
}

