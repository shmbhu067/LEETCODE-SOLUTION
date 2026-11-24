class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 1;

        for (int i = 0; i < n; i++) {
            long product = 1;
            long g = 0;
            long l = 1;

            for (int j = i; j < n; j++) {
                int x = nums[j];

                // Product would overflow
                if (product > Long.MAX_VALUE / x) {
                    break;
                }

                product *= x;
                g = gcd(g, x);

                long common = gcd(l, x);
                l = (l / common) * x;

                if (product == g * l) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}