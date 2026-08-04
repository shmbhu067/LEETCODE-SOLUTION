class Solution(object):
    def findMissingElements(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        s = set(nums)

        low = min(nums)
        high = max(nums)

        ans = []

        while low <= high:
            if low not in s:
                ans.append(low)
            low += 1
        return ans
        