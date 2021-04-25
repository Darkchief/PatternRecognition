package com.pattern.recognition.service.impl;

import com.google.common.collect.ImmutableList;
import com.pattern.recognition.exception.SpacePointAlreadyRegisteredException;
import com.pattern.recognition.model.Line;
import com.pattern.recognition.model.Point;
import com.pattern.recognition.model.PointRequest;
import com.pattern.recognition.service.RecognitionService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RecognitionServiceImplTest {

    private RecognitionService recognitionService;

    @Test
    public void testAddPoint() {
        recognitionService = new RecognitionServiceImpl().setPlane(new ArrayList<>());

        Point spacePointToAdd = new Point(3, 4);
        PointRequest request = new PointRequest()
                .setX(spacePointToAdd.getX())
                .setY(spacePointToAdd.getY());

        recognitionService.addPointInSpace(request);
        assertThat(recognitionService.retrieveSpace()).hasSize(1);
        assertThat(recognitionService.retrieveSpace().contains(spacePointToAdd));

        try {
            recognitionService.addPointInSpace(request);
        } catch (SpacePointAlreadyRegisteredException ex) {

        }
        // Verify that point [3,4], already present in the space, is not added to it
        assertThat(recognitionService.retrieveSpace()).hasSize(1);

        spacePointToAdd = new Point(10, 5);
        recognitionService.addPointInSpace(new PointRequest()
                .setX(spacePointToAdd.getX())
                .setY(spacePointToAdd.getY()));

        List<Point> space = recognitionService.retrieveSpace();
        assertThat(space).hasSize(2);
        assertTrue(space.contains(spacePointToAdd));
    }

    @Test
    void testDeleteSpace() {
        recognitionService = new RecognitionServiceImpl()
                .setPlane(Collections.singletonList(new Point(3, 4)));

        assertThat(recognitionService.retrieveSpace()).hasSize(1);
        recognitionService.deleteSpace();
        assertThat(recognitionService.retrieveSpace()).hasSize(0);
    }

    @Test
    void retrieveLines() {
        recognitionService = new RecognitionServiceImpl().setPlane(createCartesianPlane());

        Set<Line> spaceLines = recognitionService.retrieveLines(3);
        assertThat(spaceLines).hasSize(6);
        assertTrue(spaceLines.contains(expectedLine()));

    }

    private List<Point> createCartesianPlane() {
        return ImmutableList.of(
                new Point(3, 4),
                new Point(1, 6),
                new Point(2, 2),
                new Point(2, 5),
                new Point(3, 1),
                new Point(3, 3),
                new Point(3, 2),
                new Point(3, 5),
                new Point(5, 2),
                new Point(5, 5),
                new Point(4, 4),
                new Point(4, 3)
        );
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