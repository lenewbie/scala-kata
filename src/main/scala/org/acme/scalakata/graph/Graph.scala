package org.acme.scalakata.graph

case object NoSuchNode extends RuntimeException
case object NegativeIndex extends RuntimeException

trait Graph {

  def addEdge(firstNode:Int, secondNode:Int): Graph

  def getNeighbours(node:Int): List[Int]

  def nodesNumber: Int

  def edgesNumber: Int

}
