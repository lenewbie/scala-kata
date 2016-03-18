package org.acme.scalakata.graph

import org.scalatest.{FunSpec, MustMatchers}

class NeighboursListGraphSpec extends FunSpec with MustMatchers {

  describe("A Graph") {
    it("works") {
      val graph:Graph = NeighboursListGraph(0)
    }

    ignore("remembers number of nodes") {
      val graph = NeighboursListGraph(1)
      graph.nodesNumber mustBe 1
    }
  }

  describe("addEdge") {
    ignore("throws NoSuchNode when try to add edge greater than number of edges") {
      intercept[NoSuchNode.type] {
        val graph = NeighboursListGraph(1)
        graph.addEdge(0, 1)
      }
    }

    ignore("throws NegativeIndex when ask about node with negative index") {
      val graph = NeighboursListGraph(3)
      intercept[NegativeIndex.type] {
        graph.addEdge(-1, 0)
      }
      intercept[NegativeIndex.type] {
        graph.addEdge(0, -1)
      }
    }
  }

  describe("edgesNumber") {
    ignore("returns 0 for new graph") {
      val graph = NeighboursListGraph(1)
      graph.edgesNumber mustBe 0
    }

    ignore("returns 1 after adding single edge") {
      // when
      val graph = NeighboursListGraph(2)
      graph.addEdge(0,1)

      // then
      graph.edgesNumber mustBe 1
    }

    ignore("returns number of added edges") {
      val graph = NeighboursListGraph(4)
      graph.addEdge(0,1)
      graph.addEdge(1,2)
      graph.addEdge(2,3)

      graph.edgesNumber mustBe 3
    }
  }

  describe("getNeighbours") {
    ignore("throws NoSuchNode when ask about node with index greater than max") {
      val graph = NeighboursListGraph(1)
      intercept[NoSuchNode.type] {
        graph.getNeighbours(1)
      }
    }

    ignore("throws NegativeIndex when ask about node with negative index") {
      val graph = NeighboursListGraph(1)
      intercept[NegativeIndex.type] {
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
