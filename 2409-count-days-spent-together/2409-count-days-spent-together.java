class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        // Step 1: Find the overlapping interval
        // String.compareTo returns a positive number if the first string is greater
        String start = arriveAlice.compareTo(arriveBob) > 0 ? arriveAlice : arriveBob;
        String end = leaveAlice.compareTo(leaveBob) < 0 ? leaveAlice : leaveBob;
        
        // Step 2: If start is after end, there is no overlap
        if (start.compareTo(end) > 0) {
            return 0;
        }
        
        // Step 3: Calculate the difference in days (+1 because it's inclusive)
        return dateToDays(end) - dateToDays(start) + 1;
    }
    
    // Helper function to convert "MM-DD" to the day of the year (1-365)
    private int dateToDays(String date) {
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3));
        
        int totalDays = 0;
        
        // Sum days of all fully passed months
        for (int i = 0; i < month - 1; i++) {
            totalDays += daysInMonth[i];
        }
        
        // Add days of the current month
        totalDays += day;
        
        return totalDays;
    }
}