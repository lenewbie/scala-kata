package org.acme.scalakata.graph

object NeighboursListGraph {
  def apply(i: Int) = new NeighboursListGraph
}

class NeighboursListGraph extends Graph {
  override def getNeighbours(node: Int): List[Int] = ???

  override def addEdge(firstNode: Int, secondNode: Int): Graph = ???

  override def nodesNumber: Int = 1

  override def edgesNumber: Int = ???
}
