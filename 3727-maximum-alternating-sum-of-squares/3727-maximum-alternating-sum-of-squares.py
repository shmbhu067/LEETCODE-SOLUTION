class Solution:
    def maxAlternatingSum(self, nums: List[int]) -> int:
        squares = [x*x for x in nums]
        squares.sort()

        n = len(squares)

        neg_count = n//2

        neg_sum = sum(squares[:neg_count])
        pos_sum = sum(squares[neg_count:])
        return pos_sum - neg_sum