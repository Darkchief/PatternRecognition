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

import java.util.List;
import java.util.Set;

@Slf4j
@Setter
@Controller
@RequestMapping("")
@Accessors(chain = true)
public class RecognitionController implements RecognitionProvider {

    private RecognitionService recognitionService;

    @Override
    public ResponseEntity<Void> addPointInSpace(PointRequest request) {
        recognitionService.addPointInSpace(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<List<Point>> retrieveSpace() {
        return ResponseEntity.status(HttpStatus.OK).body(recognitionService.retrieveSpace());
    }

    @Override
    public ResponseEntity<Set<Line>> retrieveLines(Integer collinearPoints) {
        log.info("Value of numberOfPoint: [{}]", collinearPoints);
        return ResponseEntity.status(HttpStatus.OK).body(recognitionService.retrieveLines(collinearPoints));
    }

    @Override
    public ResponseEntity<Void> deleteSpace() {
        recognitionService.deleteSpace();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}


