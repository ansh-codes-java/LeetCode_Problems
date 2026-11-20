class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        Stack<Integer> st = new Stack<>();
        for (int i = n-1; i >= 0; i--) {
            st.push(nums[i]);
        }
        int nge[] = new int[n];
        for(int i = n-1 ; i >= 0 ; i--){
            while(st.size() >0 && st.peek() <= nums[i]) st.pop();
            if(st.size() == 0)  st.push(-1);     // nge[i] = (st.size() == 0) ? -1 : st.peel(); combined of two lines
            nge[i] = st.peek();
            st.push(nums[i]);
        }
        return nge;
    }
}