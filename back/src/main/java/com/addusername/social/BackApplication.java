package com.addusername.social;

import org.opencv.core.Core;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import nu.pattern.OpenCV;

@SpringBootApplication
public class BackApplication {

	public static void main(String[] args) {
		//OpenCV is a class that holds methods related to loading native packages required by the OpenCV library for various platforms and architectures.
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		SpringApplication.run(BackApplication.class, args);
	}

}
