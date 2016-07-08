package org.acme.scalakata.graph.weighted

import org.acme.scalakata.graph.{NodeNotFound, PathFinder, PathFromEmptyGraph, PathFromNotExistingNode}

import scala.collection.mutable.{ArrayBuffer, ListBuffer}


object DijakstraAlgorithm {

  def apply(graph: WeightedGraph, start:Int) = {
    if(graph.nodesNumber == 0) throw new PathFromEmptyGraph
    if(start >= graph.nodesNumber) throw new PathFromNotExistingNode
    if(start < 0) throw new PathFromNotExistingNode

    new DijakstraAlgorithm(graph, start)
  }

}

class DijakstraAlgorithm(graph: WeightedGraph, start:Int) extends PathFinder {

  protected val previous: ListBuffer[Option[Int]] = ListBuffer.fill[Option[Int]](graph.nodesNumber)(None)
  protected val relaxed = ListBuffer.fill[Option[Boolean]](graph.nodesNumber)(None)
  protected val weight = ArrayBuffer.fill[Option[Double]](graph.nodesNumber)(None)

  relaxStartNode()
  relaxRest()

  protected def relaxStartNode() = {
//    graph
//      .getNeighbours(start)
//      .foreach {
//        node =>
//          previous(node) = Some(start)
//          weight(node) = graph.getWeight(start, node)
//          relaxed(start) = Some(true)
//      }
  }

  def relaxRest() = {
    //
  }

  relaxNodes(List(start))

  private def relaxNodes(toProcess:List[Int]): Unit = {
    if(toProcess.nonEmpty) {
      val currNode = toProcess.head
      val children = graph.getNeighbours(currNode).filter(previous(_).isEmpty)
      children.foreach(previous(_) = Some(currNode))
      relaxNodes(toProcess.tail ++ children)
    }
  }

  override def existsPathTo(destination: Int): Boolean =
    if(destination >= graph.nodesNumber )
      throw new NodeNotFound
    else if(destination == start) true
    else previous(destination).isDefined

  override def getPathTo(destination: Int): List[Int] =
    if(destination >= graph.nodesNumber )
      throw new NodeNotFound
    else if(destination == start) List(start)
    else if(previous(destination).isEmpty) List[Int]()
    else getPathTo(destination, List[Int]())


  private def getPathTo(node:Int, soFar:List[Int]):List[Int] =
    if(node == start) start :: soFar
    else getPathTo(previous(node).get, node :: soFar)

}
