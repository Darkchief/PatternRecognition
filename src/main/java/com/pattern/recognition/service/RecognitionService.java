package com.pattern.recognition.service;

import com.pattern.recognition.model.SpaceLine;
import com.pattern.recognition.model.SpacePoint;
import com.pattern.recognition.model.SpacePointRequest;

import java.util.List;
import java.util.Set;

public interface RecognitionService {

    void addPointInSpace(SpacePointRequest request);

    List<SpacePoint> retrieveSpace();

    Set<SpaceLine> retrieveLines(Integer numberOfPoints);

    void deleteSpace();


}
