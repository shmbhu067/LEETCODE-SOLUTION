class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer>map = new HashMap<>();
        // itrate Through array:
        for(int i=0; i<nums.length ; i++){
            int complement = target - nums[i];
            
            if(map.containsKey(complement)){
                return new int[] {map.get(complement) , i};
            }
            //otherwise add current number and its index to the map;
            map.put(nums[i],i);
        }
        /// Yehh tho kabhi hoga nhii
        return new int[]{};
    }
}