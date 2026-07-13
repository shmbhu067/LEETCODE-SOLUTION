class Solution(object):
    def sequentialDigits(self, low, high):
        """
        :type low: int
        :type high: int
        :rtype: List[int]
        """
        ans = []

        for start in range(1,10):
            num = 0
            for digit in range(start,10):
                num = num*10 + digit

                if low<= num <= high:
                    ans.append(num)
        ans.sort()
        return ans