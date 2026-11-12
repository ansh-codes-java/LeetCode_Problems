class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        if(nums.length == 1) return 1;
        int inccnt = 1;
        int deccnt = 1;
        int inc = 0;
        int dec = 0;
        for(int i = 0; i<nums.length-1; i++){
            if(nums[i]<nums[i+1]){
                inccnt++;
                deccnt = 1;
            } 
            else if(nums[i]>nums[i+1]){
                deccnt++;
                inccnt = 1;
            }
            else{
                inccnt = 1;
                deccnt = 1;
            }
            inc = Math.max(inc,inccnt);
            dec = Math.max(dec,deccnt);
        }
        return Math.max(inc,dec);
    }
}