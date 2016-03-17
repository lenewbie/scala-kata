package org.acme.scalakata.graph

import org.scalatest.{MustMatchers, FunSpec}

class DepthFirstSearchSpec  extends FunSpec with MustMatchers {

  // https://github.com/lemastero/algorithms/blob/master/src/test/scala/lemastero/algorithms/graph/path/BreadthFirstSearchSpec.scala

  describe("factory method") {

    it("throws PathFromEmptyGraph when pass empty Graph") {
      intercept[PathFromEmptyGraph] {
        DepthFirstSearch(NeighboursListGraph(0), 42)
      }
    }

    ignore("throws PathFromNotExistingNode when try to construct from not existing node") {
      intercept[PathFromNotExistingNode] {
        DepthFirstSearch(NeighboursListGraph(1), 1)
      }
    }

    ignore("throws PathFromNotExistingNode when try to construct from negative node") {
      intercept[PathFromNotExistingNode] {
        DepthFirstSearch(NeighboursListGraph(1), -1)
      }
    }
  }

  describe("existsPathTo") {

    ignore("existsPathTo throws NodeNotFound when given not existing vertex") {
      val path = DepthFirstSearch(NeighboursListGraph(1), 0)
      intercept[NodeNotFound] {
        path.existsPathTo(1)
      }
    }

    ignore("exists path from root element") {
      val graph: NeighboursListGraph = NeighboursListGraph(1)
      assertExistPathBetween(graph, 0, 0)
    }

    ignore("returns false when argument is not connected") {
      val path = DepthFirstSearch(NeighboursListGraph(2), 0)
      path.existsPathTo(1) mustBe false
    }

    ignore("returns true when there is obvious edge between 3 elements") {
      val graph = NeighboursListGraph(3)
      graph.addEdge(0,1)
      graph.addEdge(1,2)

      assertExistPathBetween(graph, 0, 2)
    }

    ignore("returns true when there is obvious edge between 4 elements") {
      val graph = NeighboursListGraph(4)
      graph.addEdge(0,1)
      graph.addEdge(1,2)
      graph.addEdge(2,3)

      assertExistPathBetween(graph, 0, 3)
    }

    def assertExistPathBetween(graph: NeighboursListGraph, startNode: Int, destination: Int) =
      DepthFirstSearch(graph, startNode)
        .existsPathTo(destination) mustBe true
  }

  describe("getPathTo") {

    ignore("throws NodeNotFound when given not existing node") {
      val path = DepthFirstSearch(NeighboursListGraph(1), 0)
      intercept[NodeNotFound] {
        path.getPathTo(1)
      }
    }

    ignore("path from root element to itself consist of one step") {
      val path = DepthFirstSearch(NeighboursListGraph(13), 0)
      path.getPathTo(0) mustBe List(0)
    }

    ignore("returns empty list when given element is not connected") {
      val path = DepthFirstSearch(NeighboursListGraph(13), 0)
      path.getPathTo(1) mustBe List.empty[Int]
    }

    ignore("returns root and argument when root is connected to argument") {
      val graph = NeighboursListGraph(13)
      graph.addEdge(0, 1)

      val path = DepthFirstSearch(graph, 0)
      path.getPathTo(1) mustBe List(0, 1)
    }

    ignore("returns true when there is obvious edge between 3 elements") {
      val graph = NeighboursListGraph(13)
      graph.addEdge(0,1)
      graph.addEdge(1,2)
      val path = DepthFirstSearch(graph, 0)
      path.getPathTo(2) mustBe List(0, 1, 2)
    }

    ignore("getPathTo returns proper path when there is edge between 3 elements") {
      val graph = NeighboursListGraph(13)
      graph.addEdge(1, 2)
      graph.addEdge(1, 0)
      val path = DepthFirstSearch(graph, 0)
      path.getPathTo(2) mustBe List(0, 1, 2)
    }

    ignore("getPathTo returns empty list when no edge between given object and root") {
      val graph = NeighboursListGraph(13)
      graph.addEdge(1,2)
      val path = DepthFirstSearch(graph, 0)
      path.getPathTo(2) mustBe List[Int]()
    }
  }
}
