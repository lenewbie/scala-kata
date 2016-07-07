package org.acme.scalakata.graph.weighted

trait WeightedGraph {

  def addEdge(firstNode: Int, secondNode: Int, weight: Double): WeightedGraph

  def getNeighbours(node:Int): List[Int]

  def nodesNumber: Int

  def edgesNumber: Int

}
