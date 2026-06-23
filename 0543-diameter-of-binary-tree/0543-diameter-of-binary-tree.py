class Solution(object):
    def diameterOfBinaryTree(self, root):
        if root is None:
            return 0

        stack = [(root, False)]
        max_height_dict = {}
        diameter = 0

        while stack:
            node, visited = stack.pop()

            if not visited:
                stack.append((node, True))

                if node.left:
                    stack.append((node.left, False))
                if node.right:
                    stack.append((node.right, False))

            else:
                if node.left is None:
                    left_height = 0
                else:
                    left_height = max_height_dict.pop(node.left)

                if node.right is None:
                    right_height = 0
                else:
                    right_height = max_height_dict.pop(node.right)

                diameter = max(diameter, left_height + right_height)

                max_height_dict[node] = 1 + max(left_height, right_height)

        return diameter
        