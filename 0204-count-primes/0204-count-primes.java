class Solution {
    // public int countPrimes(int n) {
    //     int ans = 0;
    //     for(int i = 0 ; i < n ; i++){
    //         if(isprime(i)) ans++;
    //     }
    //     return ans;
    // }
    // public boolean isprime(int n){
    //     if(n <= 1) return false;
    //     for(int i = 2 ; i*i <= n;i++){
    //         if(n%i == 0) return false;
    //     }
    // return true;
    // }
    public int countPrimes(int n){
        if(n<=1) return 0;
        int[] s = Sieve(n);
        int count = 0;
        for(int i = 0 ; i<n ; i++){
           if(s[i] == 1)  count++;
        }
       return count;
    }
    int[] Sieve(int n){
            int[] arr = new int[n+1];
            Arrays.fill(arr,1);
            arr[0] = 0;
            arr[1] = 0;
            for(int i = 2; i <= Math.sqrt(n) ; i++) {
                if(arr[i]==1){
                    for (int j = i*i; j<= n; j+=i) {
                        arr[j]=0;
                    }
                }
            }
            return arr;
        }
}