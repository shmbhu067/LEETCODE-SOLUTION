class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, int[]> map = new HashMap<>();
       
        for(int i=0; i<n; i++){
           int x = nums[i];

           int left = Math.max(0,i-k+1);
           int right = Math.min(i, n-k);
           if (!map.containsKey(x)){
            map.put(x, new int[n-k+2]);
           }
           int []diff = map.get(x);
           diff[left]++;
           diff[right+1]--;
        }
        int ans = -1;
        for (int x: map.keySet()){
            int[] diff = map.get(x);
            int current = 0;
            int windows = 0;
            for(int i=0; i<=n-k; i++){
                current += diff[i];

                if (current > 0){
                    windows++;
                }
            }
            if (windows == 1){
                ans = Math.max(ans,x);
            }
        }
      return ans;
    }
}