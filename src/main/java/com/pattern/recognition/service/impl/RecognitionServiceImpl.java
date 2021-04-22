package com.pattern.recognition.service.impl;

import com.pattern.recognition.model.RecognitionRequest;
import com.pattern.recognition.model.SpaceLine;
import com.pattern.recognition.model.SpacePoint;
import com.pattern.recognition.service.RecognitionService;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.SortedSet;
import java.util.TreeSet;

@Data
@Slf4j
@Service
@Accessors(chain = true)
public class RecognitionServiceImpl implements RecognitionService {

    private SortedSet<SpacePoint> space;

    @Override
    public void addPointInSpace(RecognitionRequest request) {
        SpacePoint point = new SpacePoint(request.getX(), request.getY());

        log.info("Adding {} point to the space", point);

        if (!space.add(point)) {
            log.info("The input point {} was already inside the space", point);
        }
    }

    @Override
    public SortedSet<SpacePoint> retrieveSpace() {
        log.info("Retrieve space: [{}]", space);
        return space;
    }

    @Override
    public SortedSet<SpaceLine> retrieveLines(int numberOfPoints) {
        return null;
    }

    @Override
    public void deleteSpace() {
        log.info("Delete all points from space");
        space = new TreeSet<>();
    }

}

