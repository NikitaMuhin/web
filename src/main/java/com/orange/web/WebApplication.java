package com.orange.web;

import com.orange.web.kafka.SampleProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class WebApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(WebApplication.class, args);
	}

}
