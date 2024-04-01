package com.rodrigo.feedbacksystem.exceptions.ConfigExceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StandardError implements Serializable {

    private static final long serialVersionUID =1L;

    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
