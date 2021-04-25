package com.pattern.recognition.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Comparator;

/**
 * This class represents a point in the plane
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class Point implements Comparable<Point> {

    @JsonIgnore
    public final Comparator<Point> slopeOrder = new ComparePointsBySlope();

    private int x;
    private int y;

    @Override
    public String toString() {
        return "[x=" + x + ",y=" + y + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Point)) {
            return false;
        }
        Point that = (Point) obj;
        return (this.x == that.x) && (this.y == that.y);
    }

    @Override
    public int compareTo(Point that) {
        if (y < that.y) {
            return -1;
        }
        if (y > that.y) {
            return 1;
        }
        return x - that.x;
    }

    public Double slopeTo(Point that) {
        Double slope;
        if (this.equals(that)) {
            slope = Double.NEGATIVE_INFINITY;
        } else if (this.x == that.getX()) {
            slope = Double.POSITIVE_INFINITY;
        } else {
            double numerator = that.getY() - this.getY();
            double denominator = that.getX() - this.getX();
            slope = numerator / denominator;
        }
        return slope;
    }

    public boolean slopeOrder(Point p1, Point p2) {
        return this.slopeTo(p1) < this.slopeTo(p2);
    }

    private class ComparePointsBySlope implements Comparator<Point> {

        /**
         * Compares two specified points p1 and p2 for order. Returns a negative
         * integer, zero, or a positive integer if p1 is less than, equal to, or
         * greater than p2. All three properties of the compare method as
         * specified in the Comparator interface are met.
         */
        @Override
        public int compare(Point p1, Point p2) {
            if (p1.equals(p2)) {
                return 0;
            }
            return slopeOrder(p1, p2) ? -1 : 1;
        }
    }
}
