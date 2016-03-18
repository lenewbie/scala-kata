package org.acme.scalakata.graph

object NeighboursListGraph {
  def apply(nodesNumber: Int) = new NeighboursListGraph(nodesNumber)
}

class NeighboursListGraph(val nodesNumber: Int) extends Graph {
  private var edges = Set.empty[(Int, Int)]
  override def getNeighbours(node: Int): List[Int] = {
    validate(node)
    null
  }

  private def validate(node: Int): Unit = {
    if(node >= nodesNumber)throw new NoSuchNode
    if(node < 0) throw new NegativeIndex
  }

  override def addEdge(firstNode: Int, secondNode: Int): Graph = {
    validate(firstNode)
    validate(secondNode)
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
