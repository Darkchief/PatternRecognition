package com.pattern.recognition.controller;

import com.pattern.recognition.model.SpacePointRequest;
import com.pattern.recognition.model.SpaceLine;
import com.pattern.recognition.model.SpacePoint;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.SortedSet;

public interface RecognitionProvider {

    @PostMapping(value = "/point",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> addPointInSpace(@RequestBody SpacePointRequest request);

    @GetMapping(value = "/space",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SortedSet<SpacePoint>> retrieveSpace();

    @GetMapping(value = "/lines/{numberOfPoints}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SortedSet<SpaceLine>> retrieveLines(@PathVariable(name = "numberOfPoints") int numberOfPoints);

    @DeleteMapping(value = "/space",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deleteSpace();

}
