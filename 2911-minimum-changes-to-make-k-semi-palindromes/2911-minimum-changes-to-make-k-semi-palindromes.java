

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();

        // factors[len] = all proper divisors of len
        List<Integer>[] factors = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            factors[i] = new ArrayList<>();
        }

        // d must be a proper divisor, so d < len
        for (int d = 1; d < n; d++) {
            for (int len = d * 2; len <= n; len += d) {
                factors[len].add(d);
            }
        }

        /*
         * cost[l][r] =
         * minimum changes needed to make s[l..r]
         * a semi-palindrome.
         */
        int[][] cost = new int[n][n];

        for (int l = 0; l < n; l++) {
            for (int r = l + 1; r < n; r++) {

                int len = r - l + 1;
                int best = Integer.MAX_VALUE;

                for (int d : factors[len]) {
                    int changes = 0;

                    // There are d groups.
                    for (int offset = 0; offset < d; offset++) {

                        int left = l + offset;
                        int right = r - d + 1 + offset;

                        // Compare symmetric elements
                        // inside this group.
                        while (left < right) {

                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    best = Math.min(best, changes);
                }

                cost[l][r] = best;
            }
        }

        /*
         * dp[i][parts] =
         * minimum cost to split s[i..n-1]
         * into 'parts' semi-palindromes.
         */
        int INF = 1_000_000;

        int[][] dp = new int[n + 1][k + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        // Empty suffix with 0 parts costs 0.
        dp[n][0] = 0;

        for (int i = n - 1; i >= 0; i--) {

            for (int parts = 1; parts <= k; parts++) {

                // end is the last index of current substring.
                for (int end = i + 1; end < n; end++) {

                    if (dp[end + 1][parts - 1] == INF) {
                        continue;
                    }

                    dp[i][parts] = Math.min(
                        dp[i][parts],
                        cost[i][end] + dp[end + 1][parts - 1]
                    );
                }
            }
        }

        return dp[0][k];
    }
}