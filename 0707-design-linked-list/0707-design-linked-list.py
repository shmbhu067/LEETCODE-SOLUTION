
class Node:
    def __init__(self, val):
        self.val = val
        self.next = None


class MyLinkedList:

    def __init__(self):
        self.head = None
        self.size = 0

    def get(self, index):
        if index < 0 or index >= self.size:
            return -1

        curr = self.head
        for _ in range(index):
            curr = curr.next
        return curr.val

    def addAtHead(self, val):
        newNode = Node(val)
        newNode.next = self.head
        self.head = newNode
        self.size += 1

    def addAtTail(self, val):
        newNode = Node(val)

        if self.head is None:
            self.head = newNode
        else:
            curr = self.head
            while curr.next:
                curr = curr.next
            curr.next = newNode

        self.size += 1

    def addAtIndex(self, index, val):
        if index < 0 or index > self.size:
            return

        if index == 0:
            self.addAtHead(val)
            return

        curr = self.head
        for _ in range(index - 1):
            curr = curr.next

        newNode = Node(val)
        newNode.next = curr.next
        curr.next = newNode
        self.size += 1

    def deleteAtIndex(self, index):
        if index < 0 or index >= self.size:
            return

        if index == 0:
            self.head = self.head.next
            self.size -= 1
            return

        curr = self.head
        for _ in range(index - 1):
            curr = curr.next

        curr.next = curr.next.next
        self.size -= 1