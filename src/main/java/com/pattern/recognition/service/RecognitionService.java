package com.pattern.recognition.service;

import com.pattern.recognition.model.RecognitionRequest;
import com.pattern.recognition.model.SpaceLine;
import com.pattern.recognition.model.SpacePoint;

import java.awt.*;
import java.util.SortedSet;

public interface RecognitionService {

    void addPointInSpace(RecognitionRequest request);

    SortedSet<SpacePoint> retrieveSpace();

    SortedSet<SpaceLine> retrieveLines(int numberOfPoints);

    void deleteSpace();


}
