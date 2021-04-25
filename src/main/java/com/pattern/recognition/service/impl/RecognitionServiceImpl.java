package com.pattern.recognition.service.impl;

import com.pattern.recognition.exception.NotEnoughPointsException;
import com.pattern.recognition.exception.NotEnoughPointsRegisteredException;
import com.pattern.recognition.exception.PointAlreadyRegisteredException;
import com.pattern.recognition.model.Line;
import com.pattern.recognition.model.Point;
import com.pattern.recognition.model.PointRequest;
import com.pattern.recognition.service.RecognitionService;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Data
@Slf4j
@Service
@Accessors(chain = true)
public class RecognitionServiceImpl implements RecognitionService {

    private List<Point> plane;

    @Override
    public void addPointInPlane(PointRequest request) {
        Point spacePoint = new Point(request.getX(), request.getY());
        log.info("Adding {} point to the space", spacePoint);

        if (plane.stream().anyMatch(point -> point.equals(spacePoint))) {
            throw new PointAlreadyRegisteredException(String
                    .format("The input point %s was already inside the space", spacePoint));
        }

        plane.add(spacePoint);
    }

    @Override
    public List<Point> retrievePlane() {
        log.info("Retrieve plane: {}", plane);
        return plane;
    }

    @Override
    public SortedSet<Line> retrieveLines(Integer collinearPoints) {
        SortedSet<Line> lines = new TreeSet<>();
        if (collinearPoints < 2) {
            throw new NotEnoughPointsException("At least 2 collinear points are required to generate a segment");
        }

        if (plane.size() < 2) {
            throw new NotEnoughPointsRegisteredException("In order to generate a segment, the plane must contain " +
                    "at least 2 points");
        }

        for (Point originPoint : plane) {

            // For each point in the plane, order the remaining points by the slope they have respect
            // to the point we are considering.
            List<Point> slopeOrderedPlane = plane.stream()
                    .sorted((o1, o2) -> originPoint.getSlopeOrder().compare(o1, o2))
                    .collect(Collectors.toList());

            Line referenceLine = new Line();
            for (Point orderedPoint : slopeOrderedPlane) {

                // Now we have all the points ordered by slope, as soon as a point with a different slope is found,
                // we can save the segment obtained so far and start a new segment
                if (!CollectionUtils.isEmpty(referenceLine.getSegment())
                        && !referenceLine.first().slopeTo(orderedPoint).equals(referenceLine.last().slopeTo(orderedPoint))) {

                    // We only save the segment if it has at least {collinearPoints} collinear points
                    if (referenceLine.getSegment().size() >= collinearPoints) {
                        lines.add(referenceLine);
                    }
                    referenceLine = new Line().addPoint(originPoint);
                }
                referenceLine.addPoint(orderedPoint);
            }

            if (referenceLine.getSegment().size() >= collinearPoints) {
                lines.add(referenceLine);
            }
        }

        log.debug("Lines retrieved: {}", lines);
        return lines;
    }

    @Override
    public void deletePlane() {
        log.info("Delete all points from space");
        plane = new ArrayList<>();
    }
}