class Solution:
    def smallestNumber(self, num: str, t: int) -> str:
        a = 0
        b = 0
        c = 0
        d = 0
        tt = t
        while tt % 2 == 0:
            tt = tt // 2
            a = a + 1
        while tt % 3 == 0:
            tt = tt // 3
            b = b + 1
        while tt % 5 == 0:
            tt = tt // 5
            c = c + 1
        while tt % 7 == 0:
            tt = tt // 7
            d = d + 1
        if tt != 1:
            return "-1"

        digitExp = {
            1: (0, 0, 0, 0),
            2: (1, 0, 0, 0),
            3: (0, 1, 0, 0),
            4: (2, 0, 0, 0),
            5: (0, 0, 1, 0),
            6: (1, 1, 0, 0),
            7: (0, 0, 0, 1),
            8: (3, 0, 0, 0),
            9: (0, 2, 0, 0)
        }

        dp23 = [[0] * (b + 1) for _ in range(a + 1)]
        transitions = [(1, 0), (0, 1), (2, 0), (1, 1), (3, 0), (0, 2)]
        for i in range(a + 1):
            for j in range(b + 1):
                if i == 0 and j == 0:
                    continue
                best = None
                for e2, e3 in transitions:
                    ni = max(i - e2, 0)
                    nj = max(j - e3, 0)
                    if ni == i and nj == j:
                        # no progress made, this digit choice is useless here
                        continue
                    val = 1 + dp23[ni][nj]
                    if best is None or val < best:
                        best = val
                dp23[i][j] = best

        def minDigitsNeeded(na, nb, nc, nd):
            return dp23[na][nb] + nc + nd

        def isFeasible(remLen, na, nb, nc, nd):
            return minDigitsNeeded(na, nb, nc, nd) <= remLen

        def buildString(length, na, nb, nc, nd):
            res = []
            cur = (na, nb, nc, nd)
            for pos in range(length):
                rem = length - pos - 1
                for dgt in range(1, 10):
                    e2, e3, e5, e7 = digitExp[dgt]
                    nn = (max(cur[0] - e2, 0), max(cur[1] - e3, 0), max(cur[2] - e5, 0), max(cur[3] - e7, 0))
                    if isFeasible(rem, nn[0], nn[1], nn[2], nn[3]):
                        res.append(str(dgt))
                        cur = nn
                        break
            return "".join(res)

        n = len(num)
        digits = [int(ch) for ch in num]

        PA = [0] * (n + 1)
        PB = [0] * (n + 1)
        PC = [0] * (n + 1)
        PD = [0] * (n + 1)
        prefZeroFree = [True] * (n + 1)

        for i in range(n):
            if digits[i] == 0:
                e2 = 0
                e3 = 0
                e5 = 0
                e7 = 0
            else:
                e2, e3, e5, e7 = digitExp[digits[i]]
            PA[i + 1] = PA[i] + e2
            PB[i + 1] = PB[i] + e3
            PC[i + 1] = PC[i] + e5
            PD[i + 1] = PD[i] + e7
            prefZeroFree[i + 1] = prefZeroFree[i] and (digits[i] != 0)

        if prefZeroFree[n] and PA[n] >= a and PB[n] >= b and PC[n] >= c and PD[n] >= d:
            return num

        for i in range(n - 1, -1, -1):
            if not prefZeroFree[i]:
                continue
            base = (max(a - PA[i], 0), max(b - PB[i], 0), max(c - PC[i], 0), max(d - PD[i], 0))
            rem = n - 1 - i
            for dgt in range(digits[i] + 1, 10):
                e2, e3, e5, e7 = digitExp[dgt]
                nn = (max(base[0] - e2, 0), max(base[1] - e3, 0), max(base[2] - e5, 0), max(base[3] - e7, 0))
                if isFeasible(rem, nn[0], nn[1], nn[2], nn[3]):
                    return num[:i] + str(dgt) + buildString(rem, nn[0], nn[1], nn[2], nn[3])

        L = max(n + 1, minDigitsNeeded(a, b, c, d))
        return buildString(L, a, b, c, d)