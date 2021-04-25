package com.pattern.recognition.controller.impl;

import com.pattern.recognition.controller.RecognitionProvider;
import com.pattern.recognition.model.SpaceLine;
import com.pattern.recognition.model.SpacePoint;
import com.pattern.recognition.model.SpacePointRequest;
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
    public ResponseEntity<Void> addPointInSpace(SpacePointRequest request) {
        recognitionService.addPointInSpace(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<List<SpacePoint>> retrieveSpace() {
        return ResponseEntity.status(HttpStatus.OK).body(recognitionService.retrieveSpace());
    }

    @Override
    public ResponseEntity<Set<SpaceLine>> retrieveLines(Integer numberOfPoints) {
        log.info("Value of numberOfPoint: [{}]", numberOfPoints);
        return ResponseEntity.status(HttpStatus.OK).body(recognitionService.retrieveLines(numberOfPoints));
    }

    @Override
    public ResponseEntity<Void> deleteSpace() {
        recognitionService.deleteSpace();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}


