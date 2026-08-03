class Solution:
    def stoneGameIII(self, stoneValue: List[int]) -> str:
        n = len(stoneValue)

        @lru_cache(None)
        def dfs(i):
            if i >= n:
                return 0

            best = float('-inf')
            curr_sum = 0

            for k in range(3):
                if i + k < n:
                    curr_sum += stoneValue[i + k]
                    best = max(best, curr_sum - dfs(i + k + 1))

            return best

        diff = dfs(0)

        if diff > 0:
            return "Alice"
        elif diff < 0:
            return "Bob"
        return "Tie"