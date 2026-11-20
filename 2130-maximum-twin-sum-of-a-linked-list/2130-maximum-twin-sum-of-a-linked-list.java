/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode fwd = null; // forward

        while(curr != null){
            fwd = curr.next;
            curr.next = prev;
            prev = curr;
            curr = fwd;
        }
        return prev;
    }

     int pairSum(ListNode head) {

        if (head == null || head.next == null)
            return 0;

        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode a = slow.next;
        slow.next = null;

        a = reverseList(a);

        ListNode t1 = head;
        ListNode t2 = a;
        int max = Integer.MIN_VALUE;
        while (t2 != null) {
        int fir = t1.val;
        int sec = t2.val;
            if (fir + sec > max) max = fir + sec;
            t1 = t1.next;
            t2 = t2.next;
        }
        return max;
    }
}