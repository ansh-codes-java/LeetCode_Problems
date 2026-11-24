class Solution {
    public int gcd(int a, int b){
        while(b!=0){
            int temp = b;
             b = a%b;
            a = temp;
            
        }
        return a;
    }
    public int lcm(int a, int b){
        return a*b/(gcd(a,b));
    }
   
    public int maxLength(int[] nums) {
        int maxLen = 0;
        for(int i = 0; i<nums.length; i++){
            int prod = 1;
            int GCD = 0;
            int LCM = 1;
            for(int j = i; j<nums.length; j++){
                prod*=nums[j];
                GCD=gcd(GCD,nums[j]);
                LCM = lcm(LCM,nums[j]);
                
                if(prod == LCM*GCD) maxLen= Math.max(maxLen,(j-i+1));
            }
        }
        return maxLen;
    }
}