package org.acme.scalakata.graph

import org.scalatest.{FunSpec, MustMatchers}

class NeighboursListGraphSpec extends FunSpec with MustMatchers {

  describe("A Graph") {
    it("works") {
      val graph:Graph = NeighboursListGraph(0)
    }

    it("remembers number of nodes") {
      val graph = NeighboursListGraph(1)
      graph.nodesNumber mustBe 1
    }
  }

  describe("addEdge") {
    it("throws NoSuchNode when called with node index greater or equal to number of nodes") {
      val graph = NeighboursListGraph(1)
      intercept[NoSuchNode] {
        graph.addEdge(0, 1)
      }
      intercept[NoSuchNode] {
        graph.addEdge(1, 0)
      }
    }

    it("throws NegativeIndex when called with negative node index") {
      val graph = NeighboursListGraph(3)
      intercept[NegativeIndex] {
        graph.addEdge(-1, 0)
      }
      intercept[NegativeIndex] {
        graph.addEdge(0, -1)
      }
    }
  }

  describe("edgesNumber") {
    it("returns 0 for new graph") {
      val graph = NeighboursListGraph(1)
      graph.edgesNumber mustBe 0
    }

    it("returns 1 after adding single edge") {
      val graph = NeighboursListGraph(2)
      graph.addEdge(0,1)

      graph.edgesNumber mustBe 1
    }

    it("returns number of added edges") {
      val graph = NeighboursListGraph(4)
      graph.addEdge(0,1)
      graph.addEdge(1,2)
      graph.addEdge(2,3)

      graph.edgesNumber mustBe 3
    }

    ignore("returns number of edges excluding duplicate edges") {
      val graph = NeighboursListGraph(2)
      graph.addEdge(0,1)
      graph.addEdge(0,1)

      graph.edgesNumber mustBe 1
    }
  }

  describe("getNeighbours") {
    ignore("throws NoSuchNode when ask about node with index greater than max") {
      val graph = NeighboursListGraph(1)
      intercept[NoSuchNode] {
        graph.getNeighbours(1)
      }
    }

    ignore("throws NegativeIndex when ask about node with negative index") {
      val graph = NeighboursListGraph(1)
      intercept[NegativeIndex] {
        graph.getNeighbours(-1)
      }
    }

    ignore("remember that one neighbour was added") {
      val graph = NeighboursListGraph(2)
      graph.addEdge(0,1)
      graph.getNeighbours(0) mustBe List(1)
    }

    ignore("remember that one neighbour was added on other end (this is undirected graph)") {
      val graph = NeighboursListGraph(2)
      graph.addEdge(0,1)
      graph.getNeighbours(1) mustBe List(0)
    }
  }

}
