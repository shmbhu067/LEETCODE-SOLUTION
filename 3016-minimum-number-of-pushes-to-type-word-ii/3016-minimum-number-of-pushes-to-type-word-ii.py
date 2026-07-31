
class Solution(object):
    def minimumPushes(self, word):
        """
        :type word: str
        :rtype: int
        """
        freq = Counter(word)

        frequencies = sorted(freq.values(), reverse = True)

        ans = 0 

        for i, f in enumerate(frequencies):
            ans += f*((i//8) + 1)
        return ans
        