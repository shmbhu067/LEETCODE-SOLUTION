class Solution:
    def countDaysTogether(self, arriveAlice: str, leaveAlice: str, arriveBob: str, leaveBob: str) -> int:
        # Step 1: Find the overlapping interval
        start = max(arriveAlice, arriveBob)
        end = min(leaveAlice, leaveBob)
        
        # Step 2: If start is after end, there is no overlap
        if start > end:
            return 0
        
        # Array of days in each month for a non-leap year
        days_in_month = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
        
        # Helper function to convert "MM-DD" to the day of the year (1-365)
        def date_to_days(date_str: str) -> int:
            month = int(date_str[:2])
            day = int(date_str[3:])
            
            # Sum days of all fully passed months
            total_days = sum(days_in_month[:month - 1])
            # Add days of the current month
            total_days += day
            
            return total_days
        
        # Step 3: Calculate the difference in days (+1 because it's inclusive)
        return date_to_days(end) - date_to_days(start) + 1