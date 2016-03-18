package org.acme.scalakata.graph

object NeighboursListGraph {
  def apply(nodesNumber: Int) = new NeighboursListGraph(nodesNumber)
}

class NeighboursListGraph(val nodesNumber: Int) extends Graph {
  override def getNeighbours(node: Int): List[Int] = ???

  override def addEdge(firstNode: Int, secondNode: Int): Graph = throw new NoSuchNode

  override def edgesNumber: Int = ???
}
