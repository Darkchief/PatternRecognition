package com.pattern.recognition.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PointRequest {

    private int x;
    private int y;
}
