package com.myclassroom.classroom.services;

import com.myclassroom.classroom.pojo.AdminRegistrationReq;
import com.myclassroom.classroom.pojo.AdminRegistrationRes;

public interface AdminService {
    AdminRegistrationRes registerAdmin(AdminRegistrationReq adminRegistrationReq);
}
