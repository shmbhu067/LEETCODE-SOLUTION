class Solution {
    public int minMoves(int[] nums) {
        int maxVal = Integer.MIN_VALUE;
        int sum = 0;
        for(int num : nums){
            if(num > maxVal){
                maxVal = num;
            }
        }
        int moves = 0;
        for(int num : nums){
            maxVal = Math.max(maxVal,num);
            sum += num;
             
        }

        return (nums.length*maxVal ) - sum;
    }
}