class Solution(object):
    def numberOfSteps(self, num):
        """
        :type num: int
        :rtype: int
        """
        ans = 0
        
        while num  != 0:
            ans +=1
            if num % 2 == 0:
                num /= 2
            else:
                num=num-1
            
        return ans

        # MY FIRST EVER SOLVED BY ME MINE MYSELF 
        # THANK YOU KRISHNA JII