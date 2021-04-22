package com.pattern.recognition.service.impl;

import com.pattern.recognition.model.RecognitionRequest;
import com.pattern.recognition.service.RecognitionService;

import java.awt.*;

public class RecognitionServiceImpl implements RecognitionService {

    @Override
    public void addPoint(RecognitionRequest request) {
        Point point = new Point(request.getX(), request.getY());

    }

}
