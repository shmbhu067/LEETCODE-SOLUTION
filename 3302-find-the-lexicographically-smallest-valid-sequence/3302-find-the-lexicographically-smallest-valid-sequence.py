class Solution:
    def validSequence(self, word1: str, word2: str) -> List[int]:
        n,m = len(word1),len(word2)

        last = [-1]*m

        i = n - 1
        j = m - 1

        while i >= 0 and j >= 0:
            if word1[i] == word2[j]:
                last[j] = i
                j -=1
            i-=1
        
        ans = []
        j = 0
        
        canSkip = True

        for i, c in enumerate(word1):
            if j == m:
                break
            if c== word2[j]:
                ans.append(i)
                j+=1
            elif canSkip and (j==m-1 or i <last[j+1]):
                ans.append(i)
                j+=1
                canSkip = False

       
        return ans if j == m else[]

