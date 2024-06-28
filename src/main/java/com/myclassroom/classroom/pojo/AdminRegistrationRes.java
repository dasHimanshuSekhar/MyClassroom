package com.myclassroom.classroom.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminRegistrationRes {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String adminUserId;
    private Integer status;
    private String statusDesc;
}
