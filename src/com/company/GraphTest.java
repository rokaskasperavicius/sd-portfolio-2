package com.company;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
  public static AdjacencyGraph firstAdjGraph(){
    AdjacencyGraph newG = new AdjacencyGraph();

    Vertex v1 = new Vertex("v1");
    Vertex v2 = new Vertex("v2");
    Vertex v3 = new Vertex("v3");
    Vertex v4 = new Vertex("v4");
    Vertex v5 = new Vertex("v5");
    Vertex v6 = new Vertex("v6");

    newG.addVertex(v1);
    newG.addVertex(v2);
    newG.addVertex(v3);
    newG.addVertex(v4);
    newG.addVertex(v5);
    newG.addVertex(v6);

    newG.addEdge(v1, v2,1);
    newG.addEdge(v1, v5,6);
    newG.addEdge(v1, v3,3);

    newG.addEdge(v2, v3,2);
    newG.addEdge(v2, v4,3);
    newG.addEdge(v2, v5,5);

    newG.addEdge(v3, v4,5);
    newG.addEdge(v3, v6,2);

    newG.addEdge(v4, v5,2);
    newG.addEdge(v4, v6,4);

    newG.addEdge(v5, v6,1);

    return newG;
  }

  public static AdjacencyGraph secondAdjGraph(){
    AdjacencyGraph newG = new AdjacencyGraph();

    Vertex v1 = new Vertex("v1");
    Vertex v2 = new Vertex("v2");
    Vertex v3 = new Vertex("v3");
    Vertex v4 = new Vertex("v4");
    Vertex v5 = new Vertex("v5");
    Vertex v6 = new Vertex("v6");

    newG.addVertex(v1);
    newG.addVertex(v2);
    newG.addVertex(v3);
    newG.addVertex(v4);
    newG.addVertex(v5);
    newG.addVertex(v6);

    newG.addEdge(v1, v2,5);

    newG.addEdge(v2, v3,8);
    newG.addEdge(v2, v4,5);

    newG.addEdge(v3, v5,9);

    newG.addEdge(v4, v5,3);
    newG.addEdge(v4, v6,3);

    newG.addEdge(v5, v6,1);

    return newG;
  }

  AdjacencyGraph first = firstAdjGraph();
  AdjacencyGraph second = secondAdjGraph();

  @Test
  public void testFirstGraph() {
    first.PrimsMST();

    // v1
    assertNull(first.Vertices.get(0).getPrev());
    assertEquals(first.Vertices.get(0).dist, Integer.MAX_VALUE);

    // v2
    assertEquals(first.Vertices.get(1).getPrev(), first.Vertices.get(0));
    assertEquals(first.Vertices.get(1).dist, 1);

    // v3
    assertEquals(first.Vertices.get(2).getPrev(), first.Vertices.get(1));
    assertEquals(first.Vertices.get(2).dist, 2);

    // v4
    assertEquals(first.Vertices.get(3).getPrev(), first.Vertices.get(4));
    assertEquals(first.Vertices.get(3).dist, 2);

    // v5
    assertEquals(first.Vertices.get(4).getPrev(), first.Vertices.get(5));
    assertEquals(first.Vertices.get(4).dist, 1);

    // v6
    assertEquals(first.Vertices.get(5).getPrev(), first.Vertices.get(2));
    assertEquals(first.Vertices.get(5).dist, 2);
  }

  @Test
  public void testSecondGraph() {
    second.PrimsMST();

    // v1
    assertNull(second.Vertices.get(0).getPrev());
    assertEquals(second.Vertices.get(0).dist, Integer.MAX_VALUE);

    // v2
    assertEquals(second.Vertices.get(1).getPrev(), second.Vertices.get(0));
    assertEquals(second.Vertices.get(1).dist, 5);

    // v3
    assertEquals(second.Vertices.get(2).getPrev(), second.Vertices.get(1));
    assertEquals(second.Vertices.get(2).dist, 8);

    // v4
    assertEquals(second.Vertices.get(3).getPrev(), second.Vertices.get(1));
    assertEquals(second.Vertices.get(3).dist, 5);

    // v5
    assertEquals(second.Vertices.get(4).getPrev(), second.Vertices.get(3));
    assertEquals(second.Vertices.get(4).dist, 3);

    // v6
    assertEquals(second.Vertices.get(5).getPrev(), second.Vertices.get(4));
    assertEquals(second.Vertices.get(5).dist, 1);
  }
}
