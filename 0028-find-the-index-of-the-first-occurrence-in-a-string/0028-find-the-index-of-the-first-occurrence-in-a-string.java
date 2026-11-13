class Solution {
    public int strStr(String haystack, String needle) {
        String check = needle;
        int end =  haystack.length()-needle.length();
        
        for(int i = 0; i<=end; i++){
            int j = i+needle.length();
            if(check.equals(haystack.substring(i,j))) return i;
        }
        return -1;
    }
}