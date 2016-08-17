
Objective of this exercise is to implement undirected graph using adjacency list.

Graph consists of points - called vertices and connections between them - called edges. 
Vertices that are connected to given one are called adjacent vertices. 

```scala
trait Graph {

  def addEdge(firstNode:Int, secondNode:Int): Graph

  def getNeighbours(node:Int): List[Int]

  def nodesNumber: Int

  def edgesNumber: Int

}
```

There is a few methods to implement graph: https://en.wikipedia.org/wiki/Graph_(abstract_data_type)#Representations
The adjacency list representation stores all connected pairs. For example in a list. 
Therefore it is called adjacency list for reason :)

In this implementation demonstrate how to use Java style using Scala language. This is only for educational purposes.
If you are Java developer I would like to show you you that Scala is not that scary. Actually it is better Java than Java :)
If you are not familiar with Java you can observe how object oriented style is gradually transited to functional one.

So we have two exception types to represent problems with using the API:

```scala
case object NoSuchNode extends RuntimeException

case object NegativeIndex extends RuntimeException
```

I would fire SBT and ask it to invoke tests whenever code change:

```scala
sbt ~test
```

and change the code so it compiles first. 
Then un-ignore tests gradually in NeighboursListGraphSpec. To do so replace:

```scala
ignore("my awesome test")
```

to

```scala
it("my awesome test")
```

I hope it will be fun journey :)