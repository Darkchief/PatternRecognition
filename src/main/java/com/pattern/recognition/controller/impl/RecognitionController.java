package com.pattern.recognition.controller.impl;

import com.pattern.recognition.controller.RecognitionProvider;
import com.pattern.recognition.model.RecognitionRequest;
import com.pattern.recognition.model.RecognitionResponse;
import com.pattern.recognition.service.RecognitionService;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Setter
@Controller
@RequestMapping("")
@Accessors(chain = true)
public class RecognitionController implements RecognitionProvider {

    private RecognitionService recognitionService;

    @Override
    public ResponseEntity<RecognitionResponse> addPoint(RecognitionRequest request) {
        log.info("Add new point to the plane");
        ResponseEntity responseEntity;
        try {
            recognitionService.addPoint(request);
            responseEntity = ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex) {
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return responseEntity;
    }
}


