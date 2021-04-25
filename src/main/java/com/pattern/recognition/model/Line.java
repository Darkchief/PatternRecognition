package com.pattern.recognition.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This class represents a line segment in the plane
 */
@Data
@Accessors(chain = true)
public class Line implements Comparable<Line> {

    private SortedSet<Point> segment;

    public Line() {
        this.segment = new TreeSet<>();
    }

    public Point first() {
        return this.segment.first();
    }

    public Point last() {
        return this.segment.last();
    }

    public Line addPoint(Point point) {
        this.segment.add(point);
        return this;
    }

    @Override
    public int compareTo(Line other) {
        if (this.segment.size() == 0 && other.getSegment().size() == 0) {
            return 0;
        }
        if (this.segment.size() == 0) {
            return -1;
        }
        if (other.getSegment().size() == 0) {
            return 1;
        }
        // This line is less than other
        if (this.first().compareTo(other.first()) < 0
                || (this.first().equals(other.first()) && this.last().compareTo(other.last()) < 0)) {
            return -1;
        }
        // This line is equals to other
        if (this.first().equals(other.first()) && this.last().equals(other.last())) {
            return 0;
        }

        // This line is greater than other
        return 1;
    }
}