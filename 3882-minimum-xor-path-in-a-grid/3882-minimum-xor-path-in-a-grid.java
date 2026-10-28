class Solution {
    public int minCost(int[][] grid) {
        
        int m = grid.length;
        int n = grid[0].length;

        boolean [][]dp = new boolean [n][1024];

        for(int i=0; i <m; i++){
            boolean[][] nextDp = new boolean[n][1024];
            for(int  j=0; j<n; j++){
                int val = grid[i][j];
                if(i == 0 && j==0){
                    nextDp[0][val] = true;
                    continue;
                }
                for(int v=0; v<1024;v++){
                    if((i>0 && dp[j][v]) || (j > 0 && nextDp[j-1][v])){
                        nextDp[j][v^val] = true;
                    }
                }
            }
            dp = nextDp;
        }for(int v=0; v< 1024;v++){
            if(dp[n-1][v]){
                return v;
            }
        }
        return -1;
    }
}