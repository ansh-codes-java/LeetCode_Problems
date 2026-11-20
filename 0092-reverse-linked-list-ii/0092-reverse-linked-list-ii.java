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
    public static ListNode reverse(ListNode head){

        ListNode prev = null;
        ListNode curr = head;
        ListNode fwd = null;

        while(curr != null){
            fwd = curr.next;
            curr.next = prev;
            prev = curr;
            curr = fwd;
        }

        return prev;
    }
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode a = dummy;
        for(int i = 1 ; i<=left-1 ; i++){
            a = a.next;
        }
        ListNode b = a.next;
        ListNode c = dummy;
        for (int i = 1; i <=right ; i++) {
            c = c.next;
        }
        ListNode d = c.next;
        a.next = null;
        c.next = null;
        reverse(b);
        a.next = c;
        b.next = d;

        return dummy.next;
    }
}