class Solution {
     public int calPoints(String[] operations) {
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < operations.length; i++) {
            String s = operations[i];
            if(s.equals("C")) st.pop();
           else if(s.equals("D") ) st.push(2*st.peek());
           else if(s.equals("+")){
                int a = st.pop();
                int b = st.peek();
                st.push(a);
                st.push(a+b);
            }
            else st.push(Integer.parseInt(s));
        }
        int size = 0;
        while(st.size() != 0){
             size += st.pop();
        }
        return size;
    }
}