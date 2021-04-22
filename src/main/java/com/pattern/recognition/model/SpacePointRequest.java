package com.pattern.recognition.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SpacePointRequest {

    private int x;
    private int y;
}
