package com.springboot.security.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
	
	// User table model
	//	Username
	//	Password (encoded)
	//	Role/s (ROLE_NAME)
	// 	Authorities ?
	//	more...
	
	// ROLES and PERMISSIONS secure an API, create in Enums
	// 	- A user can have multiple roles
	
	// ROLE: ADMIN,
	// PERMISSIONS:
	//	- STUDENT : READ
	//	- STUDENT : WRITE
	//	- COURSES : READ
	//	- COURSES : WRITE
	
	// ROLE: USER,
	// PERMISSIONS:
	//	- STUDENT : READ
	//	- STUDENT : WRITE
	//	- COURSES : READ

//	Followed: https://www.youtube.com/watch?v=her_7pa0vrg
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
