package com.myclassroom.classroom.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentRegistrationRes {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String studentUserId;
    private String statusCode;
    private String statusDesc;
}
