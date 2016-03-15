package org.acme.scalakata.graph

import org.scalatest.{FunSpec, MustMatchers}

class NeighboursListGraphSpec extends FunSpec with MustMatchers {

  describe("A Graph") {
    it("works") {
      val graph:Graph = NeighboursListGraph(0)
    }

    ignore("remember number of nodes") {
      val graph = NeighboursListGraph(1)
      graph.nodesNumber mustBe 1
    }
  }

  describe("addEdge") {
    ignore("throws NoSuchNode when try to add edge greater than number of edges") {
      intercept[NoSuchNode] {
        val graph = NeighboursListGraph(1)
        graph.addEdge(0, 1)
      }
    }

    ignore("throws NegativeIndex when ask about node with negative index") {
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
    ignore("returs 0 for new graph") {
      val graph = NeighboursListGraph(1)
      graph.edgesNumber mustBe 0
    }

    ignore("returs 1 after adding signe edge") {
      // when
      val graph = NeighboursListGraph(2)
      graph.addEdge(0,1)

      // then
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
