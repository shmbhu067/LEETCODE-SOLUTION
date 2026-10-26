class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        
        int[] q = new int[m*n];

        int[] dr = {-1,1,0,0};
        int[] dc = {0,0,-1,1};

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] > 0){
                    int head=0,tail =0;
                    q[tail++] = i*n+j;

                    long sum = grid[i][j];
                    grid[i][j] = 0;

                    while (head < tail){
                        int curr = q[head++];
                        int r = curr/n;
                        int c = curr%n;

                        for(int d=0; d<4; d++){
                            int nr = r+dr[d];
                            int nc = c+ dc[d];

                            if(nr >=0 && nr < m && nc >= 0 && nc <n && grid[nr][nc] > 0){
                                sum+= grid[nr][nc];
                                q[tail++] = nr*n+nc;
                                grid[nr][nc] = 0;
                            }
                        }
                    }if (sum % k ==0){
                    count++;
                }
                    }

                } 
            }return count;
        }  
       
    }
