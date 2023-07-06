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
def dfs(node):
    if node not in visited:
        visited.add(node)
        print(node)
        for neighbour in graph[node]:
            dfs(neighbour)
dfs(start)
