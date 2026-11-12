class Solution {
    public String reverseStr(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        for(int i = 0; i<sb.length(); i=i+2*k){
            int j = i;
            int end = Math.min(j+k-1,sb.length()-1);
            while(j<end){
                char temp = sb.charAt(j);
                sb.setCharAt(j,sb.charAt(end));
                sb.setCharAt(end,temp);
                j++;
                end--;
            }
        }
        return sb.toString();
    }
}