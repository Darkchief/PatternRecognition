package com.pattern.recognition.controller.impl;

import com.pattern.recognition.controller.RecognitionProvider;
import com.pattern.recognition.model.Line;
import com.pattern.recognition.model.Point;
import com.pattern.recognition.model.PointRequest;
import com.pattern.recognition.service.RecognitionService;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.SortedSet;

/**
 * This is the Controller, here you will find all the implementation of the exposed API
 */
@Slf4j
@Setter
@Controller
@RequestMapping("")
@Accessors(chain = true)
public class RecognitionController implements RecognitionProvider {

    private RecognitionService recognitionService;

    /**
     * API exposed to add a point into the plane
     *
     * @param request - [x, y] point
     * @return 200 OK
     */
    @Override
    public ResponseEntity<Void> addPointInPlane(PointRequest request) {
        recognitionService.addPointInPlane(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * @return all the points in the plane
     */
    @Override
    public ResponseEntity<SortedSet<Point>> retrievePlane() {
        return ResponseEntity.status(HttpStatus.OK).body(recognitionService.retrievePlane());
    }

    /**
     * Get all line segments passing through at least {collinearPoints} points.
     *
     * @param collinearPoints minimum number of collinear points
     * @return a set of lines
     */
    @Override
    public ResponseEntity<SortedSet<Line>> retrieveLines(Integer collinearPoints) {
        return ResponseEntity.status(HttpStatus.OK).body(recognitionService.retrieveLines(collinearPoints));
    }

    /**
     * Delete all the points in the plane
     *
     * @return 200 OK
     */
    @Override
    public ResponseEntity<Void> deleteSpace() {
        recognitionService.deletePlane();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}


