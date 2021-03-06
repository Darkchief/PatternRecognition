package com.pattern.recognition.controller;

import com.pattern.recognition.model.Line;
import com.pattern.recognition.model.Point;
import com.pattern.recognition.model.PointRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.SortedSet;

/**
 * Interface for the controller, here you will find the exposed api
 */
public interface RecognitionProvider {

    @PostMapping(value = "/point",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> addPointInPlane(@RequestBody PointRequest request);

    @GetMapping(value = "/space",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<SortedSet<Point>> retrievePlane();

    @GetMapping(value = "/lines/{collinearPoints}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<SortedSet<Line>> retrieveLines(@PathVariable("collinearPoints") Integer collinearPoints);

    @DeleteMapping(value = "/space",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deletePlane();

}
