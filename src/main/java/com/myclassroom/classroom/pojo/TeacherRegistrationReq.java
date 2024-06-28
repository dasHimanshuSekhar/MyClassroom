package com.myclassroom.classroom.pojo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class TeacherRegistrationReq {
    @NotEmpty(message = "Teacher Id is Mandatory !")
    private String teacherId;
    @NotEmpty(message = "Password is Mandatory !")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}", message = "Password must be 8 digit of Alphabets, Numbers, and Special Character")
    private String password;
    private String firstName;
    private String lastName;
    private String emailId;
    private String mobileNumber;
}
