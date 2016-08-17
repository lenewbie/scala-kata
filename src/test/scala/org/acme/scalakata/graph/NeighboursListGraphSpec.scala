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
      val graph = NeighboursListGraph(2)
        .addEdge(0,1)

      graph.edgesNumber mustBe 1
    }

    ignore("returns number of added edges") {
      val graph =
        NeighboursListGraph(4)
          .addEdge(0,1)
          .addEdge(1,2)
          .addEdge(2,3)

      graph.edgesNumber mustBe 3
    }

    ignore("returns number of edges excluding duplicate edges") {
      val graph =
        NeighboursListGraph(2)
          .addEdge(0,1)
          .addEdge(0,1)

      graph.edgesNumber mustBe 1
    }

    ignore("returns number of edges excluding reversed edges") {
      val graph =
        NeighboursListGraph(2)
          .addEdge(0,1)
          .addEdge(1,0)

      graph.edgesNumber mustBe 1
    }

    ignore("returns number of edges excluding loops") {
      val graph = NeighboursListGraph(1)
      graph.addEdge(0,0)

      graph.edgesNumber mustBe 0
    }
  }

  describe("getNeighbours") {
    ignore("throws NoSuchNode when called with node index greater or equal to number of nodes") {
      val graph = NeighboursListGraph(1)
      intercept[NoSuchNode.type] {
        graph.getNeighbours(1)
      }
    }

    ignore("throws NegativeIndex when called with negative node index") {
      val graph = NeighboursListGraph(1)
      intercept[NegativeIndex.type] {
        graph.getNeighbours(-1)
      }
    }

    ignore("returns neighbours of requested node") {
      val graph = NeighboursListGraph(2)
        .addEdge(0,1)
      graph.getNeighbours(0) mustBe List(1)
    }

    ignore("returns neighbours of node that was used as second node when adding edge (this is undirected graph)") {
      val graph = NeighboursListGraph(2)
        .addEdge(0,1)

      graph.getNeighbours(1) mustBe List(0)
    }
  }

}
