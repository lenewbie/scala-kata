package org.acme.scalakata.graph

import org.scalatest.{MustMatchers, FunSpec}

class BreadthFirstSearchSpec  extends FunSpec with MustMatchers {

  describe("factory method") {

    it("throws PathFromEmptyGraph when pass empty Graph") {
      intercept[PathFromEmptyGraph] {
        BreadthFirstSearch(NeighboursListGraph(0), 42)
      }
    }

    it("throws PathFromNotExistingNode when try to construct from not existing node") {
      intercept[PathFromNotExistingNode] {
        BreadthFirstSearch(NeighboursListGraph(1), 1)
      }
    }

    it("throws PathFromNotExistingNode when try to construct from negative node") {
      intercept[PathFromNotExistingNode] {
        BreadthFirstSearch(NeighboursListGraph(1), -1)
      }
    }
  }

  describe("existsPathTo") {

    it("existsPathTo throws NodeNotFound when given not existing vertex") {
      val path = BreadthFirstSearch(NeighboursListGraph(1), 0)
      intercept[NodeNotFound] {
        path.existsPathTo(1)
      }
    }

    it("exists path from root element") {
      val graph: NeighboursListGraph = NeighboursListGraph(1)
      assertExistPathBetween(graph, 0, 0)
    }

    it("returns false when argument is not connected") {
      val path = BreadthFirstSearch(NeighboursListGraph(2), 0)
      path.existsPathTo(1) mustBe false
    }

    it("returns true when there is obvious edge between 3 elements") {
      val graph = NeighboursListGraph(3)
      graph.addEdge(0,1)
      graph.addEdge(1,2)

      assertExistPathBetween(graph, 0, 2)
    }

    it("returns true when there is obvious edge between 4 elements") {
      val graph = NeighboursListGraph(4)
      graph.addEdge(0,1)
      graph.addEdge(1,2)
      graph.addEdge(2,3)

      assertExistPathBetween(graph, 0, 3)
    }

    def assertExistPathBetween(graph: NeighboursListGraph, startNode: Int, destination: Int) =
      BreadthFirstSearch(graph, startNode)
        .existsPathTo(destination) mustBe true
  }

  describe("getPathTo") {

    it("throws NodeNotFound when given not existing node") {
      val path = BreadthFirstSearch(NeighboursListGraph(1), 0)
      intercept[NodeNotFound] {
        path.getPathTo(1)
      }
    }

    it("path from root element to itself consist of one step") {
      val path = BreadthFirstSearch(NeighboursListGraph(13), 0)
      path.getPathTo(0) mustBe List(0)
    }

    it("returns empty list when given element is not connected") {
      val path = BreadthFirstSearch(NeighboursListGraph(13), 0)
      path.getPathTo(1) mustBe List.empty[Int]
    }

    it("returns root and argument when root is connected to argument") {
      val graph = NeighboursListGraph(13)
      graph.addEdge(0, 1)

      val path = BreadthFirstSearch(graph, 0)
      path.getPathTo(1) mustBe List(0, 1)
    }

    it("returns true when there is obvious edge between 3 elements") {
      val graph = NeighboursListGraph(13)
      graph.addEdge(0,1)
      graph.addEdge(1,2)
      val path = BreadthFirstSearch(graph, 0)
      path.getPathTo(2) mustBe List(0, 1, 2)
    }

    it("getPathTo returns proper path when there is edge between 3 elements") {
      val graph = NeighboursListGraph(13)
      graph.addEdge(1, 2)
      graph.addEdge(1, 0)
      val path = BreadthFirstSearch(graph, 0)
      path.getPathTo(2) mustBe List(0, 1, 2)
    }

    it("getPathTo returns empty list when no edge between given object and root") {
      val graph = NeighboursListGraph(13)
      graph.addEdge(1,2)
      val path = BreadthFirstSearch(graph, 0)
      path.getPathTo(2) mustBe List[Int]()
    }

    it("getPathTo traverse first all path then leaves") {
      /*
       0 +- 1 -- 3 -- 4
         |            |
         +- 2 --------+
       */
      val graph = NeighboursListGraph(13)
      graph.addEdge(0,1)
      graph.addEdge(0,2)
      graph.addEdge(1,3)
      graph.addEdge(3,4)
      graph.addEdge(2,4)

      val path = BreadthFirstSearch(graph, 0)

      path.getPathTo(4) mustBe List(0, 2, 4)
    }
  }
}

