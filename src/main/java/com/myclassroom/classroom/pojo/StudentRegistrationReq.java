package com.myclassroom.classroom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class StudentRegistrationReq {
    private String student;
    private String password;
    private String firstName;
    private String lastName;
    private Long mobileNumber;
    private String email;

}
