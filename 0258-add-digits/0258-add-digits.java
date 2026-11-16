class Solution {
    public int addDigits(int num) {
        // while(num>9){
        //     int sum = 0;
        //     while(num!=0){
        //         sum+=num%10;
        //         num=num/10;
        //     }
        //     num=sum;
        // }
        // return num;
        // Digital Root Formula
        if(num == 0) return 0;

        return 1+(num-1)%9;
    }
}