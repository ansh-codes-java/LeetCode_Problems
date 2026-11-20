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
    public ListNode removeNodes(ListNode head) {
        Stack<ListNode> st = new Stack<>();
        ListNode temp = head;
        while(temp != null){
            while(st.size() != 0 && st.peek().val < temp.val) st.pop();
            st.push(temp);
            temp = temp.next;
        }
        ListNode newhead = st.pop();
        temp = newhead;
        while(st.size()>0){
            ListNode top = st.pop();
            temp.next = top;
            temp = top;
        }
        temp.next =null;
        return reverse(newhead);
    }
    private ListNode reverse(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        ListNode fwd = head;
        while(curr!=null){
            fwd = curr.next;
            curr.next = prev;
            prev = curr;
            curr = fwd;
        }
        return prev;
    }
}