package org.acme.scalakata.graph

object NeighboursListGraph {
  def apply(nodesNumber: Int) = new NeighboursListGraph(nodesNumber)
}

class NeighboursListGraph(val nodesNumber: Int) extends Graph {
  override def getNeighbours(node: Int): List[Int] = ???

  override def addEdge(firstNode: Int, secondNode: Int): Graph =
    if(firstNode < 0 || secondNode < 0) throw new NegativeIndex
    else if(firstNode >= nodesNumber || secondNode >= nodesNumber) throw new NoSuchNode
    else this

  override def edgesNumber: Int = 0
}
