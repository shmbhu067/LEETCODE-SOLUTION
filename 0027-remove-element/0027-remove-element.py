class Solution:
    def removeElement(self, nums: list[int], val: int) -> int:
        # k keeps track of the index for the next valid element
        k = 0
        
        for i in range(len(nums)):
            # If the current element is not the value we want to remove
            if nums[i] != val:
                # Place it at the 'k' index and increment k
                nums[k] = nums[i]
                k += 1
                
        return k