package com.pattern.recognition.service.impl;

import com.google.common.collect.ImmutableList;
import com.pattern.recognition.exception.SpacePointAlreadyRegisteredException;
import com.pattern.recognition.model.SpaceLine;
import com.pattern.recognition.model.SpacePoint;
import com.pattern.recognition.model.SpacePointRequest;
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
        recognitionService = new RecognitionServiceImpl().setSpace(new ArrayList<>());

        SpacePoint spacePointToAdd = new SpacePoint(3, 4);
        SpacePointRequest request = new SpacePointRequest()
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

        spacePointToAdd = new SpacePoint(10, 5);
        recognitionService.addPointInSpace(new SpacePointRequest()
                .setX(spacePointToAdd.getX())
                .setY(spacePointToAdd.getY()));

        List<SpacePoint> space = recognitionService.retrieveSpace();
        assertThat(space).hasSize(2);
        assertTrue(space.contains(spacePointToAdd));
    }

    @Test
    void testDeleteSpace() {
        recognitionService = new RecognitionServiceImpl()
                .setSpace(Collections.singletonList(new SpacePoint(3, 4)));

        assertThat(recognitionService.retrieveSpace()).hasSize(1);
        recognitionService.deleteSpace();
        assertThat(recognitionService.retrieveSpace()).hasSize(0);
    }

    @Test
    void retrieveLines() {
        recognitionService = new RecognitionServiceImpl().setSpace(createCartesianPlane());

        Set<SpaceLine> spaceLines = recognitionService.retrieveLines(3);
        System.out.println(spaceLines);

    }

    private List<SpacePoint> createCartesianPlane() {
        return ImmutableList.of(
                new SpacePoint(3, 4),
                new SpacePoint(1, 6),
                new SpacePoint(2, 2),
                new SpacePoint(2, 5),
                new SpacePoint(3, 1),
                new SpacePoint(3, 3),
                new SpacePoint(3, 2),
                new SpacePoint(3, 5),
                new SpacePoint(5, 2),
                new SpacePoint(5, 5),
                new SpacePoint(4, 4),
                new SpacePoint(4, 3)
        );
    }
}