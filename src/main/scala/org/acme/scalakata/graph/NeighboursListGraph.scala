package org.acme.scalakata.graph

object NeighboursListGraph {
  def apply(nodesNumber: Int) = new NeighboursListGraph(nodesNumber)
}

class NeighboursListGraph(val nodesNumber: Int) extends Graph {
  private var edges = 0
  override def getNeighbours(node: Int): List[Int] = ???

  override def addEdge(firstNode: Int, secondNode: Int): Graph =
    if(firstNode < 0 || secondNode < 0) throw new NegativeIndex
    else if(firstNode >= nodesNumber || secondNode >= nodesNumber) throw new NoSuchNode
    else {
      edges = 1
      this
    }

  override def edgesNumber: Int = edges
}
