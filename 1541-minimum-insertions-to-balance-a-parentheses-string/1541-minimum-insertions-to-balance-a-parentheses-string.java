class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int neededRight = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                // If we have an odd number of needed right parentheses,
                // insert one ')' right now before starting the new '('
                if (neededRight % 2 != 0) {
                    insertions++;
                    neededRight--;
                }
                neededRight += 2;
                
            } else { // c == ')'
                neededRight--;
                
                // We have a ')' but no corresponding '('
                if (neededRight == -1) {
                    insertions++; // Insert a '('
                    neededRight = 1; // The inserted '(' still needs one more ')'
                }
            }
        }
        
        return insertions + neededRight;
    }
}