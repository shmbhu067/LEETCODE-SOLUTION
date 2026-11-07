class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<int[]> free = new PriorityQueue<>((a,b) -> a[0] == b[0]? a[1]-b[1]:a[0]-b[0]);
        PriorityQueue<long[]> busy = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));

        for(int i= 0; i< servers.length; i++)
            free.add(new int[]{servers[i],i});
        int[] ans = new int[tasks.length];
        long t = 0;
        
        for(int i=0; i < tasks.length; i++){
            t= Math.max(t,i);
            if(free.isEmpty()) t = busy.peek()[0];

            while(!busy.isEmpty() && busy.peek()[0] <= t){
                long[] s = busy.poll();
                free.add(new int[]{(int)s[1],(int)s[2]});
            }
            int[] s = free.poll();
            ans[i] = s[1];
            busy.add(new long[]{t + tasks[i], s[0], s[1]});
        }
        return ans;
    }
}