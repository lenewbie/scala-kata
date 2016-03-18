package org.acme.scalakata.graph

object NeighboursListGraph {
  def apply(nodesNumber: Int) = new NeighboursListGraph(nodesNumber)
}

class NeighboursListGraph(val nodesNumber: Int) extends Graph {
  private var edges = Set.empty[(Int, Int)]
  override def getNeighbours(node: Int): List[Int] = throw new NoSuchNode

  override def addEdge(firstNode: Int, secondNode: Int): Graph =
    if(firstNode < 0 || secondNode < 0) throw new NegativeIndex
    else if(firstNode >= nodesNumber || secondNode >= nodesNumber) throw new NoSuchNode
    else {
      for(newEdge <- createEdge(firstNode, secondNode)){
        edges = edges + newEdge
      }
      this
    }

  private def createEdge(firstNode: Int, secondNode: Int) =
    if (firstNode < secondNode) Some(firstNode -> secondNode)
    else if (firstNode > secondNode) Some(secondNode -> firstNode)
    else None

  override def edgesNumber: Int = edges.size
}
