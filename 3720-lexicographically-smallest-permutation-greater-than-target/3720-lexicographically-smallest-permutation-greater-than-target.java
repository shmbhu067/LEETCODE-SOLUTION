class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int[] cnt = new int[26];

        for (char c : s.toCharArray())
            cnt[c - 'a']++;

        char[] ans = target.toCharArray();
        int i = 0;

        // Match target as much as possible
        while (i < ans.length) {
            int x = ans[i] - 'a';

            if (cnt[x] == 0) break;

            cnt[x]--;
            i++;
        }

        // Try to make current position greater
        if (i < ans.length) {
            int x = ans[i] - 'a';

            for (int j = x + 1; j < 26; j++) {
                if (cnt[j] > 0) {
                    ans[i] = (char) ('a' + j);
                    cnt[j]--;
                    return build(ans, i + 1, cnt);
                }
            }
        }

        // Backtrack only through the matched prefix
        for (int j = i - 1; j >= 0; j--) {
            cnt[ans[j] - 'a']++;

            int x = target.charAt(j) - 'a';

            for (int k = x + 1; k < 26; k++) {
                if (cnt[k] > 0) {
                    ans[j] = (char) ('a' + k);
                    cnt[k]--;
                    return build(ans, j + 1, cnt);
                }
            }
        }

        return "";
    }

    private String build(char[] ans, int pos, int[] cnt) {
        for (int i = 0; i < 26; i++) {
            while (cnt[i] > 0) {
                ans[pos++] = (char) ('a' + i);
                cnt[i]--;
            }
        }
        return new String(ans);
    }
}