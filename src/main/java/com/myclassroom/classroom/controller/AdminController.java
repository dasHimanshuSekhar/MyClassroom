package com.myclassroom.classroom.controller;


import com.myclassroom.classroom.pojo.AdminRegistrationReq;
import com.myclassroom.classroom.pojo.AdminRegistrationRes;
import com.myclassroom.classroom.services.AdminService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {
    final AdminService adminService;
    // Constructor Injections
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("admin_registration")
    public ResponseEntity<AdminRegistrationRes> registerAdmin(@Valid @RequestBody AdminRegistrationReq adminRegistrationReq){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.registerAdmin(adminRegistrationReq));
    }
}
