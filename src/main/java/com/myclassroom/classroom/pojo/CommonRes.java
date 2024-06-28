package com.myclassroom.classroom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonRes {
    private Integer statusCode;
    private String statusDesc;
}
