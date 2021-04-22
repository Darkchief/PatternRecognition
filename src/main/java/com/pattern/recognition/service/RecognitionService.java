package com.pattern.recognition.service;

import com.pattern.recognition.model.SpacePointRequest;
import com.pattern.recognition.model.SpaceLine;
import com.pattern.recognition.model.SpacePoint;

import java.util.SortedSet;

public interface RecognitionService {

    void addPointInSpace(SpacePointRequest request);

    SortedSet<SpacePoint> retrieveSpace();

    SortedSet<SpaceLine> retrieveLines(int numberOfPoints);

    void deleteSpace();


}
