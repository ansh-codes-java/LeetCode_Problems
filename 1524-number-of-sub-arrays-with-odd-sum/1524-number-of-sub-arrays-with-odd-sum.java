class Solution {
    public int numOfSubarrays(int[] arr) {
        int sum = 0;
        int evencnt = 1;
        int oddcnt = 0;
        int ans = 0;
        int mod = 1000000007;
        for(int i = 0; i<arr.length; i++){
            sum+=arr[i];
            if(sum%2==0){
                ans=(ans+oddcnt)%mod;
                evencnt++;
            }
            else{
                ans=(ans+evencnt)%mod;
                oddcnt++;
            }
        }
        ans = ans%mod;
        return ans;
    }
}