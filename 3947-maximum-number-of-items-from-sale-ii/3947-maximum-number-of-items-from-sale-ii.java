import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;

        // Sort by price
        Arrays.sort(items, (a, b) -> Integer.compare(a[1], b[1]));

        // Cheapest item
        int minPrice = items[0][1];

        // Maximum factor
        int maxFactor = 0;
        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
        }

        // freq[f] = number of items having factor f
        int[] freq = new int[maxFactor + 1];

        for (int[] item : items) {
            freq[item[0]]++;
        }

        // Count how many items have factors divisible by f
        for (int f = 1; f <= maxFactor; f++) {

            if (freq[f] == 0)
                continue;

            for (int multiple = f * 2;
                 multiple <= maxFactor;
                 multiple += f) {

                freq[f] += freq[multiple];
            }
        }

        long ans = 0;
        long remaining = budget;

        /*
         * For each item:
         *
         * gain = number of free copies it can unlock.
         *
         * First 'gain' purchased copies have value 2.
         */
        for (int[] item : items) {

            int factor = item[0];
            int price = item[1];

            // This bonus copy is not better than
            // simply buying two cheapest ordinary copies.
            if ((long) price >= 2L * minPrice) {
                break;
            }

            int gain = freq[factor] - 1;

            if (gain <= 0)
                continue;

            // Number of bonus copies we can afford
            long take = Math.min(
                (long) gain,
                remaining / price
            );

            ans += 2L * take;
            remaining -= take * price;
        }

        // Spend whatever remains on the cheapest item.
        ans += remaining / minPrice;

        return (int) ans;
    }
}