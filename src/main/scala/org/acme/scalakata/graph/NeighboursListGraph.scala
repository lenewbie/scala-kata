package org.acme.scalakata.graph

object NeighboursListGraph {
  def apply(nodesNumber: Int) = new NeighboursListGraph(nodesNumber)
}

class NeighboursListGraph(val nodesNumber: Int) extends Graph {
  private var neighbours = Vector.fill(nodesNumber)(Set.empty[Int])
  override def getNeighbours(node: Int): List[Int] = {
    validate(node)
    neighbours(node).toList
  }

  private def validate(node: Int): Unit = {
    if(node >= nodesNumber)throw new NoSuchNode
    if(node < 0) throw new NegativeIndex
  }

  override def addEdge(firstNode: Int, secondNode: Int): Graph = {
    validate(firstNode)
    validate(secondNode)
    if(firstNode != secondNode){
      neighbours = neighbours
        .updated(firstNode, neighbours(firstNode) + secondNode)
        .updated(secondNode, neighbours(secondNode) + firstNode)
    }
    this
  }

  override def edgesNumber: Int = neighbours.foldLeft(0)(_ + _.size) / 2
}
