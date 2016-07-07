package org.acme.scalakata.graph.weighted

import org.acme.scalakata.graph.{NegativeIndex, NoSuchNode}
import org.scalatest.{FunSpec, MustMatchers}

class WeightedNeighboursListSpec extends FunSpec with MustMatchers {

  val randomWeight: Double = -1.0

  describe("A Graph") {
    it("is a graph") {
      val graph:WeightedGraph = WeightedNeighboursList(0)
    }

    it("remembers number of nodes") {
      val graph = WeightedNeighboursList(1)
      graph.nodesNumber mustBe 1
    }
  }

  describe("addEdge") {
    it("throws NoSuchNode when try to add edge greater than number of edges") {
      intercept[NoSuchNode] {
        val graph = WeightedNeighboursList(1)
        graph.addEdge(0, 1, randomWeight)
      }
    }

    it("throws NegativeIndex when ask about node with negative index") {
      val graph = WeightedNeighboursList(3)
      intercept[NegativeIndex] {
        graph.addEdge(-1, 0, randomWeight)
      }
      intercept[NegativeIndex] {
        graph.addEdge(0, -1, randomWeight)
      }
    }
  }

  describe("edgesNumber") {
    it("returns 0 for new graph") {
      val graph = WeightedNeighboursList(1)
      graph.edgesNumber mustBe 0
    }

    it("returns 1 after adding single edge") {
      // when
      val graph = WeightedNeighboursList(2)
      graph.addEdge(0, 1, randomWeight)

      // then
      graph.edgesNumber mustBe 1
    }
  }

  describe("getNeighbours") {
    it("throws NoSuchNode when ask about node with index greater than max") {
      val graph = WeightedNeighboursList(1)
      intercept[NoSuchNode] {
        graph.getNeighbours(1)
      }
    }

    it("throws NegativeIndex when ask about node with negative index") {
      val graph = WeightedNeighboursList(1)
      intercept[NegativeIndex] {
        graph.getNeighbours(-1)
      }
    }

    it("remembers that one neighbour was added") {
      val graph = WeightedNeighboursList(2)
      graph.addEdge(0, 1, randomWeight)
      graph.getNeighbours(0) mustBe List(1)
    }

    it("remembers that one neighbour was added on other end (this is undirected graph)") {
      val graph = WeightedNeighboursList(2)
      graph.addEdge(0, 1, randomWeight)
      graph.getNeighbours(1) mustBe List(0)
    }
  }

  describe("weight") {
    it("returns None if there is no edge") {
      val graph = WeightedNeighboursList(2)
      graph.getWeight(0, 1) mustBe None
    }

    it("returns weight of edge") {
      val graph = WeightedNeighboursList(2)
      graph.addEdge(0, 1, 42.0)
      graph.getWeight(0, 1) mustBe Some(42.0)
    }

    it("returns weight of edge from end to start") {
      val graph = WeightedNeighboursList(2)
      graph.addEdge(0, 1, 42.0)
      graph.getWeight(1, 0) mustBe Some(42.0)
    }

    it("returns 1 by default") {
      val graph = WeightedNeighboursList(2)
      graph.addEdge(0, 1)
      graph.getWeight(1, 0) mustBe Some(1.0)
    }
  }

}
