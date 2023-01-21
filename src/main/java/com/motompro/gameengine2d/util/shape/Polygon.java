package com.motompro.gameengine2d.util.shape;

import com.motompro.gameengine2d.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Polygon extends Shape {

    private List<Vector2> vertices;
    private List<Vector2> rotatedVertices;
    private List<Vector2> edgeVectors;
    private double rotation = 0;

    public Polygon(List<Vector2> vertices) {
        setVertices(vertices);
    }

    public List<Vector2> getVertices() {
        return rotatedVertices;
    }

    /**
     * Get the vectors coresponding to the direction and the length of every faces.
     * @return A {@link List} containings the edge vectors
     */
    public List<Vector2> getEdgeVectors() {
        return edgeVectors;
    }

    /**
     * Get the normal vectors of every face. If the vertices are defined clockwise, the normal vector will be pointing
     * to the outside of the polygon (and to the inside if counterclockwise).
     * @return A {@link List} containings the normal vectors (in the same order as the vertices)
     */
    public List<Vector2> getNormalVectors() {
       return edgeVectors.stream()
                .map(vector -> Vector2.of(-vector.getY(), vector.getX()).normalize())
                .collect(Collectors.toList());
    }

    /**
     * Set the vertices of this polygon. Two consecutive vertices must be adjacent. The first and the last vertices are
     * considered as adjacent.
     * @param vertices The vertices
     */
    public void setVertices(List<Vector2> vertices) {
        if(vertices == null)
            throw new IllegalArgumentException("vertices can't be null");
        if(vertices.size() < 3)
            throw new IllegalArgumentException("a polygon must have at least 3 vertices");
        this.vertices = vertices;
        setRotation(rotation);
        computeEdgeVectors();
    }

    private void computeEdgeVectors() {
        this.edgeVectors = new ArrayList<>();
        // Compute edges vector
        for(int i = 0; i < rotatedVertices.size() - 1; i++)
            edgeVectors.add(rotatedVertices.get(i + 1).copy().subtract(rotatedVertices.get(i)));
        edgeVectors.add(rotatedVertices.get(0).copy().subtract(rotatedVertices.get(rotatedVertices.size() - 1)));
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation % (Math.PI * 2);
        this.rotatedVertices = new ArrayList<>();
        vertices.forEach(vertice -> {
            rotatedVertices.add(vertice.copy().rotate(rotation));
        });
        computeEdgeVectors();
    }

    @Override
    public double getPerimeter() {
        return edgeVectors.stream().mapToDouble(Vector2::length).sum();
    }
}
