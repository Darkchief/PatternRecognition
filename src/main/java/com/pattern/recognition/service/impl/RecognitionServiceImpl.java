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

import java.util.*;
import java.util.stream.Collectors;

@Data
@Slf4j
@Service
@Accessors(chain = true)
public class RecognitionServiceImpl implements RecognitionService {

    private List<SpacePoint> space;
    private List<SpaceLine> lines;

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
//        SortedSet<SpaceLine> allTwoPointsSegments = createAllTwoPointsSegments();
//        log.info("retrieved lines: [{}]", allTwoPointsSegments);

//        return allTwoPointsSegments
//                .stream()
//                .filter(spaceLine -> spaceLine.getLinePoints().size() >= numberOfPoints)
//                .collect(Collectors.toSet());

        //        Sort punti in base allo slope rispetto ad uno di reference
        //        Scan dei punti per trovare tutti quelli maggiori di numberOfPoints collineari
        //        Ripetere per gli N -1 points


        log.info("info [{}]", space);
        List<SpacePoint> collect = new ArrayList<>();
//        for (SpacePoint point : space) {
//            collect = space.stream().sorted((o1, o2) -> point.getSlopeOrder().compare(o1, o2)).collect(Collectors.toList());
//
//        }

        SpacePoint point = space.stream().findFirst().get();
        collect = space.stream()
                .sorted((o1, o2) -> point.getSlopeOrder().compare(o1, o2))
                .collect(Collectors.toList());

        SortedSet<SpaceLine> lines1 = new TreeSet<>();
        SpaceLine referredLine = new SpaceLine();
        for (SpacePoint p : collect) {
            if (referredLine.getLinePoints().isEmpty()) {
                lines1.add(referredLine);
                referredLine.getLinePoints().add(point);
            } else {
                if (!referredLine.first().slopeTo(p).equals(referredLine.last().slopeTo(p))) {
                    referredLine = new SpaceLine();
                    referredLine.getLinePoints().add(point);
                }
                referredLine.getLinePoints().add(p);
            }
        }


        log.info("info [{}]", lines1);

        return lines1;
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






















