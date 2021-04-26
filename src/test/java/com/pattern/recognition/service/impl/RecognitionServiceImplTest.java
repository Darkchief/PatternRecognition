package com.pattern.recognition.service.impl;

import com.pattern.recognition.exception.PointAlreadyRegisteredException;
import com.pattern.recognition.model.Line;
import com.pattern.recognition.model.Point;
import com.pattern.recognition.model.PointRequest;
import com.pattern.recognition.service.RecognitionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class RecognitionServiceImplTest {

    private RecognitionService recognitionService;

    @Test
    public void addPointTest() {
        recognitionService = new RecognitionServiceImpl().setPlane(new TreeSet<>());

        Point pointToAdd = new Point(3, 4);
        PointRequest request = new PointRequest()
                .setX(pointToAdd.getX())
                .setY(pointToAdd.getY());

        recognitionService.addPointInPlane(request);
        assertThat(recognitionService.retrievePlane()).hasSize(1);
        assertTrue(recognitionService.retrievePlane().contains(pointToAdd));

        try {
            recognitionService.addPointInPlane(request);
        } catch (PointAlreadyRegisteredException ex) {
            log.info("The point {} has not been added to the plane because it already exists", pointToAdd);
        }
        // Verify that point [3,4], already present in the plane, is not added to it
        assertThat(recognitionService.retrievePlane()).hasSize(1);

        pointToAdd = new Point(10, 5);
        recognitionService.addPointInPlane(new PointRequest()
                .setX(pointToAdd.getX())
                .setY(pointToAdd.getY()));

        SortedSet<Point> plane = recognitionService.retrievePlane();
        assertThat(plane).hasSize(2);
        assertTrue(plane.contains(pointToAdd));
    }

    @Test
    void deletePlaneTest() {
        recognitionService = new RecognitionServiceImpl().setPlane(createCartesianPlane());

        recognitionService.deletePlane();
        assertThat(recognitionService.retrievePlane()).hasSize(0);
    }

    @Test
    void retrieveLinesTest() {
        recognitionService = new RecognitionServiceImpl().setPlane(createCartesianPlane());

        Set<Line> lines = recognitionService.retrieveLines(3);
        assertThat(lines).hasSize(6);
        assertTrue(lines.contains(expectedLine()));

    }

    private SortedSet<Point> createCartesianPlane() {
        SortedSet<Point> plane = new TreeSet<>();
        plane.add(new Point(3, 4));
        plane.add(new Point(1, 6));
        plane.add(new Point(2, 2));
        plane.add(new Point(2, 5));
        plane.add(new Point(3, 1));
        plane.add(new Point(3, 3));
        plane.add(new Point(3, 2));
        plane.add(new Point(3, 5));
        plane.add(new Point(5, 2));
        plane.add(new Point(5, 5));
        plane.add(new Point(4, 4));
        plane.add(new Point(4, 3));
        return plane;
    }

    private Line expectedLine() {
        return new Line()
                .addPoint(new Point(3, 4))
                .addPoint(new Point(5, 2))
                .addPoint(new Point(2, 5))
                .addPoint(new Point(1, 6))
                .addPoint(new Point(4, 3));
    }
}