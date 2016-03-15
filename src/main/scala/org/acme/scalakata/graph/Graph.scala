package org.acme.scalakata.graph

class NoSuchNode extends RuntimeException
class NegativeIndex extends RuntimeException

trait Graph {

  def addEdge(firstNode:Int, secondNode:Int): Graph

  def getNeighbours(node:Int): List[Int]

  def nodesNumber: Int

  def edgesNumber: Int

}
