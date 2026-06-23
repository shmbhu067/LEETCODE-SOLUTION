class Solution {
    public int lengthOfLongestSubstring(String s) {
        int windowStart = 0;
        int maxLen = 0;
        int n = s.length();

        HashMap<Character, Integer> map = new HashMap<>();

        for (int windowEnd = 0; windowEnd < n; windowEnd++) {
            char ch = s.charAt(windowEnd);

            if (map.containsKey(ch) && map.get(ch) >= windowStart) {
                windowStart = map.get(ch) + 1;
            }

            map.put(ch, windowEnd);
            maxLen = Math.max(maxLen, windowEnd - windowStart + 1);
        }

        return maxLen;
    }
}
