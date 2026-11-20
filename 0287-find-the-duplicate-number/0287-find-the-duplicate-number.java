class Solution {
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int i = 0;
        int[] arr = new int[n];
        for( i = 0; i<arr.length; i++){
            arr[i] = nums[i];
        }
        i = 0;
        while(i < arr.length){
            if(arr[i] == i+1 || arr[i] == arr[arr[i]-1]) i++;
            else{
                int idx = arr[i]-1;
                int temp = arr[i];
                arr[i] = arr[idx];
                arr[idx] = temp;
            }
        }
        int ans = 0;
        for(i = 0;i<arr.length;i++){
            if(arr[i] != i+1) ans=arr[i];
        }  
        return ans;  
    }
}