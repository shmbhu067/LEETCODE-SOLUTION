from collections import defaultdict

class Solution(object):
    def pyramidTransition(self, bottom, allowed):

        mp = defaultdict(list)

        for s in allowed:
            mp[s[:2]].append(s[2])

        def dfs(curr, next_row, index):
            # If we've reached the top
            if len(curr) == 1:
                return True

            # Finished building the next row
            if index == len(curr) - 1:
                return dfs(next_row, "", 0)

            pair = curr[index:index+2]

            if pair not in mp:
                return False

            for ch in mp[pair]:
                if dfs(curr, next_row + ch, index + 1):
                    return True

            return False

        return dfs(bottom, "", 0)