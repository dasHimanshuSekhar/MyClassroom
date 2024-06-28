package com.myclassroom.classroom.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeacherRegistrationRes {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String teacherUserId;
    private Integer status;
    private String statusDesc;
}
