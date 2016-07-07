package org.acme.scalakata.graph.weighted

import org.acme.scalakata.graph.{NodeNotFound, PathFinder, PathFromEmptyGraph, PathFromNotExistingNode}

import scala.collection.mutable.ListBuffer


object DijakstraAlgorithm {

  def apply(graph: WeightedGraph, start:Int) = {
    if(graph.nodesNumber == 0) throw new PathFromEmptyGraph
    if(start >= graph.nodesNumber) throw new PathFromNotExistingNode
    if(start < 0) throw new PathFromNotExistingNode

    new DijakstraAlgorithm(graph, start)
  }

}

class DijakstraAlgorithm(graph: WeightedGraph, start:Int) extends PathFinder {

  private val neighboursNodes: ListBuffer[Option[Int]] = ListBuffer.fill[Option[Int]](graph.nodesNumber)(None)

  initialize(List(start))

  private def initialize(toProcess:List[Int]): Unit = {
    if(toProcess.nonEmpty) {
      val currNode = toProcess.head
      val children = graph.getNeighbours(currNode).filter(neighboursNodes(_).isEmpty)
      children.foreach(neighboursNodes(_) = Some(currNode))
      initialize(toProcess.tail ++ children)
    }
  }

  override def existsPathTo(destination: Int): Boolean =
    if(destination >= graph.nodesNumber )
      throw new NodeNotFound
    else if(destination == start) true
    else neighboursNodes(destination).isDefined

  override def getPathTo(destination: Int): List[Int] =
    if(destination >= graph.nodesNumber )
      throw new NodeNotFound
    else if(destination == start) List(start)
    else if(neighboursNodes(destination).isEmpty) List[Int]()
    else getPathTo(destination, List[Int]())


  private def getPathTo(node:Int, soFar:List[Int]):List[Int] =
    if(node == start) start :: soFar
    else getPathTo(neighboursNodes(node).get, node :: soFar)

}
