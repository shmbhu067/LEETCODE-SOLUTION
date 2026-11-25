import java.util.Arrays;

class Solution {
    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 1_000_000_009L;
    private static final long BASE1 = 100_003L;
    private static final long BASE2 = 100_019L;

    private long[] pref1, pref2, pow1, pow2;
    private int n;

    public int smallestUniqueSubarray(int[] nums) {
        n = nums.length;
        pref1 = new long[n + 1];
        pref2 = new long[n + 1];
        pow1 = new long[n + 1];
        pow2 = new long[n + 1];

        pow1[0] = 1;
        pow2[0] = 1;
        for (int i = 0; i < n; i++) {
            pref1[i + 1] = (pref1[i] * BASE1 + nums[i]) % MOD1;
            pref2[i + 1] = (pref2[i] * BASE2 + nums[i]) % MOD2;
            pow1[i + 1] = (pow1[i] * BASE1) % MOD1;
            pow2[i + 1] = (pow2[i] * BASE2) % MOD2;
        }

        int low = 1, high = n;
        int ans = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (hasUniqueSubarray(mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean hasUniqueSubarray(int len) {
        int count = n - len + 1;
        long[] hashes = new long[count];

        for (int i = 0; i < count; i++) {
            long h1 = (pref1[i + len] - (pref1[i] * pow1[len]) % MOD1 + MOD1) % MOD1;
            long h2 = (pref2[i + len] - (pref2[i] * pow2[len]) % MOD2 + MOD2) % MOD2;
            hashes[i] = (h1 << 32) | h2;
        }

        Arrays.sort(hashes);

        for (int i = 0; i < count; i++) {
            boolean sameAsPrev = (i > 0 && hashes[i] == hashes[i - 1]);
            boolean sameAsNext = (i < count - 1 && hashes[i] == hashes[i + 1]);
            if (!sameAsPrev && !sameAsNext) {
                return true;
            }
        }

        return false;
    }
}