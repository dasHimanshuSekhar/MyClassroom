package com.myclassroom.classroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;

@SpringBootApplication
public class MyClassroomApplication {

	MyClassroomApplication(){
		System.out.println("Inside My Class Room Application !!");

	}

	public static void main(String[] args) {
		SpringApplication.run(MyClassroomApplication.class, args);
	}
}
