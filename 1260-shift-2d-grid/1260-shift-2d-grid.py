class Solution(object):
    def shiftGrid(self, grid, k):
        m = len(grid)
        n = len(grid[0])

        total = m * n
        k = k % total

        result = [[0] * n for _ in range(m)]

        for i in range(m):
            for j in range(n):
                index = i * n + j
                new_index = (index + k) % total

                new_row = new_index / n      # Integer division in Python 2
                new_col = new_index % n

                result[new_row][new_col] = grid[i][j]

        return result