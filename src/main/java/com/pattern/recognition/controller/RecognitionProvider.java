package com.pattern.recognition.controller;

import com.pattern.recognition.model.Line;
import com.pattern.recognition.model.Point;
import com.pattern.recognition.model.PointRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

public interface RecognitionProvider {

    @PostMapping(value = "/point",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> addPointInSpace(@RequestBody PointRequest request);

    @GetMapping(value = "/space",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Point>> retrieveSpace();

    @GetMapping(value = "/lines/{collinearPoints}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Set<Line>> retrieveLines(@PathVariable("collinearPoints") Integer numberOfPoints);

    @DeleteMapping(value = "/space",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deleteSpace();

}
