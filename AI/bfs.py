from collections import deque
# Define the graph as an adjacency list
graph = {
    'A': ['D', 'B'],
    'B': ['F', 'I'],
    'D': ['E','C'],
    'E': [],
    'C': ['G'],
    'G': ['H'],
    'F': [],
    'E': [],
    'H': [],
    'I': []
}
# Define the starting node
start = 'A'
# Initialize the visited set and the queue
visited = set()
queue = deque([start])
# Loop until the queue is empty
while queue:
    # Dequeue a node from the queue
    node = queue.popleft()
    # If the node has not been visited yet
    if node not in visited:
        # Mark the node as visited
        visited.add(node)
        # Print the visited node
        print(node)
        # Enqueue all the unvisited neighbors of the node
        for neighbor in graph[node]:
            if neighbor not in visited:
                queue.append(neighbor)
