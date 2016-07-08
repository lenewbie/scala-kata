package org.acme.scalakata.graph.weighted


import org.acme.scalakata.graph.{NodeNotFound, PathFromEmptyGraph, PathFromNotExistingNode}
import org.scalatest.{FunSpec, MustMatchers}

class DijakstraAlgorithmSpec extends FunSpec with MustMatchers {


  // should behave like BFS when all edges have identical weight

  describe("factory method") {

    it("throws PathFromEmptyGraph when pass empty Graph") {
      intercept[PathFromEmptyGraph] {
        DijakstraAlgorithm(WeightedNeighboursList(0), 42)
      }
    }

    it("throws PathFromNotExistingNode when try to construct from not existing node") {
      intercept[PathFromNotExistingNode] {
        DijakstraAlgorithm(WeightedNeighboursList(1), 1)
      }
    }

    it("throws PathFromNotExistingNode when try to construct from negative node") {
      intercept[PathFromNotExistingNode] {
        DijakstraAlgorithm(WeightedNeighboursList(1), -1)
      }
    }
  }

  describe("existsPathTo") {

    it("existsPathTo throws NodeNotFound when given not existing vertex") {
      val path = DijakstraAlgorithm(WeightedNeighboursList(1), 0)
      intercept[NodeNotFound] {
        path.existsPathTo(1)
      }
    }

    it("exists path from root element") {
      val graph: WeightedNeighboursList = WeightedNeighboursList(1)
      assertExistPathBetween(graph, 0, 0)
    }

    it("returns false when argument is not connected") {
      val path = DijakstraAlgorithm(WeightedNeighboursList(2), 0)
      path.existsPathTo(1) mustBe false
    }

    it("returns true when there is obvious edge between 3 elements") {
      val graph = WeightedNeighboursList(3)
      graph.addEdge(0,1)
      graph.addEdge(1,2)

      assertExistPathBetween(graph, 0, 2)
    }

    it("returns true when there is obvious edge between 4 elements") {
      val graph = WeightedNeighboursList(4)
      graph.addEdge(0,1)
      graph.addEdge(1,2)
      graph.addEdge(2,3)

      assertExistPathBetween(graph, 0, 3)
    }

    def assertExistPathBetween(graph: WeightedNeighboursList, startNode: Int, destination: Int) =
      DijakstraAlgorithm(graph, startNode)
        .existsPathTo(destination) mustBe true
  }

  describe("getPathTo") {

    it("throws NodeNotFound when given not existing node") {
      val path = DijakstraAlgorithm(WeightedNeighboursList(1), 0)
      intercept[NodeNotFound] {
        path.getPathTo(1)
      }
    }

    it("path from root element to itself consist of one step") {
      val path = DijakstraAlgorithm(WeightedNeighboursList(13), 0)
      path.getPathTo(0) mustBe List(0)
    }

    it("returns empty list when given element is not connected") {
      val path = DijakstraAlgorithm(WeightedNeighboursList(13), 0)
      path.getPathTo(1) mustBe List.empty[Int]
    }

    it("returns root and argument when root is connected to argument") {
      val graph = WeightedNeighboursList(13)
      graph.addEdge(0, 1)

      val path = DijakstraAlgorithm(graph, 0)
      path.getPathTo(1) mustBe List(0, 1)
    }

    it("returns true when there is obvious edge between 3 elements") {
      val graph = WeightedNeighboursList(13)
      graph.addEdge(0,1)
      graph.addEdge(1,2)
      val path = DijakstraAlgorithm(graph, 0)
      path.getPathTo(2) mustBe List(0, 1, 2)
    }

    it("getPathTo returns proper path when there is edge between 3 elements") {
      val graph = WeightedNeighboursList(13)
      graph.addEdge(1, 2)
      graph.addEdge(1, 0)
      val path = DijakstraAlgorithm(graph, 0)
      path.getPathTo(2) mustBe List(0, 1, 2)
    }

    it("getPathTo returns empty list when no edge between given object and root") {
      val graph = WeightedNeighboursList(13)
      graph.addEdge(1,2)
      val path = DijakstraAlgorithm(graph, 0)
      path.getPathTo(2) mustBe List[Int]()
    }

    it("getPathTo traverse first all path then leaves") {
      /*
       0 +- 1 -- 3 -- 4
         |            |
         +- 2 --------+
       */
      val graph = WeightedNeighboursList(13)
      graph.addEdge(0,1)
      graph.addEdge(0,2)
      graph.addEdge(1,3)
      graph.addEdge(3,4)
      graph.addEdge(2,4)

      val path = DijakstraAlgorithm(graph, 0)

      path.getPathTo(4) mustBe List(0, 2, 4)
    }
  }

  // weight specific stuff
  describe("relax start node") {
    ignore("") {
      /*
       0 +- 1 -- 3 -- 4
         |            |
         +- 2 --------+
       */
      val graph = WeightedNeighboursList(13)
      graph.addEdge(0,1)
      graph.addEdge(0,2)
      graph.addEdge(1,3)
      graph.addEdge(3,4)
      graph.addEdge(2,4)

      val path = new DijakstraAlgorithmRelaxingOnlyRoot(graph, 0)
      path.getPathTo(4) mustBe List(0, 2, 4)
    }
  }

}
