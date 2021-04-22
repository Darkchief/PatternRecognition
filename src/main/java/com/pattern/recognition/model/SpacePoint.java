package com.pattern.recognition.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class SpacePoint implements Comparable<SpacePoint> {

    private int x;
    private int y;

    @Override
    public String toString() {
        return "[x=" + x + ",y=" + y + "]";
    }

    @Override
    public int compareTo(SpacePoint that) {
        if (y < that.y) {
            return -1;
        }
        if (y > that.y) {
            return 1;
        }
        return x - that.x;
    }
}
