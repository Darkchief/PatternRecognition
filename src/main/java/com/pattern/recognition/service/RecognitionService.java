package com.pattern.recognition.service;

import com.pattern.recognition.model.Line;
import com.pattern.recognition.model.Point;
import com.pattern.recognition.model.PointRequest;

import java.util.SortedSet;

/**
 * Interface for the service, here you will find the core methods
 */
public interface RecognitionService {

    void addPointInPlane(PointRequest request);

    SortedSet<Point> retrievePlane();

    SortedSet<Line> retrieveLines(Integer collinearPoints);

    void deletePlane();


}
