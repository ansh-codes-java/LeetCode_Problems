class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums.length == 0) return new int[]{-1,-1};

        int lb = nums.length;
        int ub = nums.length;

        int low = 0;
        int high = nums.length-1;
        while(low<=high){
            int mid = (low+(high-low)/2);
            if(nums[mid]>=target){
                lb = mid;
                high=mid-1;
            }
            else low = mid+1;
        }

        low = 0;
        high = nums.length-1;
        while(low<=high){
            int mid = (low+(high-low)/2);
            if(nums[mid]>target){
                ub = mid;
                high = mid-1;
            }
            else low = mid+1;
        }
        if(lb==ub) return new int[]{-1,-1};
        return new int[]{lb,ub-1};
    }
  
}