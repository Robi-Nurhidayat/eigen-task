package com.task.soaltestkerja.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Response {
    
    private String statusCode;
    private String message;
    private Object data;
}
