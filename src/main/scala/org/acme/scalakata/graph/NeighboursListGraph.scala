package org.acme.scalakata.graph

import scala.collection.mutable

case class NeighboursListGraph(nodesNumber:Int) extends Graph {

  private val neighbours:List[mutable.ListBuffer[Int]] =
    List.fill(nodesNumber)(new mutable.ListBuffer[Int])

  override def addEdge(firstNode: Int, secondNode: Int):Graph = {
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
