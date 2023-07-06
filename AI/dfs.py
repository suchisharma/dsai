from collections import deque
graph = {
    'A': ['D', 'B'],
    'B': ['F', 'I'],
    'D': ['E', 'C'],
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
# Initialize the visited set
visited = set()
# Define the DFS function
def dfs(node):
    # If the node has not been visited yet
    if node not in visited:
        # Mark the node as visited
        visited.add(node)
        # Print the visited node
        print(node)
        # Recursively call the DFS function for each unvisited neighbor
        for neighbor in graph[node]:
            dfs(neighbor)
# Call the DFS function with the starting node
dfs(start)

