class Solution(object):
    def stoneGameII(self, piles):
        n = len(piles)

        suffix = [0]*(n+1)

        for i in range(n-1, -1, -1):
            suffix[i] = suffix[i+1] + piles[i]
        
        memo = {}
        
        def dp(i,M):
            # taking everything
            if i+2* M >= n:
                return suffix[i]
            
            if (i,M) in memo:
                return memo[(i,M)]
            
            ans = 0

            for X in range(1 ,2*M+1):
                opponent = dp(i+X, max(M,X))

                current = suffix[i] - opponent

                ans = max(ans,current)

            memo[(i,M)] = ans
            return ans
        return dp (0,1)

        
        """
        :type piles: List[int]
        :rtype: int
        """
        