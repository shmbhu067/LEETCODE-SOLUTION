class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        int ans = 1;

        // dp0 = longest sequence ending at current value
        // dp1 = longest sequence ending at current value + 1
        int dp0 = 1;
        int dp1 = 1;

        for (int i = 1; i < n; i++) {
            int diff = nums[i] - nums[i - 1];

            if (diff == 0) {
                // Current number can become previous + 1
                dp1 = dp0 + 1;

            } else if (diff == 1) {
                // Current number can directly continue dp0
                // Or increase it by 1 and continue dp1
                dp0++;
                dp1++;

            } else if (diff == 2) {
                // Previous number can be increased by 1
                // to become current number
                dp0 = dp1 + 1;
                dp1 = 1;

            } else {
                // Gap too large -> start new sequences
                dp0 = 1;
                dp1 = 1;
            }

            ans = Math.max(ans, Math.max(dp0, dp1));
        }

        return ans;
    }
}