class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] cnt = new int[3];

        for (int x : stones) {
            cnt[x % 3]++;
        }

        // If there are no stones with remainder 1 or 2,
        // Alice must take a 0-mod-3 stone and loses.
        if (cnt[1] == 0 && cnt[2] == 0) {
            return false;
        }

        // If the number of 0-mod-3 stones is even,
        // the game essentially depends on the balance
        // between remainder-1 and remainder-2 stones.
        if (cnt[0] % 2 == 0) {
            return cnt[1] > 0 && cnt[2] > 0;
        }

        // If cnt[0] is odd, Alice needs a sufficient imbalance
        // between remainder-1 and remainder-2 stones.
        return Math.abs(cnt[1] - cnt[2]) > 2;
    }
}