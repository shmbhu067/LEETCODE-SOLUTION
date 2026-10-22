class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if(n <= 1) return 0;

        boolean isAsc = true;
        boolean isDesc = true;

        int p = -1;

        for(int i=0; i <n ; i++){
            if(nums[i] ==0) p=i;
            int nxt = nums[(i+1) % n];
            if(nxt != (nums[i] + 1) % n) isAsc  = false;
            if(nxt != (nums[i] -  1 + n)%n) isDesc = false;
        }
        if(!isAsc && !isDesc) return-1;

        int ans = Integer.MAX_VALUE;

        if(isAsc){
            ans = Math.min(ans,Math.min(p,n-p+2));
        }
        if(isDesc){
            ans = Math.min(ans, Math.min((p+1) % n+1,  n-p));
        }
        return ans;
    }
}