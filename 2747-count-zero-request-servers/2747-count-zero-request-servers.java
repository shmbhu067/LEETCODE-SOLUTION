import java.util.Arrays;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int qLen = queries.length;
        // Store queries alongside their original indices: {query_time, original_index}
        int[][] sortedQueries = new int[qLen][2];
        for (int i = 0; i < qLen; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        
        // Sort queries based on time in ascending order
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Sort logs based on time in ascending order
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        
        int[] ans = new int[qLen];
        // Frequency array to keep track of active servers in the current window
        // Using an array instead of HashMap makes it optimally fast
        int[] count = new int[n + 1]; 
        
        int left = 0;
        int right = 0;
        int uniqueServers = 0;
        int m = logs.length;
        
        for (int i = 0; i < qLen; i++) {
            int endTime = sortedQueries[i][0];
            int startTime = endTime - x;
            
            // Expand the window: Add logs that fall within the current query time
            while (right < m && logs[right][1] <= endTime) {
                int serverId = logs[right][0];
                if (count[serverId] == 0) {
                    uniqueServers++; // Newly active server
                }
                count[serverId]++;
                right++;
            }
            
            // Shrink the window: Remove logs that fall strictly before the start time
            while (left < m && logs[left][1] < startTime) {
                int serverId = logs[left][0];
                count[serverId]--;
                if (count[serverId] == 0) {
                    uniqueServers--; // Server is no longer active in the window
                }
                left++;
            }
            
            // The number of servers receiving 0 requests is the total servers minus active unique servers
            ans[sortedQueries[i][1]] = n - uniqueServers;
        }
        
        return ans;
    }
}