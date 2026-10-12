class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int MOD = 1_000_000_007;
        int[] bitCounts = new int[32];

        //Count the 1-bits at EVRY BIT position ACROSS ALL NUMBERSSSS
        for(int num: nums){
            for(int i=0;i<32;i++){
                if((num & (1<<i)) !=0){
                    bitCounts[i]++;
                }
            }
        }
        long totalSum = 0;
        /// WE need to pick k numbers
        for(int j=0;j<k;j++){
            long currentNum = 0;

            ///// Construct the LARGEST possible number USING the available bits
            for(int i=0; i<32; i++){
                if(bitCounts[i] > 0){
                    currentNum |= (1L << i);
                    bitCounts[i]--;
                }
            }
            // SQUARE  the number, apply modulo, and add to total sum
            long squared = (currentNum * currentNum)% MOD;
            totalSum = (totalSum + squared) % MOD;
        } 
        return (int) totalSum;
    }
}