class Solution(object):
    def removeNthFromEnd(self, head, n):
        """
        :type head: Optional[ListNode]
        :type n: int
        :rtype: Optional[ListNode]
        """
        dummy = ListNode(0)
        dummy.next = head

        fast = dummy
        slow = dummy

        # Move fast n steps ahead
        for _ in range(n):
            fast = fast.next

        # Move until fast reaches the last node
        while fast.next:
            fast = fast.next
            slow = slow.next

        # Remove the target node
        slow.next = slow.next.next

        return dummy.next