class Solution {
public:
    int minMoves(vector<int>& nums) {
        int min = nums[0];
        int sum = 0;

        for(int i=0; i < nums.size(); i++){
            if(nums[i] < min){
                min = nums[i];
            }
            sum += nums[i];
        }
        return sum - (min*nums.size());
    }
};