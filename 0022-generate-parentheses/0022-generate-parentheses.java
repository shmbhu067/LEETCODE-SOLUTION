class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, String current, int open, int close, int n){
        // A complete vaild combination is formed 
        if (current.length() == 2 *n){
            result.add(current);
            return;
        }

        // WE can add '(' if we still have opeaning brackets available 
        if(open <n){
            backtrack(result, current + '(', open +1, close, n);
        }

        // Now We can add ')' only when there is an unmatched '('
        if (close < open){
            backtrack(result,  current + ')',open, close+1,n);
        }
    }
}