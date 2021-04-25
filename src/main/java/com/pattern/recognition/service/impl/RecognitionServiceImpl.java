package com.pattern.recognition.service.impl;

import com.pattern.recognition.exception.NotEnoughPointsException;
import com.pattern.recognition.exception.NotEnoughPointsRegisteredException;
import com.pattern.recognition.exception.SpacePointAlreadyRegisteredException;
import com.pattern.recognition.model.SpaceLine;
import com.pattern.recognition.model.SpacePoint;
import com.pattern.recognition.model.SpacePointRequest;
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

    private List<SpacePoint> plane;

    @Override
    public void addPointInSpace(SpacePointRequest request) {
        SpacePoint spacePoint = new SpacePoint(request.getX(), request.getY());
        log.info("Adding {} point to the space", spacePoint);

        if (plane.stream().anyMatch(point -> point.equals(spacePoint))) {
            throw new SpacePointAlreadyRegisteredException(String
                    .format("The input point %s was already inside the space", spacePoint));
        }

        plane.add(spacePoint);
    }

    @Override
    public List<SpacePoint> retrieveSpace() {
        log.info("Retrieve space: {}", plane);
        return plane;
    }

    @Override
    public SortedSet<SpaceLine> retrieveLines(Integer collinearPoints) {
        SortedSet<SpaceLine> lines = new TreeSet<>();
        if (collinearPoints < 2) {
            throw new NotEnoughPointsException("At least 2 collinear points are required to generate a segment");
        }

        if (plane.size() < 2) {
            throw new NotEnoughPointsRegisteredException("In order to generate a segment, the plane must contain " +
                    "at least 2 points");
        }

        for (SpacePoint originPoint : plane) {

            // For each point in the plane, order the remaining points by the slope they have respect
            // to the point we are considering.
            List<SpacePoint> collect = plane.stream()
                    .sorted((o1, o2) -> originPoint.getSlopeOrder().compare(o1, o2))
                    .collect(Collectors.toList());

            SpaceLine referenceLine = new SpaceLine();
            for (SpacePoint p : collect) {

                // Now we have all the points ordered by slope, as soon as a point with a different slope is found,
                // we can save the segment obtained so far and start a new segment
                if (!CollectionUtils.isEmpty(referenceLine.getLinePoints())
                        && !referenceLine.first().slopeTo(p).equals(referenceLine.last().slopeTo(p))) {

                    // We only save the segment if it has at least {numberOfPoints} points
                    if (referenceLine.getLinePoints().size() >= collinearPoints) {
                        lines.add(referenceLine);
                    }
                    referenceLine = new SpaceLine().addPoint(originPoint);
                }
                referenceLine.addPoint(p);
            }

            if (referenceLine.getLinePoints().size() >= collinearPoints) {
                lines.add(referenceLine);
            }
        }

        log.debug("Lines retrieved: {}", lines);
        return lines;
    }

    @Override
    public void deleteSpace() {
        log.info("Delete all points from space");
        plane = new ArrayList<>();
    }
}