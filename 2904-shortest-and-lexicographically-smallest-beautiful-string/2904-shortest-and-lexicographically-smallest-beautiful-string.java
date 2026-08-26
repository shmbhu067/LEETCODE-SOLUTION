class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int left = 0;
        int  ones=0;
        String ans="";

        for(int right = 0; right<n; right++){
            if(s.charAt(right)=='1'){
                ones++;}

            //Keep at most k ones in the window

            while(ones >k){
                if(s.charAt(left) == '1'){
                 ones--;
             
             }left++;
            
            }
            if(ones == k){
                //Leading zeros are never useful for a shortest subtring 
                while(left < right && s.charAt(left) =='0')
                    left++;
                
                String cur = s.substring(left,right+1);

                if(ans.isEmpty() || 
                    cur.length() < ans.length()||
                    (cur.length() == ans.length() && cur.compareTo(ans)<0)){
                        ans = cur;
                    }
            }
        }
        return ans;
    }
}