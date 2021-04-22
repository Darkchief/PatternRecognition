package com.pattern.recognition.controller;

import com.pattern.recognition.model.RecognitionRequest;
import com.pattern.recognition.model.RecognitionResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

public interface RecognitionProvider {

    @PutMapping(value = "/point",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<RecognitionResponse> addPoint(@RequestBody RecognitionRequest request);

}
