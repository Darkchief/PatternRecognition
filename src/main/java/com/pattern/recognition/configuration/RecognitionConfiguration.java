package com.pattern.recognition.configuration;

import com.pattern.recognition.controller.RecognitionProvider;
import com.pattern.recognition.controller.impl.RecognitionController;
import com.pattern.recognition.service.RecognitionService;
import com.pattern.recognition.service.impl.RecognitionServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class RecognitionConfiguration {

    @Bean
    public RecognitionProvider recognitionController(RecognitionService recognitionService) {
        return new RecognitionController()
                .setRecognitionService(recognitionService);
    }

    @Bean
    public RecognitionService recognitionService() {
        return new RecognitionServiceImpl()
                .setSpace(new ArrayList<>());
    }
}