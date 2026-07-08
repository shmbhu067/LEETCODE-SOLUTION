class Solution(object):
    def fib(self, n):
        dp = [-1] * (n+1)

        def dfs(n):
            if n <= 1:
                return n
            if dp[n] != -1:
                return dp[n]

            dp[n] = dfs(n-1) + dfs(n-2)
            return dp[n]
        return dfs(n)

    
        