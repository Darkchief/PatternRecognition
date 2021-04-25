package com.pattern.recognition.service;

import com.pattern.recognition.model.Line;
import com.pattern.recognition.model.Point;
import com.pattern.recognition.model.PointRequest;

import java.util.List;
import java.util.SortedSet;

public interface RecognitionService {

    void addPointInPlane(PointRequest request);

    List<Point> retrievePlane();

    SortedSet<Line> retrieveLines(Integer collinearPoints);

    void deletePlane();


}
