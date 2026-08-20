class Solution {
    public int findComplement(int nums) {
        int mask =0;
        int temp = nums;

        while (temp > 0){
            mask = (mask << 1) | 1;
            temp >>=1;
        }
        return nums ^ mask;
 }
}