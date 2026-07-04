class Solution:
    def sortList(self, head):
        arr = []

        # Store values in an array
        while head:
            arr.append(head.val)
            head = head.next

        # Sort the array
        arr.sort()

        # Create a new sorted linked list
        dummy = ListNode(0)
        curr = dummy

        for val in arr:
            curr.next = ListNode(val)
            curr = curr.next

        return dummy.next