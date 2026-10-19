import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int lessCount = 0;
        int equalCount = 0;
        
        // 1. Single pass to count occurrences (O(N) time)
        for (int num : nums) {
            if (num < target) {
                lessCount++;
            } else if (num == target) {
                equalCount++;
            }
        }
        
        // 2. Generate the result using the counts
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < equalCount; i++) {
            // The first target will be at index 'lessCount'
            // Subsequent targets will be at 'lessCount + 1', 'lessCount + 2', etc.
            result.add(lessCount + i);
        }
        
        return result;
    }
}