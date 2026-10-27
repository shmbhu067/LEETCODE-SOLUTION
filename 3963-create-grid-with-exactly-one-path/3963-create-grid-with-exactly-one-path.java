import java.util.*;

class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];

        // Fill entire grid with '#'
        for (int i = 0; i < m; i++) {
            Arrays.fill(grid[i], '#');
        }

        // Top row from col 0 to n - 1
        for (int c = 0; c < n; c++) {
            grid[0][c] = '.';
        }

        // Last column from row 0 to m - 1
        for (int r = 0; r < m; r++) {
            grid[r][n - 1] = '.';
        }

        String[] result = new String[m];
        for (int i = 0; i < m; i++) {
            result[i] = new String(grid[i]);
        }
        return result;
    }
}