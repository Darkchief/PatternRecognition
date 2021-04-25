package com.pattern.recognition.service;

import com.pattern.recognition.model.Line;
import com.pattern.recognition.model.Point;
import com.pattern.recognition.model.PointRequest;

import java.util.List;
import java.util.Set;

public interface RecognitionService {

    void addPointInSpace(PointRequest request);

    List<Point> retrieveSpace();

    Set<Line> retrieveLines(Integer collinearPoints);

    void deleteSpace();


}
