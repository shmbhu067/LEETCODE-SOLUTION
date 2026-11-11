class Solution {
public:
    long long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long long ans = LLONG_MAX;
        vector<long long> x = {
            0LL,
            min(need1, need2),
            max(need1,need2)
        };
        for(long long both : x) {
            long long cost = both * costBoth;
            cost += max(0LL,(long long)need1-both) * cost1;
            cost += max(0LL,(long long)need2 - both) * cost2;

            ans = min(ans, cost);

        } 
        return ans;
    }
};