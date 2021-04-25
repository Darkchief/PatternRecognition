package com.pattern.recognition.service.impl;

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

import java.util.*;
import java.util.stream.Collectors;

@Data
@Slf4j
@Service
@Accessors(chain = true)
public class RecognitionServiceImpl implements RecognitionService {

    private List<SpacePoint> space;

    @Override
    public void addPointInSpace(SpacePointRequest request) {
        SpacePoint spacePoint = new SpacePoint(request.getX(), request.getY());
        log.info("Adding {} point to the space", spacePoint);

        if (space.stream().anyMatch(point -> point.equals(spacePoint))) {
            throw new SpacePointAlreadyRegisteredException(String
                    .format("The input point %s was already inside the space", spacePoint));
        }

        space.add(spacePoint);
    }

    @Override
    public List<SpacePoint> retrieveSpace() {
        log.info("Retrieve space: {}", space);
        return space;
    }

    @Override
    public Set<SpaceLine> retrieveLines(Integer numberOfPoints) {
        //        Sort punti in base allo slope rispetto ad uno di reference
        //        Scan dei punti per trovare tutti quelli maggiori di numberOfPoints collineari
        //        Ripetere per gli N -1 points
        SortedSet<SpaceLine> lines = new TreeSet<>();
        if (space.size() < 2) {
//            TODO: set error in controller advice
            throw new RuntimeException();
        }

        // Foreach point in space,
        for (SpacePoint originPoint : space) {

            // For each point in the plane, order the remaining points by the slope they have respect
            // to the point we are considering.
            List<SpacePoint> collect = space.stream()
                    .sorted((o1, o2) -> originPoint.getSlopeOrder().compare(o1, o2))
                    .collect(Collectors.toList());

            SpaceLine referenceLine = new SpaceLine();
            for (SpacePoint p : collect) {

                // Now we have all the points ordered by slope, as soon as a point with a different slope is found,
                // we can save the segment obtained so far and start a new segment
                if (!CollectionUtils.isEmpty(referenceLine.getLinePoints())
                        && !referenceLine.first().slopeTo(p).equals(referenceLine.last().slopeTo(p))) {

                    // We only save the segment if it has at least {numberOfPoints} points
                    if (referenceLine.getLinePoints().size() >= numberOfPoints) {
                        lines.add(referenceLine);
                    }
                    referenceLine = new SpaceLine().addPoint(originPoint);
                }
                referenceLine.addPoint(p);
            }

            if (referenceLine.getLinePoints().size() >= numberOfPoints) {
                lines.add(referenceLine);
            }
        }

        log.info("info [{}]", lines);

        return lines;
    }

    @Override
    public void deleteSpace() {
        log.info("Delete all points from space");
        space = new ArrayList<>();
    }

//    private SortedSet<SpaceLine> createAllTwoPointsSegments() {
//        SortedSet<SpaceLine> lineSortedSet = new TreeSet<>();
//
//        int i = 1;
//        for (SpacePoint spacePoint : space) {
//            SpaceLine spaceLine = new SpaceLine();
//            spaceLine.getLinePoints().add(spacePoint);
//            lineSortedSet.addAll(recursiveBuild(spaceLine, space.stream()
//                    .skip(i)
//                    .limit(space.size() - 1)
//                    .collect(Collectors.toSet())));
//            i++;
//        }
////
////        int i = 1;
////        for (SpacePoint spacePoint : space) {
////            SpaceLine line = new SpaceLine();
////            line.getLinePoints().add(spacePoint);
////            lineSortedSet.addAll(recursiveBuild(line,
////                    space.stream()
////                            .skip(i)
////                            .limit(space.size() - 1)
////                            .collect(Collectors.toSet())));
////            i++;
////        }
//        return lineSortedSet;
//    }

//    private SortedSet<SpaceLine> recursiveBuild(SpaceLine originLine, Set<SpacePoint> subSpace) {
//        SortedSet<SpaceLine> subOriginLines = new TreeSet<>();
//        if (subSpace.size() == 0) {
//            subOriginLines.add(originLine);
//        } else {
//            for (SpacePoint subSpacePoint : subSpace) {
//                if (originLine.first().slopeTo(subSpacePoint).equals(originLine.last().slopeTo(subSpacePoint))) {
//                    originLine.getLinePoints().add(subSpacePoint);
//                }
//                subOriginLines.addAll(recursiveBuild(originLine, subSpace.stream()
//                        .skip(1)
//                        .limit(space.size() - 1)
//                        .collect(Collectors.toSet())));
//            }
//        }
//        return subOriginLines;
//    }

//    private SortedSet<SpaceLine> recursiveBuild(SpaceLine originLine, Set<SpacePoint> subSpace) {
//        SortedSet<SpaceLine> sortedSet = new TreeSet<>();
//        SpaceLine spaceLine = new SpaceLine();
//        if (subSpace.size() == 0) {
//            sortedSet.add(originLine);
//        } else {
//            SpacePoint subSpacePoint = subSpace.stream().findFirst().get();
//            if (originLine.first().slopeTo(subSpacePoint).equals(originLine.last().slopeTo(subSpacePoint))) {
//                originLine.getLinePoints().add(subSpacePoint);
//            }
//            sortedSet.addAll(recursiveBuild(originLine, subSpace.stream()
//                    .skip(1)
//                    .limit(subSpace.size() - 1)
//                    .collect(Collectors.toSet())));
//        }
//        return sortedSet;
//    }

//    private SpaceLine recursiveBuild(Set<SpacePoint> subSpace) {
//        SortedSet<SpaceLine> sortedSet = new TreeSet<>();
//        SpaceLine spaceLine = new SpaceLine();
//        if (subSpace.size() == 1) {
//            spaceLine.getLinePoints().add(subSpace.stream().findFirst().get());
//            sortedSet.add(spaceLine);
//        } else {
//
//            SpaceLine spaceLineWithLast = recursiveBuild(subSpace.stream()
//                    .skip(1)
//                    .limit(subSpace.size() - 1)
//                    .collect(Collectors.toSet()));
//
//
//            SpacePoint subSpacePoint = subSpace.stream().findFirst().get();
//            if (originLine.first().slopeTo(subSpacePoint).equals(originLine.last().slopeTo(subSpacePoint))) {
//                originLine.getLinePoints().add(subSpacePoint);
//            }
//
//        }
//        return spaceLine;
//    }
}






















