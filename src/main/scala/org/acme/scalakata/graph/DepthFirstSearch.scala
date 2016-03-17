package org.acme.scalakata.graph

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

case class DepthFirstSearch(graph: Graph, start:Int) extends PathFinder {

  private val previousNodes: ListBuffer[Option[Int]] =
    mutable.ListBuffer.fill(graph.nodesNumber)(None)

  if(graph.nodesNumber == 0) throw new PathFromEmptyGraph
  if(start < 0) throw new PathFromNotExistingNode
  if(start >= graph.nodesNumber) throw new PathFromNotExistingNode

  previousNodes(start) = Some(start)
  initialize(start)

  private def initialize(node: Int): Unit =
    graph
      .getNeighbours(node)
      .filter(isNotMarked)
      .foreach( e => {
        previousNodes(e) = Some(node)
        initialize(e)
      }
     )

  private def isNotMarked(elem:Int): Boolean =
    previousNodes(elem).isEmpty

  override def existsPathTo(destination: Int): Boolean =
    if(destination >= graph.nodesNumber)
      throw new NodeNotFound
    else ! isNotMarked(destination)

  override def getPathTo(destination: Int): List[Int] =
    if (destination >= graph.nodesNumber)
      throw new NodeNotFound
    else if (isNotMarked(destination)) List.empty[Int]
    else if (destination == start) List(start)
    else getPathTo(destination, List[Int]())

  private def getPathTo(destination: Int, soFar:List[Int]): List[Int] =
    if (isNotMarked(destination)) soFar
    else if (previousNodes(destination).contains(start))
      start :: destination :: soFar
    else
      getPathTo(previousNodes(destination).get, destination :: soFar)

}
