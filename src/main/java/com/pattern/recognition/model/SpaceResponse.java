package com.pattern.recognition.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.SortedSet;

@Data
@Accessors(chain = true)
public class SpaceResponse {

    private String message;
    private SortedSet<SpacePoint> space;
}
