# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution(object):
    def searchBST(self, root, val):
        """
        :type root: Optional[TreeNode]
        :type val: int
        :rtype: Optional[TreeNode]
        """
        current_node = root

        while current_node:
            if current_node.val == val:
                return current_node

            elif current_node.val < val:
                current_node = current_node.right

            else:
                current_node = current_node.left
        return None