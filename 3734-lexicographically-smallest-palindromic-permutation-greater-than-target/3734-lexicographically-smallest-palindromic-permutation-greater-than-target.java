class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length(), m = n / 2;
        int[] cnt = new int[26];

        for (char ch : s.toCharArray())
            cnt[ch - 'a']++;

        int odd = 0, mid = -1;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2 == 1) {
                odd++;
                mid = i;
            }
        }

        if (odd > 1) return "";

        // We need the smallest half > target's first half
        char[] h = new char[m];

        for (int i = 0; i < m; i++) {
            int x = target.charAt(i) - 'a';

            if (cnt[x] > 1) {
                h[i] = target.charAt(i);
                cnt[x] -= 2;
            } else {
                // Try to make this position larger
                int j = x + 1;
                while (j < 26 && cnt[j] < 2) j++;

                if (j == 26) {
                    // Backtrack
                    int p = i - 1;
                    while (p >= 0) {
                        int old = h[p] - 'a';
                        cnt[old] += 2;

                        j = old + 1;
                        while (j < 26 && cnt[j] < 2) j++;

                        if (j < 26) {
                            h[p] = (char)('a' + j);
                            cnt[j] -= 2;

                            for (int k = p + 1; k < m; k++) {
                                for (j = 0; j < 26; j++)
                                    if (cnt[j] >= 2) break;

                                h[k] = (char)('a' + j);
                                cnt[j] -= 2;
                            }

                            return make(h, mid);
                        }

                        p--;
                    }
                    return "";
                }

                h[i] = (char)('a' + j);
                cnt[j] -= 2;

                for (int k = i + 1; k < m; k++) {
                    for (j = 0; j < 26; j++)
                        if (cnt[j] >= 2) break;

                    h[k] = (char)('a' + j);
                    cnt[j] -= 2;
                }

                return make(h, mid);
            }
        }

        // Half == target half, so palindrome may still be <= target.
        String ans = make(h, mid);

        if (ans.compareTo(target) > 0)
            return ans;

        // Find next permutation of half
        int i = m - 2;
        while (i >= 0 && h[i] >= h[i + 1]) i--;

        while (i >= 0) {
            int j = m - 1;
            while (h[j] <= h[i]) j--;

            char t = h[i];
            h[i] = h[j];
            h[j] = t;

            for (int l = i + 1, r = m - 1; l < r; l++, r--) {
                t = h[l];
                h[l] = h[r];
                h[r] = t;
            }

            return make(h, mid);
        }

        return "";
    }

    String make(char[] h, int mid) {
        StringBuilder sb = new StringBuilder();

        for (char c : h) sb.append(c);
        if (mid != -1) sb.append((char)('a' + mid));
        for (int i = h.length - 1; i >= 0; i--)
            sb.append(h[i]);

        return sb.toString();
    }
}