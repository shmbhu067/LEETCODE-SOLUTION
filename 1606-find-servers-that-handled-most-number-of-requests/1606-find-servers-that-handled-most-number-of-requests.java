// class Solution {
//     public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
//         int [] counts = new int[k];
//         TreeSet<Integer> available = new TreeSet<>();
//         for(int i=0; i < k; i++){
//             available.add(i);
//         } 
//         PriorityQueue<int[]> busy = new PriorityQueue<>((a,b) -> Integer.compare(a[0],b[0]));
//         for(int i=0; i< arrival.lengeth;i++){
//             int start = arrival[i];
//             int end = satrt + load[i];
//         }
//     }
// }
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // Keeps track of the number of requests handled by each server
        int[] counts = new int[k];
        
        // Keeps track of available servers
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }
        
        // Priority Queue to keep track of busy servers.
        // Stores int array: [freeTime, serverId]
        // Sorted ascending by the time the server becomes free.
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        for (int i = 0; i < arrival.length; i++) {
            int start = arrival[i];
            int end = start + load[i];
            
            // Free up servers that have finished their requests by the current arrival time
            while (!busy.isEmpty() && busy.peek()[0] <= start) {
                int[] freedServer = busy.poll();
                available.add(freedServer[1]);
            }
            
            // If all servers are busy, drop the request
            if (available.isEmpty()) {
                continue;
            }
            
            // Find the preferred server or the next available one
            Integer destServer = available.ceiling(i % k);
            
            // If there's no available server with an ID >= i % k, wrap around to the lowest ID
            if (destServer == null) {
                destServer = available.first();
            }
            
            // Assign the request: remove from available, add to busy, increment count
            available.remove(destServer);
            busy.offer(new int[]{end, destServer});
            counts[destServer]++;
        }
        
        // Find the maximum number of requests handled by any server
        int max = 0;
        for (int count : counts) {
            max = Math.max(max, count);
        }
        
        // Collect all servers that handled the maximum number of requests
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (counts[i] == max) {
                result.add(i);
            }
        }
        
        return result;
    }
}
