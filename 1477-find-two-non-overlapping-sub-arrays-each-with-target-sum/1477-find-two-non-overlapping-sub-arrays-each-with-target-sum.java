class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;

        int [] best = new int[n];

        int INF = n+1;

        for(int i=0; i < n; i++){
            best[i] = INF;
        }
        int left =0;
        int sum =0;
        int ans = INF;
        int minLen = INF;

        for(int right = 0; right < n; right++){
            sum += arr[right];
            // SHRINK window if sum becomes greater than target
            while(sum > target){
                sum -= arr[left];
                left++;
            }

            if( sum == target){
                int currLen = right - left  +1;

                // IF there is a previous non-overlapping subarray
                if(left > 0 && best[left-1] != INF){
                    ans = Math.min(ans, currLen + best[left - 1]);
                }

                // KEEP the shortest target subarray seen so far 
                minLen = Math.min(minLen, currLen);

            }
            // Store the best subarray up to 'right'
            best[right] = minLen;
        }
        return ans == INF ? -1: ans;
    }
}