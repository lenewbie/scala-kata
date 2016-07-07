package org.acme.scalakata.graph.weighted

import org.acme.scalakata.graph.{NegativeIndex, NoSuchNode}
import scala.collection.mutable

object WeightedNeighboursList {
  def apply(nodesNumber:Int) = new WeightedNeighboursList(nodesNumber)
}

class WeightedNeighboursList(override val nodesNumber:Int) extends WeightedGraph {

  private val neighbours:List[mutable.ListBuffer[Int]] =
    List.fill(nodesNumber)(new mutable.ListBuffer[Int])

  override def addEdge(firstNode: Int, secondNode: Int, weight: Double):WeightedGraph = {
    if(firstNode >= nodesNumber || secondNode >= nodesNumber)
      throw new NoSuchNode
    else if(firstNode < 0 || secondNode < 0) throw new NegativeIndex
    else {
      neighbours(firstNode) += secondNode
      neighbours(secondNode) += firstNode
      this
    }
  }

  override def getNeighbours(node: Int): List[Int] = {
    if(node >= nodesNumber) throw new NoSuchNode
    else if(node < 0) throw new NegativeIndex
    else neighbours(node).toList
  }

  override def edgesNumber: Int =
    neighbours.count(_.nonEmpty) / 2

}
