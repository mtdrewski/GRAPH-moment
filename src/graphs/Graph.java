package graphs;

import java.util.Vector;

public class Graph {

    private Vector<Vertex> vertices;
    private Vector<Edge> edges;

    public Graph() {
        vertices = new Vector<Vertex>();
        edges = new Vector<Edge>();
    }

    public int size() {
        return vertices.size();
    }

    public void addVertex() {
        int newId = size() + 1;
        vertices.add(new Vertex(newId));
    }

    public void addVertex(float x, float y) {
        int newId = size() + 1;
        vertices.add(new Vertex(newId, x, y));
    }

    public void addEdge(int vertexId1, int vertexId2) throws LoopEdgeException, NonExistingVertexException {

        if (vertexId1 < 1 || vertexId1 > size() ||
            vertexId2 < 1 || vertexId2 > size()   ) {

            throw new NonExistingVertexException(vertexId1, vertexId2, size());
        }

        if (vertexId1 == vertexId2) {
            throw new LoopEdgeException(vertexId1);
        }

        //TODO maybe change not intuitive indexing
        edges.add(new Edge(vertices.get(vertexId1 - 1), vertices.get(vertexId2 - 1)));
    }

    public boolean contains(int vertexId) {
        for (Vertex vertex : vertices) {
            if (vertex.id() == vertexId)
                return true;
        }
        return false;
    }

    public boolean contains(int vertexId1, int vertexId2) {
        for (Edge edge : edges) {
            if ((edge.vert1().id() == vertexId1 && edge.vert2().id() == vertexId2) ||
                    (edge.vert1().id() == vertexId2 && edge.vert2().id() == vertexId1)) {

                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return vertices.toString() + ", " +edges.toString();
    }

    public static class LoopEdgeException extends RuntimeException { //TODO maybe create other file for this?

        int loopedVertex = 0;

        LoopEdgeException(int vertID) {
            loopedVertex = vertID;
        }

        @Override
        public String toString() {
            return "Tried to add a loop edge to " + loopedVertex + ".";
        }
    }

    public static class NonExistingVertexException extends RuntimeException { //TODO maybe create other file for this?

        int vertexId1;
        int vertexId2;
        int limit;

        NonExistingVertexException(int u, int v, int limit) {
            vertexId1 = u;
            vertexId2 = v;
            this.limit = limit;
        }

        @Override
        public String toString() {
            return "Tried to create an edge between non existing pair of vertices. Wrong Ids: " +
                    vertexId1 + " " + vertexId2 + ". Max available Id is: " + limit;
        }
    }
}
