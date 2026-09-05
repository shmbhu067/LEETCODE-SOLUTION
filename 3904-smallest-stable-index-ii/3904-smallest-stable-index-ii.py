class Solution(object):
    def firstStableIndex(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: int
        """
        n = len(nums)

        suffixMin = [0]*n
        suffixMin[n-1] = nums[n-1]

        for i in range(n-2,-1,-1):
            suffixMin[i] = min(nums[i], suffixMin[i+1])

        prefixMax = nums[0]

        for i in range (n):
            prefixMax = max(prefixMax,nums[i])

            instability = prefixMax - suffixMin[i]

            if instability <= k:
                return i
        return -1
        