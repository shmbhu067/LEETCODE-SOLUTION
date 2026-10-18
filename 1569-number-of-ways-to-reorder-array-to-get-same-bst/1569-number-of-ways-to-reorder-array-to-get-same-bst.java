class Solution {
    private static final long MOD = 1_000_000_007;
    private long[][] table;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        
        // Precompute combinations (nCr) using Pascal's Triangle
        table = new long[n][n];
        for (int i = 0; i < n; i++) {
            table[i][0] = 1;
            table[i][i] = 1;
        }
        for (int i = 2; i < n; i++) {
            for (int j = 1; j < i; j++) {
                table[i][j] = (table[i - 1][j - 1] + table[i - 1][j]) % MOD;
            }
        }
        
        // Convert int array to List for easier filtering
        List<Integer> arr = new ArrayList<>();
        for (int num : nums) {
            arr.add(num);
        }
        
        // Subtract 1 to exclude the original array itself, and add MOD to prevent negative results
        return (int) ((dfs(arr) - 1 + MOD) % MOD);
    }
    
    private long dfs(List<Integer> arr) {
        int m = arr.size();
        // Base case: arrays of length 2 or less only have 1 valid arrangement
        if (m <= 2) {
            return 1;
        }
        
        int root = arr.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        
        // Partition elements into left and right subtrees
        for (int i = 1; i < m; i++) {
            int val = arr.get(i);
            if (val < root) {
                left.add(val);
            } else {
                right.add(val);
            }
        }
        
        // Recursively find the number of ways for the left and right subtrees
        long leftWays = dfs(left);
        long rightWays = dfs(right);
        
        // Calculate the interleaving combinations: (L + R) choose L
        long waysToInterleave = table[left.size() + right.size()][left.size()];
        
        // Multiply them all together under the modulo
        return (waysToInterleave * leftWays % MOD) * rightWays % MOD;
    }
}