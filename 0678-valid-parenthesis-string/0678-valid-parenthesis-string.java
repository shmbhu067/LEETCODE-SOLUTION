class Solution {
    public boolean checkValidString(String s) {
        int cmin = 0; ///// MINIMUM POSSIBLE open '('
        int cmax = 0; //////// MAXIMU possible open '('

        for (char ch : s.toCharArray()){
            if(ch == '('){
                cmin++;
                cmax++;
            }else if(ch == ')'){
                cmin--;
                cmax--;
            } else{
                cmin--;
                cmax++;
            }

            //// MORE ')' than can ever be matched by '(' and '*' combined

            if(cmax < 0){
                return false;
            }

            // cmin can never be negative; excess ')' are distracted by treating '*' as empty 
            cmin = Math.max(cmin, 0);
        }
        return cmin == 0;
    }
}