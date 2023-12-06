# Sink Elimination Algorithm
This program detects acyclic graphs using the Sink Elimination algorithm

### Important Instructions

* You need to modify the path in the `src/com/company/main` file for the graph input. Curruntly, as per my computer I have set it to `src/com/company/assets/input.txt` 
* Make sure the path is relative to the root or you can als add the absolute path.

## About  this program

A sink vertex in the context of a directed graph is a vertex without any outgoing edges. It is a vertex that is the endpoint of edges but not the starting point of any edges, to put it another way.

Because it can symbolize the conclusion of a procedure or the accomplishment of a task, the sink vertex idea is significant in graph theory. For instance, a sink vertex in a directed acyclic graph (DAG) that depicts a workflow or dependency network may represent the last step or the outcome that is generated once all the dependencies have been met.

One can iterate over all of the graph's vertices and determine whether the size of the adjacency list for each vertex is zero in order to discover a sink vertex in a directed graph using the adjacency list representation. A vertex is a sink vertex if it has no outward edges. In a graph with numerous sink vertices, any one of them may be regarded as the sink.

It's significant to remember that sink vertices are not present in all directed graphs. There might not be any sink vertices in a directed network if it contains cycles. A directed graph can also contain numerous connected parts, each of which can have a separate sink vertex.