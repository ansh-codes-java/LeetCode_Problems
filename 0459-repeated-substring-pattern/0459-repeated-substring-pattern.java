class Solution {
    public boolean repeatedSubstringPattern(String s) {
        
        String st = s + s;
        int n = st.length()-1;
        
        return st.substring(1,n).contains(s);
    }
}