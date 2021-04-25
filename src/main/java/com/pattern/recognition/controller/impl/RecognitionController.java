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

@Slf4j
@Setter
@Controller
@RequestMapping("")
@Accessors(chain = true)
public class RecognitionController implements RecognitionProvider {

    private RecognitionService recognitionService;

    @Override
    public ResponseEntity<Void> addPointInPlane(PointRequest request) {
        recognitionService.addPointInPlane(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<SortedSet<Point>> retrievePlane() {
        return ResponseEntity.status(HttpStatus.OK).body(recognitionService.retrievePlane());
    }

    @Override
    public ResponseEntity<SortedSet<Line>> retrieveLines(Integer collinearPoints) {
        return ResponseEntity.status(HttpStatus.OK).body(recognitionService.retrieveLines(collinearPoints));
    }

    @Override
    public ResponseEntity<Void> deleteSpace() {
        recognitionService.deletePlane();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}


