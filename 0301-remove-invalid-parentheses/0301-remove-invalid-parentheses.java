class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int leftRem = 0;
        int rightRem = 0;
        
        // Step 1: Find minimum number of '(' and ')' to remove
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftRem++;
            } else if (c == ')') {
                if (leftRem > 0) {
                    leftRem--;
                } else {
                    rightRem++;
                }
            }
        }
        
        Set<String> validExpressions = new HashSet<>();
        dfs(s, 0, leftRem, rightRem, 0, 0, new StringBuilder(), validExpressions);
        
        return new ArrayList<>(validExpressions);
    }
    
    private void dfs(String s, int index, int leftRem, int rightRem, int leftCount, int rightCount, StringBuilder path, Set<String> result) {
        // Base case: Reached the end of the string
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0) {
                result.add(path.toString());
            }
            return;
        }
        
        char c = s.charAt(index);
        int currentPathLength = path.length(); // Save length for quick backtracking
        
        // Option 1: Remove the current character (if it's a parenthesis and we have quota)
        if (c == '(' && leftRem > 0) {
            dfs(s, index + 1, leftRem - 1, rightRem, leftCount, rightCount, path, result);
        } else if (c == ')' && rightRem > 0) {
            dfs(s, index + 1, leftRem, rightRem - 1, leftCount, rightCount, path, result);
        }
        
        // Option 2: Keep the current character
        path.append(c);
        
        if (c == '(') {
            dfs(s, index + 1, leftRem, rightRem, leftCount + 1, rightCount, path, result);
        } else if (c == ')') {
            // Pruning: Only keep a right parenthesis if it can form a valid pair
            if (leftCount > rightCount) {
                dfs(s, index + 1, leftRem, rightRem, leftCount, rightCount + 1, path, result);
            }
        } else {
            // It's a letter, always keep it
            dfs(s, index + 1, leftRem, rightRem, leftCount, rightCount, path, result);
        }
        
        // Backtrack: Remove the character we just appended
        path.setLength(currentPathLength);
    }
}