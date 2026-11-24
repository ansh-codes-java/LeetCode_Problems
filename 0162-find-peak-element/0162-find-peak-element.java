class Solution {
    public int findPeakElement(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        int ans  = 0;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (mid != nums.length-1 && nums[mid] < nums[mid + 1]) {
                lo = mid + 1;
            } else {
                ans = mid;
                hi = mid-1;
            }
        }

        return ans;
        // int idx = 0;
        // int num = nums[0];
        // for(int i = 0; i<nums.length; i++){
        //     if(nums[i] > num){ 
        //         num = nums[i];
        //         idx = i;
        //     }
        // }
        // return idx;
    }
}