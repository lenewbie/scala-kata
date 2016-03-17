package org.acme.scalakata.graph

class PathFromEmptyGraph extends RuntimeException
class PathFromNotExistingNode extends RuntimeException
class NodeNotFound extends RuntimeException

trait PathFinder {

  def existsPathTo(destination:Int): Boolean

  def getPathTo(destination:Int): List[Int]

}
