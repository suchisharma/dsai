from collections import deque
graph = {
    'A':['D','B'],
    'B':['F','I'],
    'D':['E','C'],
    'E':[],
    'C':['G'],
    'G':['H'],
    'F':[],
    'H':[],
    'I':[]
}
start = 'A'
visited = set()
queue = deque([start])
while queue:
    node = queue.popleft()
    if node not in visited:
        visited.add(node)
        print(node)
        for neighbour in graph[node]:
            if neighbour not in visited:
                queue.append(neighbour)