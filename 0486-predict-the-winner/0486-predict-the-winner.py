class Solution(object):
    def predictTheWinner(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        N = len(nums)

        def f(left,right):
            if left > right:
                return 0
            choose_left = nums[left] - f(left+1,right)
            choose_right = nums[right] - f(left,right-1)

            return max(choose_left, choose_right)

        return f(0, N-1)>=0

        