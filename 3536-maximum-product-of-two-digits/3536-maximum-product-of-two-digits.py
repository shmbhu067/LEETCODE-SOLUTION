class Solution(object):
    def maxProduct(self, n):
        digits = []

        while n > 0:
            digits.append(n % 10)
            n //= 10

        digits.sort()

        return digits[-1] * digits[-2]