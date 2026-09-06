class Solution {
    public String convert(String s, int numRows) {
        if(numRows == 1 || s.length() <= numRows){
            return s;
        }

        StringBuilder[] rows = new StringBuilder[numRows];

        //Initialize each row

        for( int i =0; i< numRows; i++){
            rows[i] = new StringBuilder();
        }

        int currentRow =0;
        boolean goingDown = true;

        // Place characters in zigzag rows
        for(char ch: s.toCharArray()){
            rows[currentRow].append(ch);
        
        // change direction at top or bottom
        if(currentRow == 0){
            goingDown = true;
        }else if(currentRow == numRows -1){
            goingDown = false;
        }

        currentRow += goingDown ? 1:-1; 
    }
        // Combine all rows
    StringBuilder result = new StringBuilder();

    for(StringBuilder row: rows){
        result.append(row);
    }
    return result.toString();

    }
    
}