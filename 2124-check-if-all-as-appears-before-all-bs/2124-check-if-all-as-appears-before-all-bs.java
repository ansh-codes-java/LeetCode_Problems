class Solution {
    public boolean checkString(String s) {
        Boolean bMilgya = false;
        for(char ele : s.toCharArray()){
            if(ele=='b'){
                bMilgya = true;
            }
            else if(ele=='a' && bMilgya) return false;
        }
        return true;
    }
}