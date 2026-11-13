class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        long cost = 0;
        int n = nums.length;
        long ans = 0;

        Deque<Integer> dq = new ArrayDeque<>();

        int right = n-1;

        for(int left = n - 1; left >=0 ; left--){
            int x = nums[left];

            while(!dq.isEmpty() && nums[dq.peekLast()] < x){
                int idx = dq.pollLast();

                int end = dq.isEmpty() ? right+1 : dq.peekLast();
                cost += (long) (end - idx) * (x-nums[idx]);
            }
            dq.offerLast(left);
            while(cost > k){
                cost -= (long) nums[dq.peekFirst()] - nums[right];

                if(dq.peekFirst() == right){
                    dq.pollFirst();
                }
                right--;
            }
            ans += right - left + 1L;

        }
        return ans;
    }
}