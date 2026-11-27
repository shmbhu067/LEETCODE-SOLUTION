class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int maxFactor = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }

        // Count frequency of each factor
        int[] freq = new int[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

        // Count how many items have a factor that is a multiple of f
        int[] multipleCount = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            for (int m = f; m <= maxFactor; m += f) {
                multipleCount[f] += freq[m];
            }
        }

        // 0/1 Knapsack: buying the first copy of each item
        int[] dp = new int[budget + 1];
        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            // 1 purchased copy + (multipleCount[factor] - 1) free copies = multipleCount[factor]
            int value = multipleCount[factor];

            for (int c = budget; c >= price; c--) {
                dp[c] = Math.max(dp[c], dp[c - price] + value);
            }
        }

        // Unbounded pass: spend any leftover budget on the cheapest item (1 copy per minPrice)
        for (int c = minPrice; c <= budget; c++) {
            dp[c] = Math.max(dp[c], dp[c - minPrice] + 1);
        }

        return dp[budget];
    }
}