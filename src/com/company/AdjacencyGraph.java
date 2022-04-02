package com.company;

import java.util.ArrayList;

public class AdjacencyGraph {
    ArrayList<Vertex> Vertices;
    public AdjacencyGraph(){
        Vertices = new ArrayList<Vertex>();
    }
    public void addVertex(Vertex v){
        Vertices.add(v);
    }
    public void addEdge(Vertex from, Vertex to, Integer weight){
        if(!(Vertices.contains(from) && Vertices.contains(to)))
        {
            System.out.println("Vertices missing from graph");
            return;
        }

        new Edge(from, to, weight);
        new Edge(to, from, weight);
    }

    public void PrintGraph() {
        for (int i = 0; i < Vertices.size(); i++){
            System.out.println("Vertex " + Vertices.get(i).name + " is connected to: ");
            Vertex current = Vertices.get(i);
            for (Edge e: current.OutEdge) {
                System.out.println(" " + e.to.name + " with weight: " + e.weight);
            }
        }
    }

    public void PrimsMST() {
      // Starting vertex
      Vertex start = Vertices.get(0);
      start.setVisited();

      int distance = 0;

      MinHeap<Edge> Q = new MinHeap<Edge>();

      // Fill min-heap only with edges which connect to the starting vertex
      for (Edge e: start.OutEdge) {
        Q.Insert(e);
      }

      while (!Q.isEmpty()) {
        // Extract minimum edge
        Edge edge = Q.extractMin();

        // If edge is pointing to unvisited vertex - pick it
        if (!edge.to.getVisited()) {
          // Set unvisited vertex properties
          edge.to.setPrev(edge.from);
          distance += edge.weight;
          edge.to.dist = edge.weight;
          edge.to.setVisited();

          // Map through all edges going from picked vertex
          for (Edge out: edge.to.OutEdge) {
            // If vertex which goes from picked vertex hasn't been picked - insert it into the min-heap
            if (!out.to.getVisited()) {
              Q.Insert(out);
            }
          }
        }
      }

      System.out.println("Total distance: " + distance);
      for (Vertex v: Vertices) {
        // If prev doesn't exists - vertex is the starting point
        if (v.getPrev() == null) {
          System.out.println(v.name + " is the starting point");
        } else {
          System.out.println(v.getPrev().name + " is connected with " + v.name + " (weight: " + v.dist + ")");
        }
      }
    }
}

class Vertex {
    String name;
    ArrayList<Edge> OutEdge;
    private Vertex prev;
    int dist;
    private boolean visited;

    public Vertex(String name){
        this.name = name;
        OutEdge = new ArrayList<>();

        this.prev = null;
        this.dist = Integer.MAX_VALUE;
        this.visited = false;
    }

    public void setPrev(Vertex prev) {
      this.prev = prev;
    }

    public Vertex getPrev() {
      return this.prev;
    }

    public boolean getVisited() {
      return this.visited;
    }

    public void setVisited() {
      this.visited = true;
    }
}

class Edge implements Comparable<Edge> {
    Vertex from;
    Vertex to;
    Integer weight;
    public Edge(Vertex from, Vertex to, Integer weight){
        this.from = from;
        this.to = to;
        this.weight = weight;

        from.OutEdge.add(this);
    }

    @Override
    public int compareTo(Edge e) {
    return this.weight.compareTo(e.weight);
  }
}