package com.youcode.aftasclub.exception;

import java.time.LocalDateTime;
import lombok.*;



@Getter
@Setter
@Builder
@AllArgsConstructor
public class ApiError {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;


}
