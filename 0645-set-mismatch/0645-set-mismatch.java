class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int i = 0;
        int[] ans = new int[2];
        while(i < nums.length){
            if(nums[i] == i+1 || nums[i] == nums[nums[i]-1]) i++;
            else{
                int idx = nums[i]-1;
                int temp = nums[i];
                nums[i] = nums[idx];
                nums[idx] = temp;
            }
        }
        for(i = 0 ; i < nums.length;i++){
            if(nums[i] != i+1){
                ans[0] = nums[i];
                ans[1] = i+1;
            }
        }
        return ans;
    }
}