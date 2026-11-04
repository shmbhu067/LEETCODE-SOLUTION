import heapq
import collections
from typing import List

class Solution:
    def minOperations(self, nums: List[int], x: int, k: int) -> int:
        n = len(nums)
        
        # Array to store the minimum cost to make each window of size x equal
        costs = [0] * (n - x + 1)
        
        # Max-heap for the smaller half, Min-heap for the larger half
        lo, hi = [], []
        lo_sum, hi_sum = 0, 0
        lo_sz, hi_sz = 0, 0
        del_lo = collections.defaultdict(int)
        del_hi = collections.defaultdict(int)
        
        def balance():
            nonlocal lo_sum, hi_sum, lo_sz, hi_sz
            while lo_sz > hi_sz + 1:
                while lo and del_lo[-lo[0]] > 0:
                    del_lo[-heapq.heappop(lo)] -= 1
                val = -heapq.heappop(lo)
                lo_sum -= val
                lo_sz -= 1
                heapq.heappush(hi, val)
                hi_sum += val
                hi_sz += 1
                
            while hi_sz > lo_sz:
                while hi and del_hi[hi[0]] > 0:
                    del_hi[heapq.heappop(hi)] -= 1
                val = heapq.heappop(hi)
                hi_sum -= val
                hi_sz -= 1
                heapq.heappush(lo, -val)
                lo_sum += val
                lo_sz += 1
                
        def add(val):
            nonlocal lo_sum, hi_sum, lo_sz, hi_sz
            while lo and del_lo[-lo[0]] > 0:
                del_lo[-heapq.heappop(lo)] -= 1
            
            if lo and val <= -lo[0]:
                heapq.heappush(lo, -val)
                lo_sum += val
                lo_sz += 1
            else:
                heapq.heappush(hi, val)
                hi_sum += val
                hi_sz += 1
            balance()
            
        def remove(val):
            nonlocal lo_sum, hi_sum, lo_sz, hi_sz
            if lo and val <= -lo[0]:
                del_lo[val] += 1
                lo_sum -= val
                lo_sz -= 1
            else:
                del_hi[val] += 1
                hi_sum -= val
                hi_sz -= 1
            balance()
            
        def get_cost():
            while lo and del_lo[-lo[0]] > 0:
                del_lo[-heapq.heappop(lo)] -= 1
            m = -lo[0]
            if x % 2 != 0:
                return hi_sum - lo_sum + m
            else:
                return hi_sum - lo_sum

        for i in range(x):
            add(nums[i])
            
        costs[0] = get_cost()
        for i in range(x, n):
            remove(nums[i - x])
            add(nums[i])
            costs[i - x + 1] = get_cost()
            
        dp = [0] * (n + 1)
        
        for j in range(1, k + 1):
            new_dp = [float('inf')] * (n + 1)
            min_prev = float('inf')
            
            for i in range(j * x, n + 1):
                min_prev = min(min_prev, dp[i - x])
                new_dp[i] = min(new_dp[i - 1], min_prev + costs[i - x])
                
            dp = new_dp
            
        return dp[n]