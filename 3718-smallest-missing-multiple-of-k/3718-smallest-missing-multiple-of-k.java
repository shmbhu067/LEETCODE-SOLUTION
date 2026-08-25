class Solution {
    public int missingMultiple(int[] nums, int k) {
        boolean[] present = new boolean[nums.length + 1];

        for (int num : nums) {
            if (num > 0 && num % k == 0) {
                int x = num / k;

                if (x < present.length) {
                    present[x] = true;
                }
            }
        }

        for (int i = 1; i < present.length; i++) {
            if (!present[i]) {
                return i * k;
            }
        }

        return (nums.length + 1) * k;
    }
}