class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long k = (long) k1+k2;
        int n = nums1.length;

        int [] counts = new int[100001];
        long totalDiff = 0;

        for(int i=0; i<n ; i++){
            int diff = Math.abs(nums1[i]-nums2[i]);
            counts[diff]++;
            totalDiff += diff;
        }
        if(k >= totalDiff){
            return 0;
        }

        for (int d = 100000; d>0 && k >0; d--){
            if(counts[d] > 0){
                if(k >= counts[d]){
                    k -= counts[d];
                    counts[d-1] += counts[d];
                    counts[d] = 0;
                } else{
                    counts[d-1] += (int)k;
                    counts[d] -= (int)k;
                    k = 0;
                }
            }
        }
        long ans =0;
        for(long d = 1; d<= 100000; d++){
            if(counts[(int)d]>0){
                ans+= counts[(int) d]*d*d;
            }
        }
        return ans;
    }
}