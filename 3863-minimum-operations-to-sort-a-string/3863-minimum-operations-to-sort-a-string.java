class Solution {
    public int minOperations(String s) {
        int n =s.length();

        int [] count = new int[26];
        for(int i=0; i < n; i++){
            count[s.charAt(i) - 'a']++;
        }
        char minChar = 'z';
        char maxChar = 'a';
        for(int i=0; i< 26; i++){
            if(count[i] > 0){
                minChar = (char) ('a'+i);
                break;
            }
        }
        for(int i=25; i>=0; i--){
            if(count[i] > 0){
                maxChar = (char) ('a'+i);
                break;
            }
        }
        int firstDiff = -1;
        int lastDiff = -1;
        int charIdx = 0;

        for(int i=0; i < n; i++){
            while(count[charIdx] == 0){
                charIdx++;
            }
            char expectedChar = (char)('a' + charIdx);
            count[charIdx]--;
            if(s.charAt(i) != expectedChar){
                if(firstDiff == -1){
                    firstDiff = i;
                }
                lastDiff = i;
            }
        }
        if(firstDiff == -1){
            return 0;
        }
        if(n<3){
            return -1;
        }
        if(firstDiff > 0 || lastDiff < n-1){
            return 1;
        }
        boolean minMid = false;
        boolean maxMid = false;
        for(int i=1; i<n-1;i++){
            char c = s.charAt(i);
            if(c==minChar) minMid = true;
            if(c==maxChar) maxMid = true;
        }
        if(s.charAt(0) == maxChar && s.charAt(n-1) == minChar && !minMid && !maxMid){
            return 3;
        }
        return 2;
    }
}