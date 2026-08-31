class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int[] ans = {-1,-1,};

        if(head == null || head.next == null || head.next.next == null){
            return ans;
        }  

        ListNode prev = head;
        ListNode curr = head.next;

        int position =1;

        int firstCritical = -1;
        int lastCritical = -1;

        int minDistance = Integer.MAX_VALUE;

        while(curr.next != null){
            ListNode next = curr.next;

            boolean isMax = curr.val > prev.val && curr.val > next.val;
            boolean isMin = curr.val < prev.val && curr.val < next.val;

            if(isMax || isMin){
                // first critical point 
                if(firstCritical == -1){
                    firstCritical = position;
                }

                if(lastCritical != -1){
                    minDistance = Math.min(
                        minDistance,
                        position- lastCritical
                    );
                }
                lastCritical = position;
            }
            prev = curr;
            curr = next;
            position++;
        }  
        if(firstCritical == -1 || firstCritical == lastCritical){
            return ans;
        }
        int maxDistance = lastCritical - firstCritical;

        return new int[]{minDistance, maxDistance};
    }
}