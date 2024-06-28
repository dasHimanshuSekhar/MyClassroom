package com.myclassroom.classroom.exception;

import com.myclassroom.classroom.pojo.CommonRes;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonRes> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String error = ex.getBindingResult().getAllErrors().stream().findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("Validation error occurred, but no specific message available.");

        return ResponseEntity.status(HttpStatus.OK).body(new CommonRes(-1, error));
    }
}
