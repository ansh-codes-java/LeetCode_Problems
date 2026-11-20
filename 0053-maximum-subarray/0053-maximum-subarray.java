class Solution {
    public int maxSubArray(int[] nums) {
        int currsum = 0;
        int maxsum = Integer.MIN_VALUE;
        int i = 0;
        while(i<nums.length){
            currsum += nums[i++];
            maxsum = Math.max(currsum,maxsum);
            if(currsum < 0) currsum = 0;
        }
        return maxsum;
    }
}