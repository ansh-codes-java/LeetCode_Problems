class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int sum = Integer.MAX_VALUE;
        for(int i =0; i<nums.size(); i++){
            int currSum = 0;
            for(int j=i; j<=i+r-1 && j<nums.size(); j++){
                currSum+=nums.get(j);
                if(currSum>0 && j>=i+l-1 && j<=i+r-1)sum=Math.min(currSum,sum);
            }
        }
        return sum == Integer.MAX_VALUE ? -1 : sum;
    }
}