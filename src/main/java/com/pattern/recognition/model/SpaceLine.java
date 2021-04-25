package com.pattern.recognition.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.SortedSet;
import java.util.TreeSet;

@Data
@Accessors(chain = true)
public class SpaceLine implements Comparable<SpaceLine> {

    private SortedSet<SpacePoint> linePoints;

    public SpaceLine() {
        this.linePoints = new TreeSet<>();
    }

    public SpacePoint first() {
        return this.linePoints.first();
    }

    public SpacePoint last() {
        return this.linePoints.last();
    }

    public SpaceLine addPoint(SpacePoint spacePoint) {
        this.linePoints.add(spacePoint);
        return this;
    }

    @Override
    public int compareTo(SpaceLine other) {
        if (this.linePoints.size() == 0 && other.getLinePoints().size() == 0) {
            return 0;
        }
        if (this.linePoints.size() == 0) {
            return -1;
        }
        if (other.getLinePoints().size() == 0) {
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