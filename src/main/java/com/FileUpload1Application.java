package com;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.controller.UserController;

@SpringBootApplication
public class FileUpload1Application {

	public static void main(String[] args) {
		new File(UserController.uploadDir).mkdir();
		
		SpringApplication.run(FileUpload1Application.class, args);
	}

}
